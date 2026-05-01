/* Concurrency in C
 * =================
 * C uses POSIX threads (pthreads) for concurrency.
 * Manual thread management and synchronization required.
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>


/* ── Shared data and mutex ─────────────────────────── */

int counter = 0;
pthread_mutex_t counter_mutex = PTHREAD_MUTEX_INITIALIZER;


/* ── Basic thread function ─────────────────────────── */

void* print_message(void* arg) {
    char* message = (char*)arg;
    printf("Thread: %s\n", message);
    return NULL;
}


/* ── Unsafe counter increment (race condition) ─────── */

void* unsafe_increment(void* arg) {
    for (int i = 0; i < 100000; i++) {
        counter++;  /* RACE CONDITION: read-modify-write */
    }
    return NULL;
}


/* ── Safe counter increment with mutex ───────────────── */

void* safe_increment(void* arg) {
    for (int i = 0; i < 100000; i++) {
        pthread_mutex_lock(&counter_mutex);
        counter++;
        pthread_mutex_unlock(&counter_mutex);
    }
    return NULL;
}


/* ── Producer-consumer with condition variable ────────── */

#define BUFFER_SIZE 5

typedef struct {
    int buffer[BUFFER_SIZE];
    int count;
    int in;
    int out;
    pthread_mutex_t mutex;
    pthread_cond_t not_empty;
    pthread_cond_t not_full;
} BoundedBuffer;


void buffer_init(BoundedBuffer* buf) {
    buf->count = 0;
    buf->in = 0;
    buf->out = 0;
    pthread_mutex_init(&buf->mutex, NULL);
    pthread_cond_init(&buf->not_empty, NULL);
    pthread_cond_init(&buf->not_full, NULL);
}


void buffer_produce(BoundedBuffer* buf, int item) {
    pthread_mutex_lock(&buf->mutex);

    /* Wait while buffer is full */
    while (buf->count == BUFFER_SIZE) {
        pthread_cond_wait(&buf->not_full, &buf->mutex);
    }

    buf->buffer[buf->in] = item;
    buf->in = (buf->in + 1) % BUFFER_SIZE;
    buf->count++;

    printf("Produced: %d (buffer size: %d)\n", item, buf->count);

    pthread_cond_signal(&buf->not_empty);
    pthread_mutex_unlock(&buf->mutex);
}


int buffer_consume(BoundedBuffer* buf) {
    pthread_mutex_lock(&buf->mutex);

    /* Wait while buffer is empty */
    while (buf->count == 0) {
        pthread_cond_wait(&buf->not_empty, &buf->mutex);
    }

    int item = buf->buffer[buf->out];
    buf->out = (buf->out + 1) % BUFFER_SIZE;
    buf->count--;

    printf("Consumed: %d (buffer size: %d)\n", item, buf->count);

    pthread_cond_signal(&buf->not_full);
    pthread_mutex_unlock(&buf->mutex);

    return item;
}


/* ── Thread function for producing ──────────────────── */

void* producer_thread(void* arg) {
    BoundedBuffer* buf = (BoundedBuffer*)arg;
    for (int i = 0; i < 10; i++) {
        buffer_produce(buf, i);
        usleep(100000);  /* 100ms */
    }
    return NULL;
}


/* ── Thread function for consuming ──────────────────── */

void* consumer_thread(void* arg) {
    BoundedBuffer* buf = (BoundedBuffer*)arg;
    for (int i = 0; i < 10; i++) {
        buffer_consume(buf);
        usleep(150000);  /* 150ms */
    }
    return NULL;
}


/* ── Read-write lock example ───────────────────────── */

pthread_rwlock_t rwlock = PTHREAD_RWLOCK_INITIALIZER;
int shared_value = 0;


void* reader_thread(void* arg) {
    int id = *(int*)arg;

    for (int i = 0; i < 5; i++) {
        pthread_rwlock_rdlock(&rwlock);
        printf("Reader %d: read value = %d\n", id, shared_value);
        pthread_rwlock_unlock(&rwlock);
        usleep(50000);
    }
    return NULL;
}


void* writer_thread(void* arg) {
    int id = *(int*)arg;

    for (int i = 0; i < 5; i++) {
        pthread_rwlock_wrlock(&rwlock);
        shared_value++;
        printf("Writer %d: wrote value = %d\n", id, shared_value);
        pthread_rwlock_unlock(&rwlock);
        usleep(100000);
    }
    return NULL;
}


/* ── Main ───────────────────────────────────────────── */

int main(void) {
    /* ── Basic thread creation ──────────────────────── */

    printf("--- Basic thread creation ---\n");

    pthread_t thread1, thread2;
    char* msg1 = "Hello from thread 1";
    char* msg2 = "Hello from thread 2";

    pthread_create(&thread1, NULL, print_message, (void*)msg1);
    pthread_create(&thread2, NULL, print_message, (void*)msg2);

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);


    /* ── Race condition demo ───────────────────────── */

    printf("\n--- Race condition (UNSAFE) ---\n");
    counter = 0;

    pthread_t r1, r2, r3, r4;
    pthread_create(&r1, NULL, unsafe_increment, NULL);
    pthread_create(&r2, NULL, unsafe_increment, NULL);
    pthread_create(&r3, NULL, unsafe_increment, NULL);
    pthread_create(&r4, NULL, unsafe_increment, NULL);

    pthread_join(r1, NULL);
    pthread_join(r2, NULL);
    pthread_join(r3, NULL);
    pthread_join(r4, NULL);

    printf("Expected: 400000, Got: %d (WRONG!)\n", counter);


    /* ── Mutex protection ───────────────────────────── */

    printf("\n--- Mutex protection (SAFE) ---\n");
    counter = 0;

    pthread_create(&r1, NULL, safe_increment, NULL);
    pthread_create(&r2, NULL, safe_increment, NULL);
    pthread_create(&r3, NULL, safe_increment, NULL);
    pthread_create(&r4, NULL, safe_increment, NULL);

    pthread_join(r1, NULL);
    pthread_join(r2, NULL);
    pthread_join(r3, NULL);
    pthread_join(r4, NULL);

    printf("Expected: 400000, Got: %d (CORRECT!)\n", counter);


    /* ── Producer-consumer ──────────────────────────── */

    printf("\n--- Producer-consumer pattern ---\n");

    BoundedBuffer buffer;
    buffer_init(&buffer);

    pthread_t prod, cons;
    pthread_create(&prod, NULL, producer_thread, &buffer);
    pthread_create(&cons, NULL, consumer_thread, &buffer);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    pthread_mutex_destroy(&buffer.mutex);
    pthread_cond_destroy(&buffer.not_empty);
    pthread_cond_destroy(&buffer.not_full);


    /* ── Read-write lock ───────────────────────────── */

    printf("\n--- Read-write lock ---\n");
    shared_value = 0;

    int id1 = 1, id2 = 2;
    pthread_t reader1, reader2, writer;

    pthread_create(&reader1, NULL, reader_thread, &id1);
    pthread_create(&reader2, NULL, reader_thread, &id2);
    pthread_create(&writer, NULL, writer_thread, &id1);

    pthread_join(reader1, NULL);
    pthread_join(reader2, NULL);
    pthread_join(writer, NULL);

    pthread_rwlock_destroy(&rwlock);
    pthread_mutex_destroy(&counter_mutex);


    /* ── Summary ────────────────────────────────────── */

    printf("\n--- Summary ---\n");
    printf("C concurrency (pthreads):\n");
    printf("  - pthread_create: spawn threads\n");
    printf("  - pthread_join: wait for completion\n");
    printf("  - pthread_mutex: mutual exclusion\n");
    printf("  - pthread_cond: condition variables\n");
    printf("  - pthread_rwlock: read-write locking\n");
    printf("  - Manual memory management (no GC)\n");
    printf("  - Race conditions and deadlocks easy to create\n");

    return 0;
}

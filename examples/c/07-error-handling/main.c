/* Error Handling in C
* ===================
* return codes, errno, perror, setjmp/longjmp */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#define SUCCESS 0
#define ERROR (-1)

/* ── Return code pattern ─────────────────────────── */

int divide(int numerator, int denominator) {
    if (denominator == 0) {
        return ERROR;
    }
    return numerator / denominator;
}

int main(void) {
    int result = divide(10, 2);

    /* Check return code */
    if (result == ERROR) {
        perror("Division failed");
        fprintf(stderr, "Error: %s\n", strerror(errno));
        return 1;
    }

    printf("Result: %d\n", result);


    /* ── errno demonstration ───────────────────────── */

    FILE* file = fopen("nonexistent.txt", "r");
    if (file == NULL) {
        printf("fopen failed: %s\n", strerror(errno));
    } else {
        fclose(file);
    }


    /* ── perror vs fprintf ───────────────────────── */

    printf("\n--- perror demonstration ---");
    file = fopen("nonexistent.txt", "r");
    if (file == NULL) {
        perror("Opening file");
    }


    /* ── Custom error handling pattern ────────────────── */

    int open_file(const char* path, FILE** out) {
        *out = fopen(path, "r");
        if (*out == NULL) {
            fprintf(stderr, "Failed to open %s: %s\n", path, strerror(errno));
            return ERROR;
        }
        return SUCCESS;
    }


    if (open_file("example.txt", &file) == SUCCESS) {
        fclose(file);
    }


    /* ── NULL pointer returns ─────────────────────── */

    char* allocate_string(const char* text) {
        char* ptr = strdup(text);
        if (ptr == NULL) {
            fprintf(stderr, "Memory allocation failed\n");
            return NULL;
        }
        return ptr;
    }


    char* result = allocate_string("test");
    if (result != NULL) {
        printf("Allocated: %s\n", result);
        free(result);
    }


    /* ── goto for error handling (not recommended) ── */

    printf("\n--- Manual error handling ---");

    result = divide(10, 0);
    if (result == ERROR) {
        goto error_handler;
    }

    printf("Division successful\n");
    goto cleanup;

error_handler:
    fprintf(stderr, "Division by zero!\n");

cleanup:
    printf("--- Clean up ---");
    return 0;
}

/* ── Summary ─────────────────────────────────────

int summary(void) {
    printf("\n--- Summary ---");
    printf("C error handling:");
    printf("  - Return codes: 0 for success, non-zero for failure");
    printf("  - errno: global error variable set by system calls");
    printf("  - perror(): prints error messages to stderr");
    printf("  - NULL returns: NULL pointer indicates failure");
    printf("  - setjmp/longjmp: non-local goto for errors");
    printf("  - No exceptions: must use external libraries for robustness");
    printf("  - goto discouraged: Dijkstra 1968 recommended structured code");

    return 0;
}

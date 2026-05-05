/*
 * Data Structures in C
 * ========================
 * Demonstrates arrays, structs, linked lists, and common operations.
 * C provides manual memory management and explicit control over data layout.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// ── Arrays (fixed-size sequences) ─────────────────

void demonstrate_arrays(void) {
    printf("--- Arrays (fixed size) ---\n");

    // Declaration
    int numbers[5] = {10, 20, 30, 40, 50};

    // Access
    printf("numbers[0] = %d\n", numbers[0]);   // 10
    printf("numbers[4] = %d\n", numbers[4]);   // 50

    // Modification
    numbers[2] = 35;
    printf("After modification numbers[2] = %d\n", numbers[2]);

    // Array as string (char array)
    char name[] = "Software Engineering Atlas";
    printf("name = %s\n", name);
    printf("name[0] = %c, name[4] = %c\n", name[0], name[4]);

    // Length calculation
    int count = sizeof(numbers) / sizeof(numbers[0]);
    printf("array length = %d\n", count);
}


// ── Structs (custom data types) ─────────────────

struct Point {
    int x;
    int y;
};

struct Person {
    char name[50];
    int age;
};

void demonstrate_structs(void) {
    printf("\n--- Structs ---\n");

    // Declaration and initialization
    struct Point p1 = {10, 20};
    printf("Point: x=%d, y=%d\n", p1.x, p1.y);

    // Array of structs
    struct Point points[] = {{10, 20}, {30, 40}, {50, 60}};
    printf("points[1] = (%d, %d)\n", points[1].x, points[1].y);

    // Pointer to struct
    struct Point *p_ptr = &p1;
    printf("Via pointer: x=%d, y=%d\n", p_ptr->x, p_ptr->y);

    // Struct with strings
    struct Person ada;
    strcpy(ada.name, "Ada");
    ada.age = 36;
    printf("Person: name=%s, age=%d\n", ada.name, ada.age);

    // Sizeof struct
    printf("sizeof(Person) = %zu bytes\n", sizeof(struct Person));
}


// ── Dynamic Arrays (malloc) ─────────────────────────

void demonstrate_dynamic_arrays(void) {
    printf("\n--- Dynamic Arrays ---\n");

    // Allocate array
    int size = 5;
    int *dynamic = (int *)malloc(size * sizeof(int));

    if (dynamic == NULL) {
        printf("malloc failed!\n");
        return;
    }

    // Initialize
    for (int i = 0; i < size; i++) {
        dynamic[i] = (i + 1) * 10;
    }

    printf("dynamic array: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", dynamic[i]);
    }
    printf("\n");

    // Resize (realloc)
    size = 10;
    int *resized = (int *)realloc(dynamic, size * sizeof(int));

    if (resized == NULL) {
        printf("realloc failed!\n");
        free(dynamic);
        return;
    }

    printf("resized: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", resized[i]);
    }
    printf("\n");

    free(resized);
}


// ── Linked List (singly) ───────────────────────────

struct Node {
    int data;
    struct Node *next;
};

struct LinkedList {
    struct Node *head;
};

struct LinkedList *create_list(void) {
    struct LinkedList *list = (struct LinkedList *)malloc(sizeof(struct LinkedList));
    if (list) list->head = NULL;
    return list;
}

void list_append(struct LinkedList *list, int data) {
    struct Node *new_node = (struct Node *)malloc(sizeof(struct Node));
    new_node->data = data;
    new_node->next = NULL;

    if (list->head == NULL) {
        list->head = new_node;
        return;
    }

    struct Node *current = list->head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
}

void list_display(struct LinkedList *list) {
    struct Node *current = list->head;
    printf("list: ");
    while (current != NULL) {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");
}

void free_list(struct LinkedList *list) {
    struct Node *current = list->head;
    while (current != NULL) {
        struct Node *temp = current;
        current = current->next;
        free(temp);
    }
    free(list);
}

void demonstrate_linked_list(void) {
    printf("\n--- Linked List ---\n");

    struct LinkedList *list = create_list();

    list_append(list, 10);
    list_append(list, 20);
    list_append(list, 30);

    list_display(list);
    free_list(list);
}


// ── Stack (LIFO) using array ─────────────────────

#define STACK_SIZE 100

struct Stack {
    int data[STACK_SIZE];
    int top;
};

void stack_push(struct Stack *stack, int value) {
    if (stack->top >= STACK_SIZE - 1) {
        printf("Stack overflow!\n");
        return;
    }
    stack->data[++(stack->top)] = value;
}

int stack_pop(struct Stack *stack) {
    if (stack->top < 0) {
        printf("Stack underflow!\n");
        return -1;
    }
    return stack->data[(stack->top)--];
}

void demonstrate_stack(void) {
    printf("\n--- Stack (LIFO) ---\n");

    struct Stack stack = { .top = -1 };

    stack_push(&stack, 1);
    stack_push(&stack, 2);
    stack_push(&stack, 3);

    printf("popped: %d\n", stack_pop(&stack));
    printf("popped: %d\n", stack_pop(&stack));
    printf("top: %d\n", stack.data[stack.top]);
}


// ── Queue (FIFO) using array ─────────────────────

#define QUEUE_SIZE 100

struct Queue {
    int data[QUEUE_SIZE];
    int front;
    int rear;
};

void queue_enqueue(struct Queue *queue, int value) {
    // Check if queue is full
    if ((queue->rear + 1) % QUEUE_SIZE == queue->front) {
        printf("Queue full!\n");
        return;
    }
    queue->data[queue->rear] = value;
    queue->rear = (queue->rear + 1) % QUEUE_SIZE;
}

int queue_dequeue(struct Queue *queue) {
    if (queue->front == queue->rear) {
        printf("Queue empty!\n");
        return -1;
    }
    int value = queue->data[queue->front];
    queue->front = (queue->front + 1) % QUEUE_SIZE;
    return value;
}

void demonstrate_queue(void) {
    printf("\n--- Queue (FIFO) ---\n");

    struct Queue queue = { .front = 0, .rear = 0 };

    queue_enqueue(&queue, 10);
    queue_enqueue(&queue, 20);
    queue_enqueue(&queue, 30);

    printf("dequed: %d\n", queue_dequeue(&queue));
    printf("dequed: %d\n", queue_dequeue(&queue));
}


// ── Hash Map (simplified string to int) ──────────

#define TABLE_SIZE 100

struct Entry {
    char key[50];
    int value;
    struct Entry *next;
};

struct HashTable {
    struct Entry *table[TABLE_SIZE];
};

unsigned int hash(const char *key) {
    unsigned int hash = 0;
    for (int i = 0; key[i] != '\0'; i++) {
        hash = (hash * 31 + key[i]) % TABLE_SIZE;
    }
    return hash;
}

void hash_put(struct HashTable *ht, const char *key, int value) {
    unsigned int index = hash(key);
    struct Entry *new_entry = (struct Entry *)malloc(sizeof(struct Entry));
    strcpy(new_entry->key, key);
    new_entry->value = value;

    // Insert at head of chain
    new_entry->next = ht->table[index];
    ht->table[index] = new_entry;
}

int hash_get(struct HashTable *ht, const char *key) {
    unsigned int index = hash(key);
    struct Entry *entry = ht->table[index];

    while (entry != NULL) {
        if (strcmp(entry->key, key) == 0) {
            return entry->value;
        }
        entry = entry->next;
    }

    return -1; // Not found
}

void demonstrate_hash_table(void) {
    printf("\n--- Hash Map ---\n");

    struct HashTable ht = { .table = {0} };

    hash_put(&ht, "name", 42);
    hash_put(&ht, "age", 36);

    printf("name -> %d\n", hash_get(&ht, "name"));
    printf("age -> %d\n", hash_get(&ht, "age"));
    printf("unknown -> %d\n", hash_get(&ht, "unknown"));

    // Cleanup (simplified - not fully freeing all entries)
}


// ── Binary Search Tree ─────────────────────────────

struct TreeNode {
    int key;
    struct TreeNode *left;
    struct TreeNode *right;
};

void tree_insert(struct TreeNode **root, int key) {
    if (*root == NULL) {
        *root = (struct TreeNode *)malloc(sizeof(struct TreeNode));
        (*root)->key = key;
        (*root)->left = NULL;
        (*root)->right = NULL;
        return;
    }

    if (key < (*root)->key) {
        tree_insert(&(*root)->left, key);
    } else {
        tree_insert(&(*root)->right, key);
    }
}

void tree_inorder(struct TreeNode *root) {
    if (root == NULL) return;

    tree_inorder(root->left);
    printf("%d ", root->key);
    tree_inorder(root->right);
}

void free_tree(struct TreeNode *root) {
    if (root == NULL) return;

    free_tree(root->left);
    free_tree(root->right);
    free(root);
}

void demonstrate_bst(void) {
    printf("\n--- Binary Search Tree ---\n");

    struct TreeNode *root = NULL;

    int keys[] = {50, 30, 70, 20, 40, 60, 80};
    for (int i = 0; i < 7; i++) {
        tree_insert(&root, keys[i]);
    }

    printf("inorder traversal: ");
    tree_inorder(root);
    printf("\n");

    free_tree(root);
}


// ── Summary ───────────────────────────────────────────

int main(void) {
    printf("Data Structures in C\n");
    printf("=======================\n");

    demonstrate_arrays();
    demonstrate_structs();
    demonstrate_dynamic_arrays();
    demonstrate_linked_list();
    demonstrate_stack();
    demonstrate_queue();
    demonstrate_hash_table();
    demonstrate_bst();

    printf("\n--- Summary ---\n");
    printf("C data structures:\n");
    printf("  - Arrays: fixed-size, contiguous memory\n");
    printf("  - Structs: custom data types, value semantics\n");
    printf("  - Pointers: manual memory management, indirect access\n");
    printf("  - malloc/free: dynamic memory allocation\n");
    printf("  - Linked lists: self-referential structures\n");
    printf("  - Manual memory management: no garbage collection\n");

    return 0;
}

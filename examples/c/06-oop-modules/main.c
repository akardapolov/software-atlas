/* OOP/Modules in C
* ===================
* C has no classes, but can simulate OOP with structs and function pointers

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ── Struct with methods (function pointers) ─────────── */

typedef struct {
    double x;
    double y;
} Point;

/* Constructor */
Point* point_new(double x, double y) {
    Point* p = (Point*)malloc(sizeof(Point));
    p->x = x;
    p->y = y;
    return p;
}

/* Method */
double point_distance(Point* this, Point* other) {
    double dx = this->x - other->x;
    double dy = this->y - other->y;
    return sqrt(dx*dx + dy*dy);
}

/* Destructor */
void point_destroy(Point* p) {
    free(p);
}

/* ── Shape "class" with polymorphism ───────────────── */

typedef enum {
    SHAPE_CIRCLE,
    SHAPE_RECTANGLE,
} ShapeType;

typedef struct {
    ShapeType type;
    void (*destroy)(void*);
    double (*area)(void*);
} Shape;

/* Circle "subclass" */
typedef struct {
    Shape base;
    double radius;
} Circle;

double circle_area(void* shape) {
    Circle* c = (Circle*)shape;
    return 3.14159 * c->radius * c->radius;
}

void circle_destroy(void* shape) {
    free(shape);
}

Shape* circle_new(double radius) {
    Circle* c = (Circle*)malloc(sizeof(Circle));
    c->radius = radius;
    c->base.type = SHAPE_CIRCLE;
    c->base.destroy = circle_destroy;
    c->base.area = circle_area;
    return (Shape*)c;
}

/* Rectangle "subclass" */
typedef struct {
    Shape base;
    double width;
    double height;
} Rectangle;

double rectangle_area(void* shape) {
    Rectangle* r = (Rectangle*)shape;
    return r->width * r->height;
}

void rectangle_destroy(void* shape) {
    free(shape);
}

Shape* rectangle_new(double width, double height) {
    Rectangle* r = (Rectangle*)malloc(sizeof(Rectangle));
    r->width = width;
    r->height = height;
    r->base.type = SHAPE_RECTANGLE;
    r->base.destroy = rectangle_destroy;
    r->base.area = rectangle_area;
    return (Shape*)r;
}

/* ── "Inheritance" via composition ───────────────────── */

typedef struct {
    char* name;
    int age;
} Animal;

typedef struct {
    Animal base;
    char* breed;
} Dog;

Dog* dog_new(char* name, int age, char* breed) {
    Dog* d = (Dog*)malloc(sizeof(Dog));
    d->base.name = name;
    d->base.age = age;
    d->breed = breed;
    return d;
}

void dog_bark(Dog* dog) {
    printf("%s the %s barks!\n", dog->base.name, dog->breed);
}

void animal_destroy(Animal* animal) {
    free(animal->name);
    free(animal);
}

/* ── "Module" pattern (header + implementation) ───── */

/* In stack.h:
typedef struct {
    int* items;
    int size;
    int capacity;
} Stack;

Stack* stack_new(void);
void stack_push(Stack* s, int item);
int stack_pop(Stack* s);
void stack_destroy(Stack* s);
*/

typedef struct {
    int* items;
    int size;
    int capacity;
} Stack;

Stack* stack_new(void) {
    Stack* s = (Stack*)malloc(sizeof(Stack));
    s->items = NULL;
    s->size = 0;
    s->capacity = 0;
    return s;
}

void stack_push(Stack* s, int item) {
    s->items = realloc(s->items, (s->size + 1) * sizeof(int));
    s->items[s->size++] = item;
}

int stack_pop(Stack* s) {
    if (s->size == 0) return -1;
    return s->items[--s->size];
}

void stack_destroy(Stack* s) {
    free(s->items);
    free(s);
}

/* ── Main ───────────────────────────────────────────── */

int main(void) {

    /* ── Struct with methods ──────────────────────── */

    printf("--- Struct with methods ---\n");

    Point* p1 = point_new(0, 0);
    Point* p2 = point_new(3, 4);

    printf("Distance: %.2f\n", point_distance(p1, p2));

    point_destroy(p1);
    point_destroy(p2);


    /* ── Polymorphism with function pointers ───────────── */

    printf("\n--- Polymorphism ---\n");

    Shape* shapes[] = {
        circle_new(5.0),
        rectangle_new(3.0, 4.0)
    };

    for (int i = 0; i < 2; i++) {
        printf("Shape %d area: %.2f\n", i, shapes[i]->area(shapes[i]));
    }

    for (int i = 0; i < 2; i++) {
        shapes[i]->destroy(shapes[i]);
    }


    /* ── Composition (simulated inheritance) ────────── */

    printf("\n--- Composition ---\n");

    Dog* dog = dog_new("Buddy", 5, "Golden Retriever");
    dog_bark(dog);
    animal_destroy((Animal*)dog);


    /* ── Module pattern ─────────────────────────────── */

    printf("\n--- Module pattern (Stack) ---\n");

    Stack* stack = stack_new();

    stack_push(stack, 10);
    stack_push(stack, 20);
    stack_push(stack, 30);

    printf("Popped: %d\n", stack_pop(stack));
    printf("Popped: %d\n", stack_pop(stack));
    printf("Popped: %d\n", stack_pop(stack));

    stack_destroy(stack);


    /* ── Summary ───────────────────────────────────── */

    printf("\n--- Summary ---\n");
    printf("C OOP simulation:");
    printf("  - Structs: aggregate data (like class fields)");
    printf("  - Function pointers: methods as struct members");
    printf("  - Header files: encapsulation and module boundaries");
    printf("  - Constructors: struct_create() pattern");
    printf("  - Destructors: struct_destroy() pattern");
    printf("  - Polymorphism: function pointers or unions");
    printf("  - Composition: struct embedding for inheritance");
    printf("  - No language support: patterns, not features");
    printf("  - C++ (1983) added classes to C");

    return 0;
}

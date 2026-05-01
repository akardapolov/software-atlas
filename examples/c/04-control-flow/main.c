#include <stdio.h>

const char *classify(int n) {
    if (n < 0) return "negative";
    if (n == 0) return "zero";
    return "positive";
}

int main(void) {
    printf("--- if / else ---\n");
    int vals[] = {-3, 0, 7};
    for (int i = 0; i < 3; i++) {
        printf("%d → %s\n", vals[i], classify(vals[i]));
    }

    printf("\n--- switch ---\n");
    char grade = 'B';
    switch (grade) {
        case 'A':
            printf("Excellent\n");
            break;
        case 'B':
            printf("Good\n");
            break;
        case 'C':
            printf("Pass\n");
            break;
        case 'D':
            printf("Fail\n");
            break;
        default:
            printf("Fail\n");
    }

    printf("\n--- for (classic) ---\n");
    int sum = 0;
    for (int i = 1; i <= 5; i++) {
        sum += i;
    }
    printf("sum 1..5 = %d\n", sum);

    printf("\n--- while ---\n");
    int x = 1;
    while (x < 20) {
        x *= 2;
    }
    printf("first power of two >= 20: %d\n", x);

    printf("\n--- do-while ---\n");
    int j = 0;
    do {
        printf("j = %d\n", j);
        j++;
    } while (j < 3);

    printf("\n--- break / continue ---\n");
    for (int n = 1; n < 10; n++) {
        if (n % 2 == 1) continue;
        if (n > 6) break;
        printf("even <= 6: %d\n", n);
    }

    printf("\n--- goto (discouraged; shown for completeness) ---\n");
    int a = 2, b = 0;
    if (b == 0) goto cleanup;
    printf("a/b = %d\n", a / b);
cleanup:
    printf("cleanup reached (avoid goto in most cases)\n");

    printf("\n--- summary ---\n");
    printf("C control flow: if, switch, for/while/do, break/continue, goto (avoid)\n");

    return 0;
}

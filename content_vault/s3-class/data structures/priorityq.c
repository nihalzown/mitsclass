#include <stdio.h>

int s, f, r;
struct pq {
    int item;
    int priority;
} a[20];

void enqueue(int item, int priority) {
    int loc, i;
    if (f == 0 && r == s - 1) {
        printf("Queue is full\n");
    } else if (f == -1 && r == -1) {
        f = r = 0;
        a[r].item = item;
        a[r].priority = priority;
    } else {
        if (r == s - 1) {
            for (i = f; i <= r; i++) {
                a[i - 1] = a[i];
            }
            r--;
            f--;
        }
        for (i = r; i >= f; i--) {
            if (a[i].priority < priority) {
                break;
            }
        }
        loc = i + 1;
        for (i = r; i >= loc; i--) {
            a[i + 1] = a[i];
        }
        a[loc].item = item;
        a[loc].priority = priority;
        r++;
    }
}

void dequeue() {
    if (f == -1 && r == -1) {
        printf("Queue is empty\n");
    } else if (f == r) {
        printf("Deleted item is %d\n", a[f].item);
        f = r = -1;
    } else {
        printf("Deleted item is %d\n", a[f].item);
        f++;
    }
}

void display() {
    if (f == -1 && r == -1) {
        printf("Queue is empty\n");
    } else {
        for (int i = f; i <= r; i++) {
            printf("Item: %d, Priority: %d\n", a[i].item, a[i].priority);
        }
    }
}

void main() {
    int ch, item, priority;
    printf("Enter the size of the queue\n");
    scanf("%d", &s);
    f = r = -1;
    do {
        printf("1.Enqueue\n2.Dequeue\n3.Display\n4.Exit\n");
        printf("Enter your choice\n");
        scanf("%d", &ch);
        switch (ch) {
            case 1:
                printf("Enter the item and priority\n");
                scanf("%d%d", &item, &priority);
                enqueue(item, priority);
                break;
            case 2:
                dequeue();
                break;
            case 3:
                display();
                break;
            case 4:
                break;
            default:
                printf("Invalid choice\n");
        }
    } while (ch != 4);
}
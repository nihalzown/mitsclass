#include <stdio.h>
#include <stdlib.h>
int a[15], i, item, key, ch, loc;
void display() {
    for (i = 0; i < 15; i++) {
        if (a[i] != -1) {
            printf("%d ", a[i]);
        } else {
            printf("- ");}}
    printf("\n");}


void build(int index) {
    if (index >= 15)
        return;
    printf("\nEnter item: ");
    scanf("%d", &a[index]);
    printf("\nWant to create left child for %d? If yes, enter 0: ", a[index]);
    scanf("%d", &ch);
    if (ch == 0) {
        build(2 * index + 1);}
    printf("\nWant to create right child for %d? If yes, enter 0: ", a[index]);
    scanf("%d", &ch);
    if (ch == 0) {
        build(2 * index + 2);}}


int search(int index) {
    if (index < 15) {
        if (a[index] == key) {
            loc = index;
            return 1;
        } else {
            if (search(2 * index + 1) || search(2 * index + 2)) {
                return 1;}}}
    return 0;}


void insert() {
    printf("\nEnter item to insert: ");
    scanf("%d", &item);
    printf("\nWant to insert %d as left child of %d? If yes, enter 0: ", item, key);
    scanf("%d", &ch);
    if (ch == 0) {
        if (a[2 * loc + 1] == -1) {
            a[2 * loc + 1] = item;
        } else {
            printf("\nLeft child exists");}
    } else {
        printf("\nWant to insert %d as right child of %d? If yes, enter 0: ", item, key);
        scanf("%d", &ch);
        if (ch == 0) {
            if (a[2 * loc + 2] == -1) {
                a[2 * loc + 2] = item;
            } else {
                printf("\nRight child exists");}}}}


void delete() {
    printf("\nEnter key to delete: ");
    scanf("%d", &key);
    loc = -1;
    if (search(0)) {
        if (a[2 * loc + 1] == -1 && a[2 * loc + 2] == -1) {
            a[loc] = -1;
        } else {
            printf("\nNot a leaf, deletion denied");}
    } else {
        printf("\nKey not found");}}

        
int main(void) {
    int choice;
    for (i = 0; i < 15; i++) {
        a[i] = -1;}
    while (1) {
        printf("\nMenu:\n");
        printf("1. Build Tree\n2. Display Tree\n3. Search Key\n4. Insert Item\n5. Delete Item\n6. Exit\nEnter your choice: ");
        scanf("%d", &choice);
        switch (choice) {
            case 1:
                build(0);
                break;
            case 2:
                printf("Tree is:\n");
                display();
                break;
            case 3:
                printf("\nEnter key to search: ");
                scanf("%d", &key);
                loc = -1;
                if (search(0))
                    printf("\nKey found at index %d....", loc);
                else
                    printf("\nKey not found....");
                break;
            case 4:
                printf("\nEnter key to search for insertion: ");
                scanf("%d", &key);
                loc = -1;
                if (search(0)) {
                    insert();
                } else {
                    printf("\nKey not found, cannot insert");}
                break;
            case 5:
                delete();
                break;
            case 6:
                exit(0);
            default:
                printf("Invalid choice! Please enter a valid option.\n");}}
    return 0;}
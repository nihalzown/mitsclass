#include <stdio.h>
#include <stdlib.h>

struct node {
    int data;
    struct node* left;
    struct node* right;};

struct node *root = NULL, *loc = NULL, *ploc = NULL;
int key, item;

struct node* create_node(int data) {
    struct node* new_node = (struct node*)malloc(sizeof(struct node));
    new_node->data = data;
    new_node->left = NULL;
    new_node->right = NULL;
    return new_node;}


void build(struct node* ptr) {
    int ch;
    printf("\nEnter item: ");
    scanf("%d", &ptr->data);
    printf("\nCreate left child for %d? Enter 0 for yes: ", ptr->data);
    scanf("%d", &ch);
    if (ch == 0) {
        ptr->left = create_node(0);
        build(ptr->left);}
    printf("\nCreate right child for %d? Enter 0 for yes: ", ptr->data);
    scanf("%d", &ch);
    if (ch == 0) {
        ptr->right = create_node(0);
        build(ptr->right);}}


void inorder(struct node* ptr) { 
    if (ptr == NULL)
        return;
    inorder(ptr->left);
    printf("%d ", ptr->data);
    inorder(ptr->right);}


int search(struct node* par, struct node* ptr, int key) {
    if (ptr != NULL) {
        if (ptr->data == key) {
            ploc = par;
            loc = ptr;
            return 1;
        } else {
            if (search(ptr, ptr->left, key) || search(ptr, ptr->right, key)) {
                return 1;}}}
    return 0;}



void insert(struct node* ptr) {
    printf("\nEnter item to insert: ");
    scanf("%d", &item);
    
    printf("\nEnter key to search for insertion position: ");
    scanf("%d", &key);

    loc = NULL;
    if (!search(NULL, root, key)) {
        printf("\nKey not found\n");
        return;}
    printf("\nInsert %d as left child of %d? Enter 0 for yes: ", item, loc->data);
    int ch;
    scanf("%d", &ch);
    if (ch == 0 && loc->left == NULL) {
        loc->left = create_node(item);
    } else if (ch == 0 && loc->left != NULL) {
        printf("\nLeft child already exists\n");}
    printf("\nInsert %d as right child of %d? Enter 0 for yes: ", item, loc->data);
    scanf("%d", &ch);
    if (ch == 0 && loc->right == NULL) {
        loc->right = create_node(item);
    } else if (ch == 0 && loc->right != NULL) {
        printf("\nRight child already exists\n");}}


void delete_node() {
    printf("\nEnter key to delete: ");
    scanf("%d", &key);
    loc = NULL;
    if (!search(NULL, root, key)) {
        printf("\nKey not found\n");
        return;}
    if (loc->left == NULL && loc->right == NULL) {
        if (ploc->left == loc)
            ploc->left = NULL;
        else
            ploc->right = NULL;
        free(loc);
    } else {
        printf("Node is not a leaf, can't delete\n");}}


int main() {
    int choice;
    root = create_node(0);
    while (1) {
        printf("1. Build Tree\n2. Display Tree\n3. Search Key\n4. Insert Item\n5. Delete Item\n6. Exit\nEnter your choice: ");
        scanf("%d", &choice);
        switch (choice) {
            case 1:
                build(root);
                break;
            case 2:
                printf("Inorder traversal: ");
                inorder(root);
                printf("\n");
                break;
            case 3:
                printf("\nEnter key to search: ");
                scanf("%d", &key);
                loc = NULL;
                if (!search(NULL, root, key))
                    printf("\nKey not found\n");
                else
                    printf("\nKey found\n");
                break;
            case 4:
                insert(root);
                break;
            case 5:
                delete_node();
                break;
            case 6:
                exit(0);
            default:
                printf("Invalid choice! Please enter a valid option.\n");}}
    return 0;}
#include<stdio.h>
#include<stdlib.h>

struct node {
    int data;
    struct node *link;
};

struct node *head = NULL;

void insertfront(int x){
    struct node *newnode = (struct node*)malloc(sizeof(struct node));
    newnode->data = x;
    newnode->link = head;   // newnode link is pointing to head
    head = newnode;         // head is pointing to newnode
}

void insertend(int x){
    struct node *newnode = (struct node*)malloc(sizeof(struct node));
    newnode->data = x;
    newnode->link = NULL;
    if(head == NULL){
        head = newnode;
    }
    else{
        struct node *ptr = head;
        while(ptr->link != NULL){
            ptr = ptr->link;
        }
        ptr->link = newnode;
    }
}

void insertafter(int x, int key){
    if(head == NULL){
        printf("List is empty\n");
    }
    else{
        struct node *ptr = head;
        while(ptr->link != NULL && ptr->data != key){
            ptr = ptr->link;
        }
        if(ptr->data != key){
            printf("Key not found\n");
        }
        else{
            struct node *newnode = (struct node*)malloc(sizeof(struct node));
            newnode->data = x;
            newnode->link = ptr->link;
            ptr->link = newnode;
        }
}
}

void deletefront(){
    if(head == NULL){
        printf("List is empty\n");
    }
    else{
        struct node *temp = head;
        head = head->link;
        free(temp);
    }
}

void deleteend(){
    if(head == NULL){
        printf("List is empty\n");
    }
    else if (head->link == NULL){
        struct node *temp = head;
        head = NULL;
        free(temp);
    }
    else{
        struct node *prev = head;
        struct node *cur = head->link;
        while(cur->link != NULL){
            prev = cur;
            cur = cur->link;
        }
        prev->link = NULL;
        free(cur);
    }
}

void deletekey(int key){
    if(head == NULL){
        printf("List is empty\n");
    }
    else if(head->data == key){
        struct node *temp = head;
        head = head->link;
        free(temp);
    }
    else{
        struct node *prev = head;
        struct node *cur = head->link;
        while(cur != NULL && cur->data != key){
            prev = cur;
            cur = cur->link;
        }
        if(cur == NULL){
            printf("Key not found\n");
        }
        else{
            prev->link = cur->link;
            free(cur);
        }
    }
}

void display(){
    struct node *ptr = head;
    while(ptr != NULL){
        printf("%d ", ptr->data);
        ptr = ptr->link;
    }
    printf("\n");
}

void search(int key){
    struct node *ptr = head;
    if (head == NULL){
        printf("List is empty\n");
    }
    else{
        while(ptr != NULL && ptr->data != key){
            ptr = ptr->link;
        }
        if(ptr == NULL){
            printf("Key not found\n");
        }
        else{
            printf("Key found\n");
        }
    }
}


int main(){
    int choice, x, key;
    while(1){
        printf("1. Insert at front\n");
        printf("2. Insert at end\n");
        printf("3. Insert after key\n");
        printf("4. Delete front\n");
        printf("5. Delete end\n");
        printf("6. Delete key\n");
        printf("7. Display\n");
        printf("8. Search\n");
        printf("9. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        switch(choice){
            case 1:
                printf("Enter the element to insert: ");
                scanf("%d", &x);
                insertfront(x);
                break;
            case 2:
                printf("Enter the element to insert: ");
                scanf("%d", &x);
                insertend(x);
                break;
            case 3:
                printf("Enter the element to insert: ");
                scanf("%d", &x);
                printf("Enter the key: ");
                scanf("%d", &key);
                insertafter(x, key);
                break;
            case 4:
                deletefront();
                break;
            case 5:
                deleteend();
                break;
            case 6:
                printf("Enter the key: ");
                scanf("%d", &key);
                deletekey(key);
                break;
            case 7:
                display();
                break;
            case 8:
                printf("Enter the key: ");
                scanf("%d", &key);
                search(key);
                break;
            case 9:
                exit(0);
            default:
                printf("Invalid choice\n");
        }
    }
    return 0;
}
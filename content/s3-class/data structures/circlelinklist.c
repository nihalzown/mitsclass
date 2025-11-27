#include<stdio.h>
#include<stdlib.h>

struct node {
    int data;
    struct node *link;
};

struct node *head = NULL;

void insertfront(int x){
    struct node *new = (struct node *)malloc(sizeof(struct node));
    new->data = x;
    if(head == NULL){
        head = new;
        new->link = head;
    }else{
        struct node *ptr = head;
        while(ptr->link != head){
            ptr = ptr->link;
        }
        ptr->link = new;
        new->link = head;
        head = new;
    }
}

void insertend(int x){
    struct node *new = (struct node *)malloc(sizeof(struct node));
    new->data = x;
    if(head == NULL){
        head = new;
        new->link = head;
    }else{
        struct node *ptr = head;
        while(ptr->link != head){
            ptr = ptr->link;
        }
        ptr->link = new;
        new->link = head;
    }
}

void insertafter(int key, int x){
    if(head == NULL){
        printf("List is empty\n");
    }
    else{
        struct node *ptr = head;
        while(ptr->link != head && ptr->data != key){
            ptr = ptr->link;
            if(ptr == head){
                break;
            }
        }
        if(ptr->data == key){
            struct node *new = (struct node *)malloc(sizeof(struct node));
            new->data = x;
            new->link = ptr->link;
            ptr->link = new;
        }else{
            printf("Key not found\n");
        }
    }
}

void deletefront(){
    if(head == NULL){
        printf("List is empty\n");
    }else if(head->link == head){
        struct node *temp = head;
        head = NULL;
        free(temp);
    }else{
        struct node *ptr = head;
        struct node *temp = head;
        while(ptr->link != head){
            ptr = ptr->link;
        }
        head = head->link;
        ptr->link = head;
        free(temp);
    }
}

void deleteend(){
    if(head == NULL){
        printf("List is empty\n");
    }else if(head->link == head){
        struct node *temp = head;
        head = NULL;
        free(temp);
    }else{
        struct node *prev = head;
        struct node *curr = head->link;
        while(curr->link != head){
            prev = curr;
            curr = curr->link;
        }
        prev->link = head;
        free(curr);
    }
}

void deletekey(int key){
    if(head == NULL){
        printf("List is empty\n");
    }else if(head->link == head){
        if(head->data == key){
            struct node *temp = head;
            head = NULL;
            free(temp);
        }else{
            printf("Key not found\n");
        }
    }else if(head->data == key){
        struct node *ptr = head;
        struct node *temp = head;
        while(ptr->link != head){
            ptr = ptr->link;
        }
        head = head->link;
        ptr->link = head;
        free(temp);
    }else{
        struct node *prev = head;
        struct node *curr = head->link;
        while(curr != head && curr->data != key){
            prev = curr;
            curr = curr->link;
        }
        if(curr->data == key){
            prev->link = curr->link;
            free(curr);
        }else{
            printf("Key not found\n");
        }
    }
}

void display(){
    if(head == NULL){
        printf("List is empty\n");
    }else{
        struct node *ptr = head;
        do{
            printf("%d ", ptr->data);
            ptr = ptr->link;
        }while(ptr != head);
        printf("\n");
    }
}

void main(){
    int ch, x, key;
    while(1){
        printf("1. Insert front\n");
        printf("2. Insert end\n");
        printf("3. Insert after\n");
        printf("4. Delete front\n");
        printf("5. Delete end\n");
        printf("6. Delete key\n");
        printf("7. Display\n");
        printf("8. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &ch);
        switch(ch){
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
                printf("Enter the key: ");
                scanf("%d", &key);
                printf("Enter the element to insert: ");
                scanf("%d", &x);
                insertafter(key, x);
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
                exit(0);
            default:
                printf("Invalid choice\n");
        }
    }
}

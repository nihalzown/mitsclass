#include<stdio.h>
#include<stdlib.h>

struct node {
    int data;
    struct node *Llink,*Rlink;
};

struct node *head = NULL;

void insertfront(int x){
    struct node *newnode = (struct node*)malloc(sizeof(struct node));
    newnode->data = x;
    newnode->Llink = NULL;
    newnode->Rlink = NULL;
    if(head == NULL){
        head = newnode;
    }
    else{
        newnode->Rlink = head;
        head->Llink = newnode;
        head = newnode;
    }
}

void insertend(int x){
    struct node *newnode = (struct node*)malloc(sizeof(struct node));
    newnode->data = x;
    newnode->Llink = NULL;
    newnode->Rlink = NULL;
    if(head == NULL){
        head = newnode;
    }
    else{
        struct node *ptr = head;
        while(ptr->Rlink != NULL){
            ptr = ptr->Rlink;
        }
        ptr->Rlink = newnode;
        newnode->Llink = ptr;
    }
}

void insertkey(int x,int key){
    if(head == NULL){
        printf("List is empty\n");
        return;
    }
    struct node *ptr = head;
    while(ptr->data != key && ptr->Rlink != NULL){
        ptr = ptr->Rlink;
    }
    if(ptr->data != key){
        printf("Key not found\n");
    }
    else{
        struct node *newnode = (struct node*)malloc(sizeof(struct node));
        newnode->data = x;
        newnode->Llink = ptr;
        newnode->Rlink = ptr->Rlink;
        if(ptr->Rlink != NULL){
            ptr->Rlink->Llink = newnode;
        }
        ptr->Rlink = newnode;
    }
}

void deletefront(){
    if(head == NULL){
        printf("List is empty\n");
    }
    else if(head->Rlink == NULL){
        struct node *temp = head;
        head = NULL;
        free(temp);
    }
    else{
        head = head->Rlink;
        free(head->Llink);
        head->Llink = NULL;
    }
}

void deleteend(){
    if(head == NULL){
        printf("List is empty\n");
    }
    else if(head->Rlink == NULL){
        struct node *temp = head;
        head = NULL;
        free(temp);
    }
    else{
        struct node *ptr = head;
        while(ptr->Rlink != NULL){
            ptr = ptr->Rlink;
        }
        ptr->Llink->Rlink = NULL;
        free(ptr);
    }
}

void deletekey(int key){
    if(head == NULL){
        printf("List is empty\n");
    }
    else if(head->Rlink == NULL){
        if(head->data == key){
            struct node *temp = head;
            head = NULL;
            free(temp);
        }
        else{
            printf("Key not found\n");
        }
    }
    else if(head->data == key){
        head = head->Rlink;
        free(head->Llink);
        head->Llink = NULL;
    }
    else{
        struct node *ptr = head;
        while(ptr->data != key && ptr->Rlink != NULL){
            ptr = ptr->Rlink;
        }
        if(ptr->data != key){
            printf("Key not found\n");
        }
        else{
            ptr->Llink->Rlink = ptr->Rlink;
            if(ptr->Rlink != NULL){
                ptr->Rlink->Llink = ptr->Llink;
            }
            free(ptr);
        }
    }
}

void display(){
    if(head == NULL){
        printf("List is empty\n");
    }
    else{
        struct node *ptr = head;
        while(ptr != NULL){
            printf("%d ",ptr->data);
            ptr = ptr->Rlink;
        }
        printf("\n");
    }
}

void main(){
    int ch,x,key;
    while(1){
        printf("1.Insert front\n2.Insert end\n3.Insert key\n4.Delete front\n5.Delete end\n6.Delete key\n7.Display\n8.Exit\n");
        printf("Enter your choice: ");
        scanf("%d",&ch);
        switch(ch){
            case 1:
                printf("Enter the element to insert: ");
                scanf("%d",&x);
                insertfront(x);
                break;
            case 2:
                printf("Enter the element to insert: ");
                scanf("%d",&x);
                insertend(x);
                break;
            case 3:
                printf("Enter the element to insert: ");
                scanf("%d",&x);
                printf("Enter the key: ");
                scanf("%d",&key);
                insertkey(x,key);
                break;
            case 4:
                deletefront();
                break;
            case 5:
                deleteend();
                break;
            case 6:
                printf("Enter the key: ");
                scanf("%d",&key);
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
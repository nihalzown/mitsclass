#include<stdio.h>
#include<stdlib.h>

struct node {
    int data;
    struct node *left;
    struct node *right;
};

int ch , item ,flag = 0;
struct node *root = NULL,*ptr=NULL,*parent=NULL,*new;

void insert(){
    printf("\nEnter item to insert: ");
    scanf("%d", &item);
    if(root == NULL){
        struct node *new = (struct node *)malloc(sizeof(struct node));
        new->data = item;
        new->left = NULL;
        new->right = NULL;
        root = new;
    }else{
        ptr = root;
        flag = 0;
        while(ptr != NULL && flag == 0){
            if(ptr->data == item){
                printf("\nDuplicate item");
                flag = 1;
                return;
            }else if(ptr->data > item){
                parent = ptr;
                ptr = ptr->left;
            }else{
                parent = ptr;
                ptr = ptr->right;
            }    
        }
        if(ptr == NULL){
            struct node *new = (struct node *)malloc(sizeof(struct node));
            new->data = item;
            new->left = NULL;
            new->right = NULL;
            if(parent->data > item){
                parent->left = new;
            }else{
                parent->right = new;
            }
        }   
    }
}

void inorder(struct node *ptr){
    if(ptr != NULL){
        inorder(ptr->left);
        printf("%d ",ptr->data);
        inorder(ptr->right);
    }
}

struct node *insucc(struct node *y){
    while(y->left != NULL){
        y = y->left;
    }
    return y;
}

void delete(int item){
    if(root == NULL){
        printf("\nTree is empty");
        return;
    }
    else{
        ptr = root;
        flag = 0;
        while(ptr != NULL && flag == 0){
            if(ptr->data == item){
                flag = 1;
            }else if(ptr->data > item){
                parent = ptr;
                ptr = ptr->left;
            }else{
                parent = ptr;
                ptr = ptr->right;
            }
        }
        if(flag == 0){
            printf("\nItem not found");
            return;
        }
        else{
        if(ptr->left == NULL && ptr->right == NULL){
            if(parent->left == ptr){
                parent->left = NULL;
            }else{
                parent->right = NULL;
            }
            free(ptr);
        }else if(ptr->left != NULL && ptr->right == NULL){
            if(parent->left == ptr){
                parent->left = ptr->left;
            }else{
                parent->right = ptr->left;
            }
            free(ptr);
        }else if(ptr->left == NULL && ptr->right != NULL){
            if(parent->left == ptr){
                parent->left = ptr->right;
            }else{
                parent->right = ptr->right;
            }
            free(ptr);
        }else{
            struct node *succ = insucc(ptr->right);
            printf("\nInorder successor is %d",succ->data);
            int x = succ->data;
            delete(x);
            ptr->data = x;
        }
        }
    }
}

void main(){
    int choice;
    while(1){
        printf("\n1. Insert\n2. Delete\n3. Inorder\n4. Exit\nEnter choice: ");
        scanf("%d",&choice);
        switch(choice){
            case 1: insert();
                    break;
            case 2: printf("\nEnter item to delete: ");
                    scanf("%d",&item);
                    delete(item);
                    break;
            case 3: inorder(root);
                    break;
            case 4: exit(0);
        }
    }
}

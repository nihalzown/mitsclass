#include<stdio.h>
int a[100],size,top;
void push(){
    int ele;
    if(top==size-1){
        printf("Stack is full\n");
    }
    else{
        printf("Enter the element to be pushed :");
        scanf("%d",&ele);
        top++;
        a[top]=ele;
    }
}
void pop(){
    if(top==-1){
        printf("Stack is empty\n");
    }
    else{
        printf("The element popped is %d\n",a[top]);
        top--;
    }
}
void display(){
    int i;
    if(top==-1){
        printf("Stack is empty\n");
    }
    else{
        printf("The elements of the stack are :\n");
        for(i=top;i>=0;i--){
            printf("%d\n",a[i]);
        }
    }
}
void status(){
    if(top==-1){
        printf("Stack is empty\n");
    }
    else if(top==size-1){
        printf("Stack is full\n");
    }
    else{
        printf("Stack is neither empty nor full\n");
    }
}
void main(){
    int ch;
    printf("Enter the size of the stack :");
    scanf("%d",&size);
    top=-1;
    do{
        printf("\nCHOOSE THE OPERATION\n1.PUSH\n2.POP\n3.DISPLAY\n4.STATUS\n5.EXIT");
        printf("\nEnter your choice :");
        scanf("%d",&ch);
        switch(ch){
            case 1 :
                push();
                break;
            case 2 :
                pop();
                break;
            case 3 :
                display();
                break;
            case 4 :
                status();
                break;
            case 5 :
                break;
            default :
                printf("Invalid choice\n");
        }
    }while(ch!=4);
}
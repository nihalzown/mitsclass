#include<stdio.h>
int a[20],size,front,rear;
void insert(int item){
    if(rear==size-1){
        printf("Queue is full\n");
    }
    else if(rear==-1){
        front=rear=0;
        a[rear]=item;
    }
    else{
        rear++;
        a[rear]=item;
    }
}
void delete(){
    if(front==-1){
        printf("Queue is empty\n");
    }
    else if(front==rear){
        printf("Deleted element is %d\n",a[front]);
        front=rear=-1;
    }
    else{
        printf("Deleted element is %d\n",a[front]);
        front++;
    }
}
void display(){
    int i;
    if(front==-1){
        printf("Queue is empty\n");
    }
    else{
        for(i=front;i<=rear;i++){
            printf("%d\t",a[i]);
        }
    }
}

void main(){
    int ch,item;
    printf("Enter the size of the queue\n");
    scanf("%d",&size);
    front=rear=-1;
    do{
        printf("\n1.Insert\n2.Delete\n3.Display\n4.Exit\n");
        printf("Enter your choice\n");
        scanf("%d",&ch);
        switch(ch){
            case 1: printf("Enter the element to be inserted\n");
                    scanf("%d",&item);
                    insert(item);
                    break;
            case 2: delete();
                    break;
            case 3: display();
                    break;
            case 4: break;
            default: printf("Invalid choice\n");
        }
    }while(ch!=4);
}
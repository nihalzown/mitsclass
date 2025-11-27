#include<stdio.h>
int a[20],s,f,r;
void enqueue(int item){
    if((r+1)%s==f){
        printf("Queue is full\n");
    }
    else if(f==-1&&r==-1){
        f=r=0;
        a[r]=item;
    }
    else{
        r=(r+1)%s;
        a[r]=item;
    }
}
void dequeue(){
    if(f==-1&&r==-1){
        printf("Queue is empty\n");
    }
    else if(f==r){
        printf("Deleted element is %d\n",a[f]);
        f=r=-1;
    }
    else{
        printf("Deleted element is %d\n",a[f]);
        f=(f+1)%s;
    }
}
void display(){
    int i;
    if(f==-1&&r==-1){
        printf("Queue is empty\n");
    }
    else{
        if(f<=r){
            for(i=f;i<=r;i++){
                printf("%d\t",a[i]);
            }
        }
        else{
            for(i=f;i<s;i++){
                printf("%d\t",a[i]);
            }
            for(i=0;i<=r;i++){
                printf("%d\t",a[i]);
            }
        }
        printf("\n");
    }
}
void main(){
    int ch,item;
    printf("Enter the size of the queue\n");
    scanf("%d",&s);
    f=r=-1;
    do{
        printf("\n1.Enqueue\n2.Dequeue\n3.Display\n4.Exit\n");
        printf("Enter your choice\n");
        scanf("%d",&ch);
        switch(ch){
            case 1: printf("Enter the element to be enqueued\n");
                    scanf("%d",&item);
                    enqueue(item);
                    break;
            case 2: dequeue();
                    break;
            case 3: display();
                    break;
            case 4: break;
            default: printf("Invalid choice\n");
        }
    }while(ch!=4);
}
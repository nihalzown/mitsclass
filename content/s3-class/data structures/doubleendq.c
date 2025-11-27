#include<stdio.h>
int a[20],size,f,r;
void push(int item){
    if(f==0&&r==size-1){
        printf("Queue is full\n");
    }
    else if(f==-1&&r==-1){
        f=r=0;
        a[f]=item;
    }
    else if(f>0){
        f--;
        a[f]=item;
    }
    else{
        for(int i=r;i>=f;i--){
            a[i+1]=a[i];
        }
        r++;
        a[f]=item;
    }
}
void pop(){
    if(f==-1&&r==-1){
        printf("Queue is empty\n");
    }
    else if(f==r){
        printf("Deleted element is %d\n",a[f]);
        f=r=-1;
    }
    else{
        printf("Deleted element is %d\n",a[f]);
        f++;
    }
}
void inject(int item){
    if(f==0&&r==size-1){
        printf("Queue is full\n");
    }
    else if(f==-1&&r==-1){
        f=r=0;
        a[r]=item;
    }
    else if(r<size-1){
        r++;
        a[r]=item;
    }
    else{
        for(int i=f;i<=r;i++){
            a[i-1]=a[i];
        }
        f--;
        a[r]=item;
    }
}
void eject(){
    if(f==-1&&r==-1){
        printf("Queue is empty\n");
    }
    else if(f==r){
        printf("Deleted element is %d\n",a[f]);
        f=r=-1;
    }
    else{
        printf("Deleted element is %d\n",a[r]);
        r--;
    }
}
void display(){
    int i;
    if(f==-1&&r==-1){
        printf("Queue is empty\n");
    }
    else{
        for(i=f;i<=r;i++){
            printf("%d\t",a[i]);
        }
    }
}
void main(){
    int ch,item;
    printf("Enter the size of the queue\n");
    scanf("%d",&size);
    f=r=-1;
    do{
        printf("\n1.Push\n2.Pop\n3.Inject\n4.Eject\n5.Display\n6.Exit\n");
        printf("Enter your choice\n");
        scanf("%d",&ch);
        switch(ch){
            case 1: printf("Enter the element to be pushed\n");
                    scanf("%d",&item);
                    push(item);
                    break;
            case 2: pop();
                    break;
            case 3: printf("Enter the element to be injected\n");
                    scanf("%d",&item);
                    inject(item);
                    break;
            case 4: eject();
                    break;
            case 5: display();
                    break;
            case 6: break;
            default: printf("Invalid choice\n");
        }
    }while(ch!=6);
}
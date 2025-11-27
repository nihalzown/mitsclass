#include<stdio.h>
void main(){
    int n,i,x,pos,arr[50],ch;
    printf("Enter the size of the array :");
    scanf("%d",&n);
    printf("Enter the elements of the array :");
    for(i=0;i<n;i++){
        scanf("%d",&arr[i]);}
    while(ch!=6){
        printf("\nCHOOSE THE OPERATION\n1.Insert an element at the beginning\n2.Insert an element at the end\n3.Insert an element at a given position\n4.Delete a given element\n5.Display the array\n6.EXIT");
        printf("\nEnter your choice :");
        scanf("%d",&ch);
        switch(ch){
            case 1 :
                printf("Enter the element to be inserted :");
                scanf("%d",&x);
                arr[n]=0;
                for(i=n;i>0;i--){
                    arr[i]=arr[i-1];}
                arr[0]=x;
                n++;
                printf("The array after insertion is :");
                for(i=0;i<n;i++){
                    printf("%d\t",arr[i]);}
                break;

            case 2 :
                printf("Enter the element to be inserted :");
                scanf("%d",&x);
                arr[n]=x;
                n++;
                printf("The array after insertion is :");
                for(i=0;i<n;i++){
                    printf("%d\t",arr[i]);}
                break;

            case 3 :
                printf("Enter the element to be inserted :");
                scanf("%d",&x);
                printf("Enter the position at which the element is to be inserted :");
                scanf("%d",&pos);
                arr[n]=0;
                for(i=n;i>pos;i--){
                    arr[i]=arr[i-1];}
                arr[pos]=x;
                n++;
                printf("The array after insertion is :");
                for(i=0;i<n;i++){
                    printf("%d\t",arr[i]);}
                break;

            case 4 :
                    printf("Enter the element to be deleted :");
                    scanf("%d",&x);
                    for(i=0;i<n;i++){
                        if(arr[i]==x){
                            pos=i;printf("Enter the element to be inserted :");
                scanf("%d",&x);
                printf("Enter the position at which the element is to be inserted :");
                scanf("%d",&pos);
                arr[n]=0;
                for(i=n;i>pos;i--){
                    arr[i]=arr[i-1];}
                arr[pos]=x;
                n++;
                printf("The array after insertion is :");
                for(i=0;i<n;i++){
                    printf("%d\t",arr[i]);}
                break;
                            break;}}
                    for(i=pos;i<n;i++){
                        arr[i]=arr[i+1];}
                    
                    n--;
                    printf("The array after deletion is :");
                    for(i=0;i<n;i++){
                        printf("%d\t",arr[i]);}
                    break;
                
            case 5 :
                printf("The array is :");
                for(i=0;i<n;i++){
                    printf("%d\t",arr[i]);}
                break;

            case 6 :
                printf("thank you");
                break;
    }}}

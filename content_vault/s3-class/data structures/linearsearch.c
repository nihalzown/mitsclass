#include<stdio.h>
void main(){
	int a[30],n,s,i,c=0;
	printf("Enter the limit of the array :");
	scanf("%d",&n);
	printf("enter the elements of the array :");
	for(i=0;i<n;i++){
		scanf("%d",&a[i]);
	}
	printf("Enter the name to be searched :");
	scanf("%d",&s);
	for(i=0;i<n;i++){
		if(a[i]==s){
			c++;
			break;
		}}
	if(c==0){
		printf("\nThe number %d is not found",s);}
	else{
		printf("\nThe number %d is found at %d position",s,i+1);
	}
}

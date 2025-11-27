#include<stdio.h>
void main(){
	int n,i,x,pos,arr[50];
	printf("Enter the size of the array :");
	scanf("%d",&n);
	printf("Enter the elements of the array :");
	for(i=0;i<n;i++){
		scanf("%d",&arr[i]);}
	for(i=0;i<n;i++){
		printf("%d\t",arr[i]);}
	printf("\nEnter the index in which element is added :");
	scanf("%d",&pos);
	arr[n]=0;
	for(i=n;i>pos;i--){
		arr[i]=arr[i-1];}
	printf("Enter the element to add :");
	scanf("%d",&arr[pos]);
	for(i=0;i<n+1;i++){
		printf("%d\t",arr[i]);}
	
}

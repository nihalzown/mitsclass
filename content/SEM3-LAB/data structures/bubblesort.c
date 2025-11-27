#include<stdio.h>
void main(){
	int a[30],n,s,i,j,temp;
	printf("Enter the limit of the array :");
	scanf("%d",&n);
	printf("enter the elements of the array :");
	for(i=0;i<n;i++){
		scanf("%d",&a[i]);
	}
	for(i=0;i<n;i++){
		for(j=i+1;j<n;j++){
			if(a[i]>a[j]){
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}}}
	printf("\n Sorted array :-");
	for(i=0;i<n;i++){
		printf("\t%d",a[i]);
	}
}

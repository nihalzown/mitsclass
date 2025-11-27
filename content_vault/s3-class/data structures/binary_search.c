#include<stdio.h>
void main(){
	int a[30],n,s,i,m,l,h,c=0;
	printf("Enter the limit of the array :");
	scanf("%d",&n);
	printf("enter the elements of the array :");
	for(i=0;i<n;i++){
		scanf("%d",&a[i]);
	}
	printf("Enter the elemant to be searched :");
	scanf("%d",&s);
	l=0;
	h=n-1;
	for(i=0;i<n;i++){
		m=(l+h)/2;
		if(s==a[m]){
			c++;}
		if(s<a[m]){
			h=m-1;}
		if(s>a[m]){
			l=m+1;}
	}
	if(c>0){
		printf("The element %d is found",s);
	}
	if(c==0){
		printf("The element %d is not found",s);
	}
	

}

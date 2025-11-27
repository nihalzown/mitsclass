#include<stdio.h>
void main(){
	int a[30][30],b[30][30],sum[30][30],pro[30][30],trans[30][30],r1,c1,r2,c2,i,j,k,ch=0,x;
	while(ch!=4){
	
		printf("\nENTER THE OPERATION TO PERFORM \n1.addition\n2.multiplication\n3.transpose\n4.EXIT");
		printf("\nEnter the operation to perform :");
		scanf("%d",&x);
		
		switch(x){
			case 1 :
				printf("Enter the order of the 1st matrix :");
				scanf("%d %d",&r1,&c1);
				printf("Enter the order of the 2nd matrix :");
				scanf("%d %d",&r2,&c2);
				printf("enter the elements of the 1st matrix :");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						scanf("%d",&a[i][j]);}
				}
				printf("enter the elements of 2nd matrix :");
				for(i=0;i<r2;i++){
					for(j=0;j<c2;j++){
						scanf("%d",&b[i][j]);}
				}
				printf("the first matrix :-\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						printf("\t %d",a[i][j]);}
					printf("\n");
				}
				printf("the second matrix :-\n");
				for(i=0;i<r2;i++){
					for(j=0;j<c2;j++){
						printf("\t %d",b[i][j]);}
					printf("\n");
				}
				printf("the sum of the matrix\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						sum[i][j]=a[i][j]+b[i][j];
						printf("\t %d",sum[i][j]);}
					printf("\n");
				}
				break;
				
			case 2 :
				printf("Enter the order of the 1st matrix :");
				scanf("%d %d",&r1,&c1);
				printf("Enter the order of the 2nd matrix :");
				scanf("%d %d",&r2,&c2);
				printf("enter the elements of the 1st matrix :");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						scanf("%d",&a[i][j]);}
				}
				printf("enter the elements of 2nd matrix :");
				for(i=0;i<r2;i++){
					for(j=0;j<c2;j++){
						scanf("%d",&b[i][j]);}
				}
				printf("the first matrix :-\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						printf("\t %d",b[i][j]);}
					printf("\n");
				}
				printf("the second matrix :-\n");
				for(i=0;i<r2;i++){
					for(j=0;j<c2;j++){
						printf("\t %d",b[i][j]);}
					printf("\n");
				}
				for(i=0;i<r1;i++){
					for(j=0;j<c2;j++){
						for(k=0;k<r1;k++){
							pro[i][j]=a[i][k]*b[k][j];
						}}}
				printf("product :-\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						printf("\t %d",pro[i][j]);
					}
					printf("\n");}
				break;
				
			case 3 :
				printf("Enter the order of the matrix :");
				scanf("%d %d",&r1,&c1);
				printf("enter the elements of the matrix :");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						scanf("%d",&a[i][j]);}
				}
				printf("the matrix :-\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						printf("\t %d",a[i][j]);}
					printf("\n");
				}
				printf("the transpose matrix :-\n");
				for(i=0;i<r1;i++){
					for(j=0;j<c1;j++){
						trans[i][j]=a[j][i];
						printf("\t %d",trans[i][j]);}
					printf("\n");
				}
				break;
			case 4 :
				ch=4;
				printf("THANK YOU");
				break;
		
		}
}
}


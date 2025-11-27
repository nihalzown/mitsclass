#include<stdio.h>
int s[10][10],t[10][10];
int read(int a[10][10],int m,int n){
    int i,j,k=1;
    s[0][0]=m;
    s[0][1]=n;
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            if(a[i][j]!=0){
                s[k][0]=i;
                s[k][1]=j;
                s[k][2]=a[i][j];
                k++;
            }
        }
    }
    s[0][2]=k-1;
    printf("\nThe sparse matrix is :\n");
    for(i=0;i<k;i++){
        for(j=0;j<3;j++){
            printf("%d\t",s[i][j]);
        }
        printf("\n");
    }
}
void transpose(){
    int m,n,k,i,j;
    t[0][0]=s[0][1];
    t[0][1]=s[0][0];
    t[0][2]=s[0][2];
    k=1;
    m=s[0][1];
    n=s[0][2];
    for(i=0;i<m;i++){
        for(j=1;j<=n;j++){
            if(s[j][1]==i){
                t[k][0]=s[j][1];
                t[k][1]=s[j][0];
                t[k][2]=s[j][2];
                k++;
            }
        }
    }
    printf("\nThe transpose of the sparse matrix is :\n");
    for(i=0;i<k;i++){
        for(j=0;j<3;j++){
            printf("%d\t",t[i][j]);
        }
        printf("\n");
    }
}
void main(){
    int a[10][10],m,n,i,j;
    printf("Enter the number of rows and columns of the matrix :");
    scanf("%d%d",&m,&n);
    printf("Enter the elements of the matrix :\n");
    for(i=0;i<m;i++){
        for(j=0;j<n;j++){
            scanf("%d",&a[i][j]);
        }
    }
    read(a,m,n);
    transpose();
}
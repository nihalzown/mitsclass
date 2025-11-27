#include<stdio.h>

void display(int x[10][3]){
    int i,j;
    printf("Sparse matrix representation\n");
    for(i=0;i<=x[0][2];i++){
        for(j=0;j<3;j++){
            printf("%d  \t",x[i][j]);
        }
        printf("\n");
    }
}

void read(int a[10][10], int s[10][3], int m, int n){
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
    s[0][2] = k - 1; 
}

void add(int a[10][3], int b[10][3], int s[10][3]){
    int i=1,j=1,k=1;
    if(a[0][0]!=b[0][0] || a[0][1]!=b[0][1]){
        printf("Matrix size mismatch\n");
        return;
    }
    s[0][0] = a[0][0];
    s[0][1] = a[0][1];
    while(i <= a[0][2] && j <= b[0][2]){
        if(a[i][0] < b[j][0] || (a[i][0] == b[j][0] && a[i][1] < b[j][1])){
            s[k][0] = a[i][0];
            s[k][1] = a[i][1];
            s[k][2] = a[i][2];
            i++;
        } else if(a[i][0] > b[j][0] || (a[i][0] == b[j][0] && a[i][1] > b[j][1])){
            s[k][0] = b[j][0];
            s[k][1] = b[j][1];
            s[k][2] = b[j][2];
            j++;
        } else {
            s[k][0] = a[i][0];
            s[k][1] = a[i][1];
            s[k][2] = a[i][2] + b[j][2];
            i++;
            j++;
        }
        k++;
    }
    while(i <= a[0][2]){
        s[k][0] = a[i][0];
        s[k][1] = a[i][1];
        s[k][2] = a[i][2];
        i++;
        k++;
    }
    while(j <= b[0][2]){
        s[k][0] = b[j][0];
        s[k][1] = b[j][1];
        s[k][2] = b[j][2];
        j++;
        k++;
    }
    s[0][2] = k - 1; // Number of non-zero elements
}

int main(){
    int a[10][10], b[10][10], s1[10][3], s2[10][3], sum[10][3];
    int m, n, i, j;

    printf("Enter the number of rows and columns of the matrices: ");
    scanf("%d %d", &m, &n);

    printf("Enter the elements of the first matrix:\n");
    for(i=0; i<m; i++){
        for(j=0; j<n; j++){
            scanf("%d", &a[i][j]);
        }
    }

    printf("Enter the elements of the second matrix:\n");
    for(i=0; i<m; i++){
        for(j=0; j<n; j++){
            scanf("%d", &b[i][j]);
        }
    }

    read(a, s1, m, n);
    read(b, s2, m, n);

    add(s1, s2, sum);

    printf("First matrix in sparse form:\n");
    display(s1);

    printf("Second matrix in sparse form:\n");
    display(s2);

    printf("Sum of matrices in sparse form:\n");
    display(sum);

    return 0;
}
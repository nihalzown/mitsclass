#include<stdio.h>
struct poly{
    int coeff;
    int expo;
}p1[10],p2[10],p3[10];

int read(struct poly p[]);
int add(struct poly [],struct poly [],int,int,struct poly []);
void display(struct poly [],int);

void main(){
    int t1,t2,t3;
    t1=read(p1);
    t2=read(p2);
    t3=add(p1,p2,t1,t2,p3);
    printf("Polynomial 1\n");
    display(p1,t1);
    printf("Polynomial 2\n");
    display(p2,t2);
    printf("Resulting polynomial\n");
    display(p3,t3);
}

int read(struct poly p1[]){
    int t1,i;
    printf("Enter the number of terms in the polynomial: ");
    scanf("%d",&t1);
    printf("Enter the polynomial terms with coeff and expo in descending order\n");
    for(i=0;i<t1;i++){
        scanf("%d %d",&p1[i].coeff,&p1[i].expo);
    }
    return t1;
}

int add(struct poly p1[],struct poly p2[],int t1,int t2,struct poly p3[]){
    int i=0,j=0,k=0;
    while(i<t1 && j<t2){
        if(p1[i].expo==p2[j].expo){
            p3[k].coeff=p1[i].coeff+p2[j].coeff;
            p3[k].expo=p1[i].expo;
            i++;
            j++;
            k++;
        }
        else if(p1[i].expo>p2[j].expo){
            p3[k].coeff=p1[i].coeff;
            p3[k].expo=p1[i].expo;
            i++;
            k++;
        }
        else{
            p3[k].coeff=p2[j].coeff;
            p3[k].expo=p2[j].expo;
            j++;
            k++;
        }
    }
    while(i<t1){
        p3[k].coeff=p1[i].coeff;
        p3[k].expo=p1[i].expo;
        i++;
        k++;
    }
    while(j<t2){
        p3[k].coeff=p2[j].coeff;
        p3[k].expo=p2[j].expo;
        j++;
        k++;
    }
    return k;
}

void display(struct poly p[],int t){
    int i;
    for(i=0;i<t;i++){
        printf("%dX^%d\t",p[i].coeff,p[i].expo);
        if(i<t-1){
            printf("+");
        }
    }
    printf("\n");
}


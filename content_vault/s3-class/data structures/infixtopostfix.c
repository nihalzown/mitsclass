#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
char stack[10];
int top=-1;

void push(char x)
{
    stack[++top]=x;
}

char pop()
{
    return (stack[top--]);
}

int prec(char op){
    switch (op){
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '(':
        case '#':
            return 0;
    }
}

int main(){
    char infix[20],postfix[20],symbol;
    int i,k;
    printf("Enter the infix expression\n");
    scanf("%s",infix);

    stack[++top]='#';
    for(i=0,k=0;infix[i]!='\0';i++){
        symbol=infix[i];
        if(isalpha(symbol)){
            postfix[k++]=symbol;
            continue;
        }
        if(symbol=='('){
            push(symbol);
            continue;
        }
        if(symbol==')'){
            while((symbol = pop()) != '('){
                postfix[k++]=symbol;
            }
            continue;
        }
        while(prec(symbol)<=prec(stack[top])){
            postfix[k++]=pop();
        }
        push(symbol);
    }
    while(stack[top]!='#'){
        postfix[k++]=pop();
    }
    postfix[k]='\0';
    printf("Infix expression is %s\n",infix);
    printf("Postfix expression is %s\n",postfix);
    return 0;
}
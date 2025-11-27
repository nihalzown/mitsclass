#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>

int stack[20];
int top = -1;

void push(int x) {
    stack[++top] = x;
}

int pop() {
    return stack[top--];
}

int evaluatePostfix(char* postfix) {
    int i;
    char symbol;
    int op1, op2;

    for(i = 0; postfix[i] != '\0'; i++) {
        symbol = postfix[i];
        if(isdigit(symbol)) {
            push(symbol - '0');
        } else {
            op2 = pop();
            op1 = pop();
            switch(symbol) {
                case '+': push(op1 + op2); break;
                case '-': push(op1 - op2); break;
                case '*': push(op1 * op2); break;
                case '/': push(op1 / op2); break;
            }
        }
    }
    return pop();
}

int main() {
    char postfix[20];
    printf("Enter the postfix expression\n");
    scanf("%s", postfix);

    int result = evaluatePostfix(postfix);
    printf("Postfix expression is %s\n", postfix);
    printf("Evaluation result is %d\n", result);

    return 0;
}
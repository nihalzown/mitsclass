#include<stdio.h>
#include<stdlib.h>
struct node
{
    int coef;
    int expo;
    struct node *link;
};

struct node *create(int coef, int expo)
{
    struct node *newnode = (struct node *)malloc(sizeof(struct node));
    newnode->coef = coef;
    newnode->expo = expo;
    newnode->link = NULL;
    return newnode;
}

struct node *insert(struct node *head, int coef, int expo)
{
    struct node *newnode = create(coef, expo);
    if(head == NULL)
    {
        return newnode;
    }
    if(expo > head->expo)
    {
        newnode->link = head;
        return newnode;
    }
    struct node *current = head;
    while(current->link != NULL && current->link->expo > expo)
    {
        current = current->link;
    }
    newnode->link = current->link;
    current->link = newnode;
    return head;
}

void display(struct node *head)
{
    while(head != NULL)
    {
        printf("%dx^%d", head->coef, head->expo);
        head = head->link;
        if(head != NULL)
        {
            printf(" + ");
        }
    }
    printf("\n");
}

struct node *readpoly(){
    struct node *head = NULL;
    int n, coef, expo;
    printf("Enter the number of terms: ");
    scanf("%d", &n);
    printf("Enter the coefficient and exponent: ");
    for(int i = 0; i < n; i++)
    {
        scanf("%d %d", &coef, &expo);
        head = insert(head, coef, expo);
    }
    return head;
}

struct node *addpoly(struct node *p1, struct node *p2)
{
    struct node *result = NULL;
    struct node *ptr1 = p1, *ptr2 = p2;
    while(ptr1 != NULL && ptr2 != NULL)
    {
        if(ptr1==NULL)
        {
            result = insert(result, ptr2->coef, ptr2->expo);
            ptr2 = ptr2->link;
        }
        else if(ptr2==NULL)
        {
            result = insert(result, ptr1->coef, ptr1->expo);
            ptr1 = ptr1->link;
        }
        else if(ptr1->expo == ptr2->expo)
        {
            result = insert(result, ptr1->coef + ptr2->coef, ptr1->expo);
            ptr1 = ptr1->link;
            ptr2 = ptr2->link;
        }
        else if(ptr1->expo > ptr2->expo)
        {
            result = insert(result, ptr1->coef, ptr1->expo);
            ptr1 = ptr1->link;
        }
        else
        {
            result = insert(result, ptr2->coef, ptr2->expo);
            ptr2 = ptr2->link;
        }
    }
    return result;
}

struct node *multipoly(struct node *p1, struct node *p2)
{
    struct node *result = NULL;
    struct node *ptr1 = p1;
    while(ptr1 != NULL)
    {
        struct node *ptr2 = p2;
        while(ptr2 != NULL)
        {
            int res1 = ptr1->coef * ptr2->coef;
            int res2 = ptr1->expo + ptr2->expo;
            result = insert(result, res1, res2);
            ptr2 = ptr2->link;
        }
        ptr1 = ptr1->link;
    }
    return result;
}


struct node *simplify(struct node *head)
{
    struct node *ptr1 = head, *ptr2, *dup;
    while(ptr1 != NULL && ptr1->link != NULL)
    {
        ptr2 = ptr1;
        while(ptr2->link != NULL)
        {
            if(ptr1->expo == ptr2->link->expo)
            {
                ptr1->coef = ptr1->coef + ptr2->link->coef;
                dup = ptr2->link;
                ptr2->link = ptr2->link->link;
                free(dup);
            }
            else
            {
                ptr2 = ptr2->link;
            }
        }
        ptr1 = ptr1->link;
    }
    return head;
}

int main()
{
    struct node *poly1 = NULL, *poly2 = NULL;
    printf("Enter the first polynomial:\n");
    poly1 = readpoly();
    printf("Enter the second polynomial:\n");
    poly2 = readpoly();
    printf("The first polynomial is: ");
    display(poly1);
    printf("The second polynomial is: ");
    display(poly2);
    struct node *addition = addpoly(poly1, poly2);
    printf("The sum of the polynomials is: ");
    display(addition);
    struct node *multiplication = multipoly(poly1, poly2);
    multiplication = simplify(multiplication);
    printf("The product of the polynomials is: ");
    display(multiplication);
    return 0;
}
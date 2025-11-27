#include <stdio.h>
#include <stdlib.h>

#define MAX 20
int q[MAX], f = -1, r = -1, nv, am[MAX][MAX], visited[MAX];

void enqueue(int item) 
{
    if (r==MAX)
    {
        printf("Queue overflow\n");
        return;
    }
    if (f == -1)
    {  
        f = r = 0;
    }
    else 
    {
        r++;  
    }
    q[r] = item;
}

int dequeue() 
{
    if (f == -1)
    {
        printf("Queue is empty\n");
        return -1;  
    }
    int item = q[f];
    if (f == r) 
    {  
        f = r = -1; 
    } else
    {
        f++;  
    }
    return item;
}

void bfs(int sv)
{
    visited[sv] = 1;
    enqueue(sv);

    printf("BFS Traversal: ");

    while (f != -1) 
    {
        int cv = dequeue();
        printf("v%d ", cv);

        for (int i = 0; i < nv; i++)
        {
            if (am[cv][i] == 1 && visited[i] == 0)
            {
                visited[i] = 1;
                enqueue(i);
            }
        }
    }
    printf("\n");
}

int main()
{
    int e, sv;
    printf("Enter the number of vertices: ");
    scanf("%d", &nv);

    for (int i = 0; i < nv; i++)
    {
        visited[i] = 0;
        for (int j = 0; j < nv; j++)
        {
            am[i][j] = 0;
        }
    }
    for (int i = 0; i < nv; i++) 
    {
        for (int j = 0; j < nv; j++)
        {
            if (i != j) { 
                printf("Do you want to create an edge between v%d and v%d? (enter 1 for yes, 0 for no): ", i, j);
                scanf("%d", &e);
                if (e == 1) 
                {
                    am[i][j] = 1;  
                }
            }
        }
    }

    printf("Adjacency matrix:\n");
    for (int i = 0; i < nv; i++) 
    {
        for (int j = 0; j < nv; j++) 
        {
            printf("%d ", am[i][j]);
        }
        printf("\n");
    }
    printf("Enter the starting vertex (0 to %d): ", nv - 1);
    scanf("%d", &sv);

    bfs(sv);
    return 0;
}
#include <stdio.h>
int B[10];
void printArray(int *A, int n)
{
    for (int i = 0; i < n; i++)
    {
        printf("%d ", A[i]);
    }
    printf("\n");
}
void merge(int A[], int mid, int low, int high)
{
    int i = low, j = mid + 1, k = low;
    while (i <= mid && j <= high)
    {
        if (A[i] < A[j])
        {
            B[k] = A[i];
            k++;
            i++;
        }
        else
        {
            B[k] = A[j];
            k++;
            j++;
        }
    }
    while (i <= mid)
    {
        B[k] = A[i];
        k++;
        i++;
    }
    while (j <= high)
    {
        B[k] = A[j];
        k++;
        j++;
    }
    for (int i = low; i <= high; i++)
    {
        A[i] = B[i];
    }
}

void merge_sort(int *A, int low, int high)
{
    if (low < high)
    {
        int mid = (low + high) / 2;
        merge_sort(A, low, mid);
        merge_sort(A, mid + 1, high);
        merge(A, mid, low, high);
    }
}

int main()
{
   int n;
printf("Enter the number of elements: ");
scanf("%d", &n);
int A[n];
printf("Enter the elements: ");
for (int i = 0; i < n; i++)
{
    scanf("%d", &A[i]);
}
merge_sort(A, 0, n - 1);
printArray(A, n);
return 0;
}

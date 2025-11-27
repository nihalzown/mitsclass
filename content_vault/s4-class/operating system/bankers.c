#include<stdio.h>
#define MAX 10

int main() {
    int alloc[MAX][MAX], max[MAX][MAX], avail[MAX], need[MAX][MAX];
    int finish[MAX], safe_seq[MAX];
    int proc, res, i, j, k, count = 0;

    printf("Enter the number of processes: ");
    scanf("%d", &proc);
    printf("Enter the number of resources: ");
    scanf("%d", &res);
    printf("Enter the allocation matrix:\n");
    for (i = 0; i < proc; i++) {
        for (j = 0; j < res; j++) {
            scanf("%d", &alloc[i][j]);
        }
    }
    printf("Enter the max matrix:\n");
    for (i = 0; i < proc; i++) {
        for (j = 0; j < res; j++) {
            scanf("%d", &max[i][j]);
        }
    }
    printf("Enter the available resources:\n");
    for (i = 0; i < res; i++) {
        scanf("%d", &avail[i]);
    }

    // Calculate the need matrix
    for (i = 0; i < proc; i++) {
        for (j = 0; j < res; j++) {
            need[i][j] = max[i][j] - alloc[i][j];
        }
    }

    // mark all processes as unfinished
    for (i = 0; i < proc; i++) {
        finish[i] = 0;
    }
    int work[MAX];
    for (i = 0; i < res; i++) {
        work[i] = avail[i];
    }

    while(count < proc) {
        int found = 0;
        for (i = 0; i < proc; i++) {
            if (finish[i] == 0) {
                int can_allocate = 1;
                for (j = 0; j < res; j++) {
                    if (need[i][j] > work[j]) {
                        can_allocate = 0;
                        break;
                    }
                }
                if (can_allocate) {
                    for (j = 0; j < res; j++) {
                        work[j] += alloc[i][j];
                    }
                    safe_seq[count++] = i;
                    finish[i] = 1;
                    found = 1;
                }
            }
        }
        if (found == 0) {
            printf("System is not in a safe state\n");
            return 0;
        }
    }
    printf("System is in a safe state.\nSafe sequence is: ");
    for (i = 0; i < proc-1; i++) {
        printf("%d -> ", safe_seq[i]);
    }
    printf("%d\n", safe_seq[proc-1]);
    return 0;
}


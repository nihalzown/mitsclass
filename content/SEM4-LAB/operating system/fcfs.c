// Aim: Program to perform FCFS operations (FCFS).
#include<stdio.h>
#include<stdlib.h>

struct process{
       int process_id,at,bt,tat,wt,ct;       
       };
int main()
{
    struct process p[100], temp;
    int n, i, j, ct;
    printf("Enter the total number of processes: ");
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        p[i].process_id = i + 1;
        printf("Enter the arrival time of process %d: ", i + 1);
        scanf("%d", &p[i].at);
        printf("Enter the burst time of process %d: ", i + 1);
        scanf("%d", &p[i].bt);
    }

    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (p[j].at > p[j + 1].at) {
                temp = p[j];
                p[j] = p[j + 1];
                p[j + 1] = temp;
            }
        }
    }
    p[0].ct = p[0].at + p[0].bt;
    p[0].tat = p[0].ct - p[0].at;
    p[0].wt = p[0].tat - p[0].bt;

    for (i = 1; i < n; i++) {
        ct = p[i - 1].ct;
        if (p[i].at > ct) {
            p[i].ct = p[i].at + p[i].bt;
        } else {
            p[i].ct = ct + p[i].bt;
        }
        p[i].tat = p[i].ct - p[i].at;
        p[i].wt = p[i].tat - p[i].bt;
    }
    printf("Process id\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time\n");
    for (i = 0; i < n; i++) {
        printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", p[i].process_id, p[i].at, p[i].bt, p[i].ct, p[i].tat, p[i].wt);
    }
    return 0;
}
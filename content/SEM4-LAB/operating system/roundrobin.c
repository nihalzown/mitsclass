// Aim: Program to perform Round Robin operations (Round Robin).
#include<stdio.h>

void findwt(int processes[], int n, int bt[], int wt[], int quantum) {
    int rembt[n];
    for (int i = 0; i < n; i++) {
        rembt[i] = bt[i];
    }
    int t = 0;
    while (1) {
        int done = 1;
        for (int i = 0; i < n; i++) {
            if (rembt[i] > 0) {
                done = 0;
                if (rembt[i] > quantum) {
                    t += quantum;
                    rembt[i] -= quantum;
                } else {
                    t = t + rembt[i];
                    wt[i] = t - bt[i];
                    rembt[i] = 0;
                }
            }
        }
        if (done == 1) {
            break;
        }
    }   
}

void findtat(int processes[], int n, int bt[], int wt[], int tat[]) {
    for (int i = 0; i < n; i++) {
        tat[i] = bt[i] + wt[i];
    }
}

void findavgtime(int processes[], int n, int bt[], int quantum) {
    int wt[n], tat[n];
    findwt(processes, n, bt, wt, quantum);
    findtat(processes, n, bt, wt, tat);
    
    printf("Processes\tBurst Time\tWaiting Time\tTurnaround Time\n");
    int total_wt = 0, total_tat = 0;
    for (int i = 0; i < n; i++) {
        total_wt += wt[i];
        total_tat += tat[i];
        printf("\n%d\t\t%d\t\t%d\t\t%d", processes[i], bt[i], wt[i], tat[i]);
    }
    printf("\nAverage waiting time = %f", (float)total_wt / (float)n);
    printf("\nAverage turnaround time = %f", (float)total_tat / (float)n);
}

int main() {
    printf("Enter the number of processes: ");
    int n;
    scanf("%d", &n);
    int processes[n], bt[n];
    printf("Enter the burst time of each process: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", &bt[i]);
        processes[i] = i + 1;
    }
    int quantum;
    printf("Enter the time quantum: ");
    scanf("%d", &quantum);
    findavgtime(processes, n, bt, quantum);
    return 0;
}
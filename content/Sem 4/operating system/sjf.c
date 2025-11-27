#include<stdio.h>

void findwt(int n, int bt[], int wt[]) {
    wt[0] = 0;
    for (int i = 1; i < n; i++) {
        wt[i] = bt[i - 1] + wt[i - 1];
    }
}

void findtat(int n, int bt[], int wt[], int tat[]) {
    for (int i = 0; i < n; i++) {
        tat[i] = bt[i] + wt[i];
    }
}

void findavgtime(int n, int bt[]) {
    int wt[100], tat[100];
    findwt(n, bt, wt);
    findtat(n, bt, wt, tat);
    int total_wt = 0, total_tat = 0;
    printf("Processes\tBurst Time\tWaiting Time\tTurnaround Time\n");
    for (int i = 0; i < n; i++) {
        printf("\n%d\t\t%d\t\t%d\t\t%d", i + 1, bt[i], wt[i], tat[i]);
        total_wt += wt[i];
        total_tat += tat[i];
    }
    printf("\nAverage waiting time = %f", (float)total_wt / (float)n);
    printf("\nAverage turnaround time = %f", (float)total_tat / (float)n);
}

void sort(int n, int bt[]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (bt[j] > bt[j + 1]) {
                int temp = bt[j];
                bt[j] = bt[j + 1];
                bt[j + 1] = temp;
            }
        }
    }
}

void main(){
    printf("enter the number of processes :");
    int n;
    scanf("%d", &n);
    int bt[100];
    printf("enter the burst time of each process :");
    for (int i = 0; i < n; i++) {
        scanf("%d", &bt[i]);
    }
    sort(n, bt);
    findavgtime(n, bt);
}
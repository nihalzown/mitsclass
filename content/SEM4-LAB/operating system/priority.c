// Aim: Program to perform Priority operations (Priority).
#include<stdio.h>

struct process {
    int pid;
    int bt;
    int priority;
    int wt;
    int tat;
};

void sort(int n, struct process p[]) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (p[j].priority > p[j + 1].priority) {
                struct process temp = p[j];
                p[j] = p[j + 1];
                p[j + 1] = temp;
            }
        }
    }
}

void findtime(int n, struct process p[]) {
    p[0].wt = 0;
    p[0].tat = p[0].bt;
    for (int i = 1; i < n; i++) {
        p[i].wt = p[i - 1].bt + p[i - 1].wt;
        p[i].tat = p[i].bt + p[i].wt;
    }
}

void ganttchart(int n, struct process p[]) {
    printf("\nGantt Chart\n");
    printf("-----------------\n");
    for (int i = 0; i < n; i++) {
        printf("| P%d ", p[i].pid);
    }
    printf("|\n");
    for (int i = 0; i < n; i++) {
        if(i == 0) {
            printf("0   ", p[i].tat);
        } printf("%d   ", p[i].tat);
    }
    printf("\n");
}

void main() {
    printf("Enter the number of processes: ");
    int n;
    scanf("%d", &n);
    struct process p[n];
    for (int i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("Enter the burst time of process %d: ", p[i].pid);
        scanf("%d", &p[i].bt);
        printf("Enter the priority of process %d: ", p[i].pid);
        scanf("%d", &p[i].priority);
    }
    sort(n, p);
    findtime(n, p);
    ganttchart(n, p);

    float avgwt = 0, avgtat = 0;
    for(int i = 0; i < n; i++) {
        avgwt += p[i].wt;
        avgtat += p[i].tat;
    }
    avgwt /= n;
    avgtat /= n;
    printf("average waiting time = %f\n", avgwt);
    printf("average turnaround time = %f\n", avgtat);
}
    
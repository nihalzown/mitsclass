// Aim: Program to perform Memory Management operations (Memory Management).
#include<stdio.h>
#include<stdlib.h>

void display(int psize, int bsize, int blank){
    if(bsize != -1){
        printf("%d allocated block %d.....wastage is %d\n", psize, bsize, blank);
    }
    else{
        printf("%d not allocated\n", psize);
    }

}

void first_fit(int memory[], int process[], int msize, int prsize){
    int allocated[100]= {0};
    printf("First Fit\n");
    for(int i = 0; i < prsize; i++){
        int bindex = -1;
        for(int j = 0; j < msize; j++){
            if(memory[j] >= process[i] && allocated[j] == 0){
                bindex = j;
                break;
            }
        }
        if(bindex != -1){
            display(process[i], memory[bindex], memory[bindex] - process[i]);
        }
        else{
            display(process[i], -1, -1);
        }
    }
}

void best_fit(int memory[], int process[], int msize, int prsize){
    int allocated[100]= {0};
    printf("Best Fit\n");
    for(int i = 0; i < prsize; i++){
        int be_index = -1;
        int bwaste = __INT_MAX__;
        for(int j = 0; j < msize; j++){
            if(memory[j] >= process[i] && allocated[j] == 0){
                if(bwaste > memory[j] - process[i]){
                    bwaste = memory[j] - process[i];
                    be_index = j;
                }
            }
        }
        if(be_index != -1){
            display(process[i], memory[be_index], memory[be_index] - process[i]);
        }
        else{
            display(process[i], -1, -1);
        }
    }
}

void worst_fit(int memory[], int process[], int msize, int prsize){
    int allocated[100]= {0};
    printf("Worst Fit\n");
    for(int i = 0; i < prsize; i++){
        int bw_index = -1;
        int bwaste = -1;
        for(int j = 0; j < msize; j++){
            if(memory[j] >= process[i] && allocated[j] == 0){
                if(bwaste < memory[j] - process[i]){
                    bwaste = memory[j] - process[i];
                    bw_index = j;
                }
            }
        }
        if(bw_index != -1){
            display(process[i], memory[bw_index], memory[bw_index] - process[i]);
        }
        else{
            display(process[i], -1, -1);
        }
    }
}

int main(){
    int msize, prsize;
    printf("Enter the number of memory blocks: ");
    scanf("%d", &msize);
    int memory[msize];
    printf("Enter the size of memory blocks:\n");
    for(int i = 0; i < msize; i++){
        scanf("%d", &memory[i]);
    }
    printf("Enter the number of processes: ");
    scanf("%d", &prsize);
    int process[prsize];
    printf("Enter the size of processes:\n");
    for(int i = 0; i < prsize; i++){
        scanf("%d", &process[i]);
    }
    first_fit(memory, process, msize, prsize);
    best_fit(memory, process, msize, prsize);
    worst_fit(memory, process, msize, prsize);
}
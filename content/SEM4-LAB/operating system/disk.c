// Aim: Program to perform Disk Scheduling operations (Disk Scheduling).
#include<stdio.h>
#include<stdlib.h>

void fcfs(int request[], int n, int head) {
    int seek_count = 0;
    int distance, cur_track;
    for(int i = 0; i < n; i++) {
        cur_track = request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    printf("fcfs seek count: %d\n", seek_count);
}

void sstf(int request[], int n, int head) {
    int seek_count = 0;
    int distance, cur_track;
    int completed[n];
    for(int i = 0; i < n; i++) {
        completed[i] = 0;
    }
    for(int i = 0; i < n; i++) {
        int min_distance = 10000, index = -1;
        for(int j = 0; j < n; j++) {
            if(completed[j] == 0) {
                distance = abs(request[j] - head);
                if(distance < min_distance) {
                    min_distance = distance;
                    index = j;
                }
            }
        }
        completed[index] = 1;
        cur_track = request[index];
        seek_count += min_distance;
        head = cur_track;
    }
    printf("sstf seek count: %d\n", seek_count);
}

void scan(int request[], int n, int head , int disk_size) {
    int seek_count = 0;
    int distance, cur_track;
    int sorted_request[n];
    for(int i = 0; i < n; i++) {
        sorted_request[i] = request[i];
    }
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(sorted_request[j] > sorted_request[j + 1]) {
                int temp = sorted_request[j];
                sorted_request[j] = sorted_request[j + 1];
                sorted_request[j + 1] = temp;
            }
        }
    }
    int index;
    for(index = 0; index < n; index++) {
        if(sorted_request[index] >= head) {
            break;
        }
    }
    for(int i = index; i < n; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    seek_count += abs(disk_size - 1 - head);
    head = disk_size - 1;
    for(int i = index - 1; i >= 0; i--) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    printf("scan seek count: %d\n", seek_count);
}

void cscan(int request[], int n, int head , int disk_size) {
    int seek_count = 0;
    int distance, cur_track;
    int sorted_request[n];
    for(int i = 0; i < n; i++) {
        sorted_request[i] = request[i];
    }
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(sorted_request[j] > sorted_request[j + 1]) {
                int temp = sorted_request[j];
                sorted_request[j] = sorted_request[j + 1];
                sorted_request[j + 1] = temp;
            }
        }
    }
    int index;
    for(index = 0; index < n; index++) {
        if(sorted_request[index] > head) {
            break;
        }
    }
    for(int i = index; i < n; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    seek_count += abs(disk_size - 1 - head);
    head = 0;
    seek_count += abs(disk_size - 1);
    for(int i = 0; i < index; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    printf("cscan seek count: %d\n", seek_count);
}

void look(int request[], int n, int head) {
    int seek_count = 0;
    int distance, cur_track;
    int sorted_request[n];
    for(int i = 0; i < n; i++) {
        sorted_request[i] = request[i];
    }
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(sorted_request[j] > sorted_request[j + 1]) {
                int temp = sorted_request[j];
                sorted_request[j] = sorted_request[j + 1];
                sorted_request[j + 1] = temp;
            }
        }
    }
    int index;
    for(index = 0; index < n; index++) {
        if(sorted_request[index] > head) {
            break;
        }
    }
    for(int i = index; i < n; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    for(int i = index - 1; i >= 0; i--) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    printf("look seek count: %d\n", seek_count);
}

void clook(int request[], int n, int head) {
    int seek_count = 0;
    int distance, cur_track;
    int sorted_request[n];
    for(int i = 0; i < n; i++) {
        sorted_request[i] = request[i];
    }
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {
            if(sorted_request[j] > sorted_request[j + 1]) {
                int temp = sorted_request[j];
                sorted_request[j] = sorted_request[j + 1];
                sorted_request[j + 1] = temp;
            }
        }
    }
    int index;
    for(index = 0; index < n; index++) {
        if(sorted_request[index] > head) {
            break;
        }
    }
    for(int i = index; i < n; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    head = sorted_request[0];
    seek_count += abs(sorted_request[n - 1] - head);
    for(int i = 0; i < index; i++) {
        cur_track = sorted_request[i];
        distance = abs(cur_track - head);
        seek_count += distance;
        head = cur_track;
    }
    printf("clook seek count: %d\n", seek_count);
}

int main(){
    int n , head, disk_size;
    printf("Enter number of requests: ");
    scanf("%d", &n);
    int request[n];
    printf("Enter requests: ");
    for(int i = 0; i < n; i++) {
        scanf("%d", &request[i]);
    }
    printf("Enter initial head position: ");
    scanf("%d", &head);
    printf("Enter disk size: ");
    scanf("%d", &disk_size);
    fcfs(request, n, head);
    sstf(request, n, head);
    scan(request, n, head, disk_size);
    cscan(request, n, head, disk_size);
    look(request, n, head);
    clook(request, n, head);
    return 0;  
}
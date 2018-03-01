#include<stdio.h>
#include<stdlib.h>

void swap(int *x, int *y){
    int t = *x;
    *x = *y;
    *y = t;
}

void sort(int *arr, int n){
    int i, j;
    for (i = 0; i < n - 1; i++)
        for (j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1])
                swap(&arr[j], &arr[j + 1]);
}

int notBisect(int *arr, int n, int target){
    int i = 0;
    for(; i < n; i++){
        if(arr[i] >= target){
            return i;
        }
    }
    return n-1;
}

int main(){
    int n, target, i;
    scanf("%d", &n);
    scanf("%d", &target);
    int *arr = malloc(n * sizeof(int));
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    sort(arr, n);
    printf("%d", notBisect(arr, n, target));
}
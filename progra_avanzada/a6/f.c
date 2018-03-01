#include <stdio.h>
#include <stdlib.h>

void swap(int *xp, int *yp){
    int t = *xp;
    *xp = *yp;
    *yp = t;
}

void sort(int *arr, int n){
    int i, j;
    for (i = 0; i < n - 1; i++)
        for (j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1])
                swap(&arr[j], &arr[j + 1]);
}

int main(){
    int n, i;
    scanf("%d", &n);
    int *arr = malloc(n * sizeof(int));
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);

    sort(arr, n);
    // for (i = 0; i < n; i++) printf("%d\n", arr[i]);
    if(!(n%2)){
        printf("%.1lf\n", (arr[(n/2)-1] + arr[n/2])/2.0 );
    }else{
        printf("%.1f\n", (double)arr[(n-1)/2] );
    }
}
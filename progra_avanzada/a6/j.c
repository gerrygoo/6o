#include<stdio.h>
#include<limits.h>

int main(){
    int n, i, N;
    int min;
    int max;
    int total = 0;
    
    scanf("%d", &min);
    scanf("%d", &max);
    scanf("%d", &N);

    for (i = 0; i < N; i++){
        scanf("%d", &n);
        if( n >= min && n <= max ) total ++;
    };
    printf("%d", total);
}
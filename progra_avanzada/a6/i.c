#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

int main(){
    int n, i, N;
    int min = INT_MAX;
    int max = INT_MIN;
    
    scanf("%d", &N);

    for (i = 0; i < N; i++){
        scanf("%d", &n);
        if( n < min ){
            min = n;
        }
        if( n > max ){
            max = n;
        }
    };
    printf("%d", 1+max-min);


}
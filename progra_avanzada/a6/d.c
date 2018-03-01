#include<stdio.h>
#include<limits.h>

int main(){
    int n;
    int max = -1;
    scanf("%d");
    while(scanf("%d", &n) != EOF){
        if( n > max ) max = n;
    }
    printf("%d\n", max);
}
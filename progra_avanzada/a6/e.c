#include<stdio.h>
#include<limits.h>
int main(){
    int n;
    int min = INT_MAX;
    while(scanf("%d", &n) != EOF)
        if( n < min ) min = n;
    printf("%d\n", min);
}
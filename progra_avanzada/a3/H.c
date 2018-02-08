#include<stdio.h>
#include<string.h>

int n;
int main(){
    scanf("%d", &n);
    int i = 1;
    for(; i < n; i++){
        printf("[%d] ", i);
    }
    printf("[%d]\n", n);
}

#include<stdio.h>

int n, s;
int main(){
    scanf("%d", &n);
    int i = 1;
    s = 1;
    for(; i < n; i++) if (!(n%i)) s++;
    printf("%d\n", s);
}
#include<stdio.h>

int main(){
    int c;
    while(scanf("%d", &c) != EOF) printf("%d\n", c%1000);
}
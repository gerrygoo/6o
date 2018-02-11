#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int hasMidpoint(int a, int b, int c){
    return ((a+b)/2 == c)
        || ((a+c)/2 == b)
        || ((b+c)/2 == a);
}

int a, b, c;
int main(){
    scanf("%d %d %d", &a, &b, &c);
    printf(
        hasMidpoint(a, b, c) ?
        "true":
        "false"
    );
}

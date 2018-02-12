#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int hasMidpoint(int a, int b, int c){
    return ( (a+b)/2 == c && !((a+b) % 2) )
        || ( (a+c)/2 == b && !((a+c) % 2) )
        || ( (b+c)/2 == a && !((b+c) % 2) );
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

#include<stdio.h>
#include<limits.h>
typedef unsigned int ui;

int nth(ui x, int n){ return (x >> n)&1; }

void printbits(ui n){
    int i = (CHAR_BIT * sizeof(n))-1;
    for(; i > -1 ; i--){
        printf("%d", nth(n, i));
    }
    printf("\n");
}

ui rotateL(ui x, int n){
    printbits(x << n);
    return (x << n) | ( x >> ((sizeof x * CHAR_BIT)-n) );
}

int main(){
    ui n1;
    int n2;
    scanf("%u %d", &n1, &n2);
    printf( "%u", rotateL(n1, n2) );
}


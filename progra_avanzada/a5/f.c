#include<stdio.h>
#include<limits.h>

#define bits(x) sizeof(x) * CHAR_BIT
typedef unsigned int ui;

ui rotateR(ui x, int n){
    return (x >> n) | (x<<(bits(x)-n));
}

int main(){
    ui n1;
    int n2;
    scanf("%u %d", &n1, &n2);
    printf( "%u", rotateR(n1, n2) );
}


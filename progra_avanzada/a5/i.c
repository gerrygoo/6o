#include<stdio.h>
#include<limits.h>
typedef unsigned int ui;
#define b(x) sizeof(x) * CHAR_BIT

ui get(ui x, int n){
    return (x>>n) & 1;
}

ui set(ui x, int n, int value){ 
    return ((x<<(32-n))>>(32-n) |
    ((((x>>(n)) & 0xFE) | value )<<(n)));
}

int main(){
    ui n;
    int i, j, ith;
    scanf("%u %d %d", &n, &i, &j);

    ith = get(n, i);
    printf("%u\n", set(set(n, i, get(n, j)), j , ith));
}


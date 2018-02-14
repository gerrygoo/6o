#include<stdio.h>
#include<limits.h>
typedef unsigned int ui;
#define b(x) sizeof(x) * CHAR_BIT

ui get(ui x, int n){
    return (x>>n) & 1;
}

ui set(ui x, int n, int value){     
    return (x & ~(1<<n)) | ((value<<n));
}

void swap(ui *x, int i, int j){
    int ith = get(*x, i);
    int jth = get(*x, j);
    int tmp = set(*x, i, jth);
    *x = set(tmp, j, ith);
}

int main(){
    ui n;
    int a, b;
    scanf("%u %d %d", &n, &a, &b);
    int i = 0;
    for(; i <= (b-a)/2; i++) swap(&n, a+i, b-i);
    printf("%u", n);
}


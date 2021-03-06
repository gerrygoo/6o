#include<stdio.h>
#include<limits.h>
typedef unsigned int ui;
#define b(x) sizeof(x) * CHAR_BIT

ui get(ui x, int n){ return (x>>n) & 1; }

ui set(ui x, int n, int value){ return (x & ~(1<<n)) | ((value<<n)); }

void swap(ui *x, int i, int j){
    int ith = get(*x, i);
    int jth = get(*x, j);
    int tmp = set(*x, i, jth);
    *x = set(tmp, j, ith);
}

int main(){
    ui n;
    int i, j, ith;
    scanf("%u %d %d", &n, &i, &j);
    swap(&n, i, j);
    printf("%u", n);
}


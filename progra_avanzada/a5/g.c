#include<stdio.h>
#include<limits.h>

#define bits(x) sizeof(x) * CHAR_BIT
typedef unsigned int ui;

ui swap(ui x, int i, int j){
    return x;
}

int main(){
    ui n1;
    int n2, n3;
    scanf("%u %d %d", &n1, &n2, &n3);
    printf( "%u", swap(n1, n2, n3));
}


#include<stdio.h>
typedef unsigned int ui;

int invertBits(ui x, int p, int n){
    return x ^ ((1<<n)-1)<<p;
}

int main(){
    ui x;
    int p, n;
    scanf("%u %d %d", &x, &p, &n);
    printf( "%u", invertBits(x, p, n) );
}


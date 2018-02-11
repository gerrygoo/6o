#include<stdio.h>
#include<stdlib.h>

int gcd(a, b){
    int t;
    while(b != 0){
        t = b; 
        b = a % b; 
        a = t; 
    }
     return a;
}


int main(){
    int a, b;
    scanf("%d %d", &a, &b);
    printf("%d\n", gcd(abs(a), abs(b)) );
}
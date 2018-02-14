#include<stdio.h>
#include<limits.h>
typedef unsigned int ui;

int isPalinrome(ui n){
    ui reversed = 0, i = 0, original = n;
    for(; n; n >>= 1, i++){
        reversed <<= 1;
        reversed |= n & 1;
    }
    return reversed == original;
}

int main(){
    ui n;
    scanf("%u", &n);
    printf("%d", isPalinrome(n));
}


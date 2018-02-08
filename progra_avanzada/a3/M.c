#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>
//pow(base, exp)

int a, b;
int main(){
    scanf("%d %d", &a, &b);
    printf("%d",  abs(a)>abs(b)?abs(a):abs(b));
}

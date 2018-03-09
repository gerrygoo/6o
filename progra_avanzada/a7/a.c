#include<stdio.h>

struct fraction {
    int nominator;
    int denominator;
};
typedef struct fraction Fraction;

void simplifyFraction(Fraction* fr){
    int a = fr -> nominator, b= fr -> denominator;

    int t;
    while(b != 0){
        t = b; 
        b = a % b; 
        a = t; 
    }

    fr->nominator /= a;
    fr->denominator /= a;
}

int main(){
    Fraction fr;
    scanf("%d %d", &fr.nominator, &fr.denominator);
    simplifyFraction(&fr);
    printf("%d\n%d\n", fr.nominator, fr.denominator);
}
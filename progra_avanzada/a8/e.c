#include<stdio.h>
#include<stdlib.h>

struct fraction {
    int nominator;
    int denominator;
};
typedef struct fraction Fraction;

Fraction simplifyFraction(const Fraction* fr){
    Fraction *res = malloc( sizeof(Fraction) );
    int a = fr -> nominator, b= fr -> denominator;

    int t;
    while(b != 0){
        t = b; 
        b = a % b; 
        a = t; 
    }

    res->nominator = fr->nominator / a;
    res->denominator = fr->denominator / a;
    return *res;
}

int main(){
    Fraction fr, res;
    scanf("%d %d", &fr.nominator, &fr.denominator);
    res = simplifyFraction(&fr);
    printf("%d\n%d\n", res.nominator, res.denominator);
}
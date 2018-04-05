#include<stdio.h>
#include<stdlib.h>

struct fraction {
    int nominator;
    int denominator;
};
typedef struct fraction Fraction;

int gcd(int a, int b){
    int t;
    while(b != 0){
        t = b; 
        b = a % b; 
        a = t; 
    }
    return a;
}

void simplifyFraction(Fraction* fr){
    int _gcd = gcd(fr -> nominator, fr -> denominator);
    fr->nominator /= _gcd;
    fr->denominator /= _gcd;
}

Fraction sumFraction(const Fraction* cfr1, const Fraction* cfr2){
    Fraction * res = (Fraction*)malloc( sizeof(Fraction) );
    
    Fraction *fr1 = cfr1, *fr2 = cfr2;

    int t = fr1->denominator;
    fr1->nominator *= fr2->denominator;
    fr1->denominator *= fr2->denominator;
    fr2->nominator *= t;
    fr2->denominator *= t;

    res->nominator = fr1->nominator + fr2->nominator;
    res->denominator = fr1->denominator;
    simplifyFraction(&res);
    return *res;
}

int main(){
    Fraction fr1, fr2, res;
    scanf(
        "%d %d %d %d",
        &fr1.nominator, &fr1.denominator,
        &fr2.nominator, &fr2.denominator
    );
    res = sumFraction(&fr1, &fr2);
    printf("%d\n%d\n", res.nominator, res.denominator);
}
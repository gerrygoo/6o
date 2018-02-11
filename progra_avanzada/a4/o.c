#include<stdio.h>

int mmin(a, b, c){
    int min = 100000;
    if(a < min) min = a;
    if(b < min) min = b;
    if(c < min) min = c;
    return(min);
}

int mmax(a, b, c){
    int min = -10000;
    if(a > min) min = a;
    if(b > min) min = b;
    if(c > min) min = c;
    return(min);
}

int a, b, c;
int r[3];

int main(){
    scanf("%d %d %d", &a, &b, &c);
    if(mmax(a,b,c) == mmin(a,b,c)){
        printf("%d\n", c);
        return 0;
    }

    if(mmax(a,b,c) == mmin(a,b,c)){
        printf("%d\n", c);
        return 0;
    }
    
}
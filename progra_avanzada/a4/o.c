#include<stdio.h>

int a, b, c;
int main(){
    scanf("%d %d %d", &a, &b, &c);
    //abc - cba
    if( ((a <= b) && ( b <= c)) || ((c <= b) && ( b <= a) ) ){
        printf("%d", b);
        return 0;
    }
    
    //bca - acb
    if( ((b <= c) && ( c <= a)) || ((a <= c) && ( c <= b ) ) ){
        printf("%d", c);
        return 0;
    }

    //bac - cab
    if( ((b <= a) && ( a <= c)) || ((c <= a) && ( a <= b ) ) ){
        printf("%d", a);
        return 0;
    }
}
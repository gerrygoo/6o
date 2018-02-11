#include<stdio.h>

int a, b, c;
int main(){
    scanf("%d %d %d", &a, &b, &c);

    if ( a == b && b == c && c == a){
        printf("equilateral\n");
        return 0;
    }

    if ( a == b || b == c || c == a){
        printf("isosceles\n");
        return 0;
    }

    printf("scalene\n");
    return 0;
}

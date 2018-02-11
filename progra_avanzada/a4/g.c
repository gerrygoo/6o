#include <stdio.h>
#include <math.h>

int n;
int main () {
    scanf("%d", &n);
    printf("%d = ", n);
    while( n % 2 == 0 ){
        n /= 2;
        printf(" 2 *");
    }
    printf(" %d\n", n);
}
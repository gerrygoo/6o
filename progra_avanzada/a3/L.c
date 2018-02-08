#include<stdio.h>
#include<string.h>
#include<math.h>
//pow(base, exp)

double base, n;
int main(){
    scanf("%lf %lf", &base, &n);
    int i = 0;
    for(; i < n; i++){
        printf( "%d ", (int)pow(base, i) );
    }
    printf("%d\n", (int)pow(base, n));
}

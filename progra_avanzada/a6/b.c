#include<stdio.h>

int main(){
    double n;
    double avg;
    int i = 1;
    while(scanf("%lf", &n) != EOF) avg += (n - avg)/i++;
    printf("%.1lf\n", avg);

}
#include <stdarg.h>
#include <stdio.h>
#include<limits.h>
typedef unsigned int ui;


int nth(ui x, int n){ return (x >> n)&1; }

void printbits(ui n){
    int i = (CHAR_BIT * sizeof(n))-1;
    for(; i > -1 ; i--){
        printf("%d", nth(n, i));
    }
    printf("\n");
}

int concat (int count, ...){
    va_list ap;
    va_start (ap, count);

    ui res = 0;

    int i;
    for (i = 0; i < count; i++){
      res <<= 8;
      res |= va_arg(ap, ui);
    }
    return res;
}

int
main (void){
    int a, b, c, d;
    scanf("%u %u %u %u", &a, &b, &c, &d);
    printf ("%d\n", concat(4, d, c, b, a));

  return 0;
}
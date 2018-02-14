#include<stdio.h>
typedef unsigned int ui;

int main(){
    ui v, c;
    for(scanf("%u", &v), c = 0; v; c++) v &= v - 1;
    printf("%u\n", c);
}

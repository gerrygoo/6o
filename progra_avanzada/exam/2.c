#include<stdio.h>

typedef unsigned int ui;

ui encodeMinutia(ui x, ui y, ui d, ui t);

ui encodeMinutia(ui x, ui y, ui d, ui t){
   return (t<<29) | (d<<20) | (y<<10) | x;
}

int main(){
    ui x, y, d, t;
    scanf("%u %u %u %u", &x, &y, &d, &t);
    printf("%u", encodeMinutia(x, y, d, t));
return 0;}
#include<stdio.h>
typedef unsigned int ui;

ui bits(ui n){
    ui c;
    for(c = 0; n; c++) n &= n - 1;
    return c;
}

int main(){
    ui a, b;
    scanf("%u %u", &a, &b);
    printf(
        (bits(a) < bits(b)) ? "-1\n" :
        (bits(a) > bits(b) ) ? "1\n": "0\n"        
    );
    
}

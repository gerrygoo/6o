#include<stdio.h>

int main(){
    int i;
    for(i = 1; i <= 60; i++) {
        if( i%10 == 9 ){
            printf("|");
            continue;
        }
        printf(" ");
    }
    printf("\n");
    for(i = 1; i <= 60; i++) printf("%d", i%10);
    printf("\n");
}
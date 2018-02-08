#include<stdio.h>

int main(){
    int i;
    for(i = 0; i < 40; i++) printf("-");
    printf("\n");
    for(i = 0; i < 10; i++) printf("_-^-");
    printf("\n");
    for(i = 1; i <= 10; i++) printf("%d%d", i%10, i%10);
    for(i = 1; i <= 10; i++) printf("%d%d", i%10, i%10);
    printf("\n");
    for(i = 0; i < 40; i++) printf("-");
    printf("\n");
}
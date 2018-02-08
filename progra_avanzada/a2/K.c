#include<stdio.h>

int main(){
    int i, j, k;
    for(i = 0, j = 7; i <= 12 && j >= 1; i+=2, j--){
        for(k = 0; k < i; k++, printf("*"));
        for(k = 0; k < j; k++, printf("$"));
        for(k = 0; k < (14-i); k++, printf("*"));
        for(k = 0; k < j; k++, printf("$"));
        for(k = 0; k < i; k++, printf("*"));
        printf("\n");
    }
}

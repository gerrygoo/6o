#include<stdio.h>

int main(){int i, j;
    for(i = 1; i < 10; i += 2){
        for(j = 0; j < (11-i)/2 ; j++, printf("-"));
        for(j = 0; j < i; j++, printf("%d", i));
        for(j = 0; j < (11-i)/2 ; j++, printf("-"));
        printf("\n");
    }
}
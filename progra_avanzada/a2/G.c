#include<stdio.h>

int main(){
    int i, j, k;
    printf("Write nested for loops that produce the following output:\n\n\n");
    for(k = 0; k < 3; k++){
        for(i = 0; i < 10; i++) for(j = 0; j < 3; j++) printf("%d", i);
        printf("\n");
    }
}
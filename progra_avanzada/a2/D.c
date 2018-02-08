#include<stdio.h>

int main(){
    char c = 'u';
    // 4 x 5
    int i, j;
    for(i = 0; i < 4; i++){
        for(j = 0; j < 5; j++){
            printf("%c", c);
        }
        printf("\n");
    }
}
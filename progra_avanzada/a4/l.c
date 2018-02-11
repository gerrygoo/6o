#include<stdio.h>

int size;
int main(){
    scanf("%d", &size);
    
    int i = 0, j = 0;
    for(; i < size; i++){
        j = 0;
        for(; j < size; j++){
            if(j == i || j == (size-1) - i){
                printf("x");
            }else{
                printf("o");
            }
        }
        printf("\n");
    }
    printf("\n");
}

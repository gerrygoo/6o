#include<stdio.h>

int main(){
    //22
    int i, j;
    for(i = 0; i <= 10; i += 2){
        for(j = 0; j < i ; j++, printf("\\"));
        for(j = 0; j < (22-2*i); j++, printf("!"));
        for(j = 0; j < i ; j++, printf("/"));
        printf("\n");
    }
}
#include<stdio.h>

int main(){
    int i, j, n, w;
    while(scanf("%d", &n) != EOF){
        w = -2+(n*4);
        for(i = 0; i < n; i++){
            for(j = 0; j < 2*i ; j++, printf("\\"));
            for(j = 0; j < (w-4*i); j++, printf("!"));
            for(j = 0; j < 2*i ; j++, printf("/"));
            printf("\n");
        }
    }
}
#include<stdio.h>
#include<string.h>

int a, b, offset;
int main(){
    scanf("%d %d", &a, &b);
    offset = a;
    int i = 1, j; 
    for(; i <= a; i++){
        for(j = i; j < (a*b) - (a-i); j += a){
            printf("%d, ", j);
        }
        printf("%d\n", (a*b) - (a-i));
    }
}

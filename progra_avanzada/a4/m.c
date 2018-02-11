#include<stdio.h>

int a, b;
int main(){
    scanf("%d %d", &a, &b);
    if(b > a){
        int i = a;
        for(; i < b; i++){
            printf("%d ", i);
        }
        printf("%d\n", b);
    }else if(a > b){
        int i = a;
        for(; i > b; i--){
            printf("%d ", i);
        }
        printf("%d\n", b);
    }else{
        printf("%d\n", b);
    }
}

#include<stdio.h>
#include<string.h>

int a, b, offset;
int main(){
    scanf("%d %d", &a, &b);
    offset = a;
    int i = 1;
    for(; i <= b; i++){
        printf("%d, ", i+offset);
    }
}

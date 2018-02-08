#include<stdio.h>

int n;
int main(){
    scanf("%d", &n);
    printf("%s",
        n & 1 ?
        "odd\n":
        "even\n"
    );
}

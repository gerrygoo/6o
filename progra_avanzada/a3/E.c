#include<stdio.h>
#include<string.h>

int n;
int main(){
    scanf("%d", &n);
    printf("%d", ( ( n - (n%25) )%100 )/25 );
}

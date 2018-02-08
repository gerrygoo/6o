#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int n;
char str[1000];
char c;
int main(){
	scanf("%d", &n);
    scanf(" %[^\n]s", str);
    int i;
    for(i  = 0; i < n ; i++){
        printf("%s", str);
    }
    printf("\n");
}

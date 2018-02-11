#include<stdio.h>
#include<string.h>

char s[1000];
char t;

int main(){
    scanf("%s", s);
    int i = strlen(s)-1;
    for(; i > 0; i-=2){
        t = s[i];
        s[i] = s[i-1];
        s[i-1] = t;
    }
    printf("%s\n", s);
}
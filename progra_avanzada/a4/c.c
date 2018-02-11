#include<stdio.h>
#include<string.h>

char s[1000];

int main(){

    while( scanf("%s", s) != EOF ){
        int sum = 0;
        int i = 0;
        for (; i < strlen(s); i++){
            if( s[i] != '-')
                sum += s[i]-'0';
        }
        printf("%d\n", sum);
    }
    
}

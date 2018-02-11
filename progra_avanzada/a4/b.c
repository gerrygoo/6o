#include<stdio.h>
#include<string.h>

char s[1000];
char max = '0', min = '9';
int main(){
    scanf("%s", s);
    int i = 0;
    if( strlen(s) == 1 ){
        printf("%d\n", 1);
        return 0;
    }
    for (; i < strlen(s); i++){
        if ( s[i] != '-' ){
            if (s[i] > max) max = s[i];
            if (s[i] < min) min = s[i];
        }
    }
    printf("%d\n", max-min+1);
}

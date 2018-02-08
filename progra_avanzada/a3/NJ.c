#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

int a, b;
int main(){
    scanf("%d %d", &a, &b);
    if( (a == 0 && b == 0) ) return 0;
    int i, j, start;
    for(i = a; i <= b; i++){
        start = i;
        for(j = a; j <= b; j++){
            if(start > b){
                start = a;
            }
            printf("%d", start++);
        }
        printf("\n");
    }
}

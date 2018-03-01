#include<stdio.h>
#define SIZE 1000000

int main(){
    int map[SIZE];
    int n, mode, maxcount = -1;
    scanf("%d", &mode);
    while(scanf("%d", &n) != EOF){
        if(++map[n] > maxcount){
            maxcount = map[n];
            mode = n;
        }
    }
    printf("%d\n", mode);
}
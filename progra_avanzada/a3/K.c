#include<stdio.h>
#include<string.h>
#include<math.h>
//pow(base, exp)

int n;
int main(){
    scanf("%d", &n);
    int i = 0;
    for(; i < n; i++){
        printf("%d ", 1<<i);
    }
    printf("%d\n", 1<<n);
}

#include<stdio.h>

int n;
int main(){
    scanf("%d", &n);
    printf("   1\n");
    
    int i = 0;
    int j = 2;
    for(; i < n; i++, j+=3 ){
        printf("%d     %d\n   %d\n",
            j, j+1, j+2
        );
    }

}

#include<stdio.h>
#include<string.h>

int isPerfect(int k){
    int s = 0;
    int i = 1;
    for(; i < k; i++){
        if( !(k % i) ){
            s += i;
        }
    }
    return s == k;
}


int main(){
    int n;
    scanf("%d", &n);
    printf("Perfect numbers up to %d: ", n);
    
    int i = 1;
    for(; i <= n; i++){
        if ( isPerfect(i) ){
            printf("%d ", i);
        }
    }

}

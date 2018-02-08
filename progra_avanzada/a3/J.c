#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>



int a, b;
char *aPtr;

void rotate(char * arr, int len){
    char temp = arr[0];
    
    int i = 0;
    for(; i < len-1; i++){
        arr[i] = arr[i+1];
    }
    arr[len-1] = temp;

}
int main(){
    scanf("%d %d", &a, &b);
    int l =  b-(a-1);
    aPtr = malloc(sizeof(char) * (l+1) );

    int i, j;
    for(i = 0, j = a; j <= b; j++, i++){
        aPtr[i] = j + '0';
    }

    for(i = 0; i < b; i++){
        printf("%s\n", aPtr);
        rotate(aPtr, l);
    }
}

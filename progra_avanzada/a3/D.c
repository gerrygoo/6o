#include<stdio.h>
#include<string.h>

int mmin(a, b, c){
    int min = 100000;
    if(a < min) min = a;
    if(b < min) min = b;
    if(c < min) min = c;
    return(min);
}

int x, y, z;
int main(){
    scanf("%d %d %d", &x, &y, &z);
    printf("%d", mmin(x, y, z));
}

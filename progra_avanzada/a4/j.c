#include<stdio.h>

float x, y;
int main(){
        scanf("%f %f", &x, &y);
        if(x == 0.0 || y == 0.0){
            printf("0\n");
            return 0;
        }
        if( x > 0.0 && y > 0.0){ 
            printf("1\n");
            return 0;
        }
        if( x < 0.0 && y > 0.0) {
            printf("2\n");
            return 0;
        }
        if( x < 0.0 && y < 0.0){
             printf("3\n");
             return 0;
        }
        if( x > 0.0 && y < 0.0){
            printf("4\n");
            return 0;
        }
    return 0;
}

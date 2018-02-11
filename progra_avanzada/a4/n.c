#include<stdio.h>

int m, d;
int main(){
    scanf("%d %d", &m, &d);
    if ( (m >= 12 && d >= 16) || ( m <= 3 && d <= 15 ) || m < 3 ){
        printf("Winter\n"); return 0;
    }

    if (
        (m == 3 && d >= 16) || (m == 6 && d <= 15) 
        || (m > 3 && m < 6)
    ){printf("Spring\n"); return 0;}

    if (
        (m == 6 && d >= 16) || (m == 9 && d <= 15) 
        || (m > 6 && m < 9)
    ){ printf("Summer\n"); return 0; }

    if (
        (m == 9 && d >= 16) || (m == 12 && d <= 15) 
        || (m > 9 && m < 12)
    ){ printf("Fall\n"); return 0; }
    return 0;   
}
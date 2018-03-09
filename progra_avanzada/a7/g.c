#include<stdio.h>

struct point { int x, y; };
typedef struct point Point;

void swapAndNegate(Point* p){
    int t = -p->x;
    p->x = -p->y;
    p->y = t;
}

int main(){
    Point p;
    scanf( "%d %d", &p.x, &p.y);
    swapAndNegate(&p);
    printf( "%d\n%d\n", p.x, p.y );
}
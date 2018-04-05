#include<stdio.h>

struct point { int x, y; };
typedef struct point Point;

int quadrant(const Point * p){
    if(p->x == 0.0 || p->y == 0.0) return 0;
    if( p->x > 0.0 && p->y > 0.0) return 1;
    if( p->x < 0.0 && p->y > 0.0) return 2;
    if( p->x < 0.0 && p->y < 0.0) return 3;
    if( p->x > 0.0 && p->y < 0.0) return 4;
    return -1;
}

int main(){
    Point p;
    scanf("%d %d", &p.x, &p.y);
    printf("%d\n", quadrant(&p));
}
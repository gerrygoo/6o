#include<stdio.h>

struct rectangle { int x, y, width, height; };
typedef struct rectangle Rectangle;

struct point { int x, y; };
typedef struct point Point;

int rectangleContainsPoint(Rectangle* r, Point* p){
    return 
        p->x >= r->x
        && p->x <= (r->x + r->width)
        &&p->y >= r->y
        && p->y <= (r->y + r->height)
    ;
}

int main(){
    Rectangle r;
    Point p;
    scanf(
        "%d %d %d %d %d %d", 
        &r.x,
        &r.y,
        &r.width,
        &r.height,
        &p.x,
        &p.y
    );
    printf( "%d\n",  rectangleContainsPoint(&r, &p) );
}
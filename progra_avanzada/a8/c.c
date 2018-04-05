#include<stdio.h>

struct point { int x, y; };
typedef struct point Point;

double slope(const Point* p1, const Point* p2){
    return ( (double)(p2->y - p1->y) )/(p2->x - p1->x);
}

int main(){
    Point p1, p2;
    scanf(
        "%d %d %d %d", 
        &p1.x,
        &p1.y,
        &p2.x,
        &p2.y
    );
    printf( "%.1f\n",  slope(&p1, &p2) );
}
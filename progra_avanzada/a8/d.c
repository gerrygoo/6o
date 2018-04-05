#include<stdio.h>

struct point { int x, y; };
typedef struct point Point;

double _abs(double x){ return x < 0 ? x * -1 : x; }

double slope(Point* p1, Point* p2){
    return ( (double)(p2->y - p1->y) )/(p2->x - p1->x);
}

int areCollinear(const Point* p1, const Point* p2, const Point* p3){
    return _abs(slope(p1, p2) - slope(p2, p3) ) < 0.001;
}

int main(){
    Point p1, p2, p3;
    scanf(
        "%d %d %d %d %d %d", 
        &p1.x,
        &p1.y,

        &p2.x,
        &p2.y,

        &p3.x,
        &p3.y
    );
    printf( "%d\n",  areCollinear(&p1, &p2, &p3) );
}
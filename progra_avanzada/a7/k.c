#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

#define infinity 10000

typedef enum { false, true } bool;

struct point { int x, y; };
typedef struct point Point;

//colinear, clockwise, counterclockwise
int orientation(Point* a, Point* b, Point* c){
    int slope_difference =
        (b->y - a->y) * (c->x - b->x)
        - (b->x - a->x) * (c->y - b->y);
 
    if (slope_difference == 0) return 0;
    return (slope_difference > 0)? 1: 2;
}

bool p_inside_polygon(int n, Point* polygon, Point* p){
    if(n < 3) return false;

    int first = orientation(p, &polygon[0], &polygon[1]), next, i;
    for(i = 1; i < n; i++){
        next = (i+1)%n;
        if ( orientation(p, &polygon[i], &polygon[next]) != first ) return false;
    }
    return true;
}

int main(){
    int n_points, i, x, y;
    scanf("%d", &n_points);

    Point* polygon = (Point*)malloc( n_points * sizeof(Point) );
    for(i = 0; i < n_points; i++) scanf("%d %d", &polygon[i].x, &polygon[i].y);

    Point p;
    scanf("%d %d", &p.x, &p.y);

    printf("%d\n", p_inside_polygon(n_points, polygon, &p) );
}
#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

#define infinity 10000

typedef enum { false, true } bool;

struct point { int x, y; };
typedef struct point Point;

bool ordered(int a, int b, int c){ 
    return ((a <= b) && ( b <= c)) || ((c <= b) && ( b <= a));
}

//c in segment a-b when c is colinear to a-b
bool in_segment(Point* a, Point* b, Point* c){
    return ordered(a->x, c->x, b->x) && ordered(a->y, c->y, b->y);
}

//colinear, clockwise, counterclockwise
int orientation(Point* a, Point* b, Point* c){
    int slope_difference =
        (b->y - a->y) * (c->x - b->x)
        - (b->x - a->x) * (c->y - b->y);
 
    if (slope_difference == 0) return 0;
    return (slope_difference > 0)? 1: 2;
}

//intersection of line segments a-b, c-d
bool intersect(Point* a, Point* b, Point* c, Point* d){
    int 
        a_cd = orientation(a, c, d),
        b_cd = orientation(b, c, d),
        c_ab = orientation(c, a, b),
        d_ab = orientation(d, a, b);
    
    return (
        (a_cd != b_cd && c_ab != d_ab) //general case
        //overlapping segments
        ||( !a_cd && in_segment(c, d, b) )
        ||( !b_cd && in_segment(c, d, a) )
        ||( !c_ab && in_segment(a, b, d) )
        ||( !d_ab && in_segment(a, b, c) )
    );
}

bool p_inside_polygon(int n, Point* polygon, Point* p){
    if(n < 3) return false;
    
    Point inf = {infinity, p->y};

    int intersections = 0, i, next;
    for(i = 0; i < n; i++){
        next = (i+1)%n;

        if( //count as inside if p lies on polygon side
            !orientation(p, &polygon[i], &polygon[next]) //p is colinear to side
            && in_segment(&polygon[i], &polygon[next], p) //p is in side
        ) return true;

        if( intersect(&polygon[i], &polygon[next], p, &inf) ) intersections++;
    }
    return intersections&1;
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
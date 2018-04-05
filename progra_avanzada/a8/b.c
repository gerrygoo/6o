#include<stdio.h>
#include<math.h>

struct point3 { int x, y, z; };
typedef struct point3 Point3;

long double dist(const Point3* p1, const Point3* p2){
    return sqrt( 
        ((p2->x - p1->x)*(p2->x - p1->x))
        +((p2->y - p1->y)*(p2->y - p1->y))
        +((p2->z - p1->z)*(p2->z - p1->z))
    );
}

int main(){
    Point3 p1, p2;
    scanf(
        "%d %d %d %d %d %d", 
        &p1.x,
        &p1.y,
        &p1.z,

        &p2.x,
        &p2.y,
        &p2.z
    );
    printf( "%.2Lf\n",  dist(&p1, &p2) );
}
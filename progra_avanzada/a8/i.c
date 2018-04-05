#include<stdio.h>

struct rectangle { int x, y, width, height; };
typedef struct rectangle Rectangle;

int max(x, y){ return x > y ? x : y; }
int min(x, y){ return x < y ? x : y; }

Rectangle rectangleUnion(const Rectangle* r1, const Rectangle* r2){
    Rectangle* res = (Rectangle*)malloc(sizeof(Rectangle));
    int 
        r1_dx = r1->x+r1->width,
        r1_dy = r1->y+r1->height,

        r2_dx = r2->x+r2->width,
        r2_dy = r2->y+r2->height
    ;

    res->x = min(r1->x, r2->x);
    res->y = min(r1->y, r2->y);
    res->width = max(r1_dx, r2_dx) - res->x;
    res->height = max(r1_dy, r2_dy) - res->y;

    return *res;
}

int main(){
    Rectangle r1, r2, res;
    scanf(
        "%d %d %d %d %d %d %d %d", 
        &r1.x,
        &r1.y,
        &r1.width,
        &r1.height,
        &r2.x,
        &r2.y,
        &r2.width,
        &r2.height
    );
    res = rectangleUnion(&r1, &r2);
    printf(
        "%d\n%d\n%d\n%d\n", 
        res.x,
        res.y,
        res.width,
        res.height
    );
}
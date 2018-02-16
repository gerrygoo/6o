#include<stdio.h>
#include<stdint.h>
#include<limits.h>

typedef uint64_t llu;
typedef uint16_t us;

void set(us *x, int n, int value){ 
    *x |= ((value<<n)); 
}

us lowerRes(llu r0_3, llu r4_7, llu r8_11, llu r12_15){
    llu fourth = 17294086455919964160;
    llu  third = 1080880403494997760;
    llu  second = 67555025218437360;
    llu  first = 4222189076152335;

    llu originalGrid[] = { r0_3, r4_7, r8_11, r12_15 };
    llu masks[] = { first, second, third, fourth };

    us t = 0;

    int i = 0, j = 0, k = 0;
    for(i = 0; i < 4; i++){
        for(j = 0; j < 4; j++){
            set(&t, k, (originalGrid[i] & masks[j]) > 0 );
            k ++;
        }
    }
    return t;
}

int t1(){
    llu a, b, c, d;
    a = (llu)1<<46 | (llu)1<<47;
    b = (llu)1<<18 | (llu)1<<32;
    c = (llu)1<<60;
    d = 0;
    return lowerRes(a, b, c, d) == 2072;
}

int t2(){
    llu a, b, c, d;
    a = 1;
    b = 0;
    c = 0;
    d = 0;
    return lowerRes(a, b, c, d) == 1;
}

int t3(){
    llu a, b, c, d;
    a = (llu)1<<46 | (llu)1<<47;
    b = (llu)1<<18 | (llu)1<<32;
    c = (llu)1<<60;
    d = (llu)1<<63;
    return lowerRes(a, b, c, d) == 34840;
}

int t4(){
    llu a, b, c, d;
    a = (llu)1<<46 | (llu)1<<47;
    b = (llu)1<<18 | (llu)1<<32;
    c = (llu)1<<60;
    d = (llu)1<<46 | (llu)1<<47;
    return lowerRes(a, b, c, d) == 34840;
}

int t5(){
    llu a, b, c, d;
    a = 0;
    b = 0;
    c = (llu)1<<30;
    d = (llu)1<<18 | (llu)1<<32;
    return lowerRes(a, b, c, d) == 6144;
}


int main(){
    printf("%d\n", t1());
    printf("%d\n", t2());
    printf("%d\n", t3());
    printf("%d\n", t4());
    printf("%d\n", t5());
return 0;}
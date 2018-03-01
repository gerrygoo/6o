#include<stdio.h>
#include<stdlib.h>

typedef unsigned long long int ull;

int main(){
    ull n, i, j;
    scanf("%llu", &n);

    ull* sieve = (ull*)malloc(n * sizeof(ull));

    sieve[0] = 1;
    for (i = 2; i*i < n; i++)
        for(j = i*i; j < n; j += i)
            sieve[j] = 1;

    for(i = 2; i < n; i++)
        if(!sieve[i]) printf("%llu\n", i);
}
typedef unsigned int ui;

int nth(ui x, int n){ return (x >> n)&1; }

void printbits(ui n){
    int i = (CHAR_BIT * sizeof(n))-1;
    for(; i > -1 ; i--){
        printf("%d", nth(n, i));
    }
    printf("\n");
}

ui bits(ui n){
    ui c;
    for(c = 0; n; c++) n &= n - 1;
    return c;
}
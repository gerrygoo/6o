#include<stdio.h>

typedef unsigned int ui;
void decodeMinutia(ui encodedMinutia);

void decodeMinutia(ui encodedMinutia){
    printf("%u\n",
        (encodedMinutia<<(32-10))>>(32-10)
    );
    printf("%u\n",
        (encodedMinutia<<(32-20))>>(32-10)
    );
    printf("%u\n",
        (encodedMinutia<<(32-29))>>(32-9)
    );
    printf("%u\n",
        (encodedMinutia<<(32-31))>>(32-2)
    );
}

int main(){
    int minutia;
    scanf("%u", &minutia);
    decodeMinutia(minutia);
return 0;}
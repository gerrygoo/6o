#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//assumes all positive integers
int isMagicSquare(int** square, int n){
    int i, j, rowSum = 0, lastRowSum = -1;
    int* columns = malloc(n * sizeof(int));

    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            rowSum += square[i][j];
            columns[j] += square[i][j];
        }
        if(lastRowSum > 0 && rowSum != lastRowSum){
            return 0;
        }
        lastRowSum = rowSum;
        rowSum = 0;
    }

    for(i = 1; i < n; i++){
        if (columns[i] != columns[i-1]) return 0;
    }
    
    return 1;
}

int main(){
    int n, i, j;
    scanf("%d", &n);

    int** arr = (int**)malloc(n * sizeof(int*));

    for(i = 0; i < n; i++)
        arr[i] = (int*)malloc(n * sizeof(int) );
    

    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            scanf("%d", &arr[i][j]);
    
    printf("%s", isMagicSquare(arr, n) == 1 ? "true" : "false");
}
#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<limits.h>

typedef enum { false, true } bool;

typedef enum {numerical, nominal} FeatureType;

typedef struct {
    double* features;
    FeatureType* featureTypes;
    int length;
} Instance;

typedef struct { int current_max; int* counts; } NFT;

double computeEuclideanDissimilarity(const Instance* a, const Instance* b);
const Instance* findNearestNeighbor( const Instance* instances, int length, const Instance * query, double (*computeDissimilarity) (const Instance* a, const Instance* b) );
const Instance* averageDataset( const Instance* instances, int length );

double computeEuclideanDissimilarity(const Instance* a, const Instance* b) {
    if( !a->length || !b->length ) return -1; if( a->length != b->length ) return -2;
    int i;
    double sum = 0.0;
    for (i = 0; i < a->length; i++){
        if ( a->featureTypes[i] != b->featureTypes[i] ) return -3;
        sum += a->featureTypes[i] == numerical ?
            pow( a->features[i] - b->features[i], 2) :
            a->features[i] != b->features[i];
    }
    return sqrt(sum);
}

const Instance* findNearestNeighbor(
        const Instance* instances,
        int length,
        const Instance * query,
        double (*computeDissimilarity) (const Instance* a, const Instance* b)
){
    if ( query->length < 1 ) return (Instance *) -1;
    int i, min_i, j;
    double min = 1000000.0, cur;

    for(i = 0; i < length; i++){
        if ( instances[i].length < 1 ) return (Instance *) -1;
        if ( instances[i].length != query->length ) return (Instance *) -2;
        for ( j = 0; j < instances[i].length; j++ ) {
            if ( instances[i].featureTypes[j] != query->featureTypes[j] ) return (Instance *) -3;
        }
        cur = computeDissimilarity(query, &instances[i]);
        if ( cur < min ){
            min = cur;
            min_i = i;
        }
    }
    
    return &instances[min_i];
}

const Instance* averageDataset( const Instance* instances, int length ){
    int features = instances->length;

    if ( length < 1 ) return (Instance *)-1;

    int i, j, numericalFeatures = 0, nominalFeatures = 0;

    for(i = 0; i < length; i++) {
        if ( instances[i].length != instances[0].length ) {
            return (Instance *)-2;
        }
        for ( j = 0; j < features; j++ ) {
            if ( instances[i].featureTypes[j] != instances[0].featureTypes[j] ) {
                return (Instance *)-3;
            }
        }
    }

    Instance* averageInstance = (Instance*)malloc(sizeof(Instance));
    averageInstance->features = (double*) calloc(features, sizeof(double));
    averageInstance->featureTypes = (FeatureType *)malloc(features * sizeof(FeatureType));
    
    for(i = 0; i < features; i++) {
        averageInstance->featureTypes[i] = instances[0].featureTypes[i];
        if (instances[0].featureTypes[i] == nominal) nominalFeatures++;
        else numericalFeatures++; 
    }


    NFT*  nominalModes = malloc(nominalFeatures * sizeof(NFT));
    for(i = 0; i < nominalFeatures; i++){
        nominalModes[i].current_max = 0;
        nominalModes[i].counts = calloc(length, sizeof(int));
    }

    int currentNominalIdx, valueOfCurrentNominal;
    for(i = 0; i < length; i++){
        currentNominalIdx = 0;
        for(j = 0; j < features; j++){
            if ( instances[i].featureTypes[j] == nominal ) {
                valueOfCurrentNominal = (int)instances[i].features[j];
                nominalModes[currentNominalIdx].counts[valueOfCurrentNominal]++;
                if ( 
                    nominalModes[currentNominalIdx].counts[valueOfCurrentNominal]
                    >
                    nominalModes[currentNominalIdx].counts[ 
                        nominalModes[currentNominalIdx].current_max
                    ]
                ){
                    nominalModes[currentNominalIdx].current_max = valueOfCurrentNominal;
                }

                averageInstance->features[j] = nominalModes[currentNominalIdx].current_max;
                currentNominalIdx++;
            } else {
                averageInstance->features[j] += 
                    (( instances[i].features[j] - averageInstance->features[j] )/(i+1));
            }
        }
    }

    for(i = 0; i < nominalFeatures; i++){
        free(nominalModes[i].counts);
    }
    free(nominalModes);

    return averageInstance;
}

#pragma region lameTests
// utility functions
void freeInstance ( Instance* i ) {
    free(i->features);
    free(i->featureTypes);
    free(i);
}
void freeInstanceContents ( Instance* i ) {
    free(i->features);
    free(i->featureTypes);
}

// tests of computeEuclideanDissimilarity
bool eucDis0() {
    Instance
    * a = (Instance *)malloc(sizeof(Instance)),
    * b = (Instance *)malloc(sizeof(Instance));
    
    a->featureTypes = malloc(1*sizeof(FeatureType));
    a->features = malloc(1*sizeof(float));
    a->length = 0;

    b->featureTypes = malloc(1*sizeof(double));
    b->features = malloc(1*sizeof(float));
    b->length = 0;

    bool toret = computeEuclideanDissimilarity(a, b) == -1;
    
    freeInstance(a);
    freeInstance(b);

    return toret;
}

bool eucDis1() {
    Instance * a, *  b;
    a = (Instance *)malloc(sizeof(Instance));
    b = (Instance *)malloc(sizeof(Instance));
    
    a->featureTypes = malloc(sizeof(FeatureType));
    a->features = malloc(sizeof(float));
    a->length = 1;

    b->featureTypes = malloc(1*sizeof(double));
    b->features = malloc(1*sizeof(float));
    b->length = 2;

    bool toret = computeEuclideanDissimilarity(a, b) == -2;

    freeInstance(a);
    freeInstance(b);

    return toret;
}

bool eucDis2() {
    int
        length = 4,
        i;

    Instance
    * a = (Instance *)malloc(sizeof(Instance)),
    * b = (Instance *)malloc(sizeof(Instance));
    
    a->featureTypes = calloc(length, sizeof(FeatureType));
    a->features = calloc(length, sizeof(double));
    a->length = length;

    b->featureTypes = calloc(length, sizeof(FeatureType));
    b->features = calloc(length, sizeof(double));
    b->length = length;

    for ( i = 0; i < length; i++ ) {
        a->featureTypes[i] =  numerical;
        a->features[i] = 1.0;
    }

    for ( i = 0; i < length; i++ ) {
        b->featureTypes[i] =  nominal;
        b->features[i] = 1.0;
    }

    bool toret = computeEuclideanDissimilarity(a, b) == -3;

    freeInstance(a);
    freeInstance(b);

    return toret;
}

bool eucDis3() {
    int length = 4, i;

    Instance
    * a = (Instance *)malloc(sizeof(Instance));
    
    a->featureTypes = malloc(length*sizeof(FeatureType));
    a->features = calloc(length, sizeof(double));
    a->length = length;

    for ( i = 0; i < length; i++ ) {
        a->featureTypes[i] =  (i < length/2 ) ? numerical : nominal;
        a->features[i] = 1.0;
    }

    bool toret = computeEuclideanDissimilarity(a, a) == 0.0;

    freeInstance(a);

    return toret;
}

// tests of findNearestNeighbor
bool fnn0 () {
    int arrLength = 1, instanceLength = 1, i;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength ;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
    }
    
    Instance * query = (Instance *)malloc(sizeof(Instance));
    query->features = calloc(1, sizeof(double));
    query->featureTypes = calloc(1, sizeof(FeatureType));
    query->length = 0;

    const Instance * result = findNearestNeighbor(
        instances,
        arrLength,
        query,
        computeEuclideanDissimilarity
    );

    freeInstance(query);
    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)result == -1;
}

bool fnn1 () {
    int arrLength = 3, instanceLength = 1, i;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength ;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
    }
    
    Instance * query = (Instance *)malloc(sizeof(Instance));
    query->features = calloc(1, sizeof(double));
    query->featureTypes = calloc(1, sizeof(FeatureType));
    query->length = 5;

    const Instance * result = findNearestNeighbor(
        instances,
        arrLength,
        query,
        computeEuclideanDissimilarity
    );

    freeInstance(query);
    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)result == -2;
}

bool fnn2 () {
    int arrLength = 5, instanceLength = 5, i;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength ;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
    }
    
    Instance * query = (Instance *)malloc(sizeof(Instance));
    query->features = calloc(instanceLength, sizeof(double));
    query->featureTypes = calloc(instanceLength, sizeof(FeatureType));
    query->length = instanceLength;
    for ( i = 0; i < instanceLength; i++ ) {
        query->featureTypes[i] = nominal;
    }

    const Instance * result = findNearestNeighbor(
        instances,
        arrLength,
        query,
        computeEuclideanDissimilarity
    );

    freeInstance(query);
    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)result == -3;
}

bool fnn3 () {
    int arrLength = 10, instanceLength = 5, i, j;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength ;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
        for ( j = 0; j < instanceLength; j++ ) {
            instances[i].features[j] = 10.0 - i;
            instances[i].featureTypes[j] = (j < 2) ? numerical : nominal;
        }
    }
    
    Instance * query = (Instance *)malloc(sizeof(Instance));
    query->features = calloc(instanceLength, sizeof(double));
    query->featureTypes = calloc(instanceLength, sizeof(FeatureType));
    query->length = instanceLength;

    for ( j = 0; j < instanceLength; j++ ) {
        query->featureTypes[j] = (j < 2) ? numerical : nominal;
    }

    const Instance * result = findNearestNeighbor(
        instances,
        arrLength,
        query,
        computeEuclideanDissimilarity
    );

    freeInstance(query);
    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return ((long long int)result - (long long int)instances)/sizeof(Instance) == 9;
}

#pragma endregion lameTests

// tests of averageDataset
bool avgDst0 () {
    int arrLength = 10, instanceLength = 5, i, j;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength ;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
        for ( j = 0; j < instanceLength; j++ ) {
            instances[i].features[j] = 10.0 - i;
            instances[i].featureTypes[j] = (j < 2) ? numerical : nominal;
        }
    }

    const Instance * average = averageDataset( instances, 0 );

    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)average == -1;
}

bool avgDst1 () {
    int arrLength = 10, instanceLength = 5, i, j;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = i < 1 ? instanceLength : instanceLength -1;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
        for ( j = 0; j < instanceLength; j++ ) {
            instances[i].features[j] = 10.0 - i;
            instances[i].featureTypes[j] = (j < 2) ? numerical : nominal;
        }
    }

    const Instance * average = averageDataset( instances, arrLength );

    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)average == -2;
}

bool avgDst2 () {
    int arrLength = 10, instanceLength = 5, i, j;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
        for ( j = 0; j < instanceLength; j++ ) {
            instances[i].features[j] = 10.0 - i;
            instances[i].featureTypes[j] = (j < 2) ? numerical : nominal;
        }
    }
    instances[2].featureTypes[2] = numerical;

    const Instance * average = averageDataset( instances, arrLength );

    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    return (long long int)average == -3;
}

void avgDst3 () {
    int arrLength = 11, instanceLength = 4, i, j;
    Instance * instances = (Instance *)malloc(arrLength * sizeof(Instance));

    for ( i = 0; i < arrLength; i++ ) {
        instances[i].length = instanceLength;
        instances[i].featureTypes = calloc(instanceLength, sizeof(FeatureType));
        instances[i].features = calloc(instanceLength, sizeof(double));
        for ( j = 0; j < instanceLength; j++ ) {
            instances[i].features[j] = (j == 3 || j == 1) && i == 2 ? 3.0 : 10.0-i;
            instances[i].featureTypes[j] = (j < 2) ? numerical : nominal;
        }
    }

    const Instance * average = averageDataset( instances, arrLength );

    for ( i = 0; i < arrLength; i++ ) {
        freeInstanceContents(&instances[i]);
    }
    free(instances);

    for ( j = 0; j < instanceLength; j++ ) {
            printf("%lf ", average->features[j]);
    }
    printf("\n");

    freeInstance((Instance *)average);

}

int main() {
    printf("ComputeEuclideanDis---\n");
    printf("%s\n", eucDis0() ? "true" : "false" );
    printf("%s\n", eucDis1() ? "true" : "false" );
    printf("%s\n", eucDis2() ? "true" : "false" );
    printf("%s\n", eucDis3() ? "true" : "false" );

    printf("\nFNN---\n");
    printf("%s\n", fnn0() ? "true" : "false" );
    printf("%s\n", fnn1() ? "true" : "false" );
    printf("%s\n", fnn2() ? "true" : "false" );
    printf("%s\n", fnn3() ? "true" : "false" );

    printf("\nAverageDataset---\n");
    printf("%s\n", avgDst0() ? "true" : "false" );
    printf("%s\n", avgDst1() ? "true" : "false" );
    printf("%s\n", avgDst2() ? "true" : "false" );
    avgDst3();
    return 0;
}
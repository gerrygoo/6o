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
    double sum;
    for (i = 0; i < a->length; i++){
        if ( a->featureTypes[i] != b->featureTypes[i] ) return -3;
        sum += a->featureTypes[i] == numerical ?
            ( a->features[i] - b->features[i] ) 
            * ( a->features[i] - b->features[i] ) :
            !( a->features[i] == b->features[i] );
    }
    return sqrt(sum);
}

const Instance* findNearestNeighbor(
        const Instance* instances,
        int length,
        const Instance * query,
        double (*computeDissimilarity) (const Instance* a, const Instance* b)
){
    int i, min_i;
    double min = -1.0, cur;
    for(i = 0; i < length; i++){
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

    Instance* averageInstance = (Instance*)malloc(sizeof(Instance));

    averageInstance->features = (double*) calloc(features, sizeof(double));
    averageInstance->featureTypes = (FeatureType *)malloc(features * sizeof(FeatureType));
    
    int i, j, numericalFeatures = 0, nominalFeatures = 0;
    for(i = 0; i < length; i++) {
        averageInstance->featureTypes[i] = instances[0].featureTypes[i];
        if (instances[0].featureTypes[i] == nominal) nominalFeatures++;
        else numericalFeatures++; 
    }

    NFT*  nominalModes = malloc(nominalFeatures * sizeof(NFT));
    for(i = 0; i < length; i++){
        nominalModes[i].current_max = 0;
        nominalModes[i].counts = calloc(length, sizeof(int));
    }

    int currentNominalIdx = 0, valueOfCurrentNominal;
    for(i = 0; i < length; i++){
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
                    ( instances[i].features[j] - averageInstance->features[j] )/i+1;
            }
        }
    }

    return averageInstance;
}


// tests of computeEuclideanDissimilarity
bool eucDis0() {
    Instance 
        a = { },
        b = { .length = 1 };
    return computeEuclideanDissimilarity(&a, &b) == -1;
}
bool eucDis1() {
    Instance 
        a = { .length = 2 },
        b = { .length = 1 };
    return computeEuclideanDissimilarity(&a, &b) == -2;
}
bool eucDis2() {
    
}
bool eucDis3() {
    
}


int main(){ return 0; }
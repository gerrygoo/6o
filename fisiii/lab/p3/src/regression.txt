from math import sqrt
"""Regressions module.

The file includes functions for calculating
a linear regression of two sets of data of the same length.
Also their correlation coefficient and an estimating function.
"""
def avg(l): return sum(l)/len(l)

def lin_reg(x, y):
    """Calculates values b_0 and b_1 of regression of x and y.
    Args:
        x, y (iterable): The data for the regression.
    Returns:
        A tuple of the form: (b_0, b_1)
    """
    n = len(x)
    b_1 = ( 
        ( sum( [ i[0]*i[1] for i in zip(x,y) ] ) - ( n * avg(x) * avg(y) ) )
        /( sum( [ i*i for i in x ] ) - ( n * avg( x )**2 ) )
    )
    b_0 = avg(y) - ( b_1 * avg(x) )

    return (b_0, b_1)

def cor_coef(x, y):
    """Calculates values r_xy and its square.
    Args:
        x, y (iterable): The data for the calculation.
    Returns:
        A tuple of the form:  (r_xy, r_xy * r_xy)
    """
    n = len(x)
    r_xy = (
        ( ( n * sum([i[0]*i[1] for i in zip(x,y)]) ) - ( sum(x) * sum(y) ) )
        /sqrt( 
            (( n * sum( [ i*i for i in x ] ) ) - ( sum(x)**2 ) )
            * (( n * sum( [ i*i for i in y ] ) ) - ( sum(y)**2 ) )
        )
    )
    return (r_xy, r_xy * r_xy)

def y_k(x_k, x, y): 
    b_0, b_1 = lin_reg(x, y)
    return b_0 + ( b_1 *x_k )


"""Student's T using simpsons rule.
use t_simpsons function.
"""
import math
import functools

round_half = lambda _n: round(_n * 2) / 2

@functools.lru_cache(maxsize=1<<50)
def students_t(x, dof):
    return (
        ( math.gamma( (dof+1) / 2 ) * (( 1+( (x**2)/dof ) ) ** (-1*(dof+1)/2)) )
        / 
        ( ((dof*math.pi)**(1/2)) * math.gamma(dof/2) )
    )

@functools.lru_cache(maxsize=1<<50)
def t_simpsons(x, dof, f=students_t, E=0.00000001):
    """Approximate T distribution values.
    Args:
        x: the end of the interval for integration.
        dof: degrees of freedom for t distribution.
        f: (optional) function for other integrations.
        E: (optional) error goal.
    Returns:
        The resulting area for the t distribution curve.
    """
    if x == 0: return 0

    def p_eq(_F, _x, _W, _dof, _num_seg):
        return ((_W/3.0)
        *(
            _F(0, _dof) 
            + sum( [ (4*_F(i*W, _dof)) for i in range(1, _num_seg, 2) ] )
            + sum( [ (2*_F(i*W, _dof)) for i in range(2, _num_seg-1, 2) ] )
            + _F(_x, _dof)
        ))


    num_seg = 10
    W = x/num_seg
    p = p_prev = 0

    error = math.inf

    while error > E:
        p_prev = p

        num_seg *= 2; W /= 2

        p = p_eq(f, x, W, dof, num_seg)
        error = abs(p-p_prev)

    return p

def t_search(area, dof, threshold=0.0000000000000000000001):
    d = 0.5
    x = 1 + ( d if t_simpsons(1, dof) < area else -d )
    
    p_error = error = area - t_simpsons(x, dof)

    while abs(error) > threshold:
        error = area - t_simpsons(x, dof)

        if error * p_error < 0: d /= -2

        x += d
        p_error = error
    
    return x

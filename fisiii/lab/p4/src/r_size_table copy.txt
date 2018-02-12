"""Relative sizes table.
based on standard deviation

use r_size_table function.
"""
from math import log, exp, sqrt

avg = lambda x: sum(x)/len(x)
σ = lambda x, average: sqrt( sum( [ (i-average)**2 for i in x ] ) /(len(x)-1) )

def log_ranges( average, s ):
    return {
        "VS": round( exp(average - (2 * s)), 4),
        "S": round( exp(average - s), 4),
        "M": round( exp(average), 4),
        "L": round( exp(average + s), 4),
        "VL": round( exp(average + (2 * s)), 4 )
    }

def l_normally_transform(data):
    """Perform log-normal transformation.
    Args:
        data (iterable): The data for the calculation.
    Returns:
        A tuple of the form:  (avg, σ)
    """
    logs = [ log(i) for i in data]
    average = avg( logs )
    return (
        average,
        σ(logs, average)
    )

def r_size_table(data):
    a, b = l_normally_transform(data)
    return log_ranges(a, b)



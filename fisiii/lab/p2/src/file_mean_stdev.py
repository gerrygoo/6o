"""Mean value and Standard Deviation from File.

Program performs the calculations using an 
internally-defined linked list.

use mean_stdev function.
"""

from math import sqrt

class ListNode(object):
    """Node of a single-linked list.
    
    Attributes:
        val (any): The value that the node holds.
        next (ListNode): The next node in the list. 
            None if node is tail.
    """
    def __init__(self, val=None):
        self.val = val
        self.next = None


def mean_stdev(filePath):
    """Calculates mean value and stdev from a file.

    File should contain one number 
    at the beginning of the line.
    one per line. 

    Args:
        filePath (str): The absolute or relative
            path to the file to process.
    
    Returns:
        A tuple of the form: (average, stdev)
        Values are rounded to 2 decimal places.
    """
    listt = ListNode()
    head = listt
    
    N = 0
    with open(filePath) as f:
        for _line in f:
            listt.next = ListNode()
            listt.next.val = float( _line.rstrip("\n") )
            listt = listt.next
            N += 1
    
    #implementing online average algorithm
    n, avg, cur  = 1, 0, head.next
    while(True):
        if cur is None: break
        avg = avg + (cur.val - avg)/n
        n += 1
        cur = cur.next

    cur, sm = head.next, 0
    while(True):
        if cur is None: break
        sm += (cur.val - avg)**2
        cur = cur.next
    
    stdev = sqrt( sm/(N-1) )

    return ( round(avg, 2), round(stdev, 2) )

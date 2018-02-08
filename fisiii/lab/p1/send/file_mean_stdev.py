from math import sqrt

class ListNode:
    def __init__(self, val=None):
        self.val = val
        self.next = None

def calculate(filePath):        
    l = ListNode()
    head = l
    
    N = 0
    with open(filePath) as f:
        for _line in f:
            l.next = ListNode()
            l.next.val = float( _line.rstrip("\n") )
            l = l.next
            N += 1
    

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


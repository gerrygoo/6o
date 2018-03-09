    

def next_date(d, m, y):
    errors = ""
    if not (1 <= m <= 12):
        errors += ("Value of month not in range 1..12")
    
    if not (1 <= d <= 31):
        if len(errors): return "Invalid Input Date"
        errors += ("Value of day not in range 1..31")
    
    if not (1812 <= y <= 2012):
        if len(errors): return "Invalid Input Date"
        return "Value of year not in range 1812..2012"
    
    is_bis = lambda _y: _y % 100 != 0 and _y % 4 == 0 or _y % 400 == 0
    
    month_days = {
        1: 31,
        2: 28,
        3: 31,
        4: 30,
        5: 31,
        6: 30,
        7: 31,
        8: 31,
        9: 30,
        10: 31,
        11: 30,
        12: 30
    }

    
    if is_bis(y) : month_days[2] += 1
        
    if d > month_days[m]: raise "Value of day > days in month"
    
    if d == month_days[d]:
        if m == 12:
            y += 1
            m = 1
            d = 1
        else:
            m += 1
            d = 1
    else:x1
        d += 1

    
    month_days[2] = 28
    return ( d, m, y )


print(next_date(10, 10, 1997))

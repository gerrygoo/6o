from r_size_table import r_size_table # <- I IMPORT MY PROGRAM
# THE FOLLOWING ARE TWO (2) TESTS

# I CALL MY PROGRAM ON THE FOLLOWING DATA:
loc_per_method = [18/3,18/3,25/3,31/3,37/3,82/4,82/5,87/4,89/4,230/10,85/3,87/3,558/10]
pages_per_chapter = [7, 12, 10, 12, 10, 12, 12, 12, 12, 8, 8, 8, 20, 14, 18, 12]

# TWO (2) TESTS:

# TEST THE FIRST (1):
res = r_size_table( loc_per_method )
print( "LOC/Method: ", res )

# TEST THE SECOND (2):
res = r_size_table( pages_per_chapter )
print( "Pgs/Chapter: ", res )
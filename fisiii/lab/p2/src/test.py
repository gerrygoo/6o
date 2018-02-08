from  python_counter import count_LOC # <- I IMPORT MY PROGRAM

# THE FOLLOWING ARE THREE TESTS OF THE PROGRAM
# I CALL MY FUNCTION ON THREE DIFFERENT
# PYTHON SOURCE CODE FILES

#TEST NUMBER ONE 
print( "TESTS NUMBER ONE: ", 
    count_LOC('./python_counter.py')[0],"lines in total;    functions:\n",
    ", ".join( str(i) for i in count_LOC('./python_counter.py')[1].items() ),
    "\n"
)

#TEST NUMBER TWO
print( "TESTS NUMBER TWO: ", 
    count_LOC('./file_mean_stdev.py')[0], "in total;   functions:\n",
    ", ".join( str(i) for i in count_LOC('./file_mean_stdev.py')[1].items() ),
    "\n"
)

#TEST NUMBER THREE
print( "TESTS NUMBER THEE: ", 
    count_LOC('./test.py')[0], "in total;  functions:\n",
    ", ".join( str(i) for i in count_LOC('./test.py')[1].items() ),
    "\n"
)

from os import listdir
from os.path import isfile, join
interested = filter(lambda x: ".c" in x , [f for f in listdir(".") if isfile(join(".", f))] )

all = ""

for i in interested:
    s = str(i)

    all +=  "// "+ s + "------------------------------------------------------" + "\n"
    
    with open(s) as f:
        data = f.read()

    all += data + "\n"

print(all)


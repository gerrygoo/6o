print("#include<stdio.h>")
print("#include<string.h>")
print()

print("char str[] = ", end='')
with open("cheat") as f:
    data = f.read()

print("{", end='')
for c in data[:-1]:
    print(ord(c), end=",")

print(ord(data[-1]), end='')
print("};")

print("int main(){")
print("""\tprintf("%s", str);""")
print("\treturn 0;\n}")
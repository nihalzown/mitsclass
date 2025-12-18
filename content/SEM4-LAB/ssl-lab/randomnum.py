# Aim: Program to perform random number generation (Random Number Generation).
seed = 1234
a=1664525
c=1013904223
m=2**32
def random():
    global seed
    seed = (a*seed+c)%m
    return seed
for i in range(10):
    print(random())
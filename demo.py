import numpy as np

def g(x):
    return np.cos(x)/2

toleranse = 1e-5
x0 = 0
x = g(x0)

diff = abs(x0-x)
print(diff)

c = 1000
while (diff > 1e5) and (c > 0):
    c -= 1
    old_x = x
    x = g(x)
    diff = abs(old_x-x)
    print(x)

# COmment added

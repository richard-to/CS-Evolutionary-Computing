from math import e, pi, sin
from random import gauss, seed, uniform

def calcFitness(x1, x2):
    return 21.5 + x1 * sin(4.0 * pi * x1) + x2 * sin(20.0 * pi * x2)

def checkRange(x, min, max):
    return min <= x <= max

def createGeneration0(mu):

def selectSurvivors(parents, offspring):
    return parents

# gene = [x1, x2, o1, o2]
# gauss(mu, sigma)
# uniform(a, b)

if __name__ == '__main__':

numParents = 3
numOffspring = 21

# create x(3) parents (values with default sigma of 1)


# create y(21) children (intermediate and global)
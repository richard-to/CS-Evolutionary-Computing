from math import e, pi, sin
from random import gauss, sample, seed, uniform

def fitness(x1, x2):
    return 21.5 + x1 * sin(4.0 * pi * x1) + x2 * sin(20.0 * pi * x2)

def checkRange(x, min, max):
    return min <= x <= max

def createGeneration0(numParents, x1Range, x2Range, initialSigma):
    return [[uniform(*x1Range), uniform(*x2Range), initialSigma, initialSigma] for i in range(numParents)]

def discreteGlobalRecombination(numOffspring, parents):
    offspring = []
    parentIndexes = range(len(parents))
    for i in range(numOffspring):
        child = []
        for i in range(len(parents[0])):
            selectedParents = sample(parentIndexes, 2)
            chosenParent = sample(selectedParents, 1)
            child.append(parents[chosenParent[0]][i])
        offspring.append(child)
    return offspring

def surviorSelection(parents, offspring):
    return parents

# gene = [x1, x2, o1, o2]
# gauss(mu, sigma)
# uniform(a, b)

if __name__ == '__main__':

    numParents = 3
    numOffspring = 21
    x1Range = (-3, 12)
    x2Range = (4, 6)
    initialSigma = 1.0

    parents = createGeneration0(numParents, x1Range, x2Range, initialSigma)
    print parents

    print discreteGlobalRecombination(numOffspring, parents)

    # create x(3) parents (values with default sigma of 1)


    # create y(21) children (intermediate and global)
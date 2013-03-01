from math import e, pi, pow, sin, sqrt
from random import gauss, sample, seed, uniform

def fitness(x1, x2):
    return 21.5 + x1 * sin(4.0 * pi * x1) + x2 * sin(20.0 * pi * x2)

def overallLearningRate(n):
    return 1 / sqrt(2 * n)

def specificLearningRate(n):
    return 1 / sqrt(2 * sqrt(n))

def checkRange(x, min, max):
    return min <= x <= max

def createGeneration0(numParents, x1Range, x2Range, initialSigma):
    return [[uniform(*x1Range), uniform(*x2Range), initialSigma, initialSigma] for i in range(numParents)]

def recombination(parents, numVariables, numOffspring, vRecombination, sRecombination):
    vOffspring = vRecombination(parents, numOffspring, numVariables)
    sOffspring = sRecombination(parents, numOffspring, numVariables, numVariables)
    return [vOffspring[i] + sOffspring[i] for i in range(numOffspring)]

def discreteGlobalRecombination(parents, numOffspring, numVariables, offset=0):
    offspring = []
    parentIndexes = range(len(parents))
    for i in range(numOffspring):
        child = []
        for i in range(numVariables):
            selectedParents = sample(parentIndexes, 2)
            chosenParent = sample(selectedParents, 1)[0]
            child.append(parents[chosenParent][i + offset])
        offspring.append(child)
    return offspring

def intermediateGlobalRecombination(parents, numOffspring, numVariables, offset=0):
    offspring = []
    parentIndexes = range(len(parents))
    for i in range(numOffspring):
        child = []
        for i in range(numVariables):
            selectedParents = sample(parentIndexes, 2)
            newValue = (parents[selectedParents[0]][i + offset] + parents[selectedParents[1]][i + offset]) / 2.0
            child.append(newValue)
        offspring.append(child)
    return offspring

def mutation(offspring, stepSize, numVariables):
    overall = overallLearningRate(stepSize) * gauss(0, 1)
    sMutatedOffspringList = []
    t = []
    for child in offspring:
        sMutatedOffspring = []
        for i in range(numVariables):
            sMutatedOffspring.append(child[i + numVariables] * e**(overall + specificLearningRate(stepSize) * gauss(0, 1)))
        sMutatedOffspringList.append(sMutatedOffspring)

    for i in range(len(offspring)):
        tc = []
        for v in range(numVariables):
            tc.append(offspring[i][v] + sMutatedOffspringList[i][v] * gauss(0, 1))
        t.append(tc)

    return [t[i] + sMutatedOffspringList[i] for i in range(len(offspring))]

def surviorSelection(parents, offspring):
    return parents

if __name__ == '__main__':

    numParents = 3
    numOffspring = 21
    numVariables = 2
    x1Range = (-3, 12)
    x2Range = (4, 6)
    initialSigma = 1.0
    terminationCount = 10

    parents = createGeneration0(numParents, x1Range, x2Range, initialSigma)
    print parents

    offspring = recombination(parents, numVariables, numOffspring, 
        discreteGlobalRecombination, intermediateGlobalRecombination)
    print offspring

    mutatedOffspring = mutation(offspring, 2, numVariables)
    print mutatedOffspring
    # create x(3) parents (values with default sigma of 1)


    # create y(21) children (intermediate and global)
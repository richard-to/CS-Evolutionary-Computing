#!/usr/bin/env python

"""
Main program for PA2

This program uses evolutionary strategies algorithm to 
maximize the following function:

f(x1, x2) = 21.5 + x1 * sin(4 * pi * x1) + x2 * sin(20 * pi * x2)
"""


import random

import es


"""
Logging Settings

Currently only one logging setting is available. Specify None, to
turn off logging for specific value.

sample: Sample best of current generation every x generations.
"""
log = {
    'sample': None
}

"""
ES Settings

seed: If you don't want to use a specific seed, use None
numParents: Number of parents per generation
numOffspring: Number of offspring per generation
initialSigma: Starting sigma value. Defaults to 1.0
maxGenerations: Maximum number of generations to run
fitness: Fitness class for function to solve. In our case PA2Fitness
vRecombination: The recombination algorithm to use on function variables
sRecombination: The recombination algorithm to to use on step values
survivorSelection: Either (mu, lambda) or (mu + lambda) to select survivor
""" 
seed = 30340934
numParents = 3
numOffspring = 500
initialSigma = 1.0
maxGenerations = 500
fitness = es.PA2Fitness()
vRecombination = es.intermediateGlobalRecombination
sRecombination = es.intermediateGlobalRecombination
survivorSelection = es.survivorSelectionOffspring


"""
ES algorithm implementation for PA2

1. Randomly generate parents for generation 0 using uniform distribution for x1, x2 variables. Sigma = 1.0
2. Save the current best parent in generation 0
3. Do recombination on parents to create offspring
    - Use discrete global recombination on variables
    - Use intermediate global recombination on steps/sigmas
4. Mutate offspring
5. Use death penalty approach to weed out offspring that do not meet constraints
6. Create more offspring using steps 3, 4, and 5 until desired number of offspring reached
7. Do survivor selection using (mu, lambda) approach
8. Compare best of current generation with best overall. If current is better, make it the best overall
9. Repeat until maxGenerations reached
"""
random.seed(seed)

parents = es.createGeneration0(numParents, fitness, initialSigma, random.uniform)
parents = es.rank(parents, len(parents), fitness)
currentBest = parents[0]

for i in xrange(maxGenerations):
    validOffspring = []
    offspringNeeded = numOffspring
    while offspringNeeded > 0:
        offspring = es.recombination(parents, offspringNeeded, vRecombination, sRecombination, random.sample)      
        mutatedOffspring = es.mutation(offspring, random.gauss)
        validOffspring.extend(es.deathPenalty(mutatedOffspring, fitness))
        offspringNeeded = numOffspring - len(validOffspring)
    parents = survivorSelection(parents, validOffspring, fitness)
    currentBest = es.compareBestFitness(currentBest, parents[0], fitness)

    if log['sample'] is not None  and i % log['sample'] == 0:
        print "Generation {}:".format(i),
        print parents[0]
        print "Fitness = {}\n".format(fitness.calculate(*parents[0]))

print "Best x values: ",
print currentBest
print "Fitness = {}".format(fitness.calculate(*currentBest))
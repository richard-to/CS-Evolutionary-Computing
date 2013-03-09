import es

numParents = 3
numOffspring = 21
initialSigma = 1.0
terminationCount = 10000

fitness = es.PA2Fitness()

vRecombination = es.discreteGlobalRecombination
sRecombination = es.intermediateGlobalRecombination
survivorSelection = es.survivorSelectionOffspring

parents = es.createGeneration0(numParents, fitness, initialSigma)
parents = es.rank(parents, len(parents), fitness)
currentBest = parents[0]

for i in xrange(terminationCount):
    validOffspring = []
    offspringNeeded = numOffspring
    while offspringNeeded > 0:
        offspring = es.recombination(parents, offspringNeeded, vRecombination, sRecombination)      
        mutatedOffspring = es.mutation(offspring)
        validOffspring.extend(es.deathPenalty(mutatedOffspring, fitness))
        offspringNeeded = numOffspring - len(validOffspring)
    parents = survivorSelection(parents, validOffspring, fitness)
    currentBest = es.compareBestFitness(currentBest, parents[0], fitness)

print currentBest
print parents
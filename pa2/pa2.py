from math import e, pi, pow, sin, sqrt
from random import gauss, sample, seed, uniform


def transpose(matrix):
    """
    Helper function to transposes a matrix. In our case, the matrix is just a 2d list.

    Args:
        matrix: A 2d list

    Returns:
        A transposed 2d list
    """
    return map(list, zip(*matrix))


def sliceMatrix(matrix, index):
    """
    Helper function to split a matrix vertically. This is specifically used to 
    separate sigma step sizes and variable values.

    Args:
        matrix: A 2d list
        index: Index to slice on

    Returns:
        Two 2d lists sliced at specified index
    """
    return [m[:index] for m in matrix], [m[index:] for m in matrix]


def checkRange(x, min, max):
    """
    Helper function to check that variables are within a range of values. Inclusive.
    In PA2, there are constraints on x1 and x2 that we need to enforce.

    Args:
        x: Value to check
        min: Minimum value. Inclusive
        max: Maximum value. Inclusive
    
    Returns:
        Whether x value falls within min and max values
    """
    return min <= x <= max


def rank(chromosomes, numVariables, top, fitness, max=True):
    """
    Ranks chromosomes based on fitness values and select the top x.

    Args:
        chromosomes: List of chromosomes to rank
        top: Number of chromosomes to select
        fitness: Fitness function used to evaluate chromosomes
        max: Is this maximization problem? Defaults to true. If not, then it is minimization.

    Returns:
        A list of the top chromosomes
    """
    return sorted(chromosomes, key=lambda c: fitness(*c[:numVariables]), reverse=max)[:top] 


def fitness(x1, x2):
    """
    Fitness function for PA2. This is a maximization problem, so 
    higher results are better.

    Args:
        x1: Variable 1
        x2: Variable 2
    
    Returns:
        A float/double representing fitness of x1 and x2
    """
    return 21.5 + x1 * sin(4.0 * pi * x1) + x2 * sin(20.0 * pi * x2)


def compareBestFitness(best, challenger, numVariables, fitness):
    """
    Compares fitness values. Expects maximization problem.

    Args:
        best: Current best chromosome 
        challenger: Best chromosome for currrent generation

    Returns:
        Chromsome with best fitness

    TODO: Doesn't handle minimization problems.
    """
    return best if fitness(*best[:numVariables]) > fitness(*challenger[:numVariables]) else challenger


def createGeneration0(numParents, variables, initialSigma=1.0):
    """
    Creates the initial population by generating chromosomes for the specified 
    number of parents.

    Args:
        numParents: Number of parents to generate
        variables: A tuple of min/max tuples specifying constraints for each variable.
        initialSigma: The starting sigma value defaults to one
    
    Returns:
        A list of randomly generated chromosomes
    """
    sigmaList = [initialSigma] * len(variables)
    return [[uniform(*v) for v in variables] + sigmaList for i in range(numParents)]


def recombination(parents, numVariables, numOffspring, vRecombination, sRecombination):
    """
    Recombination step for ES. Parents are recombined to create new offspring.

    Values are recombined separately from step values. This is to allow different 
    recombination algorithms to be used. 

    Currently only discrete global recombination and intermediate global recombination 
    are implemented.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes
        numVariables: Number variables or values that want to find
        numOffspring: The number of offspring to produce from parents
        vRecombination: Recombination variant for values
        sRecombination: Recombination variant for steps

    Returns:
        A list of chromosomes that consist of the offspring from recombined parents
    """
    values, steps = sliceMatrix(parents, numVariables)
    newValues = vRecombination(values, numOffspring)
    newSteps = sRecombination(steps, numOffspring)
    return [newValues[i] + newSteps[i] for i in range(numOffspring)]


def discreteGlobalRecombination(parents, numOffspring):
    """
    Performs discrete global recombination.

    For each value and or step in the chromosome, we randomly select two parents from
    the population. We then randomly choose an allele from the selected 
    parents.

    This is repeated until the specified number of offspring are created.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes 
        numOffspring: The number of offspring to produce from parents 
    
    Returns:
        Offspring recombined using discrete global recombination
    """
    return [[sample(sample(values, 2), 1)[0]
        for values in transpose(parents)] for i in range(numOffspring)]


def intermediateGlobalRecombination(parents, numOffspring):
    """
    Performs intermediate global recombination.

    For each value and or step in the chromosome, we randomly select two parents from
    the population and average out their allele values.

    This is repeated until the specified number of offspring are created.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes 
        numOffspring: The number of offspring to produce from parents 
    
    Returns:
        Offspring recombined using intermediate global recombination
    """    
    return [[sum(sample(values, 2)) / 2.0 
        for values in transpose(parents)] for i in range(numOffspring)]


def overallLearningRate(n):
    """
    Calculates the overall learning rate with step size n.
    Step size n is equal to problem size (I believe this is the number 
    of variables).

    Args:
        n: Step size
    
    Returns:
        Learning rate
    """
    return 1 / sqrt(2 * n)


def specificLearningRate(n):
    """
    Calculates the specific learning rate for each variable. Step size n 
    is used here also. The step size is equal to problem size.

    Args:
        n: Step size
    
    Returns:
        Learning rate for a specific variable
    """
    return 1 / sqrt(2 * sqrt(n))


def mutation(offspring, numVariables):
    """
    Performs mutation on offspring.

    First the overall learning rate is calculated - tau' * N(0,1).

    Next we need to calculate new step size values for each step size:
    sigma_i' = sigma_i * exp(tau' * N(0,1) + tau * N_i(0, 1))

    Once we have new sigma values, we can calculate new variable values using 
    this equation:

    alpha_i' = alpha_i + sigma_i' * N_i(0, 1)

    Finally we combine the alpha and sigma values into a 2d list

    Args:
        offspring: Offspring that will go through mutation
        numVariables: Number of variables. Also acts as step size.

    Returns:
        A 2d list of mutated chromosome offsprings
    """
    overall = overallLearningRate(numVariables) * gauss(0, 1)
    specific = specificLearningRate(numVariables)

    values, steps = sliceMatrix(offspring, numVariables)

    mutatedSteps = [[o * e**(overall + specific * gauss(0, 1))
        for o in s] for s in steps]

    mutatedValues = [[values[i][g] + mutatedSteps[i][g] * gauss(0, 1)
        for g in range(numVariables)] for i in range(len(values))]

    return [mutatedValues[i] + mutatedSteps[i] for i in range(len(offspring))]


def survivorSelectionOffspring(parents, offspring, numVariables, fitness, max=True):
    """
    Survivor selection using (mu, lambda). Offspring are ranked from best to worst based 
    on the specified fitness function.

    We select the best offspring until we populated the next generation, which is specified 
    by the number of the parents.

    Args:
        parents: A list of parent chromosomes
        offspring: A list of offspring chromosomes
        numVariables: Number of variables
        fitness: Used to evaluate fitness of a chromosome
        max: Defaults to true. This sorts the fitness values from highest to lowest. Descending order

    Returns:
        New parents selected from offspring ranked by fitness
    """
    return rank(offspring, numVariables, len(parents), fitness, max) 


def survivorSelectionWithParents(parents, offspring, numVariables, fitness, max=True):
    """
    Survivor selection using (mu + lambda). Offspring and parents are ranked from best to worst based 
    on the specified fitness function.

    We select the best parents and offspring until we populated the next generation, which is specified 
    by the number of the parents.

    Args:
        parents: A list of parent chromosomes
        offspring: A list of offspring chromosomes
        fitness: Used to evaluate fitness of a chromosome
        max: Defaults to true. This sorts the fitness values from highest to lowest. Descending order

    Returns:
        New parents selected from both parents and offspring ranked by fitness
    """
    return rank(parents + offspring, numVariables, len(parents), fitness, max)


if __name__ == '__main__':

    numParents = 3
    numOffspring = 21
    numVariables = 2
    x1Range = (-3, 12)
    x2Range = (4, 6)
    initialSigma = 1.0
    terminationCount = 10
    survivorSelection = survivorSelectionOffspring

    parents = createGeneration0(numParents, (x1Range, x2Range), initialSigma)
    parents = rank(parents, numVariables, len(parents), fitness)
    currentBest = parents[0]

    for i in xrange(terminationCount):
        currentBest = compareBestFitness(currentBest, parents[0], numVariables, fitness)
        offspring = recombination(parents, numVariables, numOffspring, 
            discreteGlobalRecombination, intermediateGlobalRecombination)      
        mutatedOffspring = mutation(offspring, numVariables) 
        parents = survivorSelection(parents, mutatedOffspring, numVariables, fitness)
    print parents
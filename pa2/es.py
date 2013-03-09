import random

from math import e, pi, pow, sin, sqrt

"""
Implementation of evolutionary strategies algorithm for PA2.
"""

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


def checkRange(x, *args):
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
    return True if (args[0] == None) else args[0] <= x <= args[1]


class PA2Fitness():
    """
    Models the fitness function for PA2.

    Other fitness classes can be created following the same interface.
    """

    def __init__(self):
        self._constraints = ((-3, 12), (4, 6))

    def constraints(self):
        """
        A tuple of tuples that specify constraints for variables.

        The size of the tuple should match the number of variables.

        Constraints are modeled by a min/max tuple.

        If there are no constraints, use None.

        Returns:
            A tuple of constraints for variables.
        
        Example 1:
            This tuple models the constraints for a function with 
            two variables.

            ((-3, 12), (4, 6))

            Variable 1 has the following constraints: -3 <= x <= 12
            Variable 2 has the following constraints: 4 <= x <= 6

        Example 2:
            This tuple models a function with two variables are no 
            constraints.

            (None, None)
        """
        return self._constraints

    def calculate(self, *args):
        """
        Fitness function for PA2. This is a maximization problem, so 
        higher results are better.

        Args:
            *args: Expects two variables. x1 and x2
        
        Returns:
            A float/double representing fitness of x1 and x2

        TODO: Make sure 2 args passed. If not, should throw an exception.
        """
        return 21.5 + args[0] * sin(4.0 * pi * args[0]) + args[1] * sin(20.0 * pi * args[1])

    def compare(self, c1, c2):
        """
        Compares fitness values. Expects maximization problem.

        Args:
            c1: Chromosome 1
            c2: Chromosome 2

        Returns:
            1 if c1 is more fit
            0 if c1 and c2 are equal
            -1 if c2 is more fit

        TODO: Doesn't handle minimization problems.
        """
        c1Fitness = self.calculate(*c1)
        c2Fitness = self.calculate(*c2)
        
        if c1Fitness > c2Fitness:
            return 1
        elif c1Fitness < c2Fitness:
            return -1
        else:
            return 0


def compareBestFitness(best, challenger, fitness):
    """
    Helper function to test which chromosome is more fit.

    Args:
        best: Current best chromosome 
        challenger: Best chromosome for currrent generation
        fitness: Uses compare method of fitness class
    Returns:
        Chromosome with best fitness
    """
    return best if fitness.compare(best, challenger) >= 0 else challenger


def rank(chromosomes, top, fitness):
    """
    Ranks chromosomes based on fitness values and select the top x.

    Args:
        chromosomes: List of chromosomes to rank
        top: Number of chromosomes to select
        fitness: Fitness class used to evaluate chromosomes

    Returns:
        A list of the top chromosomes
    """
    sortedChromosomes = sorted(chromosomes, cmp=fitness.compare)
    sortedChromosomes.reverse()
    return sortedChromosomes[:top]


def createGeneration0(numParents, fitness, initialSigma=1.0, uniform=random.uniform):
    """
    Creates the initial population by generating chromosomes for the specified 
    number of parents.

    Args:
        numParents: Number of parents to generate
        fitness: Uses the constraints method to check variable constraints
        uniform: See random.uniform() function python library.
        initialSigma: The starting sigma value defaults to one
    
    Returns:
        A list of randomly generated chromosomes
    """
    constraints = fitness.constraints()
    sigmaList = [initialSigma] * len(constraints)
    return [[uniform(*v) for v in constraints] + sigmaList for i in range(numParents)]


def recombination(parents, numOffspring, vRecombination, sRecombination, sample=random.sample):
    """
    Recombination step for ES. Parents are recombined to create new offspring.

    Values are recombined separately from step values. This is to allow different 
    recombination algorithms to be used. 

    Currently only discrete global recombination and intermediate global recombination 
    are implemented.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes
        numOffspring: The number of offspring to produce from parents
        vRecombination: Recombination variant for values
        sRecombination: Recombination variant for steps
        sample: See random.sample() function python library.

    Returns:
        A list of chromosomes that consist of the offspring from recombined parents
    """
    numVariables = len(parents[0])/2
    values, steps = sliceMatrix(parents, numVariables)
    newValues = vRecombination(values, numOffspring, sample)
    newSteps = sRecombination(steps, numOffspring, sample)
    return [newValues[i] + newSteps[i] for i in range(numOffspring)]


def discreteGlobalRecombination(parents, numOffspring, sample=random.sample):
    """
    Performs discrete global recombination.

    For each value and or step in the chromosome, we randomly select two parents from
    the population. We then randomly choose an allele from the selected 
    parents.

    This is repeated until the specified number of offspring are created.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes 
        numOffspring: The number of offspring to produce from parents 
        sample: See random.sample() function python library.

    Returns:
        Offspring recombined using discrete global recombination
    """
    return [[sample(sample(values, 2), 1)[0]
        for values in transpose(parents)] for i in range(numOffspring)]


def intermediateGlobalRecombination(parents, numOffspring, sample=random.sample):
    """
    Performs intermediate global recombination.

    For each value and or step in the chromosome, we randomly select two parents from
    the population and average out their allele values.

    This is repeated until the specified number of offspring are created.

    Args:
        parents: A list of chromosomes. Each row is a list of values followed by step sizes 
        numOffspring: The number of offspring to produce from parents 
        sample: See random.sample() function python library.

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


def mutation(offspring, gauss=random.gauss):
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
        gauss: See random.gauss() function python library.

    Returns:
        A 2d list of mutated chromosome offsprings
    """
    numVariables = len(offspring[0])/2

    overall = overallLearningRate(numVariables) * gauss(0, 1)
    specific = specificLearningRate(numVariables)

    values, steps = sliceMatrix(offspring, numVariables)

    mutatedSteps = [[o * e**(overall + specific * gauss(0, 1))
        for o in s] for s in steps]

    mutatedValues = [[values[i][g] + mutatedSteps[i][g] * gauss(0, 1)
        for g in range(numVariables)] for i in range(len(values))]

    return [mutatedValues[i] + mutatedSteps[i] for i in range(len(offspring))]


def survivorSelectionOffspring(parents, offspring, fitness):
    """
    Survivor selection using (mu, lambda). Offspring are ranked from best to worst based 
    on the specified fitness function.

    We select the best offspring until we populated the next generation, which is specified 
    by the number of the parents.

    Args:
        parents: A list of parent chromosomes
        offspring: A list of offspring chromosomes
        fitness: Used to evaluate fitness of a chromosome

    Returns:
        New parents selected from offspring ranked by fitness
    """
    return rank(offspring, len(parents), fitness) 


def survivorSelectionWithParents(parents, offspring, fitness):
    """
    Survivor selection using (mu + lambda). Offspring and parents are ranked from best to worst based 
    on the specified fitness function.

    We select the best parents and offspring until we populated the next generation, which is specified 
    by the number of the parents.

    Args:
        parents: A list of parent chromosomes
        offspring: A list of offspring chromosomes
        fitness: Used to evaluate fitness of a chromosome

    Returns:
        New parents selected from both parents and offspring ranked by fitness
    """
    return rank(parents + offspring, len(parents), fitness)


def deathPenalty(offspring, fitness):
    """
    Make sure offspring values meet constraints. Offspring that do not meet 
    constraints will be removed from list. We must then do recombination 
    again until lambda offspring are created.

    Args:
        offspring: List of chromosomes created through recombination and mutation
        fitness: Used to check constraints. Values must fall within specified constraints to be valid

    Returns:
        List of offspring that are valid
    """
    constraints = fitness.constraints()
    return filter(lambda o: all(checkRange(o[i], *constraints[i]) for i in range(len(constraints))), offspring)
import es
import unittest

"""
Tests for evolutionary strategies functions for PA2.
"""

class MockRandom():
    """
    Mock random class for testing
    """

    def __init__(self, sequence):
        self.sequence = sequence

    def gauss(self, mu, sigma):
        return self.sequence.pop()

    def uniform(self, a, b):
        return self.sequence.pop()

    def sample(self, population, k):
        return [population[self.sequence.pop()] for i in range(k)]


class MockFitness():
    """
    Mock fitness class for testing
    """

    def __init__(self, hasConstraints=True):
        self._constraints = [[0, 15]] if hasConstraints else [[None]]

    def constraints(self):
        return self._constraints

    def calculate(self, *args):
        return args[0]**2

    def compare(self, c1, c2):
        c1Fitness = self.calculate(*c1)
        c2Fitness = self.calculate(*c2)
        
        if c1Fitness > c2Fitness:
            return 1
        elif c1Fitness < c2Fitness:
            return -1
        else:
            return 0


class TestEs(unittest.TestCase):

    def testTranspose(self):
        matrix = [[1, 3, 5], [2, 4, 6]]
        self.assertEqual(es.transpose(es.transpose(matrix)), matrix)

    def testSliceMatrix(self):
        matrix = [[1, 3, 5], [2, 4, 6]]
        left, right = es.sliceMatrix(matrix, 2)
        self.assertEqual(left, [[1, 3], [2, 4]])
        self.assertEqual(right, [[5], [6]])

    def testCheckRange(self):
        range = [3, 6]
        self.assertTrue(es.checkRange(3, None))
        self.assertTrue(es.checkRange(3, *range))
        self.assertTrue(es.checkRange(6, *range))
        self.assertTrue(es.checkRange(4, *range))
        self.assertFalse(es.checkRange(8, *range))

    def testPA2Fitness(self):
        fitness = es.PA2Fitness()
        c1 = [1.0, 5.0, 1.0, 1.0]
        c2 = [3.0, 4.0, 1.0, 1.0]
        c1Fit = fitness.calculate(*c1)
        c2Fit = fitness.calculate(*c2)        
        self.assertAlmostEqual(c1Fit, 21.5, delta=0.001)
        self.assertAlmostEqual(c2Fit, 21.5, delta=0.001)
        self.assertEqual(fitness.compare(c1, c2), 1)
        self.assertEqual(es.compareBestFitness(c1, c2, fitness), c1)

    def testPA2Fitness2(self):
        fitness = es.PA2Fitness() 
        c1 = [1.23, 5.723]
        c2 = [6.4, 5.992]  
        c1Fit = fitness.calculate(*c1)
        c2Fit = fitness.calculate(*c2)
        self.assertEqual(fitness.compare(c1, c2), 1)
        self.assertEqual(fitness.compare(c2, c1), -1)
        self.assertEqual(es.compareBestFitness(c1, c2, fitness), c1)
        self.assertEqual(es.compareBestFitness(c2, c1, fitness), c1)

    def testMockFitness(self):
        fitness = MockFitness()
        self.assertEqual(4, fitness.calculate(2))

    def testRank(self):
        fitness = MockFitness()
        chromosomes = [[21], [2], [3], [19], [6]]
        topChromosomes = es.rank(chromosomes, 2, fitness)
        self.assertEqual(topChromosomes, [[21], [19]])

    def testCreateGeneration0(self):
        fitness = MockFitness()
        rand = MockRandom([2, 4, 5])
        chromosomes = es.createGeneration0(3, fitness, 1.0, rand.uniform)
        self.assertEqual(chromosomes, [[5, 1.0], [4, 1.0], [2, 1.0]])

    def testDiscreteGlobalRecombination(self):
        chromosomes = [[21], [2], [3], [19], [6]]
        rand = MockRandom([0, 3, 4, 1, 2, 0])
        offspring = es.discreteGlobalRecombination(chromosomes, 2, rand.sample)
        self.assertEqual(offspring, [[3], [6]])

    def testIntermediateGlobalRecombination(self):
        chromosomes = [[21], [2], [3], [19], [6]]
        rand = MockRandom([3, 4, 2, 0])
        offspring = es.intermediateGlobalRecombination(chromosomes, 2, rand.sample)
        self.assertEqual(offspring, [[12], [12.5]])

    def testRecombination(self):
        chromosomes = [[21, 21], [2, 2], [3, 3], [19, 19], [6, 6]]
        rand = MockRandom([3, 4, 2, 0, 0, 3, 4, 1, 2, 0])
        offspring = es.recombination(chromosomes, 2, es.discreteGlobalRecombination, 
            es.intermediateGlobalRecombination, rand.sample)
        self.assertEqual(offspring, [[3, 12], [6, 12.5]])

    def testSurvivorSelectionOffspring(self):
        fitness = MockFitness()
        parents = [[21], [2]]
        offspring = [[8], [23], [1]]
        topChromosomes = es.survivorSelectionOffspring(parents, offspring, fitness)
        self.assertEqual(topChromosomes, [[23], [8]])

    def testSurvivorSelectionWithParents(self):
        fitness = MockFitness()
        parents = [[21], [2]]
        offspring = [[8], [23], [1]]
        topChromosomes = es.survivorSelectionWithParents(parents, offspring, fitness)
        self.assertEqual(topChromosomes, [[23], [21]])

    def testDeathPenalty(self):
        fitness = MockFitness()
        offspring = [[8], [23], [1], [-1], [0], [15]]
        survivors = es.deathPenalty(offspring, fitness)
        self.assertEqual(survivors, [[8], [1], [0], [15]])

if __name__ == '__main__':
    unittest.main()
/**
 * Author: Richard To
 * Date: 1/30/13
 */

import to.richard.tsp.*;

import java.util.Comparator;
import java.util.List;

/**
 * Main class for to solve TSP problem using Genetic Algorithm.
 *
 * Recombination algorithms: PMX, OX, EX, CX
 */
public class Pa1 {

    /**
     * Main class
     * @param args
     */
    public static void main(String[] args) {

        int generations = 700;
        int populationSize = 20;
        int size = 5;
        int minPrice = 99;
        int maxPrice = 2000;
        int tournamentSize = 3;
        double probabilityCrossover = .9;
        double probabilityMutation = .1;

        int currentGeneration = 0;
        Random random = new Random();

        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(new Random(1));
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(size, minPrice, maxPrice);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);
        Comparator<Pair<Double, Genotype>> comparator = new FitnessMinimizationComparator();
        ISelector parentSelector = new TournamentSelector(
                tournamentSize, fitnessEvaluator, comparator, random);

        FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer(fitnessEvaluator, comparator);
        FitnessResult fitnessResult = null;

        // Crossover
        ICrossoverOperator crossoverOperator = new PartiallyMappedCrossover();
        Recombinator recombinator = new Recombinator(
                probabilityCrossover, crossoverOperator, random);

        // Mutation
        IMutationOperator mutationOperator = new InversionMutation();
        Mutator mutator = new Mutator(probabilityMutation, mutationOperator, random);

        // Survival
        ISurvivorSelector survivorSelector = new ElitismSurvivorSelection(fitnessEvaluator, comparator);

        // Gene pool Initialization
        GenePoolInitializer genePoolInitializer =
                new GenePoolInitializer(populationSize, costMatrix, random);
        List<Genotype> population = genePoolInitializer.initializeGenePool();

        fitnessResult = fitnessAnalyzer.analyze(population);
        System.out.println(fitnessResult);

        while (currentGeneration < generations) {
            // Parent Selection
            List<Genotype> parents = parentSelector.selectParents(population);

            // Crossover
            List<Genotype> offspring = recombinator.recombine(parents);

            // Mutation
            List<Genotype> mutatedOffspring = mutator.mutate(offspring);

            // Survival
            population = survivorSelector.replace(parents, mutatedOffspring);

            currentGeneration++;
        }

        fitnessResult = fitnessAnalyzer.analyze(population);
        System.out.println(fitnessResult);
    }
}

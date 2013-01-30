import to.richard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 1/27/13
 */

/**
 * Main application class
 */
public class Ex0 {

    public static int MAX_RUNS = 1;
    public static int MAX_VALUE = 32;
    public static int MAX_FITNESS = MAX_VALUE * MAX_VALUE;
    public static int MAX_POPULATION = 4;
    public static int MAX_PARENT_PAIRS = MAX_POPULATION / 2;
    public static double MUTATION_RATE = 0.1;

    public static void main(String[] args) {
        int runs = 1;
        int maxFitness = 0;

        FitnessResult fitnessResult = null;
        FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();

        Random random = new Random();
        GenePoolInitializer genePoolInitializer = new GenePoolInitializer(
                MAX_VALUE, MAX_POPULATION, random);
        GenePool genePool = genePoolInitializer.initializeGenePool();

        ParentSelector parentSelector = new ParentSelector(random);
        MatingPool matingPool = new MatingPool(random);
        GeneMutator geneMutator = new GeneMutator(random, MUTATION_RATE);

        List<GenotypeParents> genotypeParentsList = null;
        List<Genotype> genotypesList = genePool.getGenotypeList();

        while (runs <= MAX_RUNS && maxFitness != MAX_FITNESS) {
            printHeader(runs);

            printGenotypes(genotypesList, "Current Population");

            genotypeParentsList = parentSelector.selectParents(genePool, MAX_PARENT_PAIRS);

            genotypesList = matingPool.recombine(genotypeParentsList);
            printGenotypes(genotypesList, "Recombination");
            fitnessResult = fitnessAnalyzer.analyze(genotypesList);
            printFitness(fitnessResult);

            genotypesList = geneMutator.mutate(genotypesList);
            printGenotypes(genotypesList, "Mutation");
            fitnessResult = fitnessAnalyzer.analyze(genotypesList);
            printFitness(fitnessResult);

            runs++;
        }


    }

    /**
     * Prints generation header
     */
    public static void printHeader(int generationCount) {
        System.out.format("Generation: %d%n", generationCount);
        System.out.println("=================================");
        System.out.println();
    }
    /**
     * Prints genotype information
     *
     * @param genotypeList
     */
    public static void printGenotypes(List<Genotype> genotypeList, String label) {
        System.out.println(label);
        System.out.println("------------------------------------");
        for (Genotype genotype : genotypeList) {;
            System.out.format("Bit-String: %s, Value: %2d, Fitness: %4d%n",
                genotype.getGenesString(), genotype.getValue(), genotype.getFitness());
        }
        System.out.println();
    }

    /**
     * Prints generation fitness results.
     * @param result
     */
    public static void printFitness(FitnessResult result) {
        System.out.println("Fitness Results");
        System.out.println("-------------------------------");
        System.out.format("Sum: %.2f, Avg: %.2f, Max: %.2f%n",
            result.getSum(), result.getAvg(), result.getMax());
        System.out.println();
    }
}
/**
 * Author: Richard To
 * Date: 1/30/13
 */

import to.richard.tsp.Pair;
import to.richard.tsp.Random;
import to.richard.tsp.TSPSimulator;
import to.richard.tsp.TSPSimulator.*;

import java.util.ArrayList;

/**
 * Main class for to solve TSP problem using Genetic Algorithm.
 *
 * Recombination algorithms: PMX, OX, EX, CX
 */
public class Pa1 {

    /**
     * Lists of 50 largest cities by population plus Anchorage
     */
    public static final String[] cityNames = {
        "Anchorage",
        "New York",
        "Los Angeles",
        "Chicago",
        "Houston",
        "Philadelphia",
        "Phoenix",
        "San Antonio",
        "San Diego",
        "Dallas",
        "San Jose",
        "Jacksonville",
        "Indianapolis",
        "San Francisco",
        "Austin",
        "Columbus",
        "Fort Worth",
        "Charlotte",
        "Detroit",
        "El Paso",
        "Memphis",
        "Baltimore",
        "Boston",
        "Seattle",
        "Washington",
        "Nashville-Davidson",
        "Denver",
        "Louisville-Jefferson County",
        "Milwaukee",
        "Portland",
        "Las Vegas",
        "Oklahoma City",
        "Albuquerque",
        "Tucson",
        "Fresno",
        "Sacramento",
        "Long Beach",
        "Kansas City",
        "Mesa",
        "Virginia Beach",
        "Atlanta",
        "Colorado Springs",
        "Omaha",
        "Raleigh",
        "Miami",
        "Cleveland",
        "Tulsa",
        "Oakland",
        "Minneapolis",
        "Wichita",
        "Arlington"
    };

    /**
     * Main class
     * @param args
     */
    public static void main(String[] args) {

        /**
         * See TSPSimulator.Options class for default settings.
         */
        TSPSimulator.Options options = new TSPSimulator.Options();
        options.cityNames = cityNames;

        /**
         * Create a list of runs with parameters.
         * Integer is seed. If null, then let Random class pick a random seed based on time in ms.
         * CROSSOVER is the PX, EX, OX, or CX.
         */
        ArrayList<Pair<Integer, CROSSOVER>> runs = new ArrayList<Pair<Integer, CROSSOVER>>();
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.PMX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.PMX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.PMX));

        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.EX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.EX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.EX));

        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.OX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.OX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.OX));

        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.CX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.CX));
        runs.add(new Pair<Integer, CROSSOVER>(null, CROSSOVER.CX));

        TSPSimulator simulator = new TSPSimulator();

        int count = 1;

        for (Pair<Integer, CROSSOVER> run : runs) {
            options.random = (run.getFirstValue() != null) ? new Random(run.getFirstValue()) : new Random();
            options.crossover = run.getSecondValue();
            System.out.format("Run %d%n", count++);
            System.out.println("+++++++++++++++++++++++\n\n");
            simulator.simulate(options);
        }

    }
}

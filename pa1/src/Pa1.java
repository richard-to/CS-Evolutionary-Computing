/**
 * Author: Richard To
 * Date: 1/30/13
 */

import to.richard.tsp.TSPSimulator;
import to.richard.tsp.TSPSimulator.*;

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
        TSPSimulator.Options options = new TSPSimulator.Options();
        options.cityNames = cityNames;
        TSPSimulator simulator = new TSPSimulator();
        simulator.simulate(options);
    }
}

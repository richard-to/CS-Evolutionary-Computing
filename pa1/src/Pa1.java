/**
 * Author: Richard To
 * Date: 1/30/13
 */

import to.richard.tsp.*;

import java.util.ArrayList;
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
        TSPSimulator simulator = new TSPSimulator();
        simulator.simulate(new TSPSimulator.Options());
    }
}

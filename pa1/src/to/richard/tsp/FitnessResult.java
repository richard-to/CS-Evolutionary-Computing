package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Fitness results of the generation.
 */
public class FitnessResult {
    private double _sum;
    private double _avg;
    private double _best;

    public FitnessResult(double sum, double avg, double best) {
        _sum = sum;
        _avg = avg;
        _best = best;
    }

    public double getSum() {
        return _sum;
    }

    public double getAvg() {
        return _avg;
    }

    public double getBest() {
        return _best;
    }

    public String toString() {
        return String.format("Sum: %.2f, Avg: %.2f, Best: %.2f", _sum, _avg, _best);
    }
}
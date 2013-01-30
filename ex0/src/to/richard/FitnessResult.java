package to.richard;

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
    private double _max;

    public FitnessResult(double sum, double avg, double max) {
        _sum = sum;
        _avg = avg;
        _max = max;
    }

    public double getSum() {
        return _sum;
    }

    public double getAvg() {
        return _avg;
    }

    public double getMax() {
        return _max;
    }
}

package to.richard.tsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * Parent selection using tournament selection. This is a deterministic tournament
 * so probability that the best genotype will be selected is 1.0.
 *
 * Randomly select k genotypes. The one with best fitness is selected.
 * This is repeated until we have reached the specified population size.
 *
 * Population size is currently determined by the size of the genotype list
 * passed into the selectParents method.
 *
 * If tournament size less than 1 or greater than size of genotypes, an
 * error will be thrown.
 */
public class TournamentSelector implements ISelector {
    private IRandom _random;
    private int _tournamentSize;
    private Comparator<Pair<Double, Genotype>> _fitnessComparator;
    private FitnessEvaluator _fitnessEvaluator;

    public TournamentSelector(int tournamentSize, FitnessEvaluator fitnessEvaluator,
                              Comparator<Pair<Double, Genotype>> fitnessComparator, IRandom random) {

        if (tournamentSize < 1) {
            throw new Errors.TournamentSizeOutOfBounds("Minimum tournament size can't be less than 1.");
        }

        _tournamentSize = tournamentSize;
        _fitnessEvaluator = fitnessEvaluator;
        _fitnessComparator = fitnessComparator;
        _random = random;
    }

    public List<Genotype> selectParents(List<Genotype> genotypes) {

        if (_tournamentSize > genotypes.size()) {
            throw new Errors.TournamentSizeOutOfBounds("Maximum tournament size can't be greater than population.");
        }

        if (genotypes.size() == 1) {
            return genotypes;
        }

        int populationSize = genotypes.size();
        ArrayList<Genotype> parentGenotypes = new ArrayList<Genotype>();
        ArrayList<Pair<Double, Genotype>> tournament = new ArrayList<Pair<Double, Genotype>>();
        Pair<Double, Genotype> mostFitGenotype = null;

        while (parentGenotypes.size() < populationSize) {
            tournament.clear();
            for (int i = 0; i < _tournamentSize; i++) {
                tournament.add(_fitnessEvaluator.evaluateAsPair(
                        genotypes.get(_random.nextInt(populationSize))));
            }
            mostFitGenotype = tournament.get(0);
            for (int i = 1; i < tournament.size(); i++) {
                if(_fitnessComparator.compare(mostFitGenotype, tournament.get(i)) == -1) {
                    mostFitGenotype = tournament.get(i);
                }
            }
            parentGenotypes.add(mostFitGenotype.getSecondValue());
        }
        return parentGenotypes;
    }
}

package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/12/13
 */

import java.util.List;

/**
 * Returns phenotype of genotype.
 *
 * This should be named better, but this works
 * specifically for the TSP problem and the following output:
 *
 * Anchorage/Georgia(200):Georgia/Seattle(300)
 */
public class PhenotypeDecoder {

    private CostMatrix _costMatrix;

    public PhenotypeDecoder(CostMatrix costMatrix) {
        _costMatrix = costMatrix;
    }

    public String decode(Genotype genotype) {
        StringBuilder genotypeStringBuilder = new StringBuilder();
        Allele home = new Allele(0);
        Allele fromAllele = home;
        int count = 1;
        for (Allele toAllele : genotype) {
            genotypeStringBuilder.append(String.format("%d. %s/%s(%.0f)%n", count++,
                    _costMatrix.getAlleleName(fromAllele), _costMatrix.getAlleleName(toAllele),
                    _costMatrix.getCost(fromAllele, toAllele)));
            fromAllele = toAllele;
        }
        genotypeStringBuilder.append(String.format("%d. %s/%s(%.0f)", count,
                _costMatrix.getAlleleName(fromAllele), _costMatrix.getAlleleName(home),
                _costMatrix.getCost(fromAllele, home)));
        return genotypeStringBuilder.toString();
    }
}

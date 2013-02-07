package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class FitnessProportionateSelector {
    private IRandom _random;
    private ISampler<Genotype> _sampler;

    public FitnessProportionateSelector(IRandom random, ISampler<Genotype> sampler) {
        _random = random;
        _sampler = sampler;
    }
}

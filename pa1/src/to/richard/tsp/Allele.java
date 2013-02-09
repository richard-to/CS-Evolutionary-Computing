package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/9/13
 */

/**
 * Represents an allele.
 *
 * This implementation is limited to integer values.
 *
 * Need to refactor later. Maybe make generic.
 */
public class Allele {

    private int _value;

    public Allele(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Allele) {
            return _value == ((Allele)obj).getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _value;
    }
}

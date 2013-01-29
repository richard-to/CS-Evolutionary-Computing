package to.richard;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

/**
 * Represents a genotype for SGA of x squared.
 */
public class Genotype {

    private int[] _xValueBinary;
    private int _xValue;
    private int _xSquaredValue;
    private int _bitStringLength;
    public Genotype(int value, int bitStringLength) {
        _xValue = value;
        _xSquaredValue = _xValue * _xValue;
        _xValueBinary = convertIntToBitArray(_xValue, bitStringLength);
        _bitStringLength = bitStringLength;
    }

    /**
     * Converts int to padded bit array.
     *
     * If the value is greater than the bit array length, then
     * there will overflow will occur.
     *
     * @param value  An integer
     * @param bitArrayLength Length of bit array
     * @return int[]
     */
    public int[] convertIntToBitArray(int value, int bitArrayLength) {
        int[] bitArray = new int[bitArrayLength];
        String bitString = Integer.toBinaryString(value);
        int bitStringStart = 0;
        int bitArrayStart =  bitArrayLength - bitString.length();
        if (bitArrayStart < 0) {
            bitStringStart = bitArrayStart * -1;
        }
        for (int i = bitStringStart; i < bitString.length() ; ++i) {
            bitArray[bitArrayStart + i] = (bitString.charAt(i) == '1') ? 1 : 0;
        }
        return bitArray;
    }

    public int[] getGenes() {
        return _xValueBinary;
    }

    public int getValue() {
        return _xValue;
    }

    public int getFitness() {
        return _xSquaredValue;
    }

    public Genotype clone() {
        return new Genotype(_xValue, _bitStringLength);
    }
}
package to.richard;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

/**
 * Represents a genotype for SGA of x squared.
 */
public class XSquaredGenotype {

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
    public static int[] convertIntToBitArray(int value, int bitArrayLength) {
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

    private int[] _xValueBinary;
    private int _xValue;
    private int _xSquaredValue;

    public XSquaredGenotype(int value, int bitStringLength) {
        _xValue = value;
        _xSquaredValue = _xValue * _xValue;
        _xValueBinary = XSquaredGenotype.convertIntToBitArray(_xValue, bitStringLength);
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
}
package anaydis.compression;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Bits {

    private int acum;
    private int count;

    Bits() {
        this.acum = 0;
        this.count = 0;
    }
    Bits(int acum, int count) {
        this.acum = acum;
        this.count = count;
    }


    void addBit(int value){
        if(value != 0 && value != 1)
            throw new RuntimeException("Bit can only be 1 or 0");

        acum = acum << 1 | value;
        count++;
    }

    int bitAt(int n){
        return ((acum >> n) & 1);
    }

    int getIntRepresentation(){
        return acum;
    }

    void reset(){
        acum = 0;
        count = 0;
    }

    boolean byteIsFull(){
        return count >= 8;
    }

    public String toString(){
        return Integer.toBinaryString(acum);
    }


    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object other){
        Bits o = (Bits) other;
        return acum == o.acum && count == o.count;
    }

    public int hashCode(){
        int hash = 17;
        hash = hash * 31 + acum;
        hash = hash * 31 + count;
        return hash;
    }
}

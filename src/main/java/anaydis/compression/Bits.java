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
    protected Bits(int acum, int count) {
        this.acum = acum;
        this.count = count;
    }


    public void addBit(int value){
        if(value != 0 && value != 1)
            throw new RuntimeException("Bit can only be 1 or 0");

        if(count >= 8){
            throw new RuntimeException("Byte is full");
        }
        acum = acum << 1 | value;
        count++;
    }

    public int bitAt(int n){
        if(n > 8 || n < 0)
            throw new RuntimeException("Byte has only 8 bits");

        return ((acum >> n) & 1);
    }

    public int getIntRepresentation(){
        return acum;
    }

    public void reset(){
        acum = 0;
        count = 0;
    }

    public int getCount() {
        return count;
    }
    public boolean byteIsFull(){
        return count >= 8;
    }

    public String toString(){
        return Integer.toBinaryString(acum);
    }


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

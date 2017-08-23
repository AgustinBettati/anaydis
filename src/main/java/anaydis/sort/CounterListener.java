package anaydis.sort;

import anaydis.sort.gui.SorterListener;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class CounterListener implements SorterListener {
    private int amtOfSwaps;
    private int amtOfComparisons;

    public CounterListener(){
        amtOfSwaps = 0;
        amtOfComparisons = 0;
    }

    @Override
    public void box(int from, int to) {

    }

    @Override
    public void copy(int from, int to, boolean copyToAux) {

    }

    @Override
    public void equals(int i, int j) {

    }

    @Override
    public void greater(int i, int j) {
        amtOfComparisons++;
    }

    @Override
    public void swap(int i, int j) {
        amtOfSwaps++;
    }

    public int getAmtOfSwaps() {
        return amtOfSwaps;
    }

    public int getAmtOfComparisons() {
        return amtOfComparisons;
    }
}

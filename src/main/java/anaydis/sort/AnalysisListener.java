package anaydis.sort;

import anaydis.sort.gui.SorterListener;

import java.util.concurrent.TimeUnit;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class AnalysisListener implements SorterListener {
    private int amtOfSwaps;
    private int amtOfComparisons;
    private long start;
    private long stop;


    public AnalysisListener(){
        reset();
    }

    public void reset() {
        amtOfSwaps = 0;
        amtOfComparisons = 0;
        start = 0;
        stop = 0;
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

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        stop = System.nanoTime();
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

    /** Elapsed time in milliseconds. */
    public long getElapsedTime() {
        return TimeUnit.NANOSECONDS.toNanos(stop - start);
    }
}

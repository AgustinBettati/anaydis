package anaydis.search.practice;

import java.util.concurrent.TimeUnit;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MapListener {

    private int amtOfComparisons;
    private long start;
    private long stop;


    public MapListener(){
        reset();
    }

    public void reset() {
        amtOfComparisons = 0;
        start = 0;
        stop = 0;
    }


    public void greater() {
        amtOfComparisons++;
    }

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        stop = System.nanoTime();
    }

    public int getAmtOfComparisons() {
        return amtOfComparisons;
    }

    /** Elapsed time in milliseconds. */
    public long getElapsedTime() {
        return TimeUnit.NANOSECONDS.toNanos(stop - start);
    }

}

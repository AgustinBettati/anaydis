package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class MergeSorter extends AbstractMergeSort {

    public MergeSorter() {
        super(SorterType.MERGE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        for(int middle = 1; middle <= list.size() - 1; middle *= 2) {
            final int subListLength = middle * 2;
            for (int low = 0; low < list.size() - middle; low += subListLength) {
                final int high = Math.min(low + subListLength, list.size());
                merge(list, comparator, low, low + middle - 1, high - 1);
            }
        }

    }
}

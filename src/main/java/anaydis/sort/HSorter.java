package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HSorter extends AbstractSorter {

    HSorter() {
        super(SorterType.H);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 4);
    }


    protected <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, final int h) {
        final int n = list.size();

        for (int i = h; i < n; i++) {
            final T value = list.get(i);
            int j = i;

            while(j >= h && greater( list.get(j-h), value,comparator)){
                copy(j-h, j, list);
                j -= h;
            }
            list.set(j, value);
        }
    }
}

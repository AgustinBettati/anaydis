package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SelectionSorter extends AbstractSorter{

    public SelectionSorter() {
        super(SorterType.SELECTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                final T a = list.get(min);
                final T b = list.get(j);
                if(greater(a,b,comparator)){
                    min = j;
                }
            }
            swap(i,min,list);
        }
    }
}

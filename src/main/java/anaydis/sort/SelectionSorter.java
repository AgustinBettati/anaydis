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
            box(0,i);
            int min = i;
            for (int j = i+1; j < n; j++) {
                if(greater(min,j,list,comparator)){
                    min = j;
                }
            }
            if(i != min) {
                swap(i, min, list);
            }
        }
    }
}

package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class InsertionSorter extends AbstractSorter {

    public InsertionSorter() {
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();

        for (int i = 1; i < n; i++) {
            int j = i;
            while(j > 0 && greater(list.get(j-1),list.get(j),comparator) ){
                //Implementation can be optimized using moves and declaring a variable
                swap(j-1,j,list);
                j--;
            }
        }
    }
}

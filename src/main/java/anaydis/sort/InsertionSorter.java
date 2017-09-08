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
        sort(0, list.size() -1, comparator, list);
    }

    protected <T> void sort(int l, int r,@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        for (int i = l+1; i <= r; i++) {
            box(0,i);
            int j = i;
            while(j > l && greater(j-1,j,list,comparator) ){
                //Implementation can be optimized using moves and declaring a variable
                swap(j-1,j,list);
                j--;
            }
        }
    }



}

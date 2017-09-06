package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public abstract class AbstractQuickSorter extends AbstractSorter{


    AbstractQuickSorter(SorterType type) {
        super(type);
    }

    <T> int partition(int low, int high, @NotNull Comparator<T> comparator, @NotNull List<T> list){
        {
            // si o si usa a high como pivot
            int i = low - 1;
            int j = high;

            while (true)
            {
                i++;
                while(greater(high, i, list, comparator) && i < high){
                    i++;
                }

                j--;
                while(greater(j, high, list, comparator) && j > low){
                    j--;
                }

                if(i >= j) break;
                swap(i,j, list);
            }
            swap(i, high, list);
            return i;
        }
    }

    <T> void insertion(int low,int high,@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();

        for (int i = low + 1; i <= high; i++) {
            box(0,i);
            int j = i;
            while(j > low && greater(j-1,j,list,comparator) ){
                swap(j-1,j,list);
                j--;
            }
        }
    }
}

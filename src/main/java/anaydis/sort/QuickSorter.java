package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class QuickSorter extends AbstractSorter {

    public QuickSorter(){
        super(SorterType.QUICK);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        sort(0, list.size() -1,comparator, list );
    }

    private <T> void sort(int low, int high,@NotNull Comparator<T> comparator, @NotNull List<T> list){
        if(high <= low) return;
        int i = partitionLupani(low, high,comparator, list);
        sort(low, i-1, comparator, list);
        sort(i+1, high, comparator, list);

    }

    private <T> int partitionLupani(int low, int high, @NotNull Comparator<T> comparator, @NotNull List<T> list){
        {
            // high is taken as the pivot
            int i = low - 1;
            int j = high;


            while (true)
            {
                while(greater(high, ++i,list, comparator)){
                    if (i == high)
                        break;
                }

                while(greater(--j, high, list, comparator)){
                    if(j == low)
                        break;
                }
                if(i >= j) break;

                swap(i,j, list);
            }
            swap(i, high, list);
            return i;
        }
    }

    private <T> int partition(int low, int high, @NotNull Comparator<T> comparator, @NotNull List<T> list){
        T pivot = list.get(low);
        int i = low;
        int j = high;
        while (i < j)
        {
            //i++;
            while(greater(pivot, list.get(i),comparator) && i < high){
                i++;
            }
            //j--;
            while(greater(list.get(j), pivot, comparator) && j > low) {
                j--;
            }



            if (i < j)
                swap(i, j, list);
        }
        return j;

    }
}

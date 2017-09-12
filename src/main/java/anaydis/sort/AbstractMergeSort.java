package anaydis.sort;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
abstract class AbstractMergeSort extends AbstractSorter{

    AbstractMergeSort(SorterType type) {
        super(type);
    }

    <T> void merge(@NotNull List<T> list, @NotNull Comparator<T> comparator,
                              int low, int middle, int high) {

        final T[] auxList = (T[]) new Object[high - low + 1];


        for (int i = 0; low + i <= middle ; i++) {
            auxList[i] = list.get(i + low);
        }

        for (int i = middle + 1, j = high - low; i <= high; i++, j--) {
            auxList[j] = list.get(i);
        }

        for (int k = low, i = 0, j = high - low; k <= high; k++) {
            if(greater(auxList[i], auxList[j], comparator)) {
                list.set(k, auxList[j--]);
            } else {
                list.set(k, auxList[i++]);
            }
        }
    }

}

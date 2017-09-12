package anaydis.sort;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

        final List<T> aux = new ArrayList<>(high - low + 1);


        for (int i = 0; low + i <= middle ; i++) {
            aux.add(list.get(i + low));
        }

        for (int j = high; j >= middle + 1; j--) {
            aux.add(list.get(j));
        }

        for (int k = low, i = 0, j = high - low; k <= high; k++) {
            if(greater(aux.get(i), aux.get(j), comparator)) {
                list.set(k, aux.get(j--));
            } else {
                list.set(k, aux.get(i++));
            }
        }
    }

}

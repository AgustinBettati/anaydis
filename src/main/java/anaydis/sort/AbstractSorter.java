package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */

abstract class AbstractSorter implements ObservableSorter {

    private final List<SorterListener> listeners;
    private final SorterType type;

    public AbstractSorter(SorterType type) {
        this.type = type;
        listeners = new ArrayList<>();
    }

    @NotNull
    @Override
    public SorterType getType() {
        return type;
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        listeners.add(listener);
    }


    public <T> boolean greater(int i, int j,@NotNull List<T> list, @NotNull Comparator<T> comp) {
        listeners.forEach(listener -> listener.greater(i,j));

        return comp.compare(list.get(i), list.get(j)) > 0;
    }

    public <T> void swap(int i, int j, @NotNull List<T> list) {
        listeners.forEach(listener -> listener.swap(i,j));

        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

}

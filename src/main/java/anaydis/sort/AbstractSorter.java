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

    AbstractSorter(SorterType type) {
        this.type = type;
        listeners = new ArrayList<>();
    }

    @NotNull
    @Override
    public final SorterType getType() {
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


    <T> boolean greater(int i, int j, @NotNull List<T> list, @NotNull Comparator<T> comp) {
        listeners.forEach(listener -> listener.greater(i,j));

        return comp.compare(list.get(i), list.get(j)) > 0;
    }

    <T> boolean greater(T i, T j, @NotNull Comparator<T> comp) {
        listeners.forEach(listener -> listener.greater(0,0));

        return comp.compare(i, j) > 0;
    }

    <T> void swap(int i, int j, @NotNull List<T> list) {
        listeners.forEach(listener -> listener.swap(i,j));

        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    <T> void copy(int from, int to, @NotNull List<T> list) {
        listeners.forEach(listener -> listener.copy(from,to, true));
        list.set(to, list.get(from));
    }

    void box(int start, int end) {
        listeners.forEach(listener -> listener.box(start,end));
    }
}

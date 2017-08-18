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


    //aca se pueden implementar los metodos de swap y greater.
    // para desp llamarlos a listeners
    public <T> boolean greater(@NotNull T v, @NotNull T w, @NotNull Comparator<T> comp) {
        return comp.compare(v,w) > 0;
    }

    public <T> void swap(int i, int j, @NotNull List<T> list) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

}

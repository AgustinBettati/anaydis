package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ArrayMap<K,V> implements Map<K,V>{

    private final List<K> keys;
    private final List<V> values;
    private int size;
    private Comparator<K> comp;


    public ArrayMap(Comparator<K> comparator){
        this(comparator, 200);
    }

    public ArrayMap(Comparator<K> comparator, int maxCapacity){
        keys = new ArrayList<>(maxCapacity);
        values = new ArrayList<>(maxCapacity);
        fill(keys, maxCapacity);
        fill(values, maxCapacity);

        comp = comparator;
        size = 0;
    }

    private void fill(List list, int length){
        for (int i = 0; i < length; i++) {
            list.add(null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return indexOf(key) != -1;
    }

    private int indexOf(@NotNull K key){
        final int index = find(key, 0, size-1);
        if(index < 0) return -1;
        return index;
    }

    private int find(K key, int low, int high) {
        if(low > high) return -(low+1);

        final int middle = (low + high) /2;
        int comparison = comp.compare(key, keys.get(middle));
        if(comparison ==0) return middle;
        else if(comparison < 0) return find(key, 0, middle-1);
        else return find(key, middle+1,high);
    }

    @Override
    public V get(@NotNull K key) {
        final int index = indexOf(key);
        if(index == -1) return null;
        return values.get(index);

    }

    @Override
    public V put(@NotNull K key, V value) {
        int index = find(key, 0, size -1);
        if(index < 0) {
            index = (-index) -1;
            for(int i = size; i >= index + 1; i--){
                keys.set(i, keys.get(i-1));
                values.set(i, values.get(i-1));
            }
            keys.set(index, key);
            values.set(index, value);
            size++;
            return null;
        }
        V prev = values.get(index);
        values.set(index, value);
        return prev;
    }

    @Override
    public void clear() {
        size = 0;

    }

    @Override
    public Iterator<K> keys() {
        return keys.subList(0,size).iterator();
    }


}

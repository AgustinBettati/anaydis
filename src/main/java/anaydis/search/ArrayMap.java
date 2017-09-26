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
        keys = new ArrayList<>();
        values = new ArrayList<>();

        comp = comparator;
        size = 0;
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
                setValueInList(i, keys.get(i-1), keys);
                setValueInList(i, values.get(i-1), values);

            }
            setValueInList(index, key, keys);
            setValueInList(index, value, values);
            size++;
            return null;
        }
        V prev = values.get(index);
        values.set(index, value);
        return prev;
    }

    private <T> void setValueInList(int i, T k, List<T> list) {
        if(i == list.size()) {
            list.add(k);
        }
        else{
            list.set(i, k);
        }
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

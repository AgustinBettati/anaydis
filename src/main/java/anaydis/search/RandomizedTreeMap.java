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
public class RandomizedTreeMap<K,V> implements Map<K,V> {

    private Node<K,V>  head;
    private int size;
    private Comparator<K> comp;

    public RandomizedTreeMap(Comparator<K> comparator) {
        head = null;
        size = 0;
        comp = comparator;
    }

    private class Node<K,V>{
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }


    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {

        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        Node<K,V> node = find(head, key);
        if(node == null) return null;
        return node.value;
    }

    private Node<K,V> find(Node<K, V> node, K key) {
        if(node == null) return null;
        final int comparison = comp.compare(key, node.key);
        if(comparison == 0) return node;
        else if(comparison < 0) return find(node.left, key);
        else return find(node.right, key);
    }

    @Override
    public V put(@NotNull K key, V value) {
        V prev = get(key);
        head = put(head, key, value);
        return prev;

    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if(node == null) {
                size++;
                return new Node<K,V>(key, value);
        }
        else{
            final int comparison = comp.compare(key, node.key);
            if(comparison < 0) node.left = put(node.left, key, value);
            if(comparison > 0) node.right = put(node.right, key, value);
            else node.value = value;

            return node;
        }
    }


    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public Iterator<K> keys() {
        List<K> list = new ArrayList<>();
        getTreePreOrder(head, list);
        return list.iterator();
    }

    private void getTreePreOrder(Node<K,V> node, List<K> list) {
        if (node != null) {
            list.add(node.key);
            getTreePreOrder(node.left, list);
            getTreePreOrder(node.right, list);
        }
    }
}

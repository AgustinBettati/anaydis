package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class RandomizedTreeMap<K,V> implements Map<K,V> {

    private Node<K,V>  head;
    private int size;
    private Comparator<K> comp;
    private Random random;

    public RandomizedTreeMap(Comparator<K> comparator) {
        head = null;
        size = 0;
        comp = comparator;
        random = new Random();
    }

    private class Node<K,V>{
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;

        Node(K key, V value) {
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
        if(random.nextBoolean()) head = rootPut(head, key, value);
        else head = put(head, key, value);

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

    private Node<K, V> rootPut(Node<K, V> node,K key, V value) {
        if(node == null){
            size++;
            return new Node<K,V>(key, value);
        }
        else {
            final int comparison = comp.compare(key, node.key);

            if(comparison < 0) {
                node.left = rootPut(node.left, key, value);
                return rotateRight(node);
            }
            if(comparison > 0) {
                node.right = rootPut(node.right, key, value);
                return rotateLeft(node);
            }
            else {
                node.value = value;
                return node;
            }
        }
    }

    private Node<K, V> rotateRight(Node<K, V> node)  {
        final Node<K,V> result = node.left;
        node.left = result.right;
        result.right = node;
        return result;
    }
    private Node<K, V> rotateLeft(Node<K, V> node)  {
        final Node<K,V> result = node.right;
        node.right = result.left;
        result.left = node;
        return result;
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

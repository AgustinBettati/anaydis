package anaydis.immutable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BinaryTreeTest {

    @Test
    public void generalTest() {
        Map<Integer, String> tree = new BinaryTree<Integer, String>(Integer::compareTo);
        Map<Integer, String> tree1 = tree.put(0, "Jorge");
        Map<Integer, String> tree2 = tree1.put(1, "Carlos");
        Map<Integer, String> tree3 = tree2.put(2, "Perez");

        assertEquals(tree3.size(), 3);
        assert(tree3.containsKey(0));
        assert(tree3.containsKey(1));
        assert(tree3.containsKey(2));
        assertEquals(tree3.get(0), "Jorge");
    }

    @Test
    public void keysIteratorTest(){
        Map<Integer, String> tree = new BinaryTree<Integer, String>(Integer::compareTo);
        Map<Integer, String> tree1 = tree.put(0, "Jorge");
        Map<Integer, String> tree2 = tree1.put(1, "Carlos");
        Map<Integer, String> tree3 = tree2.put(2, "Perez");

        Iterator<Integer> it = tree3.keys();
        for (int i = 0; i < 3; i++) {
            assert(it.next() == i);
        }
    }

}
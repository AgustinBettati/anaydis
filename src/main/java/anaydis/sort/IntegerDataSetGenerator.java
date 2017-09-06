package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class IntegerDataSetGenerator implements DataSetGenerator<Integer> {

    @NotNull
    @Override
    public List<Integer> createAscending(int length) {
        List<Integer> ascendingList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ascendingList.add(i);
        }
        return ascendingList;
    }

    @NotNull
    @Override
    public List<Integer> createDescending(int length) {
        List<Integer> descendingList = new ArrayList<>();
        for (int i = length-1; i >=0; i--) {
            descendingList.add(i);
        }
        return descendingList;

    }

    @NotNull
    @Override
    public List<Integer> createRandom(int length) {
        List<Integer> randomList = new ArrayList<>();
        Random random = new Random(5);
        for (int i = 0; i < length; i++) {
            randomList.add(random.nextInt());
        }
        return randomList;
    }

    @NotNull
    @Override
    public Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }
}

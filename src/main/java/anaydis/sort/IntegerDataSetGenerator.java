package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class IntegerDataSetGenerator implements DataSetGenerator<Integer> {

    @NotNull
    @Override
    public List<Integer> createAscending(int length) {
        return null;
    }

    @NotNull
    @Override
    public List<Integer> createDescending(int length) {
        return null;
    }

    @NotNull
    @Override
    public List<Integer> createRandom(int length) {
        return null;
    }

    @NotNull
    @Override
    public Comparator<Integer> getComparator() {
        return null;
    }
}

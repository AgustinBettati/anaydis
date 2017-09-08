package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class PartitionTest {
    @Test
    public void testPartition() {
        final QuickSorter quick = getQuickSorter();
        final List<SubIndexed> values = Arrays.asList(
                new SubIndexed(1, "a"),
                new SubIndexed(2, "a"),
                new SubIndexed(3, "a"),
                new SubIndexed(1, "b"),
                new SubIndexed(2, "b"),
                new SubIndexed(3, "b"),
                new SubIndexed(2, "c"));
        final Comparator<SubIndexed> comparator = SubIndexed::compareTo;

        final int partition = quick.partition(0, values.size() - 1, comparator, values);
        final SubIndexed pivot = values.get(partition);

        values.subList(0, partition).forEach(e -> assertThat(pivot.compareTo(e) >= 0));
        values.subList(partition, values.size()).forEach(e -> assertThat(pivot.compareTo(e) <= 0));
    }

    @NotNull
    private QuickSorter getQuickSorter() {
        return (QuickSorter) new SorterProviderImpl().getSorterForType(SorterType.QUICK);
    }

    private class SubIndexed implements Comparable<SubIndexed> {
        private final Integer value;
        private final String sub;

        private SubIndexed(int value, String sub) {
            this.value = value;
            this.sub = sub;
        }

        @Override public int compareTo(@NotNull final SubIndexed other) {
            return value.compareTo(other.value);
        }

        @Override
        public String toString() {
            return value + "[" + sub + "]";
        }
    }
}

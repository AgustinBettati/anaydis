package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sorter tests should subclass this abstract implementation
 */
abstract class SorterTest extends AbstractSorterTest {

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }

    @Override
    protected SorterProvider getSorterProvider() {
        throw new IllegalStateException("To be implemented!");
    }
}

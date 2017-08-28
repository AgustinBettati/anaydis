package anaydis.sort.tp2;

import anaydis.sort.*;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class StatisticDataGenerator {

    private static final int RUNS = 10;

    public enum DataUnit {
        TIME,
        SWAPS,
        COMPARISONS

    }

    public enum Schema {
        ONE(100),
        TOW(500),
        THREE(2000),
        FOUR(5000),
        FIVE(10000);


        int size;

        Schema(int size) {
            this.size = size;
        }

        public int value(){
            return size;
        }
    }

    public enum Ordering {
        ASCENDING {
            @Override
            public <T> List<T> create(@NotNull DataSetGenerator<T> generator, Schema schema) {
                return generator.createAscending(schema.size);
            }
        },
        DESCENDING {
            @Override
            public <T> List<T> create(@NotNull DataSetGenerator<T> generator, Schema schema) {
                return generator.createDescending(schema.size);
            }
        },
        RANDOM {
            @Override
            public <T> List<T> create(@NotNull DataSetGenerator<T> generator, Schema schema) {
                return generator.createRandom(schema.size);
            }
        };

        abstract <T> List<T> create(@NotNull DataSetGenerator<T> generator, Schema schema);
    }


    public class Cube {
        private final SuperCell[][] data = new SuperCell[Ordering.values().length][Schema.values().length];

        Cube() {
            for (int i = 0; i < Ordering.values().length; i++) {
                for (int j = 0; j < Schema.values().length; j++) {
                    data[i][j] = new SuperCell();
                }
            }
        }

        void submit(final Schema schema, final Ordering ordering, final AnalysisListener listener) {
            final SuperCell cell = data[ordering.ordinal()][schema.ordinal()];
            cell.submit(listener);
        }

        public SuperCell[] schemas(Ordering ordering) {
            return data[ordering.ordinal()];
        }
    }

    public class SuperCell {
        private final Cell[] data = new Cell[DataUnit.values().length];

        SuperCell() {
            init();
        }

        private void submit(AnalysisListener listener) {
            data[DataUnit.SWAPS.ordinal()].submit(listener.getAmtOfSwaps());
            data[DataUnit.COMPARISONS.ordinal()].submit(listener.getAmtOfComparisons());
            data[DataUnit.TIME.ordinal()].submit(listener.getElapsedTime());
        }

        private void init() {
            for (int i = 0; i < DataUnit.values().length; i++) {
                data[i] = new Cell();
            }
        }

        public Cell getCell(DataUnit unit) {
            return data[unit.ordinal()];
        }
    }

    public class Cell {
        private final List<Long> data = new ArrayList<>();

        public List<Long> getData() {
            return data;
        }

        private void submit(long value) {
            data.add(value);
        }
    }


    @NotNull private Cube cube(@NotNull final Sorter sorter) {
        final Cube cube = new Cube();
        final IntegerDataSetGenerator generator = new IntegerDataSetGenerator();

        for (final Schema schema : Schema.values()) {
           for (final Ordering ordering : Ordering.values()) {
                run(sorter, generator, schema, ordering, cube);
           }
        }
        return cube;
    }

    public Map<SorterType, Cube> cubes(List<Sorter> sorters) {
        final Map<SorterType, Cube> result = new EnumMap<>(SorterType.class);
        sorters.forEach(s -> result.put(s.getType(), cube(s)));
        return result;
    }

    private <T> void run(final Sorter sorter, final DataSetGenerator<T> generator, Schema schema, final Ordering ordering, final Cube cube) {
        final AnalysisListener listener = new AnalysisListener();
        addListener(sorter, listener);
        final List<T> datum = ordering.create(generator, schema);
        for (int i = 0; i < RUNS; i++) {
            final List<T> copy = new ArrayList<>(datum);
            listener.start();
            sorter.sort(generator.getComparator(), copy);
            listener.stop();
            cube.submit(schema, ordering, listener);
            listener.reset();
        }
        removeListener(sorter, listener);
    }

    private void removeListener(Sorter sorter, AnalysisListener listener) {
        if(sorter instanceof ObservableSorter) {
            ((ObservableSorter) sorter).removeSorterListener(listener);
        }
    }

    private void addListener(Sorter sorter, AnalysisListener listener) {
        if(sorter instanceof ObservableSorter) {
            ((ObservableSorter) sorter).addSorterListener(listener);
        }
    }




}

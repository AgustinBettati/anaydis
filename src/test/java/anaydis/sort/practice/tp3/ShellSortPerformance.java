package anaydis.sort.practice.tp3;

import anaydis.sort.AnalysisListener;
import anaydis.sort.IntegerDataSetGenerator;
import anaydis.sort.ShellSorter;
import anaydis.sort.Sorter;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ShellSortPerformance {

    private final int RUNS = 15;

    private enum DataUnit {
        TIME,
        COMPARISONS
    }

    private enum Sequence {
        ONE(new int[]{1, 8, 23, 77, 281, 1073, 4193, 16577}),
        TOW(new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841});

        int[] sequence;

        Sequence(int[] list) {
            this.sequence = list;
        }

        public int[] getSequence(){
            return this.sequence;
        }

    }

    private enum Schema {
        ONE(100),
        TOW(1000),
        THREE(10000);

        int size;

        Schema(int size) {
            this.size = size;
        }

        public int value(){
            return size;
        }
    }

    @NotNull
    private Cube cube() {
        final Cube cube = new Cube();
        final IntegerDataSetGenerator generator = new IntegerDataSetGenerator();

        for (final Sequence sequence : Sequence.values()) {
            for (final Schema schema : Schema.values()) {
                run(generator, schema, sequence, cube);
            }
        }
        return cube;
    }

    private <T> void run(final DataSetGenerator<T> generator, Schema schema, final Sequence sequence, final Cube cube) {
        final AnalysisListener listener = new AnalysisListener();
        ShellSorter sorter = new ShellSorter();
        addListener(sorter, listener);
        final List<T> datum = generator.createRandom(schema.value());
        for (int i = 0; i < RUNS; i++) {
            final List<T> copy = new ArrayList<>(datum);
            listener.start();
            sorter.sort(generator.getComparator(), copy, sequence.getSequence());
            listener.stop();
            cube.submit(schema, sequence, listener);
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

    private class Cube {
        private final Cell[][] data = new Cell[Sequence.values().length][Schema.values().length];

        Cube() {
            for (int i = 0; i < Sequence.values().length; i++) {
                for (int j = 0; j < Schema.values().length; j++) {
                    data[i][j] = new Cell();
                }
            }
        }

        void submit(final Schema schema, final Sequence sequence, final AnalysisListener listener) {
            final Cell cell = data[sequence.ordinal()][schema.ordinal()];
            cell.submit(listener);
        }

        protected Cell[] schemas(Sequence sequence) {
            return data[sequence.ordinal()];
        }
    }

    private class Cell {
        private final DataSet[] units = new DataSet[DataUnit.values().length];

        Cell() {
            for (int i = 0; i < DataUnit.values().length; i++) {
                units[i] = new DataSet();
            }
        }

        private void submit(AnalysisListener listener) {
            units[DataUnit.COMPARISONS.ordinal()].submit(listener.getAmtOfComparisons());
            units[DataUnit.TIME.ordinal()].submit(listener.getElapsedTime());
        }

        protected DataSet getSetOfData(DataUnit unit) {
            return units[unit.ordinal()];
        }
    }

    private class DataSet {
        private final List<Long> data = new ArrayList<>();

        public List<Long> getData() {
            return data;
        }

        private void submit(long value) {
            data.add(value);
        }
    }





}

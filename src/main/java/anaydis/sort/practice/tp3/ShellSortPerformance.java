package anaydis.sort.practice.tp3;

import anaydis.sort.AnalysisListener;
import anaydis.sort.IntegerDataSetGenerator;
import anaydis.sort.ShellSorter;
import anaydis.sort.Sorter;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class ShellSortPerformance {

    private final int RUNS = 100;

    private enum DataUnit {
        TIME,
        COMPARISONS
    }

    private enum Sequence {
        ONE(new int[]{1, 8, 23, 77, 281, 1073, 4193, 16577}),
        TWO(new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841});

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
        THREE(5000),
        FOUR(10000);

        int size;

        Schema(int size) {
            this.size = size;
        }

        public int value(){
            return size;
        }
    }
    public static void main(String[] args) throws IOException{
        ShellSortPerformance performance = new ShellSortPerformance();

        Cube cube = performance.cube();

        File file = new File("/Users/agustinbettati/Documents/shellPerformance.csv");
        FileWriter fw = new FileWriter(file);


        for (Sequence sequence : Sequence.values()) {
            fw.write("\n\n\nSequence: ");
            int[] seq = sequence.getSequence();
            for (int i = 0; i < seq.length; i++) {
                fw.write(""+seq[i] + " ");
            }
            fw.write("\nn,time,comparisons\n");
            final Cell[] schemas = cube.schemas(sequence);
            for (final Schema schema : Schema.values()) {
                fw.write(""+schema.value());
                final Cell cell = schemas[schema.ordinal()];
                for (DataUnit unit : DataUnit.values()) {
                    final DataSet dataSet = cell.getSetOfData(unit);
                    final LongSummaryStatistics statistics =
                            dataSet.getData().stream().collect(Collectors.summarizingLong(value -> value));
                    fw.write("," + String.format("%.0f", statistics.getAverage()) );
                }
                fw.write("\n");
            }
        }

        fw.close();

        System.out.println("All data has been generated and stored in /Documents/shellPerformance.csv");
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

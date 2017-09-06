package anaydis.sort.practice.tp4;

import anaydis.sort.AnalysisListener;
import anaydis.sort.HybridQuickSorter;
import anaydis.sort.IntegerDataSetGenerator;
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
public class CutoffPerformance {

    private final int RUNS = 20;

    public int[] getValuesOfM() {
        return valuesOfM;
    }

    private int[] valuesOfM = {5,6,7,8,9,10,11,12,13,14,15};

    private enum Schema {
        ONE(1000),
        TOW(10000),
        THREE(100000),
        FOUR(1000000);

        int size;

        Schema(int size) {
            this.size = size;
        }

        public int value(){
            return size;
        }
    }
    public static void main(String[] args) throws IOException {
        CutoffPerformance performance = new CutoffPerformance();

        Cube cube = performance.cube();

        int[] valuesOfM = performance.getValuesOfM();

        File file = new File("/Users/agustinbettati/Documents/cutoffPerformance.csv");
        FileWriter fw = new FileWriter(file);

        for (int i = 0; i < valuesOfM.length; i++) {
            fw.write("\n\n\nM = " + valuesOfM[i]);
            fw.write("\nn,time\n");
            final Cell[] schemas = cube.schemas(i);
            for (final Schema schema : Schema.values()) {
                fw.write(""+schema.value());
                final Cell cell = schemas[schema.ordinal()];

                final LongSummaryStatistics statistics =
                        cell.getData().stream().collect(Collectors.summarizingLong(value -> value));
                fw.write("," + String.format("%.0f", statistics.getAverage()) );

                fw.write("\n");
            }
        }

        fw.close();

        System.out.println("All data has been generated and stored in /Documents/cutoffPerformance.csv");
    }

    @NotNull
    private Cube cube() {
        final Cube cube = new Cube();
        final IntegerDataSetGenerator generator = new IntegerDataSetGenerator();

        for (int i = 0; i < valuesOfM.length; i++) {
            for (final Schema schema : Schema.values()) {
                run(generator, schema, i, cube);
            }
        }
        return cube;
    }

    private <T> void run(final DataSetGenerator<T> generator, Schema schema, final int indexOfMValue, final Cube cube) {
        final AnalysisListener listener = new AnalysisListener();
        Sorter sorter = new HybridQuickSorter(valuesOfM[indexOfMValue]);
        addListener(sorter, listener);
        final List<T> datum = generator.createRandom(schema.value());
        for (int i = 0; i < RUNS; i++) {
            final List<T> copy = new ArrayList<>(datum);
            listener.start();
            sorter.sort(generator.getComparator(), copy);
            listener.stop();
            cube.submit(schema, indexOfMValue, listener);
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
        private final Cell[][] data = new Cell[valuesOfM.length][Schema.values().length];

        Cube() {
            for (int i = 0; i < valuesOfM.length; i++) {
                for (int j = 0; j < Schema.values().length; j++) {
                    data[i][j] = new Cell();
                }
            }
        }

        void submit(final Schema schema, final int indexOfMValue, final AnalysisListener listener) {
            final Cell cell = data[indexOfMValue][schema.ordinal()];
            cell.submit(listener.getElapsedTime());
        }

        protected Cell[] schemas(int indexOfMValue) {
            return data[indexOfMValue];
        }
    }

    private class Cell {
        private final List<Long> times;

        Cell() {
            times = new ArrayList<>();
        }

        public List<Long> getData() {
            return times;
        }

        private void submit(long value) {
            times.add(value);
        }

    }


}

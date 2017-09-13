package anaydis.sort.practice.analysis;

import anaydis.sort.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import anaydis.sort.practice.analysis.StatisticDataGenerator.*;


/**
 * @author Agustin Bettati
 * @version 1.0
 */
class CSVGenerator {

    public static void main(String[] args) throws IOException {
        final StatisticDataGenerator dataGenerator = new StatisticDataGenerator();

        List<Sorter> list = new ArrayList<>();
        list.add(new MergeSorter());
        list.add(new BottomUpMergeSorter());

        final Map<SorterType, Cube> cubes = dataGenerator.cubes(list);

        File file = new File("/Users/agustinbettati/Documents/MergeSortPerformance.csv");

        FileWriter fw = new FileWriter(file);

        for (Ordering ordering : Ordering.values()) {

            fw.write("\n\n\nORDERING: " + ordering +"\n");
            cubes.forEach((sorterType, cube) -> {
                try{
                fw.write("\nSorter: " + sorterType + "\n");
                fw.write("n,time (nanoseconds)\n");
                final Cell[] schemas = cube.schemas(ordering);
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
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        }

        fw.close();

        System.out.println("All data has been generated and stored in /Documents/MergeSortPerformance.csv");
    }
}

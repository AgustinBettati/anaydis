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


/**
 * @author Agustin Bettati
 * @version 1.0
 */
class CSVGenerator {

    public static void main(String[] args) throws IOException {
        final StatisticDataGenerator dataGenerator = new StatisticDataGenerator();

        List<Sorter> list = new ArrayList<>();
        list.add(new SelectionSorter());
        list.add(new InsertionSorter());
        list.add(new BubbleSorter());

        final Map<SorterType, StatisticDataGenerator.Cube> cubes = dataGenerator.cubes(list);

        File file = new File("/Users/agustinbettati/Documents/data.csv");

        FileWriter fw = new FileWriter(file);

        for (StatisticDataGenerator.Ordering ordering : StatisticDataGenerator.Ordering.values()) {

            fw.write("\n\n\nORDERING: " + ordering +"\n");
            cubes.forEach((sorterType, cube) -> {
                try{
                fw.write("\nSORTER: " + sorterType + "\n");
                fw.write("n,time,swaps,comparisons\n");
                final StatisticDataGenerator.Cell[] schemas = cube.schemas(ordering);
                for (final StatisticDataGenerator.Schema schema : StatisticDataGenerator.Schema.values()) {
                    fw.write(""+schema.value());
                    final StatisticDataGenerator.Cell cell = schemas[schema.ordinal()];
                    for (StatisticDataGenerator.DataUnit unit : StatisticDataGenerator.DataUnit.values()) {
                        final StatisticDataGenerator.DataSet dataSet = cell.getSetOfData(unit);
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

        System.out.println("All data has been generated and stored in /Documents/data.csv");
    }
}

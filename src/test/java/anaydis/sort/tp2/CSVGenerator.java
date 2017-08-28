package anaydis.sort.tp2;

import anaydis.sort.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import anaydis.sort.tp2.StatisticDataGenerator.*;


/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class CSVGenerator {

    public static void main(String[] args) throws IOException {
        final StatisticDataGenerator dataGenerator = new StatisticDataGenerator();

        List<Sorter> list = new ArrayList<>();
        list.add(new SelectionSorter());
        list.add(new InsertionSorter());
        list.add(new BubbleSorter());

        final Map<SorterType, Cube> cubes = dataGenerator.cubes(list);

        File file = new File("/Users/agustinbettati/Documents/data.csv");

        FileWriter fw = new FileWriter(file);

        for (Ordering ordering : Ordering.values()) {

            fw.write("\n\n\nORDERING: " + ordering +"\n");
            cubes.forEach((sorterType, cube) -> {
                try{
                fw.write("\nSORTER: " + sorterType + "\n");
                fw.write("n,time,swaps,comparisons\n");
                final SuperCell[] schemas = cube.schemas(ordering);
                for (final Schema schema : Schema.values()) {
                    fw.write(""+schema.value());
                    final SuperCell superCell = schemas[schema.ordinal()];
                    for (DataUnit unit : DataUnit.values()) {
                        final Cell cell = superCell.getCell(unit);
                        final LongSummaryStatistics statistics =
                                cell.getData().stream().collect(Collectors.summarizingLong(value -> value));
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

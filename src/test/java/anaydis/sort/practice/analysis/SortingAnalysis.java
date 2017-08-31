package anaydis.sort.practice.analysis;

import anaydis.sort.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Agustin Bettati
 * @version 1.0
 */

class SortingAnalysis {


    public static void main(String[] args) {
        final StatisticDataGenerator dataGenerator = new StatisticDataGenerator();

        List<Sorter> list = new ArrayList<>();
        list.add(new SelectionSorter());
        list.add(new BubbleSorter());
        list.add(new InsertionSorter());

        final Map<SorterType, StatisticDataGenerator.Cube> cubes = dataGenerator.cubes(list);

        for (StatisticDataGenerator.Ordering ordering : StatisticDataGenerator.Ordering.values()) {
            System.out.println("ORDERING = " + ordering);
            cubes.forEach((sorterType, cube) -> {
                System.out.println("\tSORTER = " + sorterType);
                final StatisticDataGenerator.Cell[] schemas = cube.schemas(ordering);
                for (final StatisticDataGenerator.Schema schema : StatisticDataGenerator.Schema.values()) {
                    System.out.println("\t\tn = " + schema.value());
                    final StatisticDataGenerator.Cell cell = schemas[schema.ordinal()];
                    for (StatisticDataGenerator.DataUnit unit : StatisticDataGenerator.DataUnit.values()) {
                        System.out.println("\t\t\tUNIT = " + unit);
                        final StatisticDataGenerator.DataSet dataSet = cell.getSetOfData(unit);
                        final LongSummaryStatistics statistics =
                                dataSet.getData().stream().collect(Collectors.summarizingLong(value -> value));
                        System.out.print("\t\t\t\t "+ unit + " -> " + statistics + "\n");
                    }
                }

            });
        }
    }




}

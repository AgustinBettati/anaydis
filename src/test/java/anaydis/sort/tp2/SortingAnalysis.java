package anaydis.sort.tp2;

import anaydis.sort.*;
import anaydis.sort.tp2.StatisticDataGenerator.*;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Agustin Bettati
 * @version 1.0
 */

public class SortingAnalysis {


    public static void main(String[] args) {
        final StatisticDataGenerator dataGenerator = new StatisticDataGenerator();

        List<Sorter> list = new ArrayList<>();
        list.add(new SelectionSorter());
        list.add(new BubbleSorter());
        list.add(new InsertionSorter());

        final Map<SorterType, Cube> cubes = dataGenerator.cubes(list);

        cubes.forEach((sorterType, cube) -> {
            System.out.println("SORTER = " + sorterType);
//            for (StatisticDataGenerator.Ordering ordering : Ordering.values()) {
//                System.out.println("\tORDERING = " + ordering);
                final SuperCell[] schemas = cube.schemas(Ordering.RANDOM);
                for (final Schema schema : Schema.values()) {
                    System.out.println("\t\tn = " + schema.value());
                    final SuperCell superCell = schemas[schema.ordinal()];
                    for (DataUnit unit : DataUnit.values()) {
                       // System.out.println("\t\t\tUNIT = " + unit);
                        final Cell cell = superCell.getCell(unit);
                        final LongSummaryStatistics statistics =
                                cell.getData().stream().collect(Collectors.summarizingLong(value -> value));
                        System.out.print("\t\t\t\t "+ unit + " -> ");
                        System.out.printf("%.0f\n", statistics.getAverage());
                    }
                }
            //}
        });
    }




}

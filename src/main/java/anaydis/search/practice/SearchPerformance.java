package anaydis.search.practice;

import anaydis.search.ArrayMap;
import anaydis.search.Map;
import anaydis.search.RandomizedTreeMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SearchPerformance {

    private static final int RUNS = 100;


    public static void main(String[] args) throws IOException {
        int[] schemas = {5000, 50000, 100000, 150000, 200000};

        Map<String, Integer> arrayMap = new ArrayMap<>(String::compareTo);
        Map<String, Integer> treeMap = new RandomizedTreeMap<>(String::compareTo);
        Map<String,Integer>[] maps = new Map[]{arrayMap,treeMap};

        File file = new File("/Users/agustinbettati/Documents/ArrayVsTreeSearch.csv");

        FileWriter fw = new FileWriter(file);

        fw.write("amount of runs: "+ RUNS + "\n\n");
        fw.write("n,ArrayMap,RandomizedTreeMap \n");

        for (int schema : schemas) {

            fw.write("" +schema);
            for (Map<String, Integer> map : maps) {
                map.clear();
                fillMap(map, schema);

                List<Long> results = new ArrayList<>();
                for (int i = 0; i < RUNS; i++) {
                    results.add(searchInMapAndGetTime(map, schema));
                }
                final LongSummaryStatistics statistics =
                        results.stream().collect(Collectors.summarizingLong(value -> value));

                fw.write("," + String.format("%.0f", statistics.getAverage()) );
            }
            fw.write("\n");
        }

        fw.close();
        System.out.println("All data has been generated and stored in /Documents/ArrayVsTreeSearch.csv");
    }

    private static void fillMap(Map<String,Integer> map, int amountOfWords) throws IOException {
        FileReader fileReader = new FileReader("src/test/resources/books/simplifiedQuijote.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int words = 0;
        String word = bufferedReader.readLine();
        while(words < amountOfWords){
            if(map.containsKey(word)){
                map.put(word, map.get(word) + 1);
            }
            else{
                map.put(word, 1);
            }
            words++;
            word = bufferedReader.readLine();
        }
    }

    private static long searchInMapAndGetTime(Map<String,Integer> map, int amountOfWords) throws IOException {
        FileReader fileReader = new FileReader("src/test/resources/books/reversedQuijote.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int words = 0;
        String word = bufferedReader.readLine();
        long start = System.nanoTime();
        while(words < amountOfWords){

            map.get(word);

            words++;
            word = bufferedReader.readLine();
        }

        return System.nanoTime() - start;
    }


}

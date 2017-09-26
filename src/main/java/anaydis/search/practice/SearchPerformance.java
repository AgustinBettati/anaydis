package anaydis.search.practice;

import anaydis.search.ArrayMap;
import anaydis.search.Map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class SearchPerformance {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new ArrayMap<>((o1, o2) -> o1.compareTo(o2));
        int[] schemas = {5000, 50000, 100000, 150000, 200000};



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

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
        fillMap(map, 100);

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
}

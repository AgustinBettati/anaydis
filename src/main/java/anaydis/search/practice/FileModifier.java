package anaydis.search.practice;

import java.io.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class FileModifier {

    public static void main(String[] args) throws IOException {


        FileReader fileReader = new FileReader("src/test/resources/books/quijote.txt");
        BufferedReader bufferedReader= new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("src/test/resources/books/reversedQuijote.txt");


        int amountOfWords = 0;
        String line = bufferedReader.readLine();
        while (line!= null && amountOfWords < 210000) {

            String[] words = line.split("\\W+");


            for (String word : words) {
                if (word.length() != 0) {
                    fileWriter.write(new StringBuilder(word).reverse().toString().toLowerCase() + "\n");
                    amountOfWords++;
                }
            }

            line  = bufferedReader.readLine();
        }

        bufferedReader.close();
        fileWriter.close();
        System.out.println("Termina");

    }
}

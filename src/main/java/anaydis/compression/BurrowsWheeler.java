package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BurrowsWheeler implements Compressor {

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        String inputString = new BufferedReader(new InputStreamReader(input)).lines()
                .parallel().collect(Collectors.joining("\n"));

        List<Rotation> rotations = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++) {
            rotations.add(new Rotation(inputString, i));
        }

        //TODO ver a que sort llamar
        Collections.sort(rotations);

        int firstRotationIndex = getFirstRotationIndex(rotations);

        String digits = firstRotationIndex + "";
        output.write(digits.length());
        char[] arrayOfDigits = digits.toCharArray();
        for (char arrayOfDigit : arrayOfDigits) {
            output.write(Character.getNumericValue(arrayOfDigit));
        }

        for (Rotation rotation: rotations
             ) {
            output.write(rotation.charAt(inputString.length()-1));
        }
    }

    private int getFirstRotationIndex(List<Rotation> rotations) {
        for (int i = 0; i < rotations.size(); i++) {
            if(rotations.get(i).getRotationIndex() == 1){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int lengthOfDigit = input.read();
        String digit = "";
        for (int i = 0; i < lengthOfDigit; i++) {
            digit += input.read();
        }
        int indexOfFirstChar = Integer.parseInt(digit);

        List<Character> listL = new ArrayList<>();

        int inputByte = input.read();
        while(inputByte != -1){
            listL.add(((char) inputByte));
            inputByte = input.read();
        }

        List<Character> listF = new ArrayList<>(listL);
        Collections.sort(listF);

        int[] t = new int[listL.size()];
        boolean[] isUsed = new boolean[listL.size()];

        for (int i = 0; i < listL.size(); i++) {
            char nextChar = listF.get(i);
            for (int j = 0; j < listL.size(); j++) {
                if(!isUsed[j] && listL.get(j) == nextChar ){
                    t[i] = j;
                    isUsed[j] = true;
                    break;
                }
            }
        }

        int currentIndex = indexOfFirstChar;
        for (int i = 0; i < listL.size(); i++) {
            output.write(listL.get(currentIndex));
            currentIndex = t[currentIndex];
        }

    }
}

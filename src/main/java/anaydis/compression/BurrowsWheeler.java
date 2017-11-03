package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class BurrowsWheeler implements Compressor {

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        byte[] inputArray = toByteArray(input);


        List<Rotation> rotations = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            rotations.add(new Rotation(inputArray, i));
        }

        Collections.sort(rotations);

        int firstRotationIndex = getFirstRotationIndex(rotations);

        writeNumber(firstRotationIndex, output);

        for (Rotation rotation: rotations
             ) {
            output.write(rotation.byteAt(inputArray.length-1));
        }
    }

    private byte[] toByteArray(InputStream input) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private int getFirstRotationIndex(List<Rotation> rotations) {
        for (int i = 0; i < rotations.size(); i++) {
            if(rotations.get(i).getRotationIndex() == 1){
                return i;
            }
        }
        return -1;
    }

    private void writeNumber(int firstRotationIndex, OutputStream output) throws IOException {
        String digits = firstRotationIndex + "";
        output.write(digits.length());
        char[] arrayOfDigits = digits.toCharArray();
        for (char arrayOfDigit : arrayOfDigits) {
            output.write(Character.getNumericValue(arrayOfDigit));
        }
    }


    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        int indexOfFirstChar = readNumber(input);

        List<Byte> listL = new ArrayList<>();

        int inputByte = input.read();
        while(inputByte != -1){
            listL.add((byte)inputByte);
            inputByte = input.read();
        }

        List<Byte> listF = new ArrayList<>(listL);
        Collections.sort(listF);

        int[] t = new int[listL.size()];
        boolean[] isUsed = new boolean[listL.size()];

        for (int i = 0; i < listL.size(); i++) {
            byte nextByte = listF.get(i);
            for (int j = 0; j < listL.size(); j++) {
                if(!isUsed[j] && listL.get(j) == nextByte ){
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

    private int readNumber(InputStream input) throws IOException {
        int lengthOfDigit = input.read();
        String digit = "";
        for (int i = 0; i < lengthOfDigit; i++) {
            digit += input.read();
        }
        return Integer.parseInt(digit);
    }
}

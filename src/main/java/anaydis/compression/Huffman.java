package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Huffman implements Compressor {
    private final int escapeChar = 127;

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<Integer, Integer> frequencyTable = getFrequencyTable(input);

        Node head = createTree(frequencyTable);

        Map<Integer, String> codesInString = new HashMap<>();
        buildStringCode(codesInString, head, "");


        writeTable(codesInString, output);
        output.write(escapeChar);

        //TODO amt of bits da muy grande y no se puede guardar
        int amtOfBits = amountOfBits(frequencyTable, codesInString);
        output.write(amtOfBits);

        input.reset();
        writeEncodedMessage(input, output, codesInString);
    }

    private void writeTable(Map<Integer, String> codesInString, OutputStream output) throws IOException {
        for(Map.Entry<Integer, String> charAndString : codesInString.entrySet()) {
            output.write(charAndString.getKey());
            String binaryCode = charAndString.getValue();
            int amtOfBits = binaryCode.length();
            output.write(amtOfBits);

            int[] binaryArray = new int[binaryCode.length()];
            for (int i = 0; i < binaryCode.length(); i++) {
                binaryArray[i] = Character.getNumericValue(binaryCode.charAt(i));
            }
            Bits bits = new Bits();
            for (int i = 0; i < amtOfBits; i++) {
                if(bits.byteIsFull()){
                    output.write(bits.getIntRepresentation());
                    bits.reset();
                }
                bits.addBit(binaryArray[i]);
            }
            output.write(bits.getIntRepresentation());

        }
    }

    private void writeEncodedMessage(InputStream input, OutputStream output, Map<Integer, String> codesInString) throws IOException {
        int byteInput = input.read();
        Bits currentByte = new Bits();
        while(byteInput != -1){
            String binaryCode = codesInString.get(byteInput);
            int[] binaryArray = new int[binaryCode.length()];
            for (int i = 0; i < binaryCode.length(); i++) {
                binaryArray[i] = Character.getNumericValue(binaryCode.charAt(i));
            }

            for (int i = 0; i < binaryArray.length; i++) {
                if(currentByte.byteIsFull()) {
                    output.write(currentByte.getIntRepresentation());
                    currentByte.reset();
                }

                currentByte.addBit(binaryArray[i]);
            }
            byteInput = input.read();
        }
        output.write(currentByte.getIntRepresentation());
    }



    private int amountOfBits(Map<Integer, Integer> frequencyTable, Map<Integer, String> codesInString) {
        int sum = 0;
        for(Map.Entry<Integer, Integer> charAndFreq : frequencyTable.entrySet()) {
            int frequency = charAndFreq.getValue();
            int lengthOfBits = codesInString.get(charAndFreq.getKey()).length();
            sum += frequency * lengthOfBits;
        }
        return sum;
    }



//    private void writeTable(Map<Integer, Bits> codes, OutputStream output) throws IOException {
//        for(Map.Entry<Integer, Bits> code : codes.entrySet()) {
//            output.write(code.getKey());
//            output.write(code.getValue().getCount());
//            output.write(code.getValue().getIntRepresentation());
//        }
//    }

//    private void buildBitsCode(Map<Integer, Bits> codes, Node node, Bits bits) {
//        if(node.isLeaf()){
//            codes.put(node.ch, bits);
//        }
//        else{
//            Bits bitsCopy = new Bits(bits.getIntRepresentation(), bits.getCount());
//            bitsCopy.addBit(1);
//            buildBitsCode(codes, node.right, bitsCopy);
//            bits.addBit(0);
//            buildBitsCode(codes, node.left,  bits);
//
//        }
//    }

    private void buildStringCode(Map<Integer, String> codes, Node node, String s) {
        if(node.isLeaf()){
            codes.put(node.ch, s);
        }
        else{
            buildStringCode(codes, node.left,  s + "0");
            buildStringCode(codes, node.right, s + "1");
        }
    }

    private Node createTree(Map<Integer, Integer> frequencyTable) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for(Map.Entry<Integer, Integer> element : frequencyTable.entrySet()) {
            priorityQueue.add(new Node(element.getKey(), element.getValue(),null,null));
        }

        while(priorityQueue.size() > 1){
            Node node1 = priorityQueue.remove();
            Node node2 = priorityQueue.remove();
            Node newNode = new Node(-1, node1.freq + node2.freq, node1, node2);
            priorityQueue.add(newNode);
        }

        return priorityQueue.remove();
    }

    private Map<Integer, Integer> getFrequencyTable(@NotNull InputStream input) throws IOException {
        Map<Integer, Integer> frequencyTable = new HashMap<>();
        int inputByte = input.read();
        while(inputByte != -1){
            if(frequencyTable.containsKey(inputByte)){
                frequencyTable.put(inputByte, frequencyTable.get(inputByte) + 1);
            }
            else{
                frequencyTable.put(inputByte, 1);
            }
            inputByte = input.read();
        }
        return frequencyTable;
    }


    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<Bits, Integer> bitsToCharMap = new HashMap<>();

        int inputByte = input.read();
        while(inputByte != escapeChar){
            int character;
            if(inputByte < 0){
                character = inputByte + 256;
            }else {
                character = inputByte;
            }
            int amountOfBits = input.read();
            int amtOfFullBytes = amountOfBits/8;
            int amtOfBitsLastByte = amountOfBits % 8;

            Bits bits = new Bits();
            for (int i = 0; i < amtOfFullBytes; i++) {

                Bits fullByte = new Bits(input.read(), 8);

                for (int j = 7; j >= 0; j--) {
                    int newBit = fullByte.bitAt(j);
                    bits.addBit(newBit);
                }
            }
            if(amtOfBitsLastByte > 0) {
                Bits lastByte = new Bits(input.read(), amtOfBitsLastByte);
                for (int i = amtOfBitsLastByte - 1; i >= 0; i--) {
                    int newBit = lastByte.bitAt(i);
                    bits.addBit(newBit);
                }
            }

            bitsToCharMap.put(bits, character);

            inputByte = input.read();
        }

        int amountOfBits = input.read();
        int amtOfFullBytes = amountOfBits/8;
        int amtOfBitsLastByte = amountOfBits % 8;

        Bits posibleCode = new Bits();
        for (int i = 0; i < amtOfFullBytes; i++) {

            Bits fullByte = new Bits(input.read(), 8);

            for (int j = 7; j >= 0; j--) {
                int newBit = fullByte.bitAt(j);
                posibleCode.addBit(newBit);
                if(bitsToCharMap.containsKey(posibleCode)){
                    output.write(bitsToCharMap.get(posibleCode));
                    posibleCode.reset();
                }
            }
        }

        Bits fullByte = new Bits(input.read(), amtOfBitsLastByte);
        for (int i = amtOfBitsLastByte -1; i >= 0; i--) {
            int newBit = fullByte.bitAt(i);
            posibleCode.addBit(newBit);
            if(bitsToCharMap.containsKey(posibleCode)){
                output.write(bitsToCharMap.get(posibleCode));
                posibleCode.reset();
            }
        }
    }


    private class Node implements Comparable<Node> {
        final int ch;
        final int freq;
        final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(@NotNull Node that) {
            return this.freq - that.freq;
        }
    }
}

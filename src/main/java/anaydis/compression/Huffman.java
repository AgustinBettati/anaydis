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

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<Integer, Integer> frequencyTable = getFrequencyTable(input);

        Node head = createTree(frequencyTable);
        int lengthOfmessage = head.freq;

        Map<Integer, String> codes = new HashMap<>();
        buildCode(codes, head, "");

        writeTable(codes, output);
        System.out.println();
    }

    private void writeTable(Map<Integer, String> codes, OutputStream output) throws IOException {
        for(Map.Entry<Integer, String> code : codes.entrySet()) {
            output.write(code.getKey());
            output.write(code.getValue().length());
            //Bits bitRepresentation = new Bits( code.getValue() );
        }
    }

    private void buildCode(Map<Integer, String> codes, Node node, String s) {
        if(node.isLeaf()){
            codes.put(node.ch, s);
        }
        else{
            buildCode(codes, node.left,  s + "0");
            buildCode(codes, node.right, s + "1");
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
        input.reset();
        return frequencyTable;
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {


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

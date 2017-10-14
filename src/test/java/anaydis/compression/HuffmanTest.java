package anaydis.compression;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class HuffmanTest {

    @Test
    public void encodeAndDecodeTest() throws IOException {
        String message = "HHoooolaaa";
        Compressor compressor = new Huffman();

        InputStream is = new ByteArrayInputStream( message.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        compressor.encode(is, output);

        byte[] byteArray = output.toByteArray();
        String encoded = output.toString();

        InputStream newIs = new ByteArrayInputStream( byteArray);
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        compressor.decode(newIs, newOutput);

        assertEquals("HHoooolaaa",newOutput.toString());

    }


}
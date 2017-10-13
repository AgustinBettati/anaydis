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
        String message = "Hoooolaaa";
        Compressor compressor = new Huffman();

        InputStream is = new ByteArrayInputStream( message.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        compressor.encode(is, output);

        String encoded = output.toString();

        InputStream newIs = new ByteArrayInputStream( encoded.getBytes());
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        compressor.decode(newIs, newOutput);

        assertEquals("Hoooolaaa",newOutput.toString());

    }


}
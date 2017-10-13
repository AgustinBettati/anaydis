package anaydis.compression;

import anaydis.immutable.BinaryTree;
import anaydis.immutable.Map;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class RunLengthEncodingTest {

    @Test
    public void encodeAndDecodeTest() throws IOException {
        String message = "Hoooolaaa";
        Compressor compressor = new RunLengthEncoding();

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
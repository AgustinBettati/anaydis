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
        String message = "Yo, Juan Gallo de Andrada, escribano de Cámara del Rey nuestro señor, de\n" +
                "los que residen en su Consejo, certifico y doy fe que, habiendo visto por\n" +
                "los señores dél un libro intitulado El ingenioso hidalgo de la Mancha,\n" +
                "compuesto por Miguel de Cervantes Saavedra, tasaron cada pliego del dicho\n" +
                "libro a tres maravedís y medio; el cual tiene ochenta y tres pliegos, que\n" +
                "al dicho precio monta el dicho libro docientos y noventa maravedís y medio,\n" +
                "en que se ha de vender en papel; y dieron licencia para que a este precio\n" +
                "se pueda vender, y mandaron que esta tasa se ponga al principio del dicho\n" +
                "libro, y no se pueda vender sin ella. Y, para que dello conste, di la\n" +
                "presente en Valladolid, a veinte días del mes de deciembre de mil y\n" +
                "seiscientos y cuatro años.";
        Compressor compressor = new Huffman();

        InputStream is = new ByteArrayInputStream( message.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        compressor.encode(is, output);

        byte[] byteArray = output.toByteArray();
        String encoded = output.toString();

        InputStream newIs = new ByteArrayInputStream( byteArray);
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        compressor.decode(newIs, newOutput);

        assertEquals("Yo, Juan Gallo de Andrada, escribano de Cámara del Rey nuestro señor, de\n" +
                "los que residen en su Consejo, certifico y doy fe que, habiendo visto por\n" +
                "los señores dél un libro intitulado El ingenioso hidalgo de la Mancha,\n" +
                "compuesto por Miguel de Cervantes Saavedra, tasaron cada pliego del dicho\n" +
                "libro a tres maravedís y medio; el cual tiene ochenta y tres pliegos, que\n" +
                "al dicho precio monta el dicho libro docientos y noventa maravedís y medio,\n" +
                "en que se ha de vender en papel; y dieron licencia para que a este precio\n" +
                "se pueda vender, y mandaron que esta tasa se ponga al principio del dicho\n" +
                "libro, y no se pueda vender sin ella. Y, para que dello conste, di la\n" +
                "presente en Valladolid, a veinte días del mes de deciembre de mil y\n" +
                "seiscientos y cuatro años.",newOutput.toString());

    }


}
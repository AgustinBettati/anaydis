package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class RunLengthEncoding implements Compressor{
    private final int escapeChar = 255;

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int currentByte = input.read();
        int nextByte;
        int count = 1;
        while(currentByte != -1){
            nextByte = input.read();
            if(currentByte != nextByte && count <= 1){
                //escribir character solito;
                output.write(currentByte);

            }
            else if(currentByte != nextByte && count > 1 ){
                //escribir character con escape y el count;
                output.write(escapeChar);
                output.write(count);
                output.write(currentByte);
                count = 1;
            }
            else if(currentByte == nextByte){
                count++;
            }
            currentByte = nextByte;
        }

    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int currentByte = input.read();
        while (currentByte != -1){
            if(currentByte == escapeChar){
                int count = input.read();
                int character = input.read();
                for (int i = 0; i < count; i++) {
                    output.write(character);
                }
            }
            else {
                output.write(currentByte);
            }
            currentByte = input.read();
        }
    }
}

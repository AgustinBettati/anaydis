package anaydis.compression;

import org.jetbrains.annotations.NotNull;

/**
 * @author Agustin Bettati
 * @version 1.0
 */
public class Rotation implements Comparable<Rotation>{
    private final String raw;
    private final int rotation;

    public Rotation(String raw, int rotation) {
        if(rotation >= raw.length()){
            throw new RuntimeException();
        }
        this.raw = raw;
        this.rotation = rotation;
    }

    public char charAt(int position){
        if(position >= raw.length()){
            throw new RuntimeException("index out of bounds in charAt");
        }
        return raw.charAt(Math.abs(rotation + position) % raw.length());
    }

    public int getRotationIndex() {
        return rotation;
    }

    @Override
    public int compareTo(@NotNull Rotation o) {
        for (int i = 0; i < raw.length(); i++) {
            if(charAt(i) > o.charAt(i)){
                return 1;
            }
            else if(charAt(i) < o.charAt(i)){
                return -1;
            }
        }
        return 0;
    }
}

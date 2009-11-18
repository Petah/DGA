
package lzw.util;
// Keeps a limited sized dictionary
public class LimitedDict extends Dict {

    int maxSize;

    LimitedDict(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(ByteArray str) {
        if (size() < maxSize) {
            super.add(str);
        }
    }
}

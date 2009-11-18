
package lzw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The dictionary
public class Dict {
    // mp keeps : Word => Index
    // ls keeps : Index => Word
    Map mp = new HashMap();
    List ls = new ArrayList();
    // Adds an element into the dictionary
    public void add(ByteArray str) {
        mp.put(str, new Integer(ls.size()));
        ls.add(str);
    }
    // Gets the number for the given string.
    // If it does not exist, returns -1
    public final int numFromStr(ByteArray str) {
        return (mp.containsKey(str) ? ((Integer) mp.get(str)).intValue() : -1);
    }
    // Gets the string for the given number
    // If the number does not exist, return null
    public final ByteArray strFromNum(int i) {
        return (i < ls.size() ? (ByteArray) ls.get(i) : null);
    }

    public final int size() {
        return ls.size();
    }
};

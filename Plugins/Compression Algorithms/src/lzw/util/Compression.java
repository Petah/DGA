/*
 * Compression.java
 *
 * Created on 01 Dec 2005
 *
 * Interface for any compression algorithm
 */
package lzw.util;

import java.io.*;

/**
 *
 * @author  Moshe Fresko
 * @course	Algorithmic Programming 1
 * @exercise	2
 */
interface Compression {
    // Gets the input from the Input Stream
    // and writes the encoded code into Output Stream
    void compress(InputStream inp, OutputStream out) throws IOException;
    // Gets the already encoded input stream
    // Decodes it and writes into output stream
    void decompress(InputStream inp, OutputStream out) throws IOException;
}


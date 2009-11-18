package dataanalyzer.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used for creating MD5 checksums.
 * http://www.rgagnon.com/javadetails/java-0416.html
 */
public class MD5 {

    /**
     * Gets the MD5 checksum of a file.
     * @param filename the file to create the checksum from
     * @return the MD5 checksum in bytes
     * @throws IOException if the file cannot be accessed
     * @throws NoSuchAlgorithmException if the MD5 algorithm cannot be found
     */
    public static byte[] createChecksum(String filename) throws IOException, NoSuchAlgorithmException {
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    /**
     * Gets the MD5 checksum of a file in HEX form.
     * For a faster way to convert a byte array to a HEX string: http://www.rgagnon.com/javadetails/java-0596.html
     * @param filename the file to create the checksum from
     * @return the HEX MD5 checksum
     * @throws IOException if the file cannot be accessed
     * @throws NoSuchAlgorithmException if the MD5 algorithm cannot be found
     */
    public static String getMD5Checksum(String filename) throws IOException, NoSuchAlgorithmException {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}

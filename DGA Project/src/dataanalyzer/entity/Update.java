package dataanalyzer.entity;
/**
 * Represents an updated file located on a server.
 * @author David Neilsen
 */
public class Update implements Comparable<Update> {

    /** The file name */
    public String name;
    /** The file URL location of the update file */
    public String url;
    /** The file MD5 checksum of the file */
    public String md5;
    /** The file size in bytes of the file */
    public long size;
    /** The file date the file was last modified */
    public int date;

    public String toString() {
        return "Update[" + name + "," + url + "," + md5 + "," + size + "," + date + "]";
    }
    /**
     * Check if an Update instance is the same as this instance.
     * @param o the Update to compare this instance to
     * @return true if the Update is the same
     * @see Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Update o) {
        return name.compareTo(o.name);
    }
}

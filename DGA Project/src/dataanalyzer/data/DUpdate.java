package dataanalyzer.data;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.util.MD5;
import dataanalyzer.entity.Update;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import dataanalyzer.listeners.CompletionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Retrieves update information from a server. Downlaods an XML file containing
 * information on the latest versions of the applications files (on the eserver).
 * When a update has been recived completion listeners are signaled.
 * @author David Neilsen
 */
public class DUpdate {

    private DGA system;
    private DownloadThread downloadThread;
    private String updateFile;
    private String updateServer;
    private String updateDirectory;
    private String linkURL;
    private LinkedHashSet<CompletionListener> completionListeners = new LinkedHashSet<CompletionListener>();

    public DUpdate(DGA system) {
        this.system = system;
        updateDirectory = (String) system.settingsManager.findValue("updateDirectory");
        updateServer = (String) system.settingsManager.findValue("updateServer");
        updateFile = (String) system.settingsManager.findValue("updateFile");
    }

    /**
     * Downloads the update XML file and parses it. Add Update instaces created
     * to the UpdateManager.
     */
    public void check() {
        if (downloadThread == null || !downloading()) {
            downloadThread = new DownloadThread(updateServer + updateFile);
            //(DownloadThread) system.threadManager.create(new DownloadThread());
            downloadThread.start();
        }
    }

    /**
     * Downloads a specific Update and saves it to the update directory.
     * @param update the Update to download
     */
    public void downloadUpdate(Update update) {
        String link = linkURL + "/" + update.name.replaceAll("\\\\", "/");
        link = link.replaceAll(" ", "%20");


        if (updateServer != null) {
            InputStream in = null;
            FileOutputStream out = null;
            try {
                URL url = new URL(link);
                // Read all the file returned by the server
                in = url.openStream();

                // Make sure the directory exists
                File file = new File(updateDirectory + update.name);
                // Create Directory
                File directory = new File(file.getParent());
                if (!directory.exists() || !directory.isDirectory()) {
                    directory.mkdirs();
                }

                // Create file
                out = new FileOutputStream(file);

                //Read the file
                byte[] buf = new byte[4 * 1024]; // 4K buffer
                int bytesRead;
                while ((bytesRead = in.read(buf)) != -1) {
                    out.write(buf, 0, bytesRead);
                }
                in.close();
                out.close();
//                if (system.userInterface.verbose >= UserInterface.ALL) {
                system.userInterface.out("Update file downloaded: " + link);
//                }
            } catch (MalformedURLException ex) {
                system.userInterface.handleException(ex);
            } catch (IOException ex) {
                system.userInterface.handleException(ex);
            }
        }
    }

    /**
     * Returns true if the download thread is currently active.
     * @return true if the download thread is currently active
     */
    public boolean downloading() {
        return downloadThread.isAlive();
    }

    /**
     * Adds a CompletionListener instance that will get notified when a update
     * has sucsessfuly downloaded.
     * @param c the CompletionListener to add
     */
    public void addCompletionListener(CompletionListener c) {
        if (completionListeners != null) {
            completionListeners.add(c);
        }
    }

    /**
     * Sets the web server URL.
     * @param updateServerURL the web server url
     */
    public void setUpdateServerURL(String updateServerURL) {
        this.updateServer = updateServerURL;
    }

    /**
     * This object seperates download and parsing of the update
     */
    private class DownloadThread extends Thread {

        private String updateServerURL;

        public DownloadThread(String url) {
            this.updateServerURL = url;
        }

        @Override
        public void run() {
            if (updateServerURL != null) {
                BufferedReader in = null;
                BufferedWriter out = null;
                try {
                    URL url = new URL(updateServerURL);
                    // Read all the text returned by the server
                    in = new BufferedReader(new InputStreamReader(url.openStream()));
                    // Create Directory
                    File dir = new File(updateDirectory);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    // Create file 
                    out = new BufferedWriter(new FileWriter(new File(updateDirectory + updateFile)));

                    String str;
                    while ((str = in.readLine()) != null) {
                        // str is one line of text; readLine() strips the newline character(s)
                        out.write(str);
                    }
                    in.close();
                    out.close();
                    system.userInterface.out("Update downloaded.", UserInterface.ALL);
                    // Start the XML parser
                    try {
                        UpdatePaser updatePaser = new UpdatePaser(system, new File(updateDirectory + updateFile));
                        updatePaser.parse();
                    } catch (Exception ex) {
                        system.userInterface.handleException(ex);
                    }
                } catch (MalformedURLException ex) {
                    system.userInterface.handleError(ex.getMessage());
                } catch (IOException ex) {
                    system.userInterface.handleError(ex.getMessage());
                }
            }
            if (completionListeners != null) {
                for (CompletionListener c : completionListeners) {
                    c.complete(null);
                }
            }
        }
    }

    /**
     * This class parsers the update XML file.
     */
    private class UpdatePaser extends DefaultHandler {

        /** File to parse */
        private File file;
        /** The main DGA object */
        private DGA system;

        /**
         * Initializes variables.
         * @param system the main DGA object
         * @param file the file to parse
         */
        public UpdatePaser(DGA system, File file) {
            this.system = system;
            this.file = file;
        }

        /**
         * Starts the parser.
         * @throws java.lang.Exception if the SAXParser cannot be instantiated.
         */
        public void parse() throws Exception {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this);
        }

        /** {@inheritDoc} */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.compareTo("file") == 0) {
                Update update = new Update();
                update.name = attributes.getValue(attributes.getIndex("name"));
//                update.url = attributes.getValue(attributes.getIndex("url"));
                update.md5 = attributes.getValue(attributes.getIndex("md5"));
                update.size = Long.parseLong(attributes.getValue(attributes.getIndex("size")));
//                update.date = Integer.parseInt(attributes.getValue(attributes.getIndex("date")));
                try {
                    if (!validate(update)) {
                        system.updateManager.add(update);
                    }
                } catch (IOException ex) {
                    system.userInterface.handleException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    system.userInterface.handleException(ex);
                }
            } else if (qName.compareTo("version") == 0) {
                String version = attributes.getValue(attributes.getIndex("number"));
                system.updateManager.setVersion(version);
            } else if (qName.compareTo("link") == 0) {
                linkURL = attributes.getValue(attributes.getIndex("url"));
            }
        }

        /**
         * Returns if the update file is up to date
         * @param update the update to check
         * @return false if the file needs updating, otherwise true
         */
        private boolean validate(Update update) throws IOException, NoSuchAlgorithmException {
            File updateFile = new File(update.name);
            if (!updateFile.exists()) {
                system.userInterface.out("File does not exist:" + updateFile);
                return false;
            } else if (updateFile.length() != update.size) {
                system.userInterface.out("File size does not match:" + updateFile);
                return false;
            } else if (!MD5.getMD5Checksum(updateFile.getAbsolutePath()).equals(update.md5)) {
                system.userInterface.out("File checksum does not match:" + updateFile);
                return false;
            }
            return true;
        }
    }
}

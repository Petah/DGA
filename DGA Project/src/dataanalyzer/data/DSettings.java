package dataanalyzer.data;

import dataanalyzer.DGA;
import dataanalyzer.entity.Setting;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class loads and saves the settings.xml file.
 * @author David Neilsen
 */
public class DSettings {

    /**
     * Saves all settings in XML format to a file.
     * @param system the main DGA object
     * @param file the file to save the settings to
     */
    public static void save(DGA system, File file) {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(file));

            // Write xml to file
            out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
            out.println("<settings>");

            Iterator<String> i = system.settingsManager.get().keySet().iterator();
            while (i.hasNext()) {
                Setting s = system.settingsManager.find(i.next());

                //Convert value to correct type
                //Easy types
                if (s.type.equals("String") ||
                        s.type.equals("Integer") ||
                        s.type.equals("Double") ||
                        s.type.equals("Float") ||
                        s.type.equals("Boolean") ||
                        s.type.equals("Byte")) {
                    out.println("<setting name=\"" + s.name + "\" value=\"" + s.value + "\" type=\"" + s.type + "\" />");
                } //Point
                else if (s.type.equals("Point")) {
                    Point p = (Point) s.value;
                    out.println("<setting name=\"" + s.name + "\" x=\"" + p.x + "\" y=\"" + p.y + "\" type=\"" + s.type + "\" />");
                }
            }

            out.println("</settings>");
            out.close();
        } catch (IOException ex) {
            system.userInterface.handleError(ex.getMessage());
        }
    }

    /**
     * Loads the settings from a the XML file.
     * @param system the main DGA object
     * @param file the XML file to load
     */
    public static void load(DGA system, File file) {
        try {
            SettingsPaser paser = new SettingsPaser(system, file);
            paser.parse();
        } catch (Exception ex) {
            system.userInterface.handleException(ex);
        }
    }

    /**
     * This class parsers the settings XML file.
     */
    private static class SettingsPaser extends DefaultHandler {

        /** File to parse */
        private File file;
        /** The main DGA object */
        private DGA system;

        /**
         * Initializes the parser.
         * @param system the main DGA object
         * @param file the file to parse
         */
        public SettingsPaser(DGA system, File file) {
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
            try {
                parser.parse(file, this);
            } catch (SAXException sAXException) {
                system.userInterface.handleError("Settings XML parse error: " + sAXException.getMessage());
            } catch (IOException iOException) {
                system.userInterface.handleError("Settings XML IO error: " + iOException.getMessage());
            }
        }

        /** {@inheritDoc} */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.compareTo("setting") == 0) {
                Setting setting = new Setting();
                setting.name = attributes.getValue(attributes.getIndex("name"));
                setting.type = attributes.getValue(attributes.getIndex("type"));

                //Convert value to correct type
                //String
                if (setting.type.equals("String")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = value;
                } //Integer
                else if (setting.type.equals("Integer")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = Integer.valueOf(value);
                } //Double
                else if (setting.type.equals("Double")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = Double.valueOf(value);
                } //Float
                else if (setting.type.equals("Float")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = Float.valueOf(value);
                } //Byte
                else if (setting.type.equals("Byte")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = Byte.valueOf(value);
                } //Point
                else if (setting.type.equals("Point")) {
                    int x = Integer.parseInt(attributes.getValue(attributes.getIndex("x")));
                    int y = Integer.parseInt(attributes.getValue(attributes.getIndex("y")));
                    setting.value = new Point(x, y);
                } //Boolean
                else if (setting.type.equals("Boolean")) {
                    String value = attributes.getValue(attributes.getIndex("value"));
                    setting.value = Boolean.valueOf(value);
                }
                system.settingsManager.add(setting);
            }
        }
    }
}

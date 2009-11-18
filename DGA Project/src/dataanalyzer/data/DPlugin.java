package dataanalyzer.data;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.Plugin;
import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import java.io.*;
import java.lang.reflect.Method;
import java.util.zip.*;
import java.net.*;

// FIXME: DGA.jar must be rebuilt after loading a plugin because JAVA searches the jar for classes not the class directory
import java.util.ArrayList;

/**
 * This class loads plugins and adds them to the PluginManager.
 * @author David Neilsen
 */
public class DPlugin {

    /**
     * Load a plugin from a the specified file name.
     * @param fileName the file name of the plugin to load
     * @param system the main DGA system
     * @return the loaded Plugin object
     */
    public static Plugin load(String fileName, DGA system) {
        return load(new File(fileName), system);
    }

    /**
     * Load a plugin from a the specified file.
     * @param file the file to load
     * @param system the main DGA system
     * @return the loaded Plugin object
     */
    public static Plugin load(File file, DGA system) {
        // FIXME: Hack to make extracted directory no show an error
        if (file.isDirectory()) {
            return null;
        }
        Plugin plugin = new Plugin(file);
        unzipPlugin(system, plugin, file);
        system.pluginManager.add(plugin);
        return plugin;
    }

    /**
     * Loads all plugins in the plugin directory as defined by the settings.
     * @param system the main DGA system
     */
    public static void loadAll(DGA system) {

        File dir = new File((String) system.settingsManager.find("pluginDirectory").value);

        //Set the classpath
        try {
            String classpathDirectory = (String) system.settingsManager.find("algorithmDirectory").value;
            ClassPathHacker.addFile(classpathDirectory);
        } catch (IOException ex) {
            system.userInterface.handleException(ex);
        }

        String[] children = dir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory{
            system.userInterface.out("No plugins found in " + dir.getAbsolutePath(), UserInterface.MIN);
        } else {
            for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];
                load(dir + "\\" + filename, system);
                system.userInterface.out("Plugin found: " + dir + "\\" + filename, UserInterface.ALL);
            }
        }
    }

    /**
     * Unzips a plugin's contents.
     * @param system the main DGA system
     * @param plugin the plugin object to add algorithms to
     * @param zipfile the file of the plugin
     */
    private static void unzipPlugin(DGA system, Plugin plugin, File zipfile) {
        ArrayList<ClassEntry> classEntrys = new ArrayList<ClassEntry>();
        OutputStream out = null;
        ZipInputStream in = null;
        String outputDirectory = (String) system.settingsManager.find("algorithmDirectory").value;
        try {
            // Open the ZIP file
            in = new ZipInputStream(new FileInputStream(zipfile));

            while (true) {
                // Get the first entry
                ZipEntry entry = in.getNextEntry();
                if (entry == null) {
                    break;
                }
                //Create directorys 
                File outputDir = new File(outputDirectory + entry.getName());
                if (entry.isDirectory()) {
//                    File outputDir = new File(outputDirectory + entry.getName());
//                    outputDir.mkdirs();
                    continue;
                } else {
                    outputDir = outputDir.getParentFile();
                    outputDir.mkdirs();
                    outputDir.deleteOnExit();
                }

                // Open the output file
                File outputFile = new File(outputDirectory + entry.getName());
//                system.userInterface.out(outputFile.getAbsolutePath());
                out = new FileOutputStream(outputFile);

                system.userInterface.out("Found " + entry + " in plugin (" + zipfile + ")", UserInterface.ALL);


                // Transfer bytes from the ZIP file to the output file
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Close the stream
                out.close();

                system.userInterface.out("Extracted " + entry.getName() + " from plugin (" + zipfile + ") to " + outputDir, UserInterface.MAX);

                classEntrys.add(new ClassEntry(entry.getName(), outputFile));
                outputFile.deleteOnExit();
            }
            in.close();
        } catch (FileNotFoundException ex) {
            system.userInterface.handleException(ex);
            system.userInterface.handleError("File not found (" + zipfile + ") while attempting to load plugin.");
        } catch (IOException ex) {
            system.userInterface.handleException(ex);
        } finally {
            try {
                // Close the streams
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                system.userInterface.handleException(ex);
            }
        }
        
        loadClasses(system, plugin, zipfile, classEntrys);
    }

    private static void loadClasses(DGA system, Plugin plugin, File zipfile, ArrayList<ClassEntry> classEntrys) {

        for (ClassEntry classEntry : classEntrys) {
            //Get class name
            String name = classEntry.entryName.replace(".class", "");
            name = name.replaceAll("/", ".");

            //http://www.exampledepot.com/egs/java.lang/LoadClass.html
            // Attempt to load the class
            if (classEntry.outputFile.toString().endsWith(".class")) {
                try {
                    // Convert File to a URL
                    URL url = classEntry.outputFile.toURI().toURL();
                    URL[] urls = new URL[]{url};
//system.userInterface.out(classEntry.outputFile.d);
                    // Create a new class loader with the directory
                    ClassLoader cl = new URLClassLoader(urls);


                    // Load the class
                    Class cls = cl.loadClass(name);

                    //Check if the class is an algorithm
                    if (cls.getSuperclass() != null && cls.getSuperclass().getName().equals("dataanalyzer.entity.Algorithm")) {
                        Object o = cls.newInstance();
                        if (o instanceof Algorithm) {
                            Algorithm a = (Algorithm) o;
                            plugin.add(a);

                            system.userInterface.out("Loaded algorithm " + a.getName() + " from plugin (" + zipfile + ")", UserInterface.MIN);

                        } else {
                            system.userInterface.handleError("Unhandled class (" + o + ") found in plugin (" + zipfile + ")", UserInterface.MIN);

                        }
                    }
                } catch (NoClassDefFoundError ex) {
                    system.userInterface.handleError("No class definition found (" + name + ") for plugin system (" + zipfile + ")");
                } catch (ClassNotFoundException ex) {
                    system.userInterface.handleError("Class not found (" + name + ") for plugin system (" + zipfile + ")");
                } catch (InstantiationException ex) {
                    system.userInterface.handleError("Instantiation Exception (" + name + ") for plugin system (" + zipfile + ")");
                } catch (IllegalAccessException ex) {
                    system.userInterface.handleError("Illegal Access Exception (" + name + ") for plugin system (" + zipfile + ")");
                } catch (Exception ex) {
                    system.userInterface.handleException(ex);
                }
            }
        }
    }
}
// TODO: Referance this class
class ClassPathHacker {

    private static final Class[] parameters = new Class[]{URL.class};

    public static void addFile(String s) throws IOException {
        File f = new File(s);
        addFile(f);
    }//end method

    public static void addFile(File f) throws IOException {
        addURL(f.toURI().toURL());
    }//end method

    public static void addURL(URL u) throws IOException {

        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{u});
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }//end try catch

    }//end method
}//end class

class ClassEntry {

    public String entryName;
    public File outputFile;

    public ClassEntry(String entryName, File outputFile) {
        this.entryName = entryName;
        this.outputFile = outputFile;
    }
}
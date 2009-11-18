// TODO: Make this (CLI) work
package dataanalyzer;
//
import dataanalyzer.cli.CResourceManager;
import dataanalyzer.cli.CUpdateManager;
import dataanalyzer.data.DPlugin;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.listeners.SystemListener;
//
//
public class CLI extends UserInterface implements CompletionListener {

    DGA system;

    public CLI() {
        system = new DGA(this);
        //Load plugins
        DPlugin.loadAll(system);
    }

    public void complete(Object o) {
        if (o instanceof AlgorithmProcess) {
            out(((AlgorithmProcess) o).getOutput().getOutput());
        }
    }

    public void collectOutput(SystemListener systemListener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sendOutput(Output o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.processCommand(args);
    }
//

    public void processCommand(String[] args) {

        if (args.length == 0) {
            printHelp(this);
        }
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
//            //Out put help
//            if (s.compareTo("-h") == 0 || s.compareTo("/?") == 0) {
//                printHelp(cli);
//            }
//
//            //Output thread information
////            if (s.compareTo("-t") == 0 || s.compareTo("-threads") == 0) {
////                CThreadManager.printThreads(system);
////            } 
////            else 
//            //Output memory information
//            if (s.compareTo("-m") == 0 || s.compareTo("-memory") == 0) {
//                CResourceManager.printMemory(system);
////                CResourceManager.printUsage();
//            } //Set the verbosity level
//            else if (s.compareTo("-v") == 0 || s.compareTo("-verbose") == 0) {
//                if (++i < args.length) {
//                    String nextWord = args[i];
//                    if (nextWord.equalsIgnoreCase("none")) {
//                        cli.verbose = UserInterface.NONE;
//                    }
//                    if (nextWord.equalsIgnoreCase("min")) {
//                        cli.verbose = UserInterface.MIN;
//                    }
//                    if (nextWord.equalsIgnoreCase("max")) {
//                        cli.verbose = UserInterface.MAX;
//                    }
//                    if (nextWord.equalsIgnoreCase("all")) {
//                        cli.verbose = UserInterface.ALL;
//                    }
//                }
//            } //Load plugin file
//            else if (s.compareTo("-p") == 0 || s.compareTo("-plugin") == 0) {
//                while (++i < args.length) {
//                    String nextWord = args[i];
//                    if (nextWord.charAt(0) == '-') {
//                        i--;
//                        break;
//                    }
//                    system.pluginManager.add(DPlugin.load(nextWord, system));
//                }
//            } //Run an algorithm
            if (s.compareTo("-r") == 0 || s.compareTo("-run") == 0) {
                while (++i < args.length) {
                    String nextWord = args[i];
                    if (nextWord.charAt(0) == '-') {
                        i--;
                        break;
                    }
                    boolean found = false;
                    for (Algorithm a : system.algorithmManager.get()) {
                        if (nextWord.compareTo(a.getName()) == 0) {
                            AlgorithmProcess process = a.createProcess();
                            process.addCompletionListener(this);
                            system.processManager.run(process);
                            found = true;
                        }
                    }
                    if (!found) {
                        handleError("Algorithm not found from (-run " + nextWord + ")");
                    }
                }
            }
//            } else if (s.compareTo("-u") == 0 || s.compareTo("-update") == 0) {
//                cli.out("Checking for updates");
//
//                while (++i < args.length) {
//                    String nextWord = args[i];
//                    if (nextWord.charAt(0) == '-') {
//                        i--;
//                        break;
//                    }
//                    system.updateManager.setUpdateServer(nextWord);
//                    cli.out(" (" + nextWord + ")");
//                }
//                system.updateManager.check();
//                long t = 0;
//                while (system.updateManager.isChecking()) {
//                    if (t < System.currentTimeMillis()) {
//                        t = System.currentTimeMillis() + 1000;
//                        cli.out(".");
//                    }
//                }
//                cli.out("");
//                CUpdateManager.printUpdates(system);
//            } //Test switches
////            else if (s.compareTo("-s") == 0 || s.compareTo("-server") == 0) {
////                SampleServerImpl.main(args);
////            } else if (s.compareTo("-c") == 0 || s.compareTo("-client") == 0) {
////                SampleClient.main(args);
////            } 
////            else if (s.compareTo("-test") == 0) {
////                //Test cases
////                Plugin test = DPlugin.test();
////                system.pluginManager.add(test);
////                CPluginManager.printPlugins(system);
////                for (Algorithm a : test.get()) {
////                    a.run();
////                }
        }
    }
//
//    //RMI test
//    //ComputeEngine.main(args);
//    //ComputePi.main("localhost", 45);
//
//
//    }
//
//    public void collectOutput(SystemListener systemListener) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//

    private static void printHelp(UserInterface cli) {
        cli.out("Invalid command line switch!");
//        cli.out("Command line switches:");
//        cli.out("  -r | -run <algorithm name>[, <algorithm name>][...]");
//        cli.out("      Runs the specified algorithm. ");
//        cli.out("  -p | -plugin <plug-in file>[, <plug-in file>][...]");
//        cli.out("      Load a plug-in from a zip or jar file.");
//        cli.out("  -i | -input <algorithm name> <parameters>[, <parameters>][...]");
//        cli.out("      Collect input for an algorithm, The parameters will be specific to the ");
//        cli.out("      algorithm but could include input/output files and other settings.");
//        cli.out("  -v | -verbose <none, min, max, all>");
//        cli.out("      Set the output verbosity for system functions. ");
//        cli.out("      none: will only output algorithm specific information. ");
//        cli.out("      all: will output every status report from system functions.");
//        cli.out("  -t | -threads");
//        cli.out("      Triggers DGA to output information about threads running in the system.");
//        cli.out("  -m | -memory");
//        cli.out("      Triggers DGA to output memory information.");
//        cli.out("  -c | -cpu");
//        cli.out("      Trigger DGA to output CPU information.");
//        cli.out("  -c|-connect <IP>[, <IP>][...]");
//        cli.out("      Connects a network node if available.");
//        cli.out("      The run command should also be included with the command.");
//        cli.out("  -s | -server");
//        cli.out("      Starts DGA as a node server and waits for connections.");
    }
//
//    public void print(String s) {
//        System.err.print(s);
//    }

    public void handleError(String s) {
        System.err.println(s);
    }

    public void handleException(Throwable ex) {
        ex.printStackTrace();
        System.exit(1);
    }

    public void out(Object o) {
        System.out.println(o.toString());
    }
}
package dataanalyzer.cli;

import dataanalyzer.DGA;
//import dataanalyzer.manager.ThreadManager;
import dataanalyzer.entity.Memory;
import java.util.ArrayList;
import java.lang.Thread;

//printUsage method imports
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CResourceManager {

    public static void printMemory(DGA system) {
        Memory m = system.resourceManager.getMemory();
        system.userInterface.out("Memory information:");
        system.userInterface.out("  Free:      " + m.getFree());
        system.userInterface.out("  Used:      " + m.getUsed());
        system.userInterface.out("  Allocated: " + m.getAllocated());
        system.userInterface.out("  Maximum:   " + m.getMaximum());
    }
    //http://java.sun.com/j2se/1.5.0/docs/api/java/lang/management/ManagementFactory.html
//    public static void printUsage() {
//        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
//        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
//            method.setAccessible(true);
//            if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
//                Object value;
//                try {
//                    value = method.invoke(operatingSystemMXBean);
//                } catch (Exception e) {
//                    value = e;
//                } // try
//                System.out.println(method.getName() + " = " + value);
//            } // if
//        } // for
//    }
}
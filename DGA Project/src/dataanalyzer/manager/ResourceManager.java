package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.Memory;
// TODO: Add extended resource monitoring
/**
 * Manager for system resouce objects. Gets the current memory status.
 * @author David Neilsen
 */
public class ResourceManager {

    private DGA system;
    private Memory memory = new Memory();
//    private ArrayList<CPU> cpus = new ArrayList<CPU>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public ResourceManager(DGA system) {
        this.system = system;
    }

    /**
     * Returns the current memory status.
     * @return the current memory status
     */
    public Memory getMemory() {
        memory.update();
        return memory;
    }

//    public void add(CPU cpu) {
//        cpus.add(cpu);
//    }
//
//    public ArrayList<CPU> get() {
//        return cpus;
//    }
    @Override
    public String toString() {
        return "Resource Manager";
    }
}

package dataanalyzer.entity;

import dataanalyzer.interfaces.IInformation;
import java.io.Serializable;

/**
 * This class contains a refernce to an Object that has been output from another
 * Object which creates an output to owner relationship. The Output can also have 
 * a name associated with it which is return with the toString() method.
 * @author David Neilsen
 */
public class Output<O> implements Serializable, IInformation {

    /** The output Object */
    private Object output;
    /** The owner Object */
    private O owner;
    /** The name associated with the output */
    private String name;

    /**
     * Initializes the Output variables.
     * @param output the output Object
     * @param owner the owner Object
     */
    public Output(Object output, O owner) {
        this.output = output;
        this.owner = owner;
    }

    /**
     * Initializes the Output variables.
     * @param output the output Object
     * @param owner the owner Object
     * @param name the name associated with the output
     */
    public Output(Object output, O owner, String name) {
        this.output = output;
        this.owner = owner;
        this.name = name;
    }

    /**
     * Get the name associated with the output or the an encapsulated string with
     * the owners toString() method result if the name is null.
     * @return either the name associated with the output or "Output[owner.toString()]"
     */
    @Override
    public String toString() {
        if (name != null) {
            return name;
        }
        return "Output[" + owner.toString() + "]";
    }

    /**
     * Returns information about the output if it is a instance of IInformation or
     * else returns the result of toString()
     * @return the information about the output if it is a instance of IInformation or
     * else returns the result of toString()
     */
    public String getInformation() {
        if (output instanceof IInformation) {
            return ((IInformation) output).getInformation();
        }
        return toString();
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(O owner) {
        this.owner = owner;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}

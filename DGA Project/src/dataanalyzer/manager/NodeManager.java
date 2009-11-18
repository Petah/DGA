package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.Node;
import java.util.ArrayList;

public class NodeManager extends Manager<ArrayList<Node>, Node> {

    private DGA system;
    private ArrayList<Node> nodes = new ArrayList<Node>();

    public NodeManager(DGA system) {
        this.system = system;
    }

    public void add(Node n) {
        nodes.add(n);
        update();
    }

    @Override
    public void remove(Node n) {
        nodes.remove(n);
        update();
    }

    @Override
    public Node get(int i) {
        return nodes.get(i);
    }

    @Override
    public int size() {
        return nodes.size();
    }

    public ArrayList<Node> get() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Network Nodes";
    }
}

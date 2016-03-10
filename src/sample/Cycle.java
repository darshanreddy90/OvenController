package sample;

import java.util.LinkedList;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class holds the details of cycle, which could be saved and reused later
 */
public class Cycle {
    private String name;
    private LinkedList<CycleStep> cycleSteps;


    public Cycle(String name) {
        this.name = name;
        this.cycleSteps = new LinkedList<CycleStep>();
    }

    public void addStep(CycleStep step) {
        this.cycleSteps.add(step);
    }

    public void deleteStep(CycleStep step) {
        this.cycleSteps.remove(step);
    }

    public String toString() {
        return null;
    }

    public LinkedList<CycleStep> getCycleSteps() {
        return cycleSteps;
    }
}

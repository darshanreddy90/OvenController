package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class holds the details of cycle, which could be saved and reused later
 */
public class Cycle {
    private String name;
    private LinkedList<CycleStep> cycleSteps;
    private CycleStep currentStep;
    private CycleStep previousStep;
    private CycleStep nextStep;


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

    public CycleStep getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(CycleStep currentStep) {
        this.currentStep = currentStep;
    }

    public CycleStep getPreviousStep() {
        return previousStep;
    }

    public void setPreviousStep(CycleStep previousStep) {
        this.previousStep = previousStep;
    }

    public CycleStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(CycleStep nextStep) {
        this.nextStep = nextStep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Cycle> cycleLoader() throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new File("C:\\Users\\dxr141430\\Documents\\GitHub\\OvenController\\src\\sample\\data.txt"));
        List<Cycle> cycles = new ArrayList<>();
        while (scanner.hasNextLine()){
            String row = scanner.nextLine();
            cycles.add(Cycle.convertToObjectFromString(row));
        }
        return cycles;
    }

    private static Cycle convertToObjectFromString(String row) {
        Scanner scanner = new Scanner(row);
        Cycle cycle = new Cycle(scanner.next());
        while (scanner.hasNext()) {
            CycleStep step = new CycleStep(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            cycle.addStep(step);
        }
        return cycle;
    }
}

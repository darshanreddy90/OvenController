package sample;

import java.util.Iterator;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class takes a cycle and a oven and executes the cycle
 */
public class CycleExecutor {
    private Cycle cycle;
    private Oven oven;
    private CycleStep currentStep;
    private Iterator<CycleStep> stepIterator;

    public CycleExecutor(Cycle cycle, Oven oven) {
        this.cycle = cycle;
        this.oven = oven;
        this.oven.setCurrentCycle(cycle);
        this.stepIterator = cycle.getCycleSteps().iterator();
    }


    public void handleTick() {
        if(oven.getCurrentCycle() == null) {
            return;
        }
        if(currentStep!= null && currentStep.getTimeRemaining() == 0) {
//            currentStep.resetTimeRemaining();
            if(stepIterator.hasNext()) {
                setCurrentStep(stepIterator.next());
            }
        } else if (currentStep == null && stepIterator.hasNext()) {
            setCurrentStep(stepIterator.next());
        }else {
            currentStep.setTimeRemaining(currentStep.getTimeRemaining() - 1);
        }

        // Turn oven ON or OFF based on the temperature adjustment in the current step
        // currently doing it randomly
        /*if(Math.random()*10%6 < 3){
            oven.turnOvenOff();
        }else {
            oven.turnOvenOn();
        }*/
        checkGrowth();
    }

    private  void setCurrentStep(CycleStep step) {
        cycle.setPreviousStep(currentStep);
        cycle.setCurrentStep(step);
        currentStep = step;
    }

    public void checkGrowth(){
        double growthRate= (Math.abs(currentStep.getStartTemp()-currentStep.getEndTemp())/currentStep.getTimeInMinutes());
        double currentTime= currentStep.getTimeInMinutes()-currentStep.getTimeRemaining();
        double expectedTemp=  currentStep.getStartTemp() + (growthRate*(currentTime));
        if(oven.getCurrentOvenTemperature()>expectedTemp){
            oven.turnOvenOff();
        }else if(oven.getCurrentOvenTemperature()<expectedTemp){
            oven.turnOvenOn();
        }else if(currentStep.getTimeRemaining()!=0){
            //do nothing if both are same
            oven.turnOvenOn();
        }
    }
}

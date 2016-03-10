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
        this.stepIterator = cycle.getCycleSteps().iterator();
    }


    public void handleTick() {
        if(currentStep!= null && currentStep.getTimeRemaining() == 0) {
            currentStep.resetTimeRemaining();
            if(stepIterator.hasNext()) {
                currentStep = stepIterator.next();
            }
        }
        // Turn oven ON or OFF based on the temperature adjustment in the current step
        // currently doing it randomly
        if(Math.random()*10%6 < 3){
            oven.turnOvenOff();
        }else {
            oven.turnOvenOn();
        }
    }
}

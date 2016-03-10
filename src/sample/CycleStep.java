package sample;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class is used to represent each step in the cycle. to hold the details of the step in each cycle.
 */
public class CycleStep {

    private double startTemp; // Start temperature of step
    private double endTemp; //End temperature of step
    private double timeInMinutes; // time for completing the step
    private double timeRemaining; // how much time has elapsed in this step

    public CycleStep(double startTemp, double endTemp, double timeInMinutes) {
        this.startTemp = startTemp;
        this.endTemp = endTemp;
        this.timeInMinutes = timeInMinutes;
        this.timeRemaining = timeInMinutes;
    }

    public double getStartTemp() {
        return startTemp;
    }

    public void setStartTemp(double startTemp) {
        this.startTemp = startTemp;
    }

    public double getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(double timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public double getEndTemp() {
        return endTemp;
    }

    public void setEndTemp(double endTemp) {
        this.endTemp = endTemp;
    }

    public double getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(double timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public void handleTimerTick() {
        this.timeRemaining--;
    }

    public void resetTimeRemaining() {
        this.timeRemaining = this.timeInMinutes;
    }
}

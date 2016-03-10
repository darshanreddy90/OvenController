package sample;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class represents the oven, it provides functions to turn the oven on / off and also handles timer clicks appropriately
 */
public class Oven {
    private boolean isOvenOn; // To hold the state of the over ON/ oFF. true when oven is ON
    private double currentOvenTemperature; // To hold the current temperature of the oven

    public Oven() {
        this.isOvenOn = false;
        this.currentOvenTemperature = 0;
    }

    public Oven(boolean isOvenOn, int currentOvenTemperature) {
        this.isOvenOn = isOvenOn;
        this.currentOvenTemperature = currentOvenTemperature;
    }

    public void turnOvenOn() {
        this.isOvenOn = true;
    }
    
    public void turnOvenOff() {
        this.isOvenOn = false;
    }

    public double getCurrentOvenTemperature() {
        return  this.currentOvenTemperature;
    }
    /*
    * if the oven is on the temp is increase by 1 after time tick, otherwise decrease by 0.5
    *
    * */
    public  void handleTimerTick() {
        if (isOvenOn) {
            currentOvenTemperature += 1;
        } else {
            if(currentOvenTemperature != 0)
            currentOvenTemperature -= 0.5;
        }
        System.out.println(currentOvenTemperature);
    }
}

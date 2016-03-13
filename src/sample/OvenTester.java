package sample;

import java.io.FileNotFoundException;

/**
 * Created by dxr141430 on 3/9/2016.
 */
public class OvenTester {
    public static void main(String[] args) throws FileNotFoundException {
        Oven oven = new Oven(false, 0);
        CycleStep c1= new CycleStep(0,5,5);
        CycleStep c2= new CycleStep(5,10,5);

        Cycle cycle = Cycle.cycleLoader().get(0);
//        cycle.addStep(c1);
//        cycle.addStep(c2);
        CycleExecutor executor = new CycleExecutor(cycle, oven);
        OvenRunner runner = new OvenRunner(oven, executor);
        runner.start();


    }
}

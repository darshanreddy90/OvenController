package sample;

/**
 * Created by dxr141430 on 3/9/2016.
 */
public class OvenTester {
    public static void main(String[] args) {
        Oven oven = new Oven(false, 0);
        CycleStep c1= new CycleStep(0,5,5);
        CycleStep c2= new CycleStep(3,10,5);

        Cycle cycle = new Cycle("this");
        cycle.addStep(c1);
        cycle.addStep(c2);
        CycleExecutor executor = new CycleExecutor(cycle, oven);
        OvenRunner runner = new OvenRunner(oven, executor);
        runner.start();


    }
}

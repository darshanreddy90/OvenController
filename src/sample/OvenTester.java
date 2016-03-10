package sample;

/**
 * Created by dxr141430 on 3/9/2016.
 */
public class OvenTester {
    public static void main(String[] args) {
        Oven oven = new Oven(false, 900);
        Cycle cycle = new Cycle("this");
        CycleExecutor executor = new CycleExecutor(cycle, oven);
        OvenRunner runner = new OvenRunner(oven, executor);
        runner.start();


    }
}

package sample;

import java.util.Timer;

/**
 * Created by dxr141430 on 3/9/2016.
 *
 * This class is the runner for oven , which supplies the ticks using  a new thread.
 */
public class OvenRunner implements Runnable {
    private int seconds; //current value of second which is mocked as minute
    private Thread t;
    private Oven oven;
    private CycleExecutor cycleExecutor;

    public OvenRunner(Oven oven) {
        this.oven = oven;
    }

    public OvenRunner(Oven oven, CycleExecutor cycleExecutor) {
        this.oven = oven;
        this.cycleExecutor = cycleExecutor;
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(1000);
                this.oven.handleTimerTick();
                if(this.cycleExecutor != null) {
                    this.cycleExecutor.handleTick();
                }
            }while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        if(t == null) {
            t = new Thread(this, "ovenThread");
            t.start();
        }
    }
}

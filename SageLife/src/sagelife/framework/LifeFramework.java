package sagelife.framework;

import sagelife.canvas.Controls;
import sagelife.canvas.Grid;
import sagelife.misc.Globals;

/**
 * Contains the game logic and main thread.
 *
 * @author Darin Beaudreau
 */
public class LifeFramework {
    // Member variables.

    public Grid grid;
    public Controls controls;

    public LifeFramework() {
        grid = new Grid(this);
        controls = new Controls(this);
    }

    public void update() {
        //System.out.println("Update called...");
    }

    public void initializeThread() {
        Globals.mainThread = new Runnable() {
            @Override
            public void run() {
                while (Globals.simulationRunning) {
                    try {
                        update();
                        grid.repaint();

                        System.out.println("Thread running...");

                        Thread.sleep(Globals.SLEEP_TIME);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
    }

    public void startThread() {
        new Thread(Globals.mainThread).start();
    }

    public void stopThread() {
        Globals.mainThread = null;
    }
}

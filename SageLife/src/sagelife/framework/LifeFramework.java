package sagelife.framework;

import sagelife.misc.Globals;

/**
 * Contains the game logic and main thread.
 *
 * @author Darin Beaudreau
 */
public class LifeFramework {

    public static void initializeThread() {
        Globals.mainThread = new Runnable() {
            @Override
            public void run() {
                while (Globals.simulationRunning) {
                    try {
                        // Update call goes here.
                        // TODO: Figure out how to call repaint on Grid from here.

                        Thread.sleep(Globals.SLEEP_TIME);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
    }

    public static void startThread() {
        new Thread(Globals.mainThread).start();
    }
    
    public static void stopThread() {
        Globals.mainThread = null;
    }
}

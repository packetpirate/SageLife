package sagelife.misc;

/**
 * Contains global variables.
 * @author Darin Beaudreau
 */
public class Globals {
    // Constant variables.
    public static final int SLEEP_TIME = 1000; // Thread sleep time in ms.
    
    // Non-constant globals.
    public static volatile boolean simulationRunning = false;
    public static Runnable mainThread;
}

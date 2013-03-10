package sagelife.misc;

import java.awt.Dimension;

/**
 * Contains global variables.
 *
 * @author Darin Beaudreau
 */
public class Globals {
    // Constant variables.
    public static final int SLEEP_TIME = 250; // Thread sleep time in ms.
    // Non-constant globals.
    public static volatile boolean simulationRunning = false;
    public static Runnable mainThread;
    public static Dimension gridSize;
    public static int cellWidth = 10;
    public static int cellHeight = 10;
    public static int rowLength = 0;
    public static int colLength = 0;
    
    // Default values to determine game logic.
    public static int cellsLonelyMin = 0; // The lower bound number determining when cells die of loneliness.
    public static int cellsLonelyMax = 1; // The upper bound number determining when cells die of loneliness.
    public static int cellsCrowdedMin = 4; // The lower bound number determining when cells die of overcrowding.
    public static int cellsSurviveMin = 2; // The lower bound number determining how many neighbors a cell needs to survive.
    public static int cellsSurviveMax = 3; // The upper bound number determining how many neighbors a cell needs to survive.
    public static int cellsBorn = 3; // The number that determines how many neighbors an inactive cell needs to be born.
}

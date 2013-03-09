package sagelife.framework;

import sagelife.canvas.Controls;
import sagelife.canvas.Grid;
import sagelife.framework.structures.Cell;
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
    
    public Cell[][] cells;

    public LifeFramework() {
        grid = new Grid(this);
        controls = new Controls(this);
        
        // Initialize the cell array and then create each individual cell.
        int cellRows = Globals.gridSize.height / Globals.cellHeight;
        int cellCols = Globals.gridSize.width / Globals.cellWidth;
        cells = new Cell[cellRows][cellCols];
        for(int r = 0; r < cellRows; r++) {
            for(int c = 0; c < cellCols; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    public void update() {
        //System.out.println("Update called...");
        int cellRowLength = cells.length;
        int cellColLength = cells[0].length;
        
        /**
         * First pass is to kill off any crowded or lonely cells. Second pass checks if any
         * cells need to be born. It needs to be done this way because if you check for both
         * at the same time, when you check if cells need to be born, it will get incorrect
         * data because it is checking what is currently being modified by the first pass.
         * This can lead to false output.
         **/
        // First pass, kill any cells that are lonely or overcrowded.
        for(int r = 0; r < cellRowLength; r++) {
            for(int c = 0; c < cellColLength; c++) {
                cells[r][c].update(getCellNeighbors(r,c), true);
            }
        }
        // Second pass, check if any new cells should be born.
        for(int r = 0; r < cellRowLength; r++) {
            for(int c = 0; c < cellColLength; c++) {
                cells[r][c].update(getCellNeighbors(r,c), false);
            }
        }
    }
    
    private int getCellNeighbors(int row, int col) {
        int n = 0; // The number of neighbors.
        int rowLength = cells.length;
        int colLength = cells[0].length;
        
        // Check each of the eight positions around the cell, if they exist. If they are alive, increment n.
        // Each position checked is relative to the current cell, so (-1,-1) means the cell
        // at the position 1 less than the current row and col value corresponding to the cell being checked.
        if((!((row - 1) < 0))&&(!((col - 1) < 0))) { // Check position (-1,-1)(Top-Left)
            if(cells[row - 1][col - 1].isAlive()) n++;
        }
        if(!((row - 1) < 0)) { // Check position (-1,0)(Top-Center)
            if(cells[row - 1][col].isAlive()) n++;
        }
        if((!((row - 1) < 0))&&(!((col + 1) > colLength))) { // Check position (-1,+1)(Top-Right)
            if(cells[row - 1][col + 1].isAlive()) n++;
        }
        if(!((col + 1) > colLength)) { // Check position (0,+1)(Center-Right)
            if(cells[row][col + 1].isAlive()) n++;
        }
        if((!((row + 1) > rowLength))&&(!((col + 1) > colLength))) { // Check position (+1,+1)(Bottom-Right)
            if(cells[row + 1][col + 1].isAlive()) n++;
        }
        if(!((row + 1) > rowLength)) { // Check position (+1,0)(Bottom-Center)
            if(cells[row + 1][col].isAlive()) n++;
        }
        if((!((row + 1) > rowLength))&&(!((col - 1) < 0))) { // Check position (+1,-1)(Bottom-Left)
            if(cells[row + 1][col - 1].isAlive()) n++;
        }
        if(!((col - 1) < 0)) { // Check position (0,-1)(Center-Left)
            if(cells[row][col - 1].isAlive()) n++;
        }
        
        return n;
    }
    
    /**
     * Called by the Clear button of the Controls form to reset the cell grid.
     **/
    public void killAllCells() {
        int cellRowLength = cells.length;
        int cellColLength = cells[0].length;
        
        for(int r = 0; r < cellRowLength; r++) {
            for(int c = 0; c < cellColLength; c++) {
                cells[r][c].kill();
            }
        }
    }

    public void initializeThread() {
        Globals.mainThread = new Runnable() {
            @Override
            public void run() {
                while (Globals.simulationRunning) {
                    try {
                        update();
                        grid.repaint();

                        //System.out.println("Thread running...");

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

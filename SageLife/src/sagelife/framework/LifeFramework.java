package sagelife.framework;

import java.awt.geom.Rectangle2D;
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
    
    public int generations = 0;

    public LifeFramework() {
        grid = new Grid(this);
        controls = new Controls(this);
        
        // Initialize the cell array and then create each individual cell.
        int cellRows = Globals.gridSize.height / Globals.cellHeight;
        int cellCols = Globals.gridSize.width / Globals.cellWidth;
        cells = new Cell[cellRows][cellCols];
        for(int r = 0; r < cellRows; r++) {
            for(int c = 0; c < cellCols; c++) {
                double x = c * Globals.cellWidth;
                double y = r * Globals.cellHeight;
                double w = Globals.cellWidth;
                double h = Globals.cellHeight;
                Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
                cells[r][c] = new Cell(rect);
            }
        }
        
        Globals.rowLength = cells.length;
        Globals.colLength = cells[0].length;
    }

    public void update() {
        // Update the number of neighbors for each cell.
        for(int r = 0; r < Globals.rowLength; r++) {
            for(int c = 0; c < Globals.colLength; c++) {
                cells[r][c].neighbors = getCellNeighbors(r,c);
            }
        }
        
        // Update the cell state by applying game logic.
        for(int r = 0; r < Globals.rowLength; r++) {
            for(int c = 0; c < Globals.colLength; c++) {
                cells[r][c].update();
            }
        }
        
        generations++;
        controls.getGenerations().setText("Generations: " + generations);
    }
    
    public void toggleCell(int row, int col) {
        Cell c = cells[row][col];
        
        if(c.isAlive()) c.kill();
        else c.revive();
        
        grid.repaint();
    }
    
    private int getCellNeighbors(int row, int col) {
        int n = 0; // The number of neighbors.
        int rowLength = Globals.rowLength;
        int colLength = Globals.colLength;
        
        // Check each of the eight positions around the cell, if they exist. If they are alive, increment n.
        // Each position checked is relative to the current cell, so (-1,-1) means the cell
        // at the position 1 less than the current row and col value corresponding to the cell being checked.
        if((!((row - 1) < 0))&&(!((col - 1) < 0))) { // Check position (-1,-1)(Top-Left)
            if(cells[row - 1][col - 1].isAlive()) n++;
        }
        if(!((row - 1) < 0)) { // Check position (-1,0)(Top-Center)
            if(cells[row - 1][col].isAlive()) n++;
        }
        if((!((row - 1) < 0))&&(!((col + 1) > (colLength - 1)))) { // Check position (-1,+1)(Top-Right)
            if(cells[row - 1][col + 1].isAlive()) n++;
        }
        if(!((col + 1) > (colLength - 1))) { // Check position (0,+1)(Center-Right)
            if(cells[row][col + 1].isAlive()) n++;
        }
        if((!((row + 1) > (rowLength - 1)))&&(!((col + 1) > (colLength - 1)))) { // Check position (+1,+1)(Bottom-Right)
            if(cells[row + 1][col + 1].isAlive()) n++;
        }
        if(!((row + 1) > (rowLength - 1))) { // Check position (+1,0)(Bottom-Center)
            if(cells[row + 1][col].isAlive()) n++;
        }
        if((!((row + 1) > (rowLength - 1)))&&(!((col - 1) < 0))) { // Check position (+1,-1)(Bottom-Left)
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
        int cellRowLength = Globals.rowLength;
        int cellColLength = Globals.colLength;
        
        for(int r = 0; r < cellRowLength; r++) {
            for(int c = 0; c < cellColLength; c++) {
                cells[r][c].kill();
            }
        }
        
        generations = 0;
        controls.getGenerations().setText("Generations: " + generations);
        
        grid.repaint();
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

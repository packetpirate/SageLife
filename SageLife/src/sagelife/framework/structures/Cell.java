package sagelife.framework.structures;

import sagelife.misc.Globals;

/**
 * This class represents the cells that are drawn on the grid.
 * @author Darin Beaudreau
 */
public class Cell {
    // Member variables.
    private boolean alive;
    public boolean isAlive() { return alive; }
    public void kill() { alive = false; }
    
    public Cell() {
        alive = false;
    }
    
    /**
     * Updates the status of the cell based on the number of neighbors it has.
     * @param n The number of neighbors the cell currently has.
     * @param firstPass A boolean that tells the update() method whether or not 
     * this is the first time checking for new cells. New cells should only be born 
     * after the game handles the ones that need to die.
     **/
    public void update(int n, boolean firstPass) {
        // If the cell is alive or this is the first pass, check for loneliness and overcrowding. If either occur, kill the cell.
        if(alive || firstPass) {
            if(((n >= Globals.cellsLonelyMin)&&(n <= Globals.cellsLonelyMax))||
                (n >= Globals.cellsCrowdedMin)) alive = false;
        } else { // Otherwise, if the cell is inactive, check if it has the right number of neighbors to be born.
            if(n == Globals.cellsBorn) alive = true;
        }
    }
}

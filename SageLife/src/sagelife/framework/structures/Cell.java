package sagelife.framework.structures;

import java.awt.geom.Rectangle2D;
import sagelife.misc.Globals;

/**
 * This class represents the cells that are drawn on the grid.
 * @author Darin Beaudreau
 */
public class Cell extends Rectangle2D.Double {
    // Member variables.
    private boolean alive;
    public boolean isAlive() { return alive; }
    public void kill() { alive = false; }
    public void revive() { alive = true; }
    
    public int neighbors;
    
    public Cell() {
        this(new Rectangle2D.Double(0, 0, Globals.cellWidth, Globals.cellHeight));
    }
    
    public Cell(Rectangle2D rect) {
        super(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        alive = false;
        neighbors = 0;
    }
    
    /**
     * Updates the status of the cell based on the number of neighbors it has.
     * @param n The number of neighbors the cell currently has.
     * @param firstPass A boolean that tells the update() method whether or not 
     * this is the first time checking for new cells. New cells should only be born 
     * after the game handles the ones that need to die.
     **/
    public void update() {
        // If the cell is alive or this is the first pass, check for loneliness and overcrowding. If either occur, kill the cell.
        if(alive) {
            if(((neighbors >= Globals.cellsLonelyMin)&&(neighbors <= Globals.cellsLonelyMax))||
                (neighbors >= Globals.cellsCrowdedMin)) alive = false;
        } else { // Otherwise, if the cell is inactive, check if it has the right number of neighbors to be born.
            if(neighbors == Globals.cellsBorn) alive = true;
        }
    }
}

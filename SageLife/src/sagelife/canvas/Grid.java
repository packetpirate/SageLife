package sagelife.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import sagelife.framework.LifeFramework;
import sagelife.misc.Globals;

/**
 * Contains the grid to which the cells are drawn.
 *
 * @author Darin Beaudreau
 */
public class Grid extends javax.swing.JPanel {

    // Member variables.
    private LifeFramework framework;

    /**
     * Creates new form Grid
     */
    public Grid(LifeFramework framework) {
        this.framework = framework;

        this.setPreferredSize(new Dimension(400, 300));
        Globals.gridSize = this.getPreferredSize();
        //System.out.println("Dimensions: " + Globals.gridSize.width + "x" + Globals.gridSize.height);

        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw the cells. For now, yellow is used as the cell color. Inactive cells are not drawn to save time.
        int cellRowLength = framework.cells.length;
        int cellColLength = framework.cells[0].length;
        for(int r = 0; r < cellRowLength; r++) {
            for(int c = 0; c < cellColLength; c++) {
                if(framework.cells[r][c].isAlive()) {
                    // Create a rectangle to contain the cell's location in the grid.
                    double x = c * Globals.cellWidth;
                    double y = r * Globals.cellHeight;
                    double x2 = x + Globals.cellWidth;
                    double y2 = y + Globals.cellHeight;
                    Rectangle2D rect = new Rectangle2D.Double(x, y, x2, y2);

                    g2d.setColor(Color.YELLOW);
                    g2d.fill(rect);
                }
            }
        }

        drawGridLines(g2d);

        //System.out.println("Grid repainted...");
    }

    private void drawGridLines(Graphics2D g2d) {
        for (int x = Globals.cellWidth; x < Globals.gridSize.width; x += Globals.cellWidth) {
            for (int y = Globals.cellHeight; y < Globals.gridSize.height; y += Globals.cellHeight) {
                g2d.drawLine(x, 0, x, Globals.gridSize.height);
                g2d.drawLine(0, y, Globals.gridSize.width, y);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

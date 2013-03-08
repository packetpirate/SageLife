package sagelife;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import sagelife.framework.LifeFramework;

/**
 * This is a modified version of my original "Conway's Game of Life" clone. This
 * version offers better visuals and more options for simulation, including
 * variable rules for cells to simulate on.
 *
 * @author Darin Beaudreau
 */
public class SageLife extends JApplet {

    private static final float VERSION = 0.1f;
    private JFrame mainWindow = new JFrame("SageLife v" + VERSION);
    private Container contentPane = mainWindow.getContentPane();
    private LifeFramework framework = new LifeFramework();
    //private Grid grid = new Grid();
    //private Controls controls = new Controls();

    public static void main(String[] args) {
        SageLife sagelife = new SageLife();

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        sagelife.createLayout();
    }

    public void createLayout() {
        SpringLayout layout = new SpringLayout();

        // Set the constraints of the grid component.
        layout.putConstraint(SpringLayout.WEST, framework.grid, 0, SpringLayout.WEST, mainWindow);
        layout.putConstraint(SpringLayout.NORTH, framework.grid, 0, SpringLayout.NORTH, mainWindow);

        // Set the constraints of the control component.
        layout.putConstraint(SpringLayout.WEST, framework.controls, 0, SpringLayout.WEST, mainWindow);
        layout.putConstraint(SpringLayout.NORTH, framework.controls, 0, SpringLayout.SOUTH, framework.grid);

        // Set the constraints of the main window.
        layout.putConstraint(SpringLayout.EAST, contentPane, 0, SpringLayout.EAST, framework.grid);
        layout.putConstraint(SpringLayout.SOUTH, contentPane, 0, SpringLayout.SOUTH, framework.controls);

        contentPane.setLayout(layout);

        contentPane.add(framework.grid);
        contentPane.add(framework.controls);

        contentPane.setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}

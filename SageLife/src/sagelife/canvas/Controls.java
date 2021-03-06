package sagelife.canvas;

import sagelife.framework.LifeFramework;
import sagelife.misc.Globals;

/**
 *
 * @author Darin Beaudreau
 */
public class Controls extends javax.swing.JPanel {

    // Member variables.
    private LifeFramework framework;

    /**
     * Creates new form Controls
     */
    public Controls(LifeFramework framework) {
        this.framework = framework;

        initComponents();
    }
    
    public javax.swing.JLabel getGenerations() {
        return generationsLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generationsLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        premadeButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setPreferredSize(new java.awt.Dimension(400, 50));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        generationsLabel.setFont(new java.awt.Font("Meiryo UI", 0, 12)); // NOI18N
        generationsLabel.setForeground(new java.awt.Color(255, 255, 255));
        generationsLabel.setText("Generations: 0");
        add(generationsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, 22));

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 30));

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        add(resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 30));

        premadeButton.setText("Premade");
        add(premadeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, 30));

        settingsButton.setText("...");
        add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 30, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // Toggles the start button's text.
        if (Globals.simulationRunning) {
            // Set the running boolean to false and stop the thread.
            Globals.simulationRunning = false;
            framework.stopThread();

            startButton.setText("Start");
        } else {
            // Set the running boolean to true and initialize the thread.
            Globals.simulationRunning = true;
            framework.initializeThread();
            framework.startThread();

            startButton.setText("Stop");
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        framework.killAllCells();
    }//GEN-LAST:event_resetButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel generationsLabel;
    private javax.swing.JButton premadeButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}

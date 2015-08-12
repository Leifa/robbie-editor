package de.leifaktor.robbie.editor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ToolBarButton extends JButton {
    
    private MainWindow mainWindow;
    
    public ToolBarButton(String imageName, String actionCommand, String toolTipText, String altText,
            MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        // Look for the image.
        String imgLocation = "res/editor/" + imageName;

        // Create and initialize the button.
        this.setActionCommand(actionCommand);
        this.setToolTipText(toolTipText);
        this.setFocusPainted(false);
        this.addActionListener(new MyActionListener());

        ImageIcon icon = new ImageIcon(imgLocation);
        if (icon.getImage() != null) { //image found
            this.setIcon(icon);
            this.setBorderPainted(false);
            this.setBorder(new EmptyBorder(2,10,2,10));
        } else {
            this.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }
    }
    
    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("save")) {
                mainWindow.savePressed();
            } else if (e.getActionCommand().equals("open")) {
                mainWindow.loadPressed();
            } else if (e.getActionCommand().equals("new")) {
                mainWindow.newEpisodePressed();
            } else if (e.getActionCommand().equals("play")) {
                mainWindow.playPressed();
            } 
        }       
    }
    


}

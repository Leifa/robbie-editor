package de.leifaktor.robbie.editor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class ToolBarToggleButton extends JToggleButton {
    
    private MainWindow mainWindow;

    public ToolBarToggleButton(String imageName, String imageSecondImageName, String actionCommand,
            String toolTipText, String altText, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setFocusable(false);
        //Look for the images.
        String imgLocation = "res/editor/" + imageName;
        String imgLocation2 = "res/editor/" + imageSecondImageName;

        //Create and initialize the button.
        this.setActionCommand(actionCommand);
        this.setToolTipText(toolTipText);
        this.setFocusPainted(false);
        this.addActionListener(new MyActionListener());

        ImageIcon icon = new ImageIcon(imgLocation);
        ImageIcon icon2 = new ImageIcon(imgLocation2);
        if (icon.getImage() != null) { //image found
            this.setIcon(icon);
            this.setSelectedIcon(icon2);
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
            if (e.getActionCommand().equals("room_floor")) {
                mainWindow.toggleRoomFloorView();
            } else if (e.getActionCommand().equals("save")) {
                mainWindow.savePressed();
            } else if (e.getActionCommand().equals("open")) {
                mainWindow.loadPressed();
            } else if (e.getActionCommand().equals("new")) {
                mainWindow.newEpisodePressed();
            } else if (e.getActionCommand().equals("brush")) {
                mainWindow.toggleBrush();
            } else if (e.getActionCommand().equals("play")) {
                mainWindow.playPressed();
            } else if (e.getActionCommand().equals("grid")) {
                mainWindow.toggleGrid();
            }
        }       
    }
    
    
}

package de.leifaktor.robbie.editor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.Floor;

public class FloorView extends JPanel {

    public static final int EXPAND_FLOOR_WIDTH = 40;

    /**
     * A reference to the main window that acts as controller.
     */

    private MainWindow mainWindow;

    /**
     * The floor that is currently displayed.
     */

    private Floor currentFloor;

    /**
     * The room width in tiles, will be used for drawing.
     */
    private int roomWidth;

    /**
     * The room height in tiles, will be used for drawing.
     */
    private int roomHeight;

    /**
     * The scale is the multiplier for drawing the rooms.
     */

    private int scale = 5;


    public FloorView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.addMouseListener(new MyMouseListener());
    }

    /**
     * Sets the floor that should be displayed.
     * @param f
     */

    public void setFloor(Floor f) {
        this.currentFloor = f;
        this.roomWidth = mainWindow.getModel().getEpisode().getRoomWidth();
        this.roomHeight = mainWindow.getModel().getEpisode().getRoomHeight();
        repaint();
        
    }

    /**
     * Paints the floor.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        if (currentFloor != null) {
            // DRAW FLOORS
            int floorWidth = currentFloor.getWidth();
            int floorHeight = currentFloor.getHeight();
            g.setColor(Color.BLACK);
            for (int i = 0; i < floorWidth; i++) {
                for (int j = 0; j < floorHeight; j++) {
                    g.drawRect(EXPAND_FLOOR_WIDTH+i*roomWidth*scale,
                            EXPAND_FLOOR_WIDTH+j*roomHeight*scale,
                            roomWidth*scale,
                            roomHeight*scale);
                }
            }


            // DRAW EXPANSION AREAS
            g.setColor(Color.CYAN);
            // WEST
            g.fillRect(0, EXPAND_FLOOR_WIDTH, EXPAND_FLOOR_WIDTH, floorHeight*roomHeight*scale);
            // NORTH
            g.fillRect(EXPAND_FLOOR_WIDTH, 0,  floorWidth*roomWidth*scale, EXPAND_FLOOR_WIDTH);
            // SOUTH
            g.fillRect(EXPAND_FLOOR_WIDTH, EXPAND_FLOOR_WIDTH+floorHeight*roomHeight*scale, 
                    floorWidth*roomWidth*scale, EXPAND_FLOOR_WIDTH);
            // EAST
            g.fillRect(EXPAND_FLOOR_WIDTH+floorWidth*roomWidth*scale, EXPAND_FLOOR_WIDTH,
                    EXPAND_FLOOR_WIDTH, floorHeight*roomHeight*scale);
        }
    }

    public class MyMouseWheelListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
    
    public class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int w = roomWidth*scale;
            int h = roomHeight*scale;
            if (x >= EXPAND_FLOOR_WIDTH && x <= EXPAND_FLOOR_WIDTH+w*currentFloor.getWidth() &&
                    y >= EXPAND_FLOOR_WIDTH && y <= EXPAND_FLOOR_WIDTH+h*currentFloor.getHeight()) {
                x -= EXPAND_FLOOR_WIDTH;
                y -= EXPAND_FLOOR_WIDTH;
                x /= w;
                y /= h;
                mainWindow.selectRoom(currentFloor, x, y);
            }

            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            
        }
        
    }

}

package de.leifaktor.robbie.editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.Room;
import de.leifaktor.robbie.editor.model.RoomLayer;


public class RoomView extends JPanel {

    private MainWindow mainWindow;

    private int roomWidth;
    private int roomHeight;
    private int tileWidth = 18;
    private int tileHeight = 18;

    private Room currentRoom;

    private RoomLayer currentLayer;

    private boolean drawGrid;

    public RoomView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        addMouseWheelListener(new MyMouseWheelListener());
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        this.setBackground(Color.WHITE);
    }	

    public void setRoom(Room r) {
        this.currentRoom = r;
        this.roomWidth = r.getWidth();
        this.roomHeight = r.getHeight();
        this.currentLayer = r.getLayers().get(0);
        repaint();
    }	

    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);

        // NO ROOM SELECTED
        if (currentRoom == null) {
            if (mainWindow.getModel().getEpisode() != null) {
                int width = mainWindow.getModel().getEpisode().getRoomWidth()*tileWidth;
                int height = mainWindow.getModel().getEpisode().getRoomHeight()*tileHeight;
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, width, height);
            }
        } else {
            // DRAW OUTSIDE
            for (int x = -2; x < roomWidth+2; x++) {
                for (int y = -2; y < roomHeight+2; y++) {
                    boolean outside = false;
                    if (x < 0) {
                        outside = true;
                    } else if (x >= roomWidth) {
                        outside = true;
                    }
                    if (y < 0) {
                        outside = true;
                    } else if (y >= roomHeight) {
                        outside = true;
                    }
                    if (outside) {
                        g.setColor(new Color(128,128,128,128));
                        g.fillRect((x+2)*tileWidth,(y+2)*tileHeight,tileWidth,tileHeight);
                    } else {
                        for (RoomLayer layer: currentRoom.getLayers()) {
                            if (layer.getTile(x, y) != null) {
                                g.drawImage(layer.getTile(x, y).getDefaultTileGraphic().getImage(),
                                        (x+2)*tileWidth,
                                        (y+2)*tileHeight,
                                        tileWidth,
                                        tileHeight,
                                        null);
                            }
                        }
                    }
                }
            }

            // DRAW GRID
            if (drawGrid) {
                g.setColor(Color.GRAY);
                for (int i = 0; i < roomWidth+5; i++) {                
                    g.drawLine(i*tileWidth, 0, i*tileWidth, (roomHeight+4)*tileHeight);
                }
                for (int i = 0; i < roomHeight+5; i++) {
                    g.drawLine(0, i*tileHeight, (roomWidth+4)*tileWidth, i*tileHeight);
                }
            }

            // DRAW FRAME
            g.setColor(Color.BLACK);
            g.drawLine(2*tileWidth, 0, 2*tileWidth, (roomHeight+4)*tileHeight);
            g.drawLine((roomWidth+2)*tileWidth, 0, (roomWidth+2)*tileWidth, (roomHeight+4)*tileHeight);
            g.drawLine(0, 2*tileHeight, (roomWidth+4)*tileWidth, 2*tileHeight);
            g.drawLine(0,(roomHeight+2)*tileHeight, (roomWidth+4)*tileWidth, (roomHeight+2)*tileHeight);
        }
    }

    /**
     * Switches the grid on or off.
     * @param drawGrid If it is true, then the grid will be drawn, otherwise not.
     */

    public void setDrawGrid(boolean drawGrid) {
        this.drawGrid = drawGrid;
        repaint();
    }

    /**
     * Toggles whether the grid will be drawn or not.
     */

    public void toggleGrid() {
        setDrawGrid(!this.drawGrid);        
    }

    public class MyMouseWheelListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int rot = e.getWheelRotation();
            tileHeight -= rot;
            tileWidth -= rot;
            repaint();
        }
    }

    public class MyMouseListener implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / tileWidth - 2;
            int y = e.getY() / tileHeight - 2;
            mainWindow.fieldClicked(currentLayer, x, y);
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX() / tileWidth - 2;
            int y = e.getY() / tileHeight - 2;
            mainWindow.fieldClicked(currentLayer, x, y);
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
        


    }







}

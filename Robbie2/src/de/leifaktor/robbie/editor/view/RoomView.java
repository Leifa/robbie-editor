package de.leifaktor.robbie.editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import de.leifaktor.robbie.editor.model.Floor;
import de.leifaktor.robbie.editor.model.Room;
import de.leifaktor.robbie.editor.model.RoomLayer;

/**
 * This component shows a Room. The room to show is specified by a floor and the coordinates of the
 * room inside the floor, so also the adjacent rooms can be displayed.
 */

public class RoomView extends JPanel {

    private MainWindow mainWindow;
    
    private ImageLoader imageLoader;

    /**
     * The floor that the current room is in.
     */

    private Floor floor;

    /**
     * The coordinates of the current room inside the floor.
     */
    private int roomX, roomY;

    /**
     * Helper fields for convenience: The roomWidth and the roomHeight. They will be set every time
     * the setRoom-methode is called.
     */

    private int roomWidth, roomHeight;

    /**
     * Helper reference for convenience: Points to the current room
     */

    private Room currentRoom;

    /**
     * The size that is used to display the tiles
     */

    private int fieldSize = 18;



    private RoomLayer currentLayer;



    private boolean drawGrid;

    /**
     * Creates the RoomView. 
     * @param mainWindow
     */

    public RoomView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        addMouseWheelListener(new MyMouseWheelListener());
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        createKeyBindings();
    }	

    public void setRoom(Floor floor, int x, int y) {
        Room room = floor.getRoom(x, y);
        this.floor = floor;
        this.roomX = x;
        this.roomY = y;
        this.currentRoom = room;
        if (room != null) {
            this.roomWidth = room.getWidth();
            this.roomHeight = room.getHeight();
            this.currentLayer = room.getLayers().get(0);
        }        
        repaint();
    }

    /**
     * Creates the Key Bindings for navigation, so the arrow keys can be used to navigate through
     * the rooms.
     */

    private void createKeyBindings() {
        InputMap im = this.getInputMap();
        ActionMap am = this.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "RightArrow");
        im.put(KeyStroke.getKeyStroke("LEFT"), "LeftArrow");
        im.put(KeyStroke.getKeyStroke("UP"), "UpArrow");
        im.put(KeyStroke.getKeyStroke("DOWN"), "DownArrow");

        am.put("RightArrow", new ArrowAction("RightArrow"));
        am.put("LeftArrow", new ArrowAction("LeftArrow"));
        am.put("UpArrow", new ArrowAction("UpArrow"));
        am.put("DownArrow", new ArrowAction("DownArrow"));
    }

    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        this.imageLoader = mainWindow.getImageLoader();

        if (mainWindow.getModel().getEpisode() == null) return;
        if (floor == null) return;

        // NO ROOM SELECTED
        if (currentRoom == null) {
            drawIfRoomNull(g);
        }

        // DRAW ROOM INCLUDING NEIGHBORS
        for (int x = -2; x < roomWidth+2; x++) {
            for (int y = -2; y < roomHeight+2; y++) {
                int fieldRoomX = roomX;
                int fieldRoomY = roomY;
                int fieldX = x;
                int fieldY = y;
                boolean outside = false;
                if (x < 0) {
                    fieldRoomX--;
                    fieldX += roomWidth;
                    outside = true;
                } else if (x >= roomWidth) {
                    fieldRoomX++;
                    fieldX -= roomWidth;
                    outside = true;
                }
                if (y < 0) {
                    fieldRoomY--;
                    fieldY += roomHeight;
                    outside = true;
                } else if (y >= roomHeight) {
                    fieldRoomY++;
                    fieldY -= roomHeight;
                    outside = true;
                }               

                Room roomOfThisField = floor.getRoom(fieldRoomX, fieldRoomY);
                drawFieldWithAllLayers(g, roomOfThisField, fieldX, fieldY, x, y);

                if (outside) {                    
                    g.setColor(new Color(128,128,128,128));
                    g.fillRect((x+2)*fieldSize,(y+2)*fieldSize,fieldSize,fieldSize); 
                }
            }
        }

        // DRAW GRID
        if (drawGrid) drawGrid(g);

        // DRAW FRAME
        drawFrame(g);
    }

    /**
     * Draws a field of a room with all layers
     * @param g
     * @param roomOfThisField
     * @param fieldX
     * @param fieldY
     * @param x
     * @param y
     */

    private void drawFieldWithAllLayers(Graphics g, Room roomOfThisField, int fieldX, int fieldY,
            int x, int y) {
        if (roomOfThisField != null) {
            for (RoomLayer layer: roomOfThisField.getLayers()) {
                if (layer.getTile(fieldX, fieldY) != null) {
                    BufferedImage image = imageLoader.getTileImage(layer.getTile(fieldX, fieldY));
                    drawTile(g,
                            image,
                            x+2,
                            y+2);
                }
            }
        }
    }

    /**
     * Draw something when the selected room is null
     * @param g
     */

    private void drawIfRoomNull(Graphics g) {
        int width = mainWindow.getModel().getEpisode().getRoomWidth()*fieldSize;
        int height = mainWindow.getModel().getEpisode().getRoomHeight()*fieldSize;
        g.setColor(Color.GRAY);
        g.fillRect(fieldSize*2, fieldSize*2, width, height);
        g.setColor(Color.BLACK);
        g.drawString(roomX + "/" + roomY, fieldSize*4, fieldSize*4);
        g.drawString("Room not existent. Click to create!", fieldSize*4, fieldSize*4+30);
    }

    /**
     * Draw the grid
     * @param g
     */

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < roomWidth+5; i++) {                
            g.drawLine(i*fieldSize, 0, i*fieldSize, (roomHeight+4)*fieldSize);
        }
        for (int i = 0; i < roomHeight+5; i++) {
            g.drawLine(0, i*fieldSize, (roomWidth+4)*fieldSize, i*fieldSize);
        }
    }

    /**
     * Draw the frame around the room, that is the line between a room and its neighbors.
     * @param g
     */

    private void drawFrame(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(2*fieldSize, 0, 2*fieldSize, (roomHeight+4)*fieldSize);
        g.drawLine((roomWidth+2)*fieldSize, 0, (roomWidth+2)*fieldSize, (roomHeight+4)*fieldSize);
        g.drawLine(0, 2*fieldSize, (roomWidth+4)*fieldSize, 2*fieldSize);
        g.drawLine(0,(roomHeight+2)*fieldSize, (roomWidth+4)*fieldSize, (roomHeight+2)*fieldSize);
    }

    /**
     * Draws an image, with x and y being tile coordinates.
     * @param g
     * @param image
     * @param x
     * @param y
     */

    private void drawTile(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image,
                x*fieldSize,
                y*fieldSize,
                fieldSize,
                fieldSize,
                null);
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
            fieldSize -= rot;
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
            int x = e.getX() / fieldSize - 2;
            int y = e.getY() / fieldSize - 2;
            if (x >= 0 && x < roomWidth && y >= 0 && y < roomHeight) {
                mainWindow.fieldClicked(currentLayer, x, y);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX() / fieldSize - 2;
            int y = e.getY() / fieldSize - 2;
            if (x >= 0 && x < roomWidth && y >= 0 && y < roomHeight) {
                mainWindow.fieldClicked(currentLayer, x, y);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    private class NavigateRoomsKeyListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0) {

        }

        @Override
        public void keyReleased(KeyEvent arg0) {

        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setRoom(floor, roomX, roomY+1);
            }
        }

    }

    public class ArrowAction extends AbstractAction {
        private String cmd;

        public ArrowAction(String cmd) {
            this.cmd = cmd;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cmd.equalsIgnoreCase("LeftArrow")) {
                roomX--;
            } else if (cmd.equalsIgnoreCase("RightArrow")) {
                roomX++;  
            } else if (cmd.equalsIgnoreCase("UpArrow")) {
                roomY--;    
            } else if (cmd.equalsIgnoreCase("DownArrow")) {
                roomY++;    
            }
            setRoom(floor, roomX, roomY);
            repaint();
        }
    }

}

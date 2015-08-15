package de.leifaktor.robbie.editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.tiles.Tile;

public class TilePicker extends JPanel {

    private MainWindow mainWindow;
    
    private ImageLoader imageLoader;

    private int selX;
    private int selY;
    private int tileWidth;
    private int tileHeight;

    public TilePicker(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.selX = 0;
        this.selY = 0;
        this.tileHeight = 32;
        this.tileWidth = 32;
        this.addMouseListener(new MyMouseListener());
    }	

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.imageLoader = mainWindow.getImageLoader();
        
        if (mainWindow.getModel().getEpisode() == null) return;

        // RECHTECKE
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 40; j++) {
                g.setColor(Color.GRAY);
                g.fillRect(i*tileWidth, j*tileHeight, tileWidth-1, tileHeight-1);
                g.setColor(Color.BLACK);
                g.drawRect(i*tileWidth, j*tileHeight, tileWidth-1, tileHeight-1);
                g.drawString(i + "/" + j, i*tileWidth+3, j*tileHeight+18);
            }
        }

        // TILES
        int x = 0;
        int y = 0;
        for (Tile tile: mainWindow.getModel().getEpisode().getTiles()) {
            System.out.println("tilePicker : tile " + x);
            System.out.println(tile.getDefaultTileGraphic());
            BufferedImage image = imageLoader.getTileImage(tile);
            g.drawImage(image, x*tileWidth, y*tileHeight, tileWidth, tileHeight, null);
            x++;
            if (x > 6) {
                x = 0;
                y++;
            }
        }

        // MARKER
        g.setColor(Color.BLACK);
        g.drawRect(selX*tileWidth-1, selY*tileHeight-1, tileWidth+1, tileHeight+1);
        g.drawRect(selX*tileWidth-4, selY*tileHeight-4, tileWidth+7, tileHeight+7);
        g.setColor(Color.YELLOW);
        g.drawRect(selX*tileWidth-2, selY*tileHeight-2, tileWidth+3, tileHeight+3);
        g.drawRect(selX*tileWidth-3, selY*tileHeight-3, tileWidth+5, tileHeight+5);
    }
    
    public Tile getSelectedTile() {
        try {
            return mainWindow.getModel().getEpisode().getTiles().get(selX+6*selY);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }        
    }

    public class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent arg0) {}

        @Override
        public void mouseExited(MouseEvent arg0) {}

        @Override
        public void mousePressed(MouseEvent e) {
            selX = e.getX() / 32;
            selY = e.getY() / 32;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {}

    }



}

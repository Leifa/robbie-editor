package de.leifaktor.robbie.editor.view.tilesetviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.view.ImageLoader;

public class TileSetViewPanel extends JPanel {
    
    private TileSet tileSet;
    
    private TileSetWindow tileSetWindow;
    
    private ImageLoader imageLoader;
    
    /**
     * The size that is used to display the tiles
     */

    private int fieldSize = 36;
    
    /**
     * 
     */
    
    private int gridSize = 16;

    public TileSetViewPanel(TileSetWindow tileSetWindow) {
        this.setSize(512, 512);
        this.imageLoader = tileSetWindow.getImageLoader();
        this.addMouseWheelListener(new ZoomListener());
    }
    
    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
        this.gridSize = tileSet.getTileSize();
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage tileSetImage = imageLoader.getTileSetImage(tileSet);
        
        int tileSize = tileSet.getTileSize();
        double scale = (fieldSize / (double) tileSize);        
        int widthToDraw = (int) (tileSetImage.getWidth()*scale);
        int heightToDraw = (int) (tileSetImage.getHeight()*scale);
        
        g.drawImage(tileSetImage, 0, 0, widthToDraw, heightToDraw, null);       
        
        int width = tileSetImage.getWidth() / tileSize;
        int height = tileSetImage.getHeight() / tileSize;
        
        // Draw Grid
        int gridSizeToDraw = (int) (gridSize * scale);
        int cols = widthToDraw / gridSizeToDraw + 1;
        int rows = heightToDraw / gridSizeToDraw + 1;
        g.setColor(Color.RED);
        for (int i = 0; i < cols; i++) {                
            g.drawLine(i*gridSizeToDraw, 0, i*gridSizeToDraw, rows*gridSizeToDraw);
        }
        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i*gridSizeToDraw, cols*gridSizeToDraw, i*gridSizeToDraw);
        }

        
    }
    
    public class ZoomListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int rot = e.getWheelRotation();
            fieldSize -= rot;
            repaint();
        }
    }

    public void setGridSize(int newGridSize) {
        this.gridSize = newGridSize;
        repaint();
    }
    
}

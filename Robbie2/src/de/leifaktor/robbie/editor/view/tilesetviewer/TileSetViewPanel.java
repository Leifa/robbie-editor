package de.leifaktor.robbie.editor.view.tilesetviewer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.view.ImageLoader;

public class TileSetViewPanel extends JPanel {
    
    private TileSet tileSet;
    
    private int scale = 2;
    
    private TileSetWindow tileSetWindow;
    
    private ImageLoader imageLoader;

    public TileSetViewPanel(TileSetWindow tileSetWindow) {
        this.setSize(512, 512);
        this.imageLoader = tileSetWindow.getImageLoader();
    }
    
    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = imageLoader.getTileSetImage(tileSet);
        g.drawImage(image,0,0,image.getWidth()*scale, image.getHeight()*scale, null);
    }
    
}

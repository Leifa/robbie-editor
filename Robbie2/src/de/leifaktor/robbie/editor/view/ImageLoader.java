package de.leifaktor.robbie.editor.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import de.leifaktor.robbie.editor.model.Episode;
import de.leifaktor.robbie.editor.model.gfx.TileGraphic;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.model.tiles.Tile;

/**
 * The ImageLoader is part of the view. It actually loads the graphics specified in the episode,
 * so they can be drawn to the screen.
 * @author leif
 *
 */

public class ImageLoader {
    
    private Episode episode;
    
    private Map<TileSet, BufferedImage> tileSetImageMap;
    private Map<TileGraphic, BufferedImage> tileGraphicImageMap;
    
    public ImageLoader(Episode episode) {
        this.episode = episode;
        initTileSetImages();
        initTileGraphics();
    }
    
    /**
     * Returns the image that belongs to the tile.
     * @param tile The tile.
     * @return The image that belongs to the tile, specified by its defaultTileGraphic.
     */
    
    public BufferedImage getTileImage(Tile tile) {
        return tileGraphicImageMap.get(tile.getDefaultTileGraphic());
    }    
    
    /**
     * Loads the images for all tilesets in the episode.
     */
    
    private void initTileSetImages() {
        this.tileSetImageMap = new HashMap<TileSet, BufferedImage>();
        for (TileSet tileSet: episode.getTileSets()) {
            String filename = tileSet.getFilename();
            BufferedImage image = loadImage("res/tileset/" + filename);
            tileSetImageMap.put(tileSet, image);

        }
    }
    
    /**
     * Loads the images for all tile graphics in the episode.
     */
    
    private void initTileGraphics() {
        this.tileGraphicImageMap = new HashMap<TileGraphic, BufferedImage>();
        Iterator it = episode.getTileGraphics().entrySet().iterator();
        while (it.hasNext()) {            
            Map.Entry pair = (Map.Entry) it.next();
            TileGraphic tileGraphic = (TileGraphic) pair.getValue();
            TileSet tileSet = tileGraphic.getTileSet();
            int x = tileGraphic.getX();
            int y = tileGraphic.getY();            
            BufferedImage tileImage = getTileImage(tileSet, x, y);
            tileGraphicImageMap.put(tileGraphic, tileImage);
            it.remove(); // avoids a ConcurrentModificationException
        } 
    }
    
    /**
     * Returns the image at column x and row y.
     * @param x The column
     * @param y The row
     * @return
     */
    
    private BufferedImage getTileImage(TileSet tileSet, int x, int y) {
        int tileSize = tileSet.getTileSize();
        BufferedImage image = tileSetImageMap.get(tileSet);
        return image.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize);
    }
    
    
    /**
     * Loads an image.
     * @param filename
     * @return The image.
     */
    
    private BufferedImage loadImage(String filename) {
        BufferedImage pic = null;
        String path = filename;
        try {            
            pic = ImageIO.read(new File(path));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error loading graphics. " + path);
            System.exit(0);
        }
        return pic;
    }

    public BufferedImage getTileSetImage(TileSet tileSet) {
        return tileSetImageMap.get(tileSet);
    }   

}

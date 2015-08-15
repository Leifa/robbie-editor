package de.leifaktor.robbie.editor.model.gfx;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * This is a model class for a tileset. It holds 
 */
public class TileSet {

    /**
     * The filename of the image file. The file must be located inside "/tileset/".
     */

    private String filename;

    /**
     * The size of a small tile.
     */

    private int tileSize;

    /**
     * This constructor creates a TileSet from the image given by the filename and the given tile
     * width and tile height. The number of columns and rows will be calculated, where only whole
     * tiles are counted.
     * @param image The image.
     * @param tileWidth The tile width.
     * @param tileHeight The tile height.
     */    
    public TileSet(String fileName, int tileSize) {
        this.setFilename(fileName);
        this.tileSize = tileSize;
    }

//    /**
//     * Returns an array of images, starting at column x and row y and then continuing row by row
//     * from left to right and top to bottom.
//     * @param x The starting column.
//     * @param y The starting row.
//     * @param size The number of images to return.
//     * @return
//     */
//    public BufferedImage[] getTileImages(int x, int y, int size) {
//        BufferedImage[] images = new BufferedImage[size];
//        for (int i = 0; i < size; i++) {
//            images[i] = image.getSubimage(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
//            x++;
//            if (x >= 16) {
//                x=0;
//                y++;
//            }
//        }
//        return images;
//    }
//
//    /**
//     * Returns a 2d-array of images, starting at column x and row y, continuing row by row, from
//     * left to right and top to bottom. The resulting array has size [number][size].
//     * @param x
//     * @param y
//     * @param size
//     * @param number
//     * @return
//     */
//    public BufferedImage[][] getTileImageSeries(int x, int y, int size, int number) {
//        BufferedImage[][] images = new BufferedImage[number][];
//        for (int i = 0; i < number; i++) {
//            images[i] = getTileImages(x,y,size);
//            x+=size;
//            while (x >= 16) {
//                x-=16;
//                y++;
//            }
//        }
//        return images;
//    }


    /**
     * Returns the tile size.
     */
    public int getTileSize() {
        return tileSize;
    }

    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }
}


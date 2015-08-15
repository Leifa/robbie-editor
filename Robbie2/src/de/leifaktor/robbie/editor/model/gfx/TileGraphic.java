package de.leifaktor.robbie.editor.model.gfx;

import java.awt.image.BufferedImage;

public class TileGraphic {    
    
    private TileSet tileset;
    
    private int x;
    
    private int y;
    
    public TileGraphic(TileSet tileSet, int x, int y) {
        this.tileset = tileSet;
        this.x = x;
        this.y = y;
    }
    
    public TileSet getTileSet() {
        return tileset;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String toString() {
        return "TileGraphic@" + Integer.toHexString(hashCode()) + "[tileset=" + tileset + " x=" + x + " y=" + y + "]";
    }
 
}

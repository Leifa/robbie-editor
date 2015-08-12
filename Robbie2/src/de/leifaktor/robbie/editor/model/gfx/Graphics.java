package de.leifaktor.robbie.editor.model.gfx;

/**
 * A carries a list with the 
 * @author leif
 *
 */

public class Graphics {
    
    public static final TileSet TILESET = new TileSet("tileset.png", 16, 16);
    public static final TileGraphic EMPTY = new TileGraphic(TILESET,0,0);
    public static final TileGraphic WALL = new TileGraphic(TILESET,1,0);

}

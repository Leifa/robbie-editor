package de.leifaktor.robbie.editor.model.tiles;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;

/**
 * The model class for a Tile.
 * 
 * Every Tile needs a default tile graphic that will be used for displaying the Tile in the editor
 * and in the game.
 * @author leif
 *
 */

public abstract class Tile {

    private TileGraphic defaultTileGraphic;
    
    public void setDefaultTileGraphic(TileGraphic defaultTileGraphic) {
        this.defaultTileGraphic = defaultTileGraphic;
    }
    
    public TileGraphic getDefaultTileGraphic() {
        return this.defaultTileGraphic;
    }
    
}

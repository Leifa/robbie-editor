package de.leifaktor.robbie.editor.model.tiles;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;

public abstract class CustomTile extends Tile {
    
    private String scriptFileLocation;
    
    public CustomTile(TileGraphic defaultTileGraphic) {
        super(defaultTileGraphic);
    }



}

package de.leifaktor.robbie.editor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;
import de.leifaktor.robbie.editor.model.gfx.TileSet;

/**
 * This class provides static methods to create default content into an episode (graphics, tiles,
 * items, sounds...)
 * It will later be replaced by the possibility to load an episode from a file.
 * @author leif
 *
 */

public class DefaultResourceLoader {
    
    public static List<TileSet> createDefaultTileSet() {
        List<TileSet> tileSets = new ArrayList<TileSet>();
        tileSets.add(new TileSet("tileset.png", 16));
        tileSets.add(new TileSet("disks.png", 16));
        return tileSets;
    }
    
    public static Map<String, TileGraphic> createDefaultTileGraphicMap(TileSet tileSet) {        
        Map<String, TileGraphic> tileGraphicMap = new HashMap<String, TileGraphic>();        
        tileGraphicMap.put("EMPTY", new TileGraphic(tileSet,0,0));
        tileGraphicMap.put("WALL", new TileGraphic(tileSet,1,0));
        
        return tileGraphicMap;
    }

}

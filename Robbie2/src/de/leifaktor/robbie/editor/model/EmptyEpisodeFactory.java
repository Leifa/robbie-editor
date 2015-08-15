package de.leifaktor.robbie.editor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.model.tiles.EmptyTile;
import de.leifaktor.robbie.editor.model.tiles.Tile;
import de.leifaktor.robbie.editor.model.tiles.Wall;

/**
 * This class provides a static method to create an empty episode with one floor, one room, one
 * room layer and some default resources.
 * @author leif
 *
 */

public class EmptyEpisodeFactory {
    
    public static Episode createEmptyEpisode(int roomWidth, int roomHeight) {
        // Create an episode and add the default tiles
        Episode episode = new Episode(roomWidth, roomHeight);
        
        // Create the list of default tilesets
        List<TileSet> tileSets = DefaultResourceLoader.createDefaultTileSet();
        
        // Put the list of tilesets to the episode
        episode.setTileSets(tileSets);
        
        // Create the default tile graphics
        Map<String, TileGraphic> tileGraphics = DefaultResourceLoader
                .createDefaultTileGraphicMap(tileSets.get(0));
        
        // Put the Map of tile graphics to the Episode
        episode.setTileGraphics(tileGraphics);
        
        // Create the default tiles
        List<Tile> tiles = createDefaultTileList(tileGraphics);
        
        // Set the list of tiles to the episode
        episode.setTiles(tiles);
        
         // Create a default room
        Room room = RoomFactory.createRoomWithOneLayer(roomWidth, roomHeight, episode.getTiles().get(0));
        
        // Create a floor with this room
        Floor floor = new Floor(1,1);
        floor.setRoom(0, 0, room);
        
        // Create a list of floors containing the one floor
        List<Floor> floors = new ArrayList<Floor>();
        floors.add(floor);
        
        // Create an episode with this one floor
        episode.setFloors(floors);        
        
        // Return the episode
        return episode;        
    }
    
    public static List<Tile> createDefaultTileList(Map<String, TileGraphic> tileGraphics) {
        // Create a new list of tiles
        List<Tile> tiles = new ArrayList<Tile>();
        
        // Add all default tiles to the list
        tiles.add(new EmptyTile(tileGraphics.get("EMPTY")));
        tiles.add(new Wall(tileGraphics.get("WALL")));
        
        // Return the list
        return tiles;
        
    }

}

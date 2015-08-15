package de.leifaktor.robbie.editor.model;

import java.util.List;
import java.util.Map;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.model.tiles.Tile;


public class Episode {
	
	/**
	 * The room height
	 */

	private int roomHeight;
	
	/**
	 * The room width
	 */
	
	private int roomWidth;
	
	/**
	 * The title of the episode
	 */
	
	private String title;
	
	/**
	 * A list of all tiles that occur in this episode
	 */
	
	private List<Tile> tiles;
	
	/**
	 * A list of all tileSets
	 */
	
	private List<TileSet> tileSets;
	
	/**
	 * A map of all tileGraphics. The first component is a unique String for every tileGraphic.
	 */
	
	private Map<String, TileGraphic> tileGraphics;

    /**
	 * The list of floors
	 */
	
	private List<Floor> floors;
	
	/**
	 * Creates a new episode with the specified room size and creates one floor with one empty room
	 * inside.
	 */
	
	public Episode(int roomWidth, int roomHeight) {
		this.roomHeight = roomHeight;
		this.roomWidth = roomWidth;
	}
	
	/////////////////////////////////////////////////////////
	// GETTERS AND SETTERS
	/////////////////////////////////////////////////////////
	
	/**
	 * Returns the name of the episode
	 */
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the name of the episode
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the room height
	 */
	
	public int getRoomHeight() {
		return roomHeight;
	}
	
	/**
	 * Returns the room width
	 */
	
	public int getRoomWidth() {
		return roomWidth;
	}
	
	/**
	 * Returns the floors
	 */
	
	public List<Floor> getFloors() {
		return floors;
	}
	
	/**
	 * Returns the list of tiles
	 */
	
	public List<Tile> getTiles() {
	    return tiles;
	}
	
	/**
	 * Sets the list of tiles
	 * @param createDefaultTileList
	 */

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
    
    /**
     * Sets the list of floors
     * @param floors
     */

    public void setFloors(List<Floor> floors) {
        this.floors = floors;        
    }
    
    public List<TileSet> getTileSets() {
        return tileSets;
    }

    public void setTileSets(List<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    public Map<String, TileGraphic> getTileGraphics() {
        return tileGraphics;
    }

    public void setTileGraphics(Map<String, TileGraphic> tileGraphics) {
        this.tileGraphics = tileGraphics;
    }
    
    
	
}

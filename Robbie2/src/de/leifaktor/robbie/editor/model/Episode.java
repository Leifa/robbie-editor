package de.leifaktor.robbie.editor.model;

import java.util.List;
import java.util.Map;

import de.leifaktor.robbie.editor.model.gfx.TileGraphic;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.model.tiles.Tile;

/**
 * This class represents an episode.
 * @author leif
 *
 */

public class Episode {
	
	/**
	 * The room height.
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
	// GETTERS
	/////////////////////////////////////////////////////////
	
	public String                   getTitle()        {return title;}
	public int                      getRoomHeight()   {return roomHeight;}
    public int                      getRoomWidth()    {return roomWidth;}
    public List<Floor>              getFloors()       {return floors;}
    public List<Tile>               getTiles()        {return tiles;}
    public List<TileSet>            getTileSets()     {return tileSets;}
    public Map<String, TileGraphic> getTileGraphics() {return tileGraphics;}
	
    /////////////////////////////////////////////////////////
    // SETTERS
    /////////////////////////////////////////////////////////
	
	public void setTitle(String title)                                 {this.title = title;}
    public void setFloors(List<Floor> floors)                          {this.floors = floors;}
    public void setTiles(List<Tile> tiles)                             {this.tiles = tiles;}
    public void setTileSets(List<TileSet> tileSets)                    {this.tileSets = tileSets;}
    public void setTileGraphics(Map<String, TileGraphic> tileGraphics) {this.tileGraphics = tileGraphics;}
    
    
	
}

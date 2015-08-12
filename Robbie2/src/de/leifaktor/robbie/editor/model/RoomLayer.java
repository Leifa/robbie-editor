package de.leifaktor.robbie.editor.model;

import java.util.LinkedList;
import java.util.List;

import de.leifaktor.robbie.editor.model.entity.Entity;
import de.leifaktor.robbie.editor.model.tiles.Tile;

public class RoomLayer {
	
	/**
	 * The width of the layer (same as the width of the room)
	 */	
	
	private int roomWidth;
	
	/**
	 * The height of the layer (same as the height of the room)
	 */
	
	private int roomHeight;
	
	/**
	 * The tiles of this layer
	 */
	
	private Tile[] tiles;
	
	/**
	 * The entities in this layer
	 */
	
	private List<Entity> entities;
	
	/**
	 * Creates a layer with the given width and height
	 */

	public RoomLayer(int roomWidth, int roomHeight) {
		tiles = new Tile[roomWidth*roomHeight];
		this.roomWidth = roomWidth;
		this.roomHeight = roomHeight;
		entities = new LinkedList<Entity>();
	}
	
	/**
	 * Returns the Tile at the specified position
	 */
	
	public Tile getTile(int x, int y) {
		return tiles[roomWidth*y+x];
	}
	
	/**
	 * Sets a tile.
	 */
	
	public void setTile(int x, int y, Tile t) {
	    tiles[roomWidth*y + x] = t;
	}
	
}

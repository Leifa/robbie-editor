package de.leifaktor.robbie.editor.model;

import java.util.ArrayList;
import java.util.List;

import de.leifaktor.robbie.editor.model.tiles.EmptyTile;
import de.leifaktor.robbie.editor.model.tiles.Tile;
import de.leifaktor.robbie.editor.model.tiles.Wall;


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
		this.floors = new ArrayList<Floor>();
		this.floors.add(new Floor(1,1));
		this.tiles = new ArrayList<Tile>();
		tiles.add(new EmptyTile());
		tiles.add(new Wall());
	}
	
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
	
}

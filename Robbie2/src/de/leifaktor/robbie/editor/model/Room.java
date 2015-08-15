package de.leifaktor.robbie.editor.model;

import java.util.ArrayList;
import java.util.List;


public class Room {
	
	/**
	 * The width of the room.
	 */	
	
	private int width;
	
	/**
	 * The height of the room.
	 */
	
	private int height;
	
	/**
	 * The RoomLayers
	 * TODO: Ordering?
	 */
	
	private List<RoomLayer> layers;
	
	/**
	 * The name of the room.
	 */
	
	private String name;
	
	/**
	 * Creates a new Room with the specified width and height.
	 * @param width
	 * @param height
	 */
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	//////////////////////////////////////////
	// GETTERS
	//////////////////////////////////////////
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public List<RoomLayer> getLayers() {return layers;}
	
	//////////////////////////////////////////
    // SETTERS
    //////////////////////////////////////////

    public void setLayers(List<RoomLayer> layers) {this.layers = layers;} 
	
	

}

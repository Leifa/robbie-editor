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
	 */
	
	private List<RoomLayer> layers;
	
	/**
	 * The name of the room.
	 */
	
	private String name;
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		layers = new ArrayList<RoomLayer>();
	}
	
	/**
	 * Adds a new layer on top.
	 */
	
	public void addLayer() {
		layers.add(new RoomLayer(width, height));
	}
	
	/**
	 * Removes the specified layer from this room
	 */
	
	public void removeLayer(RoomLayer l) {
		layers.remove(l);
	}
	
	/**
	 * Returns the width of the room
	 */
	
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height of the room
	 */
	
	public int getHeight() {
	    return this.height;
	}
	
	/**
	 * Returns the layers of the room
	 */
	
	public List<RoomLayer> getLayers() {
		return layers;
	}

    public void addLayer(RoomLayer layer) {
        layers.add(layer);        
    }
	
	

}

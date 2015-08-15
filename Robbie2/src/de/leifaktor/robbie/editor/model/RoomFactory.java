package de.leifaktor.robbie.editor.model;

import de.leifaktor.robbie.editor.model.tiles.Tile;

public class RoomFactory {

    public static Room createRoomWithOneLayer(int roomWidth, int roomHeight, Tile tile) {
        RoomLayer layer = LayerFactory.createFilledRoomLayer(roomWidth, roomHeight, tile);
        Room room = new Room(roomWidth, roomHeight);
        room.addLayer(layer);
        return room;
    }
    
}

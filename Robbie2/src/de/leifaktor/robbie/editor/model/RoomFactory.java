package de.leifaktor.robbie.editor.model;

import java.util.ArrayList;
import java.util.List;

import de.leifaktor.robbie.editor.model.tiles.Tile;

public class RoomFactory {

    public static Room createRoomWithOneLayer(int roomWidth, int roomHeight, Tile tile) {
        RoomLayer layer = LayerFactory.createFilledRoomLayer(roomWidth, roomHeight, tile);
        List<RoomLayer> layers = new ArrayList<RoomLayer>();
        layers.add(layer);
        Room room = new Room(roomWidth, roomHeight);
        room.setLayers(layers);
        return room;
    }
    
}

package de.leifaktor.robbie.editor.model;

import de.leifaktor.robbie.editor.model.tiles.Tile;

public class LayerFactory {
    
    /**
     * Returns a RoomLayer filled with one tile.
     * @param roomWidth
     * @param roomHeight
     * @param t
     * @return
     */

    public static RoomLayer createFilledRoomLayer(int roomWidth, int roomHeight, Tile t) {
        RoomLayer layer = new RoomLayer(roomWidth, roomHeight);
        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                layer.setTile(x, y, t);
            }
        }
        return layer;
    }
    
}

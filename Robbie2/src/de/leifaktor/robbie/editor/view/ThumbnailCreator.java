package de.leifaktor.robbie.editor.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.leifaktor.robbie.editor.model.Room;
import de.leifaktor.robbie.editor.model.RoomLayer;

public class ThumbnailCreator {
    
    /**
     * Creates a thumbnail of the room
     * @param room
     * @param scale
     * @return
     */

    public static BufferedImage makeRoomThumbnail(Room room, int scale) {
        BufferedImage img = new BufferedImage(
                room.getWidth()*scale,
                room.getHeight()*scale,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        for (RoomLayer l: room.getLayers()) {
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    g.drawImage(
                            l.getTile(i, j).getDefaultTileGraphic().getImage(),
                            i*scale,
                            j*scale,
                            scale,
                            scale,
                            null);
                }
            }
        }
        return img;

    }
    
}

package de.leifaktor.robbie.editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.leifaktor.robbie.editor.model.Room;
import de.leifaktor.robbie.editor.model.RoomLayer;

public class ThumbnailCreator {
    
    private MainWindow mainWindow;
    
    private ImageLoader imageLoader;
    
    public ThumbnailCreator(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Creates a thumbnail of the room
     * @param room
     * @param scale
     * @return
     */

    public BufferedImage makeRoomThumbnail(Room room, int scale) {
        imageLoader = mainWindow.getImageLoader();        
        BufferedImage thumbnail = new BufferedImage(
                room.getWidth()*scale,
                room.getHeight()*scale,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = thumbnail.getGraphics();

        for (RoomLayer l: room.getLayers()) {
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    if (l.getTile(i, j) != null) {
                        BufferedImage image = imageLoader.getTileImage(l.getTile(i, j));
                        g.drawImage(image,
                                i*scale,
                                j*scale,
                                scale,
                                scale,
                                null);
                    }
                }
            }
        }
        return thumbnail;

    }

}

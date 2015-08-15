package de.leifaktor.robbie.editor.view.tilesetviewer;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.leifaktor.robbie.editor.model.Model;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.view.ImageLoader;

public class TileSetWindow extends JFrame {
    
    private List<TileSet> tileSets;
    
    private ImageLoader imageLoader;
    
    private JComboBox tileSetPicker;
    
    private TileSetViewPanel tileSetViewPanel;
    
    public TileSetWindow(Model model, ImageLoader imageLoader) {
        this.tileSets = model.getEpisode().getTileSets();
        this.imageLoader = imageLoader;
        initContent();
        this.pack();
        this.setVisible(true);
        tileSetViewPanel.setTileSet(tileSets.get(0));
    }
    
    private void initContent() {
        // Get the names of the tilesets
        String[] tileSetNames = new String[tileSets.size()];
        int counter = 0;
        for (TileSet tileSet: tileSets) {
            tileSetNames[counter] = tileSet.getFilename();
            counter++;
        }
        
        // Create the combobox with the list of names
        tileSetPicker = new JComboBox(tileSetNames);
        this.add(tileSetPicker, BorderLayout.WEST);
        
        // Create the panel for showing the picture
        tileSetViewPanel = new TileSetViewPanel(this);
        this.add(tileSetViewPanel, BorderLayout.CENTER);
    }
    
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    
}

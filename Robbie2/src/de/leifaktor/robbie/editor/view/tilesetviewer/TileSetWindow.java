package de.leifaktor.robbie.editor.view.tilesetviewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import de.leifaktor.robbie.editor.model.Model;
import de.leifaktor.robbie.editor.model.gfx.TileSet;
import de.leifaktor.robbie.editor.view.ImageLoader;

public class TileSetWindow extends JFrame {
    
    private Model model;
    
    private ImageLoader imageLoader;
    
    private TileSetPicker tileSetPicker;
    
    private TileSizeSpinner tileSizeSpinner;
    
    private TileSetViewPanel tileSetViewPanel;
    
    private JButton changeTileSizeButton;
    
    public TileSetWindow(Model model, ImageLoader imageLoader) {
        this.model = model;
        this.imageLoader = imageLoader;
        initContent();
        this.pack();
        this.setVisible(true);
        TileSet tileSet = tileSetPicker.getCurrentTileSet();
        if (tileSet != null) {
            setTileSet(tileSet);
        }
    }
    
    private void initContent() {        
        // Create the combobox with the list of names
        tileSetPicker = new TileSetPicker(this);
        this.add(tileSetPicker, BorderLayout.WEST);
        
        // Create the tileSizeSpinner
        tileSizeSpinner = new TileSizeSpinner(this);
        this.add(tileSizeSpinner, BorderLayout.NORTH);
        
        // Create the Button
        changeTileSizeButton = new JButton("OK");
        changeTileSizeButton.addActionListener(new UpdateTileSizeListener());
        this.add(changeTileSizeButton, BorderLayout.SOUTH); 
        
        
        // Create the panel for showing the picture
        tileSetViewPanel = new TileSetViewPanel(this);
        this.add(tileSetViewPanel, BorderLayout.CENTER);
    }
    
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void gridSizeChanged(int newGridSize) {
        tileSetViewPanel.setGridSize(newGridSize);        
    }
    
    public Model getModel() {
        return model;
    }
    
    private class UpdateTileSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int newTileSize = (int) tileSizeSpinner.getValue();
            TileSet tileSet = tileSetPicker.getCurrentTileSet();
            tileSet.setTileSize(newTileSize);
            imageLoader.update();
        }        
    }

    public void setTileSet(TileSet currentTileSet) {
        tileSetViewPanel.setTileSet(currentTileSet);
        tileSizeSpinner.setTileSet(currentTileSet);
    }
    
}

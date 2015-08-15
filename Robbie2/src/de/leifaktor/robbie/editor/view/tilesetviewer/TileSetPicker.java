package de.leifaktor.robbie.editor.view.tilesetviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.leifaktor.robbie.editor.model.gfx.TileSet;

public class TileSetPicker extends JComboBox {

    private TileSetWindow tileSetWindow;
    
    private List<TileSet> tileSets;
    
    public TileSetPicker(TileSetWindow tileSetWindow) {
        this.tileSetWindow = tileSetWindow;
        this.tileSets = tileSetWindow.getModel().getEpisode().getTileSets();
        String[] tileSetNames = new String[tileSets.size()];
        int counter = 0;
        for (TileSet tileSet: tileSets) {
            tileSetNames[counter] = tileSet.getFilename();
            counter++;
        }
        this.setModel(new DefaultComboBoxModel(tileSetNames));
        this.addActionListener(new TileSetChoiceListener());
    }
    
    public TileSet getCurrentTileSet() {
        return tileSets.get(this.getSelectedIndex());
    }
    
    private class TileSetChoiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {            
            tileSetWindow.setTileSet(getCurrentTileSet());
        }        
    }
    
    

}

package de.leifaktor.robbie.editor.view.tilesetviewer;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.leifaktor.robbie.editor.model.gfx.TileSet;

public class TileSizeSpinner extends JSpinner {

    private TileSetWindow tileSetWindow;

    private TileSet tileSet;

    public TileSizeSpinner(TileSetWindow tileSetWindow) {
        this.tileSetWindow = tileSetWindow;
        SpinnerNumberModel tileSizeSpinnerModel = new SpinnerNumberModel(16, 8, 32, 1);
        this.setModel(tileSizeSpinnerModel);
        this.addChangeListener(new MyChangeListener());
    }

    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
        this.setValue(tileSet.getTileSize());
    }

    private class MyChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int newGridSize = (int) ((JSpinner) e.getSource()).getValue();
            tileSetWindow.gridSizeChanged(newGridSize);
        }
    };

}

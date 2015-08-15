package de.leifaktor.robbie.editor.view;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class MyToolBar extends JToolBar {

    private MainWindow mainWindow;

    public MyToolBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setFloatable(false);
        addToolBarButtons();
        this.setFocusable(false);
    }

    private void addToolBarButtons() {
        ToolBarButton button = null;
        
        // NEW
        button = new ToolBarButton("new.png", "new", "New Episode", "New Episode", mainWindow);
        this.add(button);

        // OPEN
        button = new ToolBarButton("open.png", "open", "Open Episode", "Open Episode", mainWindow);
        this.add(button);

        // SAVE
        button = new ToolBarButton("save.png", "save", "Save Episode", "Save Episode", mainWindow);
        button.setEnabled(false);
        this.add(button);

        // SEPARATOR
        this.add(new JSeparator(SwingConstants.VERTICAL));

        // ROOM VIEW FLOOR VIEW SWITCH
        ToolBarToggleButton toggle = new ToolBarToggleButton("room_view.png", "floor_view.png", 
                "room_floor", "Floor View", "Floor View", mainWindow);
        this.add(toggle);
        
        // GRID ON OFF SWITCH
        toggle = new ToolBarToggleButton("grid_on.png", "grid_off.png", "grid", "Grid", "Grid",
                mainWindow);
        this.add(toggle);

        // SEPARATOR
        this.add(new JSeparator(SwingConstants.VERTICAL));

        // PLAY
        button = new ToolBarButton("play.png", "play", "Play", "Play", mainWindow);
        this.add(button);

        // BRUSH
        toggle = new ToolBarToggleButton("brush_small.png","brush_big.png", "brush", "brush",
                "brush", mainWindow);
        this.add(toggle);

        // SEPARATOR
        this.add(new JSeparator(SwingConstants.VERTICAL));
    }





}

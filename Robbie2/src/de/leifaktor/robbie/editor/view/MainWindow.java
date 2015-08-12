package de.leifaktor.robbie.editor.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import de.leifaktor.robbie.editor.model.Floor;
import de.leifaktor.robbie.editor.model.Model;
import de.leifaktor.robbie.editor.model.Room;
import de.leifaktor.robbie.editor.model.RoomLayer;

/**
 * The MainWindow is the main class of the view and the controller, so it is the VC part of the MVC
 * pattern.
 * 
 * It extends JFrame and knows about all its sub components. All subcomponents know the main window
 * so they can call the controlling methods by themselves.
 */

public class MainWindow extends JFrame {

    /**
     * A reference to the model.
     */

    private Model model;

    /**
     * The toolbar
     */

    private MyToolBar toolBar;

    /**
     * The tile picker
     */

    private TilePicker tilePanel;

    /**
     * The entity picker
     */

    private EntityPicker entityPanel;

    /**
     * The property table, showing properties of the currently selected tile or entity
     */

    private JTable propertyTable;

    /**
     * The panel containing the roomview and the floorview (with cardlayout)
     */

    private JPanel cards;

    /**
     * The room view.
     */
    private RoomView roomView;

    /**
     * The floor view.
     */
    private FloorView floorView;

    /**
     * Is true iff the room view is opened.
     */

    private boolean roomViewOpened;

    /**
     * Initilializes the main window.
     * @param model the data model
     */

    public MainWindow(Model model) {
        this.model = model;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */

    private void initialize() {
        this.setTitle("ROBBIE");
        Dimension frameDimension = new Dimension(1200, 800);
        this.setPreferredSize(frameDimension);
        this.setMinimumSize(frameDimension);
        this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
        gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 1.5, 1.0, Double.MIN_VALUE};
        this.getContentPane().setLayout(gridBagLayout);

        // OBERE TOOLBAR
        toolBar = new MyToolBar(this);
        addComponentToFrame(toolBar, 0, 0, 2, 1);

        // TILEPICKER
        tilePanel = new TilePicker(this);
        Dimension tilePanelDimension = new Dimension(256,1912);
        tilePanel.setPreferredSize(tilePanelDimension);

        // SCROLLPANE UM DEN TILEPICKER
        JScrollPane sp = new JScrollPane(tilePanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        Dimension scrollPaneDimension = new Dimension(271,512);
        sp.setPreferredSize(scrollPaneDimension);

        // ENTITYPICKER
        entityPanel = new EntityPicker(this);
        entityPanel.setPreferredSize(tilePanelDimension);				

        // SCROLLPANE UM DEN TILEPICKER
        JScrollPane sp2 = new JScrollPane(entityPanel);
        sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp2.getVerticalScrollBar().setUnitIncrement(16);
        sp2.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));		
        sp2.setPreferredSize(scrollPaneDimension);

        // TABBEDPANE FOR TILE PICKER AND ENTITY PICKER
        JTabbedPane tabbedPane = new JTabbedPane();
        //ImageIcon icon = createImageIcon("images/middle.gif");
        tabbedPane.addTab("Tiles", null, sp, "Does nothing");
        tabbedPane.addTab("Entities", null, sp2, "Does nothing");
        addComponentToFrame(tabbedPane, 0,  1);

        // PROPERTY-TABLE
        propertyTable = new JTable();
        propertyTable.setBackground(Color.RED);
        addComponentToFrame(propertyTable, 0, 2);

        // ROOMVIEW
        roomView = new RoomView(this);

        // ROOMVIEW
        floorView = new FloorView(this);

        // CARDPANEL FOR ROOMVIEW AND FLOORVIEW
        cards = new JPanel(new CardLayout(10,10));
        cards.add(roomView,"ROOMVIEW");
        cards.add(floorView,"FLOORVIEW");
        addComponentToFrame(cards, 1, 1, 1, 2);
        openRoomView();

        // STATUSBAR
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));	
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 24));
        statusPanel.setMinimumSize(new Dimension(this.getWidth(), 24));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        addComponentToFrame(statusPanel, 0, 3, 2, 1);

        this.repaint();
    }

    /**
     * Helper method placing a component in the grid layout for components that only use one cell
     * in x and y direction
     * @param component the component
     * @param gridx the x coordinate
     * @param gridy the y coordinate
     */

    private void addComponentToFrame(Component component, int gridx, int gridy) {
        addComponentToFrame(component, gridx, gridy, 1, 1);
    }

    /**
     * Helper method placing a component in the grid layout
     * @param component the component
     * @param gridx the x coordinate of the upper left corner
     * @param gridy the y coordinate of the upper left corner
     * @param gridWidth number of cells that should be taken in the x direction
     * @param gridHeight number of cells that should be taken in the y direction
     */

    private void addComponentToFrame(Component component, int gridx, int gridy, int gridWidth, int gridHeight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        this.getContentPane().add(component, gbc);
    }

    //////////////////////////////////////////////////////////////////////////
    // HANDLING THE ACTIONS
    //////////////////////////////////////////////////////////////////////////

    public void savePressed() {

    }

    public void loadPressed() {

    }

    public void newEpisodePressed() {
        showNewEpisodeDialog();
    }

    public void toggleBrush() {

    }
    
    public void toggleGrid() {
        roomView.toggleGrid();
    }

    public void playPressed() {

    }

    public void toggleRoomFloorView() {
        if (roomViewOpened) {
            openFloorView();
        } else {
            openRoomView();
        }
    }

    public void openRoomView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "ROOMVIEW");
        roomView.repaint();
        this.roomViewOpened = true;
    }

    public void openFloorView() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "FLOORVIEW");
        floorView.repaint();
        this.roomViewOpened = false;
    }

    public void setRoom(Room r) {
        roomView.setRoom(r);
    }
    
    public Model getModel() {
        return model;
    }
    
    public void showNewEpisodeDialog() {
        EpisodeDialog episodeDialog = new EpisodeDialog();      
        episodeDialog.setLocationRelativeTo(this);
        episodeDialog.setModal(true);
        episodeDialog.setVisible(true);
        model.setEpisode(episodeDialog.getEpisode());
        floorView.setFloor(model.getEpisode().getFloors().get(0));
        openFloorView();        
    }
    
    /**
     * This method will be called by the RoomView if the user clicked on field x/y.
     * @param room
     * @param x
     * @param y
     */
    
    public void fieldClicked(RoomLayer roomLayer, int x, int y) {
        System.out.println(x + " " + y);
        roomLayer.setTile(x, y, tilePanel.getSelectedTile());
        roomView.repaint();
    }
    
    /**
     * Selects the Room at the specified position in the specified floor.
     * @param currentFloor
     * @param x
     * @param y
     */

    public void selectRoom(Floor currentFloor, int x, int y) {
        Room selectedRoom = currentFloor.getRoom(x,y);
        if (selectedRoom == null) {
            System.out.println(model.getEpisode().getRoomHeight());
            selectedRoom = new Room(model.getEpisode().getRoomWidth(),model.getEpisode().getRoomHeight());
            currentFloor.setRoom(x, y, selectedRoom);
        }
        roomView.setRoom(currentFloor.getRoom(x,y));

        openRoomView();
    }

}

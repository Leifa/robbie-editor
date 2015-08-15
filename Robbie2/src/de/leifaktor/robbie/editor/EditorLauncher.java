package de.leifaktor.robbie.editor;

import java.awt.EventQueue;

import de.leifaktor.robbie.editor.model.Episode;
import de.leifaktor.robbie.editor.model.Model;
import de.leifaktor.robbie.editor.view.MainWindow;

/**
 * This class is the Controller.
 */

public class EditorLauncher {

    /**
     * A reference to the main window. It is the View of the MVC.
     */

    private MainWindow window;


    /**
     * Launches the editor by creating an instance of this class.
     */

    public static void main(String[] args) {
        new EditorLauncher();
    }

    public EditorLauncher() {
        // Initialize the Model
        final Model model = new Model();
        
        // Initialize the View
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new MainWindow(model);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



}

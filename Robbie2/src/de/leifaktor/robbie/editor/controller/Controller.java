package de.leifaktor.robbie.editor.controller;

import de.leifaktor.robbie.editor.model.Model;
import de.leifaktor.robbie.editor.view.MainWindow;

public class Controller {
    
    private Model model;
    private MainWindow view;

    public Controller(Model model, MainWindow view) {
        this.model = model;
        this.view = view;
    }
    
}

package de.leifaktor.robbie.editor.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import de.leifaktor.robbie.editor.model.Episode;

public class EpisodeDialog extends JDialog {

    private static final int PADDING = 15;

    private Episode episode;

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JTextField title;

    public EpisodeDialog() {

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        GridLayout layout = new GridLayout(4,2,PADDING,PADDING);
        panel.setLayout(layout);

        JLabel lbl;

        lbl = new JLabel("Title");
        panel.add(lbl);

        title = new JTextField(20);

        panel.add(title);


        lbl = new JLabel("Room Width");
        panel.add(lbl);

        SpinnerNumberModel widthSpinnerModel = new SpinnerNumberModel(40, 16, 48, 1);
        widthSpinner = new JSpinner(widthSpinnerModel);
        panel.add(widthSpinner);

        lbl = new JLabel("Room Height");
        panel.add(lbl);

        SpinnerNumberModel heightSpinnerModel = new SpinnerNumberModel(28, 8, 48, 1);
        heightSpinner = new JSpinner(heightSpinnerModel);
        panel.add(heightSpinner);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();              
            }

        });
        panel.add(cancel);

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int width = ((Integer)widthSpinner.getValue()).intValue();
                int height = ((Integer)heightSpinner.getValue()).intValue();
                episode = new Episode(width, height);
                episode.setTitle(title.getText());
                setVisible(false);
                dispose();              
            }

        });
        panel.add(ok);

        this.add(panel);
        this.setTitle("New Episode");
        this.setResizable(false);

        this.pack();

    }

    public Episode getEpisode() {
        return episode;
    }


}    

package de.leifaktor.robbie.editor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class EntityPicker extends JPanel implements MouseListener {
    
    private MainWindow mainWindow;
	
	private int selX;
	private int selY;
	private int tileWidth;
	private int tileHeight;
	
	public EntityPicker(MainWindow mainWindow) {
	    this.mainWindow = mainWindow;
		this.selX = 0;
		this.selY = 0;
		this.tileHeight = 32;
		this.tileWidth = 32;
		this.addMouseListener(this);
	}	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (mainWindow.getModel().getEpisode() == null) return;
		
		// RECHTECKE
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 40; j++) {
				g.setColor(Color.GRAY);
				g.fillRect(i*tileWidth, j*tileHeight, tileWidth-1, tileHeight-1);
				g.setColor(Color.BLACK);
				g.drawRect(i*tileWidth, j*tileHeight, tileWidth-1, tileHeight-1);
				g.drawString(i + "/" + j, i*tileWidth+3, j*tileHeight+18);
			}
		}
		
		// MARKER
		g.setColor(Color.BLACK);
		g.drawRect(selX*tileWidth-1, selY*tileHeight-1, tileWidth+1, tileHeight+1);
		g.drawRect(selX*tileWidth-4, selY*tileHeight-4, tileWidth+7, tileHeight+7);
		g.setColor(Color.YELLOW);
		g.drawRect(selX*tileWidth-2, selY*tileHeight-2, tileWidth+3, tileHeight+3);
		g.drawRect(selX*tileWidth-3, selY*tileHeight-3, tileWidth+5, tileHeight+5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		selX = e.getX() / 32;
		selY = e.getY() / 32;
		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}

}


package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class BoardSquare extends JPanel {

	private ChessPiece childPiece = null;
	private Color color;
	private Position pos;
	
	public BoardSquare(Position pos, Color color) {
		this.setVisible(true);
		this.pos = pos;
		this.color = color;
		this.setBackground(color);
	}
	
	public boolean movePiece(BoardSquare destination) {
		
		if(destination.hasChild()) {
			
			if(destination.getChild().getColor() == childPiece.getColor()) {
				return false;
			} else {
				destination.removePiece();
				destination.addPiece(childPiece);
				removePiece();
			}
			
		} else {
			destination.addPiece(childPiece);
			removePiece();
		}
		
		return true;
	}
	
	public void addPiece(ChessPiece newPiece) {	
		childPiece = newPiece;
		add(newPiece);
		validate();
		repaint();
	}
	
	public void removePiece() {
		remove(childPiece);
		childPiece = null;
	}
	
	public Color getOriginalColor() {
		return color;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public boolean hasChild() {
		return (childPiece == null) ? false : true;
 	}
	
	public ChessPiece getChild() {
		return childPiece;
	}
	
}

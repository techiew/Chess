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
	
	public boolean movePiece(int x, int y) {
		//prøv å flytt en brikke
		return true;
	}
	
	public void addPiece(ChessPiece newPiece) {		
		this.childPiece = newPiece;
		this.add(newPiece);
	}
	
	public Color getOriginalColor() {
		return color;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public boolean hasChild() {
		return (childPiece == null) ? false : true;
 	}
	
}

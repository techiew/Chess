package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import rules.*;

public class BoardSquare extends JPanel {

	private ChessPiece childPiece = null;
	private Color color;
	private Position pos;
	
	public BoardSquare(Position pos, Color color) {
		setVisible(true);
		this.pos = pos;
		this.color = color;
		setBackground(color);
	}
	
	public boolean movePiece(BoardSquare[][] board, BoardSquare destination) {
		boolean legalMove = ((RulesInterface) childPiece.getRules()).isLegalMove(board, childPiece, pos, destination.getPos());
		
		if(destination.hasChild()) {
			
			if(destination.getChild().getColor() == childPiece.getColor()) {
				legalMove = false;
			} else if(legalMove) {
				destination.removePiece();
				destination.addPiece(childPiece);
				removePiece();
			}
			
		} else if(legalMove) {
			destination.addPiece(childPiece);
			removePiece();
		}
		
		if(legalMove) destination.getChild().onPieceMoved();
		
		return legalMove;
	}
	
	public boolean movePieceMultiplayer(BoardSquare destination) {
		if(destination.hasChild()) {
			destination.removePiece();
			destination.addPiece(childPiece);
			removePiece();	
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
		validate();
		repaint();
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

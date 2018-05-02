package gui;

import java.awt.Color;
import javax.swing.JPanel;
import rules.*;

//En av rutene på sjakkbrettet
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
	
	//En copy constructor, for isKingInCheck
	public BoardSquare(BoardSquare square) {
		this.childPiece = square.childPiece;
		this.color = square.color;
		this.pos = square.pos;
	}
	
	//Sjekk regler og prøv å beveg brikka til en gitt rute
	//Sjekker også om en konge blir satt i sjakk etter bevegelsen
	public boolean movePiece(BoardSquare[][] board, BoardSquare destination, Position wKingPos, Position bKingPos) {
		RulesInterface rules = ((RulesInterface) childPiece.getRules());		
		boolean legalMove = rules.isLegalMove(board, childPiece, pos, destination.getPos());
		
		if(legalMove && childPiece.getType() != PieceType.KING) {
			boolean wKingInCheck = rules.isKingInCheck(board, pos, destination.getPos(), "white", wKingPos);
			boolean bKingInCheck = rules.isKingInCheck(board, pos, destination.getPos(), "black", bKingPos);
			
			if(childPiece.getColor() == "white" && wKingInCheck) {
				board[wKingPos.getX()][wKingPos.getY()].setBackground(new Color(255, 0, 0));
				return false;
				
			} else if(childPiece.getColor() == "black" && bKingInCheck) {
				board[bKingPos.getX()][bKingPos.getY()].setBackground(new Color(255, 0, 0));
				return false;
			}
			
		}
		
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
	
	//Beveg en brikke uten regler, brukes for multiplayer
	public boolean movePieceNoRules(BoardSquare destination) {
		
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
	
	//Legg til en brikke i denne ruta
	public void addPiece(ChessPiece newPiece) {	
		childPiece = newPiece;
		add(newPiece);
		validate();
		repaint();
	}
	
	//Fjern en brikke fra denne ruta
	public void removePiece() {
		remove(childPiece);
		childPiece = null;
		validate();
		repaint();
	}
	
	//Hent den originale fargen, for å fjerne highlights osv.
	public Color getOriginalColor() {
		return color;
	}
	
	//Hent posisjonen til denne ruta på brettet, samme som posisjonen i boardArray
	public Position getPos() {
		return pos;
	}
	
	//Har ruta en brikke?
	public boolean hasChild() {
		return (childPiece == null) ? false : true;
 	}
	
	//Hent brikken i denne ruta
	public ChessPiece getChild() {
		return childPiece;
	}
	
	//Sett brikken direkte
	public void setChild(ChessPiece child) {
		childPiece = child;
	}
	
}

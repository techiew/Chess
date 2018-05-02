package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import rules.*;

//En sjakkbrikke
public class ChessPiece extends JLabel {
	private PieceType type;
	private String color;
	private String direction;
	private ImageIcon image;
	private Object rulesObject;
	private boolean firstMove;
	
	public ChessPiece(PieceType type, String color, String myColor) {
		this.type = type;
		this.color = color;
		setVisible(true);
		String imgSrc = "";
		rulesObject = null;
		firstMove = true;
		
		if(color == "white" && myColor == "black") {
			direction = "down";
		} else if(color == "black" && myColor == "white"){
			direction = "down";
		} else {
			direction = "up";
		}
		
		switch(type) {
		case PAWN:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_pawn.png" : "src/images/black_pawn.png";
			rulesObject = new Pawn();
			break;
		case ROOK:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_rook.png" : "src/images/black_rook.png";
			rulesObject = new Rook();
			break;
		case KNIGHT:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_knight.png" : "src/images/black_knight.png";
			rulesObject = new Knight();
			break;
		case BISHOP:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_bishop.png" : "src/images/black_bishop.png";
			rulesObject = new Bishop();
			break;
		case QUEEN:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_queen.png" : "src/images/black_queen.png";
			rulesObject = new Queen();
			break;
		case KING:
			imgSrc = (color == "white") ? imgSrc = "src/images/white_king.png" : "src/images/black_king.png";
			rulesObject = new King();
			break;
		}
		
		image = new ImageIcon(imgSrc);
		ImageIcon scaledImageIcon = new ImageIcon(image.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
		this.setIcon(scaledImageIcon);
	}
	
	//En copy constructor, brukes i isKingInCheck
	public ChessPiece(ChessPiece piece) {
		this.type = piece.type;
		this.color = piece.color;
		this.direction = piece.direction;
		this.firstMove = piece.firstMove;
	}
	
	//Hent brikketypen
	public PieceType getType() {
		return type;
	}
	
	//Hent fargen, eller laget til denne brikken
	public String getColor() {
		return color;
	}
	
	//Hent regelobjektet
	public Object getRules() {
		return rulesObject;
	}
	
	//Hvilken retning går vi, brukes for bønder
	public String getDirection() {
		return direction;
	}
	
	//Er dette første trekket for denne brikka? brukes for bønder
	public boolean isFirstMove() {
		return firstMove;
	}
	
	//Når brikken blir beveget
	public void onPieceMoved() {
		
		if(firstMove == true) {
			firstMove = false;
		}
		
	}
	
	//Når brikken dør
	public void onPieceRemoved() {
		
	}
	
}

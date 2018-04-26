package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

enum pieceType {
	PAWN,
	ROOK,
	KNIGHT,
	BISHOP,
	QUEEN,
	KING
};

public class ChessPiece extends JLabel {
		
	private pieceType type;
	private String color;
	private ImageIcon image;
	
	public ChessPiece(pieceType type, String color) {
		this.type = type;
		this.color = color;
		this.setVisible(true);
		String imgSrc = "";
		
		switch(type) {
		case PAWN:
			imgSrc = (color == "white") ? imgSrc = "whitePawn.png" : "blackPawn.png";
			break;
		case ROOK:
			imgSrc = (color == "white") ? imgSrc = "whiteRook.png" : "blackRook.png";
			break;
		case KNIGHT:
			imgSrc = (color == "white") ? imgSrc = "whiteKnight.png" : "blackKnight.png";
			break;
		case BISHOP:
			imgSrc = (color == "white") ? imgSrc = "whiteBishop.png" : "blackBishop.png";
			break;
		case QUEEN:
			imgSrc = (color == "white") ? imgSrc = "whiteQueen.png" : "blackQueen.png";
			break;
		case KING:
			imgSrc = (color == "white") ? imgSrc = "whiteKing.png" : "blackKing.png";
			break;
		}
		
		image = new ImageIcon(imgSrc);
		ImageIcon scaledImageIcon = new ImageIcon(image.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
		this.setIcon(scaledImageIcon);
	}
	
}

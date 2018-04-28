package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessBoard extends JFrame {

	private JPanel panel = new JPanel();
	private int columns = 8;
	private int rows = 8;
	private BoardSquare[][] boardArray = new BoardSquare[rows][columns];
	private BoardSquare highlightedSquare = null;
	private Color colorHighlight = new Color(209, 206, 111); 
	
	public ChessBoard() {
		this.setVisible(true);
		this.setSize(600, 600);
		panel.setLayout(new GridLayout(rows, 0));
		this.add(panel);
		createChessBoard();
		placePieces();
		this.validate();
		this.repaint();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
	}
	
	private void createChessBoard() {
		
		int row = 1;
		Color colorOne = new Color(106, 133, 78);
		Color colorTwo = new Color(204, 201, 182);
		
		for(int y = 0; y < columns; y++) {
			
			for(int x = 0; x < rows; x++){
				Position pos = new Position(x, (columns - 1) - y);
				BoardSquare bSquare;
								
				if(row % 2 == 0) {
					bSquare = new BoardSquare(pos, colorOne);
				} else {
					bSquare = new BoardSquare(pos, colorTwo);
				}
				
				panel.add(bSquare);
				boardArray[x][(columns - 1) - y] = bSquare;
				
				bSquare.addMouseListener(new MouseAdapter() {
					
				    @Override
				    public void mousePressed(MouseEvent e) {
				       squareClickedEvent(((BoardSquare) e.getSource()));
				    }
				    
				});
				
				if(row % 8 == 0) {
					Color temp = colorOne;
					colorOne = colorTwo;
					colorTwo = temp;
				}
				
				row++;
			}
			
		}
		
		//boardArray[0][0].setBackground(new Color(255, 0, 0));
	}
	
	private void placePieces() {
		
		for(int i = 0; i < 8; i++) {
			boardArray[i][1].addPiece(new ChessPiece(pieceType.PAWN, "white"));
		}
		
		for(int i = 0; i < 8; i++) {
			boardArray[i][6].addPiece(new ChessPiece(pieceType.PAWN, "black"));
		}
		
		boardArray[0][0].addPiece(new ChessPiece(pieceType.ROOK, "white"));
		boardArray[7][0].addPiece(new ChessPiece(pieceType.ROOK, "white"));
		boardArray[0][7].addPiece(new ChessPiece(pieceType.ROOK, "black"));
		boardArray[7][7].addPiece(new ChessPiece(pieceType.ROOK, "black"));
		
		boardArray[1][0].addPiece(new ChessPiece(pieceType.KNIGHT, "white"));
		boardArray[6][0].addPiece(new ChessPiece(pieceType.KNIGHT, "white"));
		boardArray[1][7].addPiece(new ChessPiece(pieceType.KNIGHT, "black"));
		boardArray[6][7].addPiece(new ChessPiece(pieceType.KNIGHT, "black"));
	
		boardArray[2][0].addPiece(new ChessPiece(pieceType.BISHOP, "white"));
		boardArray[5][0].addPiece(new ChessPiece(pieceType.BISHOP, "white"));
		boardArray[2][7].addPiece(new ChessPiece(pieceType.BISHOP, "black"));
		boardArray[5][7].addPiece(new ChessPiece(pieceType.BISHOP, "black"));
		
		boardArray[3][0].addPiece(new ChessPiece(pieceType.QUEEN, "white"));
		boardArray[3][7].addPiece(new ChessPiece(pieceType.QUEEN, "black"));
		
		boardArray[4][0].addPiece(new ChessPiece(pieceType.KING, "white"));
		boardArray[4][7].addPiece(new ChessPiece(pieceType.KING, "black"));
	}
	
	//Event handler som kjører når man trykker på en rute
	public void squareClickedEvent(BoardSquare clickedSquare) {	
		
		//Hvis vi trykker på en tom rute
		if(!clickedSquare.hasChild()) {
			
			//Fjern highlight
			if(highlightedSquare != null) {
				attemptToMovePiece(highlightedSquare, clickedSquare);
				setHighlight(null);
			}
			
			return;
		} 
		
		//Hvis vi trykker på en rute med brikke
		if (clickedSquare.hasChild()) {
		
			if(highlightedSquare != null) {
				String highlightedColor = highlightedSquare.getChild().getColor();
				String clickedColor = clickedSquare.getChild().getColor();
				
				//Fjern highlight hvis du trykker på rute med highlight på
				if(highlightedSquare == clickedSquare) {
					setHighlight(null);
					return;
				}
								
				attemptToMovePiece(highlightedSquare, clickedSquare);
				
				if(highlightedColor != clickedColor) {
					setHighlight(null);
					return;
				} 
				
			}
			
			//Sett highlight til å være den ruta som er trykket på (bare ruter med brikker)
			setHighlight(clickedSquare);
		}
			
	}
		
	public void setHighlight(BoardSquare clickedSquare) {
		
		//Hvis clickedSquare er satt til null, ikke ha noen highlights
		if(clickedSquare == null) {
			
			if(highlightedSquare != null) {
				highlightedSquare.setBackground(highlightedSquare.getOriginalColor());
				highlightedSquare = null;
			}
			
			return;
		}
		
		//Hvis en rute alerede er i highlightedSquare, fjern highlight på den
		if(highlightedSquare != null) {
			highlightedSquare.setBackground(highlightedSquare.getOriginalColor());	
		}
		
		highlightedSquare = clickedSquare;
		highlightedSquare.setBackground(colorHighlight);	
	}
	
	public void attemptToMovePiece(BoardSquare fromSquare, BoardSquare toSquare) {
		fromSquare.movePiece(toSquare);
	}
	
	public BoardSquare getSquareAt(Position pos) {
		return boardArray[pos.getX()][pos.getY()];
	}
	
}

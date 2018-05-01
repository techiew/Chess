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
import javax.swing.SwingUtilities;

import multiplayer.Client;
import multiplayer.ConnectionInterface;
import multiplayer.Message;
import multiplayer.Server;
import rules.PieceType;
import rules.RulesInterface;
import sound.*;

public class ChessBoard extends JFrame {

	private JPanel panel = new JPanel();
	private int columns = 8;
	private int rows = 8;
	private BoardSquare[][] boardArray = new BoardSquare[rows][columns];
	private BoardSquare highlightedSquare = null;
	private Color colorHighlight = new Color(209, 206, 111); 
	private String myColor;
	private boolean isClient;
	private boolean isMultiplayer;
	private boolean isConnected = false;
	private Server server = null;
	private Client client = null;
	private int turn = 0;
	private SoundPlayer soundPlayer = null;
	private int width = 0;
	
	public ChessBoard(boolean isMultiplayer, boolean isClient, String ip, int port, String title) {
		this.isMultiplayer = isMultiplayer;
		this.isClient = isClient;
		setTitle(title);
		setVisible(true);
		setSize(600,600);
		
		if (isClient) {
			setLocation(30 + getWidth(),30);
		} else {
			setLocation(30,30);
		}
		
		panel.setLayout(new GridLayout(rows, 0));
		add(panel);
		createChessBoard();
		placePieces();
		validate();
		repaint();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		if(isMultiplayer) {
			
			if(isClient) {
				myColor = "black";
				client = new Client(this, ip, port);
				Thread clientThread = new Thread(client, "Client thread");
				clientThread.start();
			} else {
				myColor = "white";
				server = new Server(this, port);
				Thread serverThread = new Thread(server, "Server thread");
				serverThread.start();
			}
			
		}
		
		soundPlayer = new SoundPlayer();
		Thread soundThread = new Thread(soundPlayer, "Sound thread");
		soundThread.start();
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
		
	}
	
	private void placePieces() {
		
		for(int i = 0; i < 8; i++) {
			boardArray[i][1].addPiece(new ChessPiece(PieceType.PAWN, "white"));
		}
		
		for(int i = 0; i < 8; i++) {
			boardArray[i][6].addPiece(new ChessPiece(PieceType.PAWN, "black"));
		}
		
		boardArray[0][0].addPiece(new ChessPiece(PieceType.ROOK, "white"));
		boardArray[7][0].addPiece(new ChessPiece(PieceType.ROOK, "white"));
		boardArray[0][7].addPiece(new ChessPiece(PieceType.ROOK, "black"));
		boardArray[7][7].addPiece(new ChessPiece(PieceType.ROOK, "black"));
		
		boardArray[1][0].addPiece(new ChessPiece(PieceType.KNIGHT, "white"));
		boardArray[6][0].addPiece(new ChessPiece(PieceType.KNIGHT, "white"));
		boardArray[1][7].addPiece(new ChessPiece(PieceType.KNIGHT, "black"));
		boardArray[6][7].addPiece(new ChessPiece(PieceType.KNIGHT, "black"));
	
		boardArray[2][0].addPiece(new ChessPiece(PieceType.BISHOP, "white"));
		boardArray[5][0].addPiece(new ChessPiece(PieceType.BISHOP, "white"));
		boardArray[2][7].addPiece(new ChessPiece(PieceType.BISHOP, "black"));
		boardArray[5][7].addPiece(new ChessPiece(PieceType.BISHOP, "black"));
		
		boardArray[3][0].addPiece(new ChessPiece(PieceType.QUEEN, "white"));
		boardArray[3][7].addPiece(new ChessPiece(PieceType.QUEEN, "black"));
		
		boardArray[4][0].addPiece(new ChessPiece(PieceType.KING, "white"));
		boardArray[4][7].addPiece(new ChessPiece(PieceType.KING, "black"));
	}
	
	//Event handler som kjører når man trykker på en rute
	public void squareClickedEvent(BoardSquare clickedSquare) {	
	
		if(isMultiplayer && !isConnected) return;
		if(isMultiplayer && (myColor == "white" && turn == 1 || myColor == "black" && turn == 0)) return;
		
		//Hvis vi trykker på en tom rute
		if(!clickedSquare.hasChild()) {
			
			//Fjern highlight
			if(highlightedSquare != null) {
				attemptToMovePiece(highlightedSquare, clickedSquare);
				setHighlight(null);
			}
			
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
		
	//Prøv å framhev ruten som vi trykket på
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
	
	//Prøv å beveg en brikke og sjekk om det er lovlig
	public boolean attemptToMovePiece(BoardSquare fromSquare, BoardSquare toSquare) {
		
		if(turn == 0 && fromSquare.getChild().getColor() != "white") {
			return false;
		} else if(turn == 1 && fromSquare.getChild().getColor() != "black") {
			return false;
		}
		
		if(isMultiplayer && (myColor == "white" && turn == 1 || myColor == "black" && turn == 0)) return false;
		
		if(fromSquare.movePiece(boardArray, toSquare)) {			
			onPieceMoved();
			
			if(isMultiplayer) {
				sendPieceMoveEvent(fromSquare, toSquare);
			}
			
			return true;
		}
		
		return false;
	}
	
	//Når en brikke ble flyttet på, endre tur
	private void onPieceMoved() {
		turn = (turn == 0) ? 1 : 0;
		setTitle((turn == 0) ? "Hvit sin tur" : "Svart sin tur");
		soundPlayer.playSound(SoundPlayer.SoundName.PIECE_MOVED);
	}
	
	public BoardSquare getSquareAt(Position pos) {
		return boardArray[pos.getX()][pos.getY()];
	}
	
	public BoardSquare[][] getBoard() {
		return boardArray;
	}
	
	
	//Multiplayer kode ------------------------------------------------------
	
	//Tving brettet vårt til å bevege en brikke (uten å sjekke regler), brukes for trekk fra andre spilleren
	public void forceMovePiece(BoardSquare fromSquare, BoardSquare toSquare) {
		
		if(fromSquare.movePieceNoRules(toSquare)) {			
			onPieceMoved();	
		}
		
	}
	
	//Send melding til den andre spilleren om hva som ble gjort denne runden
	public void sendPieceMoveEvent(BoardSquare from, BoardSquare to) {
		String action = "move " 
				+ from.getPos().getX() + "" + from.getPos().getY()
				+ " " + to.getPos().getX() + "" + to.getPos().getY();
		Message msg = new Message(action);
		getConnection().sendResponse(msg);
	}
	
	//Ta i mot oppdatering fra socket
	public void getUpdateFromSocket(Message msg) {
		setHighlight(null);
		String response = msg.getMessage();
		
		String[] split = response.split(" ");
		String fromSquarePos = split[1];
		int fromSquarePosX = Integer.parseInt(fromSquarePos.substring(0, 1));
		int fromSquarePosY = Integer.parseInt(fromSquarePos.substring(1, 2));
		String toSquarePos = split[2];
		int toSquarePosX = Integer.parseInt(toSquarePos.substring(0, 1));
		int toSquarePosY = Integer.parseInt(toSquarePos.substring(1, 2));
		
		BoardSquare from = boardArray[fromSquarePosX][fromSquarePosY];
		BoardSquare to = boardArray[toSquarePosX][toSquarePosY];
		
		forceMovePiece(from, to);
	}
	
	//Hent instansen av socketen som brukes for tilkobling
	private ConnectionInterface getConnection() {
		
		return (ConnectionInterface)((isClient) ? client : server);
		
	}

	public void onPlayerConnected() {
		soundPlayer.playSound(SoundPlayer.SoundName.CONNECTED);
		isConnected = true;
	}
	
}

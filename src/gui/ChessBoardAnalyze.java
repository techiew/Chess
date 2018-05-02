package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import rules.PieceType;
import stockfish.Stockfish;

//Klassen som bygger og justerer sjakkbrettet for analysemodus. 
public class ChessBoardAnalyze extends JFrame {
	private JPanel panel = new JPanel();
	private int columns = 8;
	private int rows = 8;
	private int windowSizeX = 600;
	private int windowSizeY = 600;
	private int windowPosX = 50;
	private int windowPosY = 50;
	private BoardSquare[][] boardArray = new BoardSquare[rows][columns];
	private String userFenInput;
	private JTextField fenInputTextfield;
	private JButton bestMoveButton;
	private JButton getEvalScore;
	private Stockfish stockfish = new Stockfish();
	private String bestMove;
	private String evalScore;
	private String mateScore;
	AnalyzeInput analyzeWindow;
	private String myColor = "white";
	
	public ChessBoardAnalyze() {
		this.setVisible(true);
		this.setSize(windowSizeX, windowSizeY);
		this.setTitle("Chess Board Analyzer");
		this.setLocation(windowPosX, windowPosY);
		panel.setLayout(new GridLayout(rows, 0));
		this.add(panel);
		analyzeWindow = new AnalyzeInput(windowSizeX, windowPosY, windowSizeY);
		fenInputTextfield = analyzeWindow.fenInputTextfield;
		bestMoveButton = analyzeWindow.bestMoveButton;
		getEvalScore = analyzeWindow.getEvalScore;
		
		//Kjører når du taster inn en FEN nøkkel i tekstfeltet og trykker ENTER. Sender FEN nøkkelen til placePieces metoden. 
		fenInputTextfield.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String userInput = fenInputTextfield.getText();
				
				if (userInput.length() < 10) {
					System.out.println("Invalid FEN code");
					InitializeChess.infoBox("Vennligst skriv inn en gyldig FEN string", "Feilmelding");
				} else {
					placePieces(userInput);
					fenInputTextfield.setText("");
					analyzeWindow.showBestMove("");
					analyzeWindow.showEvalScore("");
					analyzeWindow.showMateScore("");
				}
				
			}
			
		});
		
		//Kjører når du trykker på "Anbefalt trekk" knappen. Kaller getBestMove metoden fra Stockfish klassen og printer den i en label. 
		bestMoveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				bestMove = "Stockfish anbefaler trekk: " + stockfish.getBestMove(30);
				analyzeWindow.showBestMove(bestMove);
			}
			
		});
		
		//Kjører når du trykker på "Get evaluation score" knappen. Kaller getEvalScore & getMateScore metodene fra Stockfish klassen og printer resultatet i en label.
		getEvalScore.addActionListener(new ActionListener() {
			
			public void actionPerformed (ActionEvent e) {
				evalScore = "Evalueringsscore er: " + stockfish.getEvalScore(30);	
				mateScore = "Sjakkmatt er mulig i " + stockfish.getMateScore(30) + " trekk!";
				analyzeWindow.showEvalScore(evalScore);
				analyzeWindow.showMateScore(mateScore);
			}
			
		});
		
		//Starter opp Stockfish. 
		if (stockfish.startEngine()) {
			System.out.println("Sjakkmotoren har startet");
		} else {
			System.out.println("Feil med starting av sjakkmotor");
		}
		
		createChessBoard();
		initializePieces();
		this.validate();
		this.repaint();
		
		this.addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				stockfish.stopEngine();
				System.exit(0);
			}
			
		});
		
	}

	//Lager selve sjakkbrettet. Setter opp de 64 rutene og farger dem. 
	private void createChessBoard() {
		int row = 1;
		Color colorOne = new Color(106, 133, 78);
		Color colorTwo = new Color(204, 201, 182);
		
		for(int y = 0; y < columns; y++) {
			
			for(int x = 0; x < rows; x++) {
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
					
				   /* 
				    * @Override
				    public void mouseClicked(MouseEvent e) {
				       setHighlight(((BoardSquare) e.getSource()).getPos());
				    } */
				    
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
	
	//Legger ut brikkene. Blir kjørt når brukeren taster inn en FEN nøkkel. Leser FEN koden i et array og legger ut brikkene basert på hva nøkkelen sier. 
	private void placePieces(String userInput) {
		FENSplitter fenSplitter = new FENSplitter(userInput);
		userFenInput = userInput;
		
		if (fenSplitter.checkFenArray()) {
			stockfish.sendFen(userInput);
			analyzeWindow.showCurrentFen(userFenInput);
			//stockfishResponse = stockfish.getBestMove(30);
			clearBoard();
			String[] fenArray = fenSplitter.getFenArray();
			
			for (int i = 0; i < 64; i++) {
				//System.out.println(fenArray[i]);
			}
			
			int x = 0;
			int y = 0;
			
			for (int i = 0; i < fenArray.length; i++) {
				
				if (i % 8 == 0 && i != 0) {
						x = 0;
						y++;
				}
				
				switch(fenArray[i]) {
					case "r":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.ROOK, "black", myColor));
						x++;
						break;
					case "n":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.KNIGHT, "black", myColor));
						x++;
						break;
					case "b":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.BISHOP, "black", myColor));
						x++;
						break;
					case "q":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.QUEEN, "black", myColor));
						x++;
						break;
					case "k":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.KING, "black", myColor));
						x++;
						break;
					case "0":
						x++;
						break;
					case "p":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.PAWN, "black", myColor));
						x++;
						break;
					case "R":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.ROOK, "white", myColor));
						x++;
						break;
					 case "N":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.KNIGHT, "white", myColor));
						x++;
						break;
					case "B": 
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.BISHOP, "white", myColor));
						x++;
						break;
					case "Q":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.QUEEN, "white", myColor));
						x++;
						break;
					case "K":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.KING, "white", myColor));
						x++;
						break;
					case "P":
						boardArray[x][(columns - 1) - y].addPiece(new ChessPiece(PieceType.PAWN, "white", myColor));
						x++;
						break; 
				}
				
			}
			
		} else {
			InitializeChess.infoBox("Vennligst skriv inn en gyldig FEN string", "Feilmelding");
		}
		
	}
	
	//Blir kjørt på oppstart, setter ut brikkene til startingposisjonene og setter FEN nøkkelen til startingposisjonen. 
	private void initializePieces() {
		userFenInput = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ";
		stockfish.sendFen(userFenInput);
		analyzeWindow.showCurrentFen(userFenInput);
		for(int i = 0; i < 8; i++) {
			boardArray[i][1].addPiece(new ChessPiece(PieceType.PAWN, "white", myColor));

		}
		
		for(int i = 0; i < 8; i++) {
			boardArray[i][6].addPiece(new ChessPiece(PieceType.PAWN, "black", myColor));
		}

		boardArray[0][0].addPiece(new ChessPiece(PieceType.ROOK, "white", myColor));
		boardArray[7][0].addPiece(new ChessPiece(PieceType.ROOK, "white", myColor));
		boardArray[0][7].addPiece(new ChessPiece(PieceType.ROOK, "black", myColor));
		boardArray[7][7].addPiece(new ChessPiece(PieceType.ROOK, "black", myColor));
		
		boardArray[1][0].addPiece(new ChessPiece(PieceType.KNIGHT, "white", myColor));
		boardArray[6][0].addPiece(new ChessPiece(PieceType.KNIGHT, "white", myColor));
		boardArray[1][7].addPiece(new ChessPiece(PieceType.KNIGHT, "black", myColor));
		boardArray[6][7].addPiece(new ChessPiece(PieceType.KNIGHT, "black", myColor));
	
		boardArray[2][0].addPiece(new ChessPiece(PieceType.BISHOP, "white", myColor));
		boardArray[5][0].addPiece(new ChessPiece(PieceType.BISHOP, "white", myColor));
		boardArray[2][7].addPiece(new ChessPiece(PieceType.BISHOP, "black", myColor));
		boardArray[5][7].addPiece(new ChessPiece(PieceType.BISHOP, "black", myColor));
		
		boardArray[3][0].addPiece(new ChessPiece(PieceType.QUEEN, "white", myColor));
		boardArray[3][7].addPiece(new ChessPiece(PieceType.QUEEN, "black", myColor));
		
		boardArray[4][0].addPiece(new ChessPiece(PieceType.KING, "white", myColor));
		boardArray[4][7].addPiece(new ChessPiece(PieceType.KING, "black", myColor));
	}
	
	//Fjerner alle brikkene fra brettet. Blir brukt når du taster inn ny FEN kode sånn at sjakkbrettet blir rent og klar for ny brikkeplasseringer. 
	private void clearBoard() {
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < 64; i++) {
			
			if (i % 8 == 0 && i != 0) {
					x = 0;
					y++;
			} 
			
			if (boardArray[x][y].hasChild()) {
				boardArray[x][y].removePiece();
				x++;
				
			} else {
				x++;
			}
			
		}
		
		this.validate();
		this.repaint();
	} 
	
}
	

package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

public class Bishop extends Rules implements RulesInterface {

	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		int xMove = Math.abs(fromX - toX);
		int yMove = Math.abs(fromY - toY);
		String direction = null;
		
		if (xMove == yMove) { //Sjekker om du går skrått
			
			if (fromX < toX && fromY < toY) { // Sjekker om du går opp mot høyre
				direction = "upRight";
				
				if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX > toX && fromY < toY) { // Sjekker om du går opp mot venstre
				direction = "upLeft";
				
				if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX < toX && fromY > toY) { // Sjekker om du går ned mot høyre
				direction = "downRight";
				
				if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX > toX && fromY > toY) { // Sjekker om du går ned mot venstre
				direction = "downLeft";
				
				if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			return true;
		}
		
		return false;
	}

	private boolean canIMovePiece(int fromX, int fromY, int toX, int toY, int squaresMoved, String direction) {
		
		for (int i = 0; i < squaresMoved; i++) { 
			String movedDirection = direction;
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8]; 
			BoardSquare square; 
			
			switch (movedDirection) {
			case "upRight":
				previousBoxX[i] = toX - (i + 1);
				previousBoxY[i] = toY - (i + 1);
				break;
			case "upLeft":
				previousBoxX[i] = toX + (i + 1);
				previousBoxY[i] = toY - (i + 1);
				break;
			case "downRight":
				previousBoxX[i] = toX - i;
				previousBoxY[i] = toY + i;
				break;
			case "downLeft":
				previousBoxX[i] = toX + i;
				previousBoxY[i] = toY + i;
				break;
			}
			
			if (previousBoxX[i] != fromX && previousBoxY[i] != fromY) { 
				square = getSquareAt(new Position(previousBoxX[i],previousBoxY[i])); 
				
				if (square.hasChild()) {	
					System.out.println("Du prøvde å gå " + i + "trekk over en brikke"); 
					return false; 
				}
				
			}
			
		}

		return true; 
	}
	
}
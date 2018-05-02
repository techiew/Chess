package rules;

import gui.Position;
import gui.BoardSquare;
import gui.ChessPiece;

public class Rook extends Rules implements RulesInterface {
	
	//Sjekker om Rook gjør et lovlig trekk. Returner true hvis trekket er lovlig, og false hvis den er ulovlig. 
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		int xMove = Math.abs(fromX - toX);
		int yMove = Math.abs(fromY - toY);
		String direction = null;
		
		if (fromX == toX) {		//Sjekker om du beveger deg på Y aksen, altså opp eller ned
		
			if (fromY < toY) {	
				direction = "up";
				
				if (canIMoveStraight(fromX, fromY, toX, toY, yMove, direction) == false) { 
					return false;
				}
				
			}
		
			if (fromY > toY) {
				direction = "down";
				
				if (canIMoveStraight(fromX, fromY, toX, toY, yMove, direction) == false) { 
					return false;
				}
				
			}
			
			return true;
		}
		
		if (fromY == toY) {		//Sjekker om du beveger deg på X aksen, altså høyre eller venstre
			
			if (fromX < toX) {	
				direction = "right";
				
				if (canIMoveStraight(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX > toX) {
				direction = "left";
				
				if (canIMoveStraight(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			return true;
		}
		
		return false;
	}	
	
	//Sjekker at Rook brikken ikke passerer noen andre brikker på veien bort til ruten brukeren trykket på. 
	private boolean canIMoveStraight(int fromX, int fromY, int toX, int toY, int squaresMoved, String direction) {
		
		for (int i = 0; i < squaresMoved; i++) { 
			String movedDirection = direction;
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8]; 
			BoardSquare square; 
			boolean horizontal = false; //sjekker om du holder deg på X aksen eller ikke
			
			switch (movedDirection) {
			case "up":
				previousBoxY[i] = toY - (i + 1);
				break;
			case "down":
				previousBoxY[i] = toY + (i + 1);
				break;
			case "right":
				horizontal = true;
				previousBoxX[i] = toX - (i + 1);
				break;
			case "left":
				horizontal = true;
				previousBoxX[i] = toX + (i + 1);
				break;
			}
			
			if (!horizontal) { //hvis du beveger deg vertikalt, så sjekker han at ruta du sjekker ikke er ruta du kom fra
				
				if (previousBoxY[i] != fromY) {
					square = getSquareAt(new Position(toX, previousBoxY[i])); 
					
					if (square.hasChild()) {	
						return false; 
					}
					
				}
				
			} else {	//hvis du beveger deg horizontalt så sjekker han at ruta du sjekker ikke er ruta du kom fra
				
				if (previousBoxX[i] != fromX) {
					square = getSquareAt(new Position(previousBoxX[i], toY)); 
					
					if (square.hasChild()) {	
						return false; 
					}
					
				}

			}
		
		}

		return true; 
	}
	
}










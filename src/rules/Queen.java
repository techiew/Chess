package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

public class Queen extends Rules implements RulesInterface {
	
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
		
		if (xMove == yMove) { //Sjekker om du går skrått
			
			if (fromX < toX && fromY < toY) { // Sjekker om du går opp mot høyre
				direction = "upRight";
				
				if (canIMovePieceDiagonal(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX > toX && fromY < toY) { // Sjekker om du går opp mot venstre
				direction = "upLeft";
				
				if (canIMovePieceDiagonal(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX < toX && fromY > toY) { // Sjekker om du går ned mot høyre
				direction = "downRight";
				
				if (canIMovePieceDiagonal(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			if (fromX > toX && fromY > toY) { // Sjekker om du går ned mot venstre
				direction = "downLeft";
				
				if (canIMovePieceDiagonal(fromX, fromY, toX, toY, xMove, direction) == false) { 
					return false;
				}
				
			}
			
			return true;
		}
		
		return false;
		
	}
	
	private boolean canIMovePieceDiagonal(int fromX, int fromY, int toX, int toY, int squaresMoved, String direction) {
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
				square = getSquareAt(new Position(previousBoxX[i], previousBoxY[i])); 
				
				if (square.hasChild()) {	
					System.out.println("Du prøvde å gå " + i + " trekk over en brikke"); 
					return false; 
				}
				
			}
			
		}

		return true; 
	}
	
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
				System.out.println(previousBoxY[i]);
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
						System.out.println("Du prøvde å gå " + (i + 1) + " trekk over en brikke"); 
						System.out.println(toX + "" + previousBoxY[i]);
						return false; 
					}
					
				}
				
			} else {	//hvis du beveger deg horizontalt så sjekker han at ruta du sjekker ikke er ruta du kom fra
				
				if (previousBoxX[i] != fromX) {
					square = getSquareAt(new Position(previousBoxX[i], toY)); 
					if (square.hasChild()) {	
						System.out.println("Du prøvde å gå " + (i + 1) + " trekk over en brikke"); 
						System.out.println(previousBoxX[i] + "" + toY);
						return false; 
					}
					
				}

			}
		
		}
		return true; 
	}	
}

















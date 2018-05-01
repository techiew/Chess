/*package rules;
kjetil som er mongo og legger rook.java i GUI
import gui.Position;
import gui.BoardSquare;
import gui.ChessPiece;

public class Rook extends Rules implements RulesInterface {
	
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		int xMove = Math.abs(fromX - toX);
		int yMove = Math.abs(fromY - toY);
		String direction = null;
		
		if (xMove != yMove) {
		
		if (fromX < toX) {
			direction = "right";
			
			if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) {
				return false;
			}
		}
		
		if (fromX > toX) {
			direction = "left";
			
			if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) {
				return false;
			}
		}
		
		if (fromY < toY) {
			direction = "up";
			
			if (canIMovePiece(fromX, fromY, toX, toY, xMove, direction) == false) {
				return false;
			}
		}
		
		if (fromY > toY) {
			direction = "down";
			
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
			case "right":
				previousBoxX[i] = toX - (i + 1);
				break;
			case "left":
				previousBoxX[i] = toX + (i + 1);
				break;
			case "up":
				previousBoxY[i] = toY - (i + 1);
				break;
			case "down":
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
}
*/
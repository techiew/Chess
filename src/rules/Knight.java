package rules;

import gui.Position;
import gui.BoardSquare;
import gui.ChessPiece;

public class Knight extends Rules implements RulesInterface {
	
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		
		boolean valid = false;
		
		Position[] legalPositions = { //Posisjoner relativt til 0, 0 på brettet
				new Position(2, 1), new Position(1, 2), 
				new Position(-2, 1), new Position(-1, 2),
				new Position(-2, -1), new Position(-1, -2),
				new Position(2, -1), new Position(1, -2)
				};
		
		for(int i = 0; i < legalPositions.length; i++) {
			
			if(legalPositions[i].getX() + from.getX() == to.getX()) {
				
				if(legalPositions[i].getY() + from.getY() == to.getY()) {
					valid = true;
				}
				
			}
			
		}
		
		return valid;
	}
	
}
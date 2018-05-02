package rules;

import gui.Position;
import gui.BoardSquare;
import gui.ChessPiece;

public class King extends Rules implements RulesInterface {
	
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		
		if(!inRange(from.getX(), to.getX(), 1)){
			return false;
		}
		
		if(!inRange(from.getY(), to.getY(), 1)){
			return false;
		}
		
		if(isKingInCheck(b, from, to, piece.getColor(), to)) {
			return false;
		}
		
		return true;
	}
	
}
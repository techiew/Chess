package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

public class Rules implements RulesInterface {

	protected BoardSquare[][] b;
	
	@Override
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inRange(int start, int value, int range) {
		
		if(start == value) return true;
		
		return ((start + range) >= value && (start - range) <= value);
	}

	@Override
	public BoardSquare getSquareAt(Position pos) {
		return b[pos.getX()][pos.getY()];
	}
	
}

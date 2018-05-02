package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

public interface RulesInterface {
	
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to);
	public boolean inRange(int start, int value, int range);
	public BoardSquare getSquareAt(Position pos);
	public boolean isKingInCheck(BoardSquare[][] board, Position fromPos, Position toPos, String kingColor, Position kingPos);

}

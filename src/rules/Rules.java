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
	
	//@Override
	//public boolean isPathObstructed(Position from, Position to) {
		//int fX = from.getX();
		//int fY = from.getY();
		//int tX = to.getX();
		//int tY = to.getY();
		
		//Position direction = new Position(fX - tX, fY - tY);
		//Position normalizedDir = new Position(direction.getX() * direction.getX(), direction.getY() * 
	//}
	
	@Override
	public boolean isKingInCheck(ChessPiece king, Position kingPosFrom, Position kingPosTo) {
		BoardSquare[][] tempB = b.clone();
		tempB[kingPosFrom.getX()][kingPosFrom.getY()].movePieceNoRules(tempB[kingPosTo.getX()][kingPosTo.getY()]);
		
		for(int x = 0; x < tempB[0].length; x++){
			
			for(int y = 0; y < tempB[1].length; y++) {
			
				if(tempB[x][y].hasChild()) {
					ChessPiece child = tempB[x][y].getChild();
					
					if(child.getColor() == king.getColor() || child == king) {
						continue;
					}
					
					boolean legalMove = ((RulesInterface) child.getRules()).isLegalMove(tempB, child, tempB[x][y].getPos(), kingPosTo);

					if(legalMove) {
						return true;
					}
					
				}
				
			}
			
		}
		
		return false;
	}
	
}

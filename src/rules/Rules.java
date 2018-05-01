package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

//Inneholder hjelpefunksjoner som er nyttig for regelklassene
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
	public boolean isKingInCheck(ChessPiece king, Position kingPos) {
		
		for(int x = 0; x < b[0].length; x++){
			
			for(int y = 0; y < b[1].length; y++) {
			
				if(b[x][y].hasChild()) {
					ChessPiece child = b[x][y].getChild();
					
					if(child.getColor() == king.getColor() || child == king) {
						continue;
					}
					
					boolean legalMove = ((RulesInterface) child.getRules()).isLegalMove(b, child, b[x][y].getPos(), kingPos);

					if(legalMove) {
						return true;
					}
					
				}
				
			}
			
		}
		
		return false;
	}
	
}

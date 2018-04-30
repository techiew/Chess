package rules;

import gui.Position;
import gui.BoardSquare;
import gui.ChessPiece;

public class Pawn extends Rules implements RulesInterface {
	
	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;
		
		boolean firstMove = piece.isFirstMove();
		
		//Beveger den seg rett oppover på en annen brikke? 
		if(from.getX() == to.getX()) {
			
			if(getSquareAt(to).hasChild()) {
				return false;
			}
			
		}
		
		//Beveger den seg skrått oppover og treffer ikke en brikke?
		if(from.getX() + 1 == to.getX() || from.getX() - 1 == to.getX()) {
			
			if(!getSquareAt(to).hasChild()) {
				return false;
			}
			
		}
		
		//Beveger den seg +1 eller -1 på X aksen?
		if(!inRange(from.getX(), to.getX(), 1)) {
			return false;
		}
		
		//Beveger den seg opp/ned? hvis det er første trekket for denne brikka,
		//la den gå 2 ruter oppover, hvis ikke, bare 1
		//Sjekker også om det er en brikke i mellom hvis bonden beveger seg 2 skritt
		if(piece.getDirection() == "up") {
			
			if(firstMove) {
				
				if(from.getY() + 1 != to.getY() && from.getY() + 2 != to.getY()) {
					return false;
				}
				
				if(to.getY() - 2 == from.getY()) {
					
					if(getSquareAt(new Position(to.getX(), to.getY() - 1)).hasChild()) {
						return false;
					}
					
				}
				
			} else if(from.getY() + 1 != to.getY()) {
				return false;
			}
			
			
		} else if(piece.getDirection() == "down") {
			
			if(firstMove) {
				
				if(from.getY() - 1 != to.getY() && from.getY() - 2 != to.getY()) {
					return false;
				}
				
				if(to.getY() + 2 == from.getY()) {
					
					if(getSquareAt(new Position(to.getX(), to.getY() + 1)).hasChild()) {
						return false;
					}
					
				}
				
			} else if(from.getY() - 1 != to.getY()) {
				return false;
			}
			
		}
		
		return true;
	}
	
}
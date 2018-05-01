package rules;

import java.awt.Color;

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
	public boolean isKingInCheck(ChessPiece king, Position kingPosFrom, Position kingPosTo) {
		BoardSquare[][] tempB = new BoardSquare[b[0].length][b[1].length];
		
		for(int x = 0; x < b[0].length; x++) {
			
			for(int y = 0; y < b[1].length; y++) {
				BoardSquare square = new BoardSquare(b[x][y]);
				tempB[x][y] = square;
				
				BoardSquare squareReset = b[x][y];
				if(squareReset.getPos() != kingPosFrom) {
					squareReset.setBackground(squareReset.getOriginalColor());
				}
				
			}
			
		}
		
		tempB[kingPosTo.getX()][kingPosTo.getY()].addPiece(king);
		
		boolean legalMoveToKing = false;
		
		for(int x = 0; x < tempB[0].length; x++) {
			
			for(int y = 0; y < tempB[1].length; y++) {
			
				if(tempB[x][y].hasChild()) {
					ChessPiece child = tempB[x][y].getChild();
					boolean canMoveToKing = false;
					
					if(child.getColor() == king.getColor() || child == king) {
						continue;
					}
					
					//Må ha et unntak for bønder, fordi de kan ha et "lovlig" trekk til kongen uten å kunne angripe den samtidig
					//Så vi må sjekke om bonden har et lovlig trekk på kongen som i tillegg tells som et angrep
					//Må også ha et unntak for den andre kongen, hvis kongene står ved siden av hverandre så vil de sette hverandre i sjakk
					//Og det vil skape en evig loop hvor de sjekker hverandres isLegalMove
					if(child.getType() == PieceType.PAWN || child.getType() == PieceType.KING) {
						
							if(child.getType() == PieceType.PAWN) {
							int posX = tempB[x][y].getPos().getX();
							int posY = tempB[x][y].getPos().getY();
							
							if(child.getDirection() == "up") {
								
								if(inRange(posX, kingPosTo.getX(), 1) && posY + 1 == kingPosTo.getY()) {
									canMoveToKing = true;
								}
								
							} else if(child.getDirection() == "down"){
								
								if(inRange(posX, kingPosTo.getX(), 1) && posY - 1 == kingPosTo.getY()) {
									canMoveToKing = true;
								}
								
							}
							
						} else if(child.getType() == PieceType.KING) {
							int posX = tempB[x][y].getPos().getX();
							int posY = tempB[x][y].getPos().getY();
							
							if(inRange(posX, kingPosTo.getX(), 1) && inRange(posY, kingPosTo.getY(), 1)) {
								canMoveToKing = true;
							}
							
						}
							
					} else {
						canMoveToKing = ((RulesInterface) child.getRules()).isLegalMove(tempB, child, tempB[x][y].getPos(), kingPosTo);
					}
					
					if(canMoveToKing) {
						legalMoveToKing = true;
						System.out.println("Blokkert av " + child.getType() + " | " + child.getColor());
						b[x][y].setBackground(new Color(255, 0, 0));
					}
					
				}
				
			}
			
		}
		
		return legalMoveToKing;
	}
	
}

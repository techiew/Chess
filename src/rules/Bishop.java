package rules;

import gui.BoardSquare;
import gui.ChessPiece;
import gui.Position;

public class Bishop extends Rules implements RulesInterface {

	public boolean isLegalMove(BoardSquare[][] board, ChessPiece piece, Position from, Position to) {
		b = board;

		boolean firstMove = piece.isFirstMove();
		boolean check = false;
		// int checkX = to.getX() - from.getX();
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		int test = (toX);
		int xMove = Math.abs(fromX - toX);
		int yMove = Math.abs(fromY - toY);
		if (xMove == yMove) {
			if (fromX < toX && fromY < toY) // OPP MOT HØYRE
			{
				if (canIMoveUpRight(fromX, fromY, toX, toY, xMove) == false) {
					return false;
				}
			}
			if (fromX > toX && fromY < toY) // OPP MOT VENSTRE
			{
				if (canIMoveUpLeft(fromX, fromY, toX, toY, xMove) == false) {
					return false;
				}
			}
			if (fromX < toX && fromY > toY) // NED MOT HØYRE
			{
				if (canIMoveDownRight(fromX, fromY, toX, toY, xMove) == false) {
					return false;
				}
			}
			if (fromX > toX && fromY > toY) // NED MOT VENSTRE
			{
				if (canIMoveDownLeft(fromX, fromY, toX, toY, xMove) == false) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private boolean canIMoveUpRight(int fromX, int fromY, int toX, int toY, int squaresMoved) {
		for (int i = 1; i <= squaresMoved; i++) { // hvis du får out bounds sjekk her
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8];
			Position[] positionArray = new Position[8];
			BoardSquare[] squareArray = new BoardSquare[8];
			previousBoxX[i] = toX - i;
			previousBoxY[i] = toY - i;

			if (previousBoxX[i] != fromX && previousBoxY[i] != fromY) {
				System.out.println("HALLO" + previousBoxX[i] + "" + previousBoxY[i]);
				positionArray[i] = new Position(previousBoxX[i], previousBoxY[i]);
				squareArray[i] = getSquareAt(positionArray[i]);

				if (squareArray[i].hasChild()) {
					System.out.println("Du prøvde å gå " + i + "trekk over en brikke");
					return false;
				}
			}
		}

		return true;
	}

	private boolean canIMoveUpLeft(int fromX, int fromY, int toX, int toY, int squaresMoved) {
		for (int i = 1; i <= squaresMoved; i++) { // hvis du får out bounds sjekk her
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8];
			Position[] positionArray = new Position[8];
			BoardSquare[] squareArray = new BoardSquare[8];
			previousBoxX[i] = toX + i;
			previousBoxY[i] = toY - i;

			if (previousBoxX[i] != fromX && previousBoxY[i] != fromY) {
				System.out.println("HALLO" + previousBoxX[i] + "" + previousBoxY[i]);
				positionArray[i] = new Position(previousBoxX[i], previousBoxY[i]);
				squareArray[i] = getSquareAt(positionArray[i]);

				if (squareArray[i].hasChild()) {
					System.out.println("Du prøvde å gå " + i + "trekk over en brikke");
					return false;
				}
			}
		}
		return true;
	}

	private boolean canIMoveDownRight(int fromX, int fromY, int toX, int toY, int squaresMoved) {
		for (int i = 1; i <= squaresMoved; i++) { // hvis du får out bounds sjekk her
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8];
			Position[] positionArray = new Position[8];
			BoardSquare[] squareArray = new BoardSquare[8];
			previousBoxX[i] = toX - i;
			previousBoxY[i] = toY + i;

			if (previousBoxX[i] != fromX && previousBoxY[i] != fromY) {
				System.out.println("HALLO" + previousBoxX[i] + "" + previousBoxY[i]);
				positionArray[i] = new Position(previousBoxX[i], previousBoxY[i]);
				squareArray[i] = getSquareAt(positionArray[i]);

				if (squareArray[i].hasChild()) {
					System.out.println("Du prøvde å gå " + i + "trekk over en brikke");
					return false;
				}
			}
		}

		return true;
	}

	private boolean canIMoveDownLeft(int fromX, int fromY, int toX, int toY, int squaresMoved) {
		for (int i = 1; i <= squaresMoved; i++) { // hvis du får out bounds sjekk her
			int[] previousBoxX = new int[8];
			int[] previousBoxY = new int[8];
			Position[] positionArray = new Position[8];
			BoardSquare[] squareArray = new BoardSquare[8];
			previousBoxX[i] = toX - i;
			previousBoxY[i] = toY + i;

			if (previousBoxX[i] != fromX && previousBoxY[i] != fromY) {
				System.out.println("HALLO" + previousBoxX[i] + "" + previousBoxY[i]);
				positionArray[i] = new Position(previousBoxX[i], previousBoxY[i]);
				squareArray[i] = getSquareAt(positionArray[i]);

				if (squareArray[i].hasChild()) {
					System.out.println("Du prøvde å gå " + i + "trekk over en brikke");
					return false;
				}
			}
		}
		return true;
	}

}

// behold, frankenstein
/*
 * int toX1 = toX-1; int toY1 = toY-1; int toX2 = toX-2; int toY2 = toY-2; int
 * toX3 = toX-3; int toY3 = toY-3; int toX4 = toX-4; int toY4 = toY-4; int toX5
 * = toX-5; int toY5 = toY-5; int toX6 = toX-6; int toY6 = toY-6; int toX7 =
 * toX-7; int toY7 = toY-7; if (toX1 != fromX && toY1 != fromY) {
 * 
 * Position pos1 = new Position(toX1, toY1); //Posisjonen er den rett bak deg
 * BoardSquare square1 = getSquareAt(pos1); //Henter firkanten ruta før ruta du
 * går til if (square1.hasChild()) {
 * System.out.println("Du prøvde å gå EN over en brikke"); return false; }
 * 
 * if (toX2 != fromX && toY2 != fromY) {
 * 
 * Position pos2 = new Position(toX2, toY2); //Posisjonen er den rett bak deg
 * BoardSquare square2 = getSquareAt(pos2); //Henter firkanten ruta før ruta du
 * går til if (square2.hasChild()) {
 * System.out.println("Du prøvde å gå TO over en brikke"); return false; }
 * 
 * if (toX3 != fromX && toY3 != fromY) { Position pos3 = new Position(toX3,
 * toY3); //Posisjonen er den rett bak deg BoardSquare square3 =
 * getSquareAt(pos3); //Henter firkanten ruta før ruta du går til if
 * (square3.hasChild()) {
 * System.out.println("Du prøvde å gå TRE over en brikke"); return false; }
 * 
 * if (toX4 != fromX && toY4 != fromY) { Position pos4 = new Position(toX4,
 * toY4); //Posisjonen er den rett bak deg BoardSquare square4 =
 * getSquareAt(pos4); //Henter firkanten ruta før ruta du går til if
 * (square4.hasChild()) {
 * System.out.println("Du prøvde å gå FIRE over en brikke"); return false; }
 * 
 * if (toX5 != fromX && toY5 != fromY) { Position pos5 = new Position(toX5,
 * toY5); //Posisjonen er den rett bak deg BoardSquare square5 =
 * getSquareAt(pos5); //Henter firkanten ruta før ruta du går til if
 * (square5.hasChild()) {
 * System.out.println("Du prøvde å gå FEM over en brikke"); return false; }
 * 
 * if (toX6 != fromX && toY6 != fromY) { Position pos6 = new Position(toX6,
 * toY6); //Posisjonen er den rett bak deg BoardSquare square6 =
 * getSquareAt(pos6); //Henter firkanten ruta før ruta du går til if
 * (square6.hasChild()) {
 * System.out.println("Du prøvde å gå SEKS over en brikke"); return false; }
 * 
 * if (toX7 != fromX && toY7 != fromY) { Position pos7 = new Position(toX7,
 * toY7); //Posisjonen er den rett bak deg BoardSquare square7 =
 * getSquareAt(pos7); //Henter firkanten ruta før ruta du går til if
 * (square7.hasChild()) {
 * System.out.println("Du prøvde å gå SYV over en brikke"); return false; } } }
 * }
 * 
 * } } } } return true;
 */
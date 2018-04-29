package gui;

import multiplayer.*;

public class Main {

	public static void main(String[] args) {
		//new ChessBoardAnalyze();
		
		//new ChessBoard(true, false, "localhost", 21337, "Sjakk"); //Server
		new ChessBoard(true, true, "localhost", 21337, "Sjakk"); //Klient
	}

}

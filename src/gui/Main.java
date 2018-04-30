package gui;

import multiplayer.*;

public class Main {

	public static void main(String[] args) {
		//new InitializeChess();
		//new ChessMenu();
		//while(menu.isVisible()) {
			//en eller annen m�te � stoppe resten p� midlertidig
		//}
		
		//String ip = menu.ip;
		//int port = menu.port;
		//boolean isMultiplayer = menu.isMultiplayer; // er det multiplayer eller singleplayer?
		//boolean isClient = menu.isClient; //hoster vi server eller joiner vi??
		//S� vil instansieringa se s�nn her ut:
		new ChessBoard(false, false, "localhost", 21337, "Sjakk");
		//new ChessBoard(true, true, "localhost", 21337, "Sjakk");
		
		//new ChessBoardAnalyze();
	}

}

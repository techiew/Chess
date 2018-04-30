package gui;

import multiplayer.*;

public class Main {

	public static void main(String[] args) {
		//new ChessMenu();
		//while(menu.isVisible()) {
			//en eller annen m�te � stoppe resten p� midlertidig
		//}
		
		//String ip = menu.ip;
		//int port = menu.port;
		//boolean isMultiplayer = menu.isMultiplayer; // er det multiplayer eller singleplayer?
		//boolean isClient = menu.isClient; //hoster vi server eller joiner vi??
		//S� vil instansieringa se s�nn her ut:
		//new ChessBoard(isMultiplayer, isClient, ip, port, "Sjakk");
		
		//new ChessBoardAnalyze();
		new ChessBoard(true, false, "localhost", 21337, "Sjakk"); //Server
		new ChessBoard(true, true, "localhost", 21337, "Sjakk"); //Klient
	}

}

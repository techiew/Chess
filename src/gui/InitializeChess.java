package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InitializeChess {
	String clientIP;
	int clientPort;
	int hostPort;
	JButton clientJoin = new JButton();
	JButton hostButton = new JButton();
	JButton chessAnalyzeButton = new JButton();
	boolean client = true;
	boolean server = false;
	boolean multiplayer = true;
	public InitializeChess() {
		
		ChessMenu menu = new ChessMenu();
		clientJoin = menu.btnJoin;
		hostButton = menu.btnHost;
		chessAnalyzeButton = menu.chessAnalyzeButton;
		
		clientJoin.addActionListener(new ActionListener() {		//JOIN GAME, KLIENT
			public void actionPerformed (ActionEvent e)
			{
				clientIP = menu.clientIP;
				clientPort = menu.clientPort;
				if (clientIP == null && clientPort == 0) {
					System.out.println("Vennligst sett inn en IP adresse og Port :)))))))");
				}
				else {
					System.out.println(clientPort + clientIP);
					new ChessBoard(multiplayer, client, clientIP, clientPort, "Sjakk");
				}
			}
		});
		
		hostButton.addActionListener(new ActionListener() {		//HOST GAME, SERVER
			public void actionPerformed (ActionEvent e)
			{
				hostPort = menu.hostPort;
				if (hostPort == 0) {
					System.out.println("Vennligst sett inn en Port Xd");
				}
				else {
					new ChessBoard(multiplayer, server, "localhost", hostPort, "Sjakk"); 
				}
			}
		});
		
		chessAnalyzeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				new ChessBoardAnalyze();
			}
		});
		
	}
}

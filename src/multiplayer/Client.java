package multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import gui.ChessBoard;

public class Client implements ConnectionInterface, Runnable {

	private String ip;
	private int port;
	private Socket socket;
	private boolean connected = false;
	private InputStreamReader ir;
	private BufferedReader input;
	private PrintStream output;
	private ChessBoard board;
	
	public Client(ChessBoard board, String ip, int port) {
		this.board = board;
		this.ip = ip;
		this.port = port;
	}
	
	private void start() {
		
		try {
			socket = new Socket(ip, port);
			connected = true;
			output = new PrintStream(socket.getOutputStream());
			//output.println("Hei fra klienten.");
			
			ir = new InputStreamReader(socket.getInputStream());
			input = new BufferedReader(ir);
			
			//String msg = input.readLine();
			//System.out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void handshake() {
		
	}
	
	public void sendResponse(String response) {
		output.println(response);
	}
	
	public void waitForResponse() {
		
		try {
			
			String response = null;
			
			System.out.println("Klient venter p� respons");
			response = input.readLine();
			
			System.out.println(response);
			return;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
}

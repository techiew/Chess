package multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import gui.ChessBoard;

//Socket til serveren, som lar klienten koble seg til
public class Server implements ConnectionInterface, Runnable {
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ChessBoard board;
	
	public Server(ChessBoard board, int port) {
		this.board = board;
		this.port = port;
	}
	
	//Entry point for tråden vår
	@Override
	public void run() {
		
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			
			if(socket.isConnected()) {
				input = new ObjectInputStream(socket.getInputStream());
				output = new ObjectOutputStream(socket.getOutputStream());
				handshake();
				notifyConnection();
			}
			
			while(socket.isConnected()) {
				waitForResponse();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	//Si hei til hverandre
	private void handshake() {
		
		try {			
			System.out.println("SERVER: handshake");
			Message sendTestMsg = new Message("Hei fra serveren");
			Message getTestMsg = (Message)input.readObject();
			
			if(getTestMsg.getMessage() != null) {
				System.out.println(getTestMsg.getMessage());
				output.writeObject(sendTestMsg);
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	//Vent på en melding fra den andre spilleren
	private void waitForResponse() {
		
		try {
			Message response = null;	
			System.out.println("Server venter på respons");
			
			while(input.available() > 0) {
				Thread.sleep(100);
			}
			
			response = (Message)input.readObject();
			
			System.out.println("SERVER: " + response.getMessage());
			updateChessBoard(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	//Send en melding til den andre spilleren, sendes når en brikke blir flyttet
	public void sendResponse(Message response) {
		
		try {
			output.writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Kjøres når en melding har blitt mottatt, lar oss oppdatere brettet vårt
	private void updateChessBoard(Message msg) {
		board.getUpdateFromSocket(msg);
	}
	
	//Si ifra til GUI at vi har blitt tilkoblet og starter sjakkspillet
	private void notifyConnection() {
		board.onPlayerConnected();
	}

}

package multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import gui.ChessBoard;

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
	
	@Override
	public void run() {
		
		try {
			
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			
			if(socket.isConnected()) {
				handshake();
			}
			
			while(socket.isConnected()) {
				waitForResponse();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				socket.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
			
		}
		
	}
	
	//Si hei til hverandre og bestem ting som f.eks. hvilken spiller som er hvilken farge
	private void handshake() {
		
		try {
			
			connected = true;
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
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
		
	private void waitForResponse() {
		
		try {
			
			Message response = null;	
			
			System.out.println("Server venter på respons");
			response = (Message)input.readObject();
			
			System.out.println(response.getMessage());
			updateChessBoard(response);
			
			return;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	private void updateChessBoard(Message msg) {
		board.getUpdateFromSocket(msg);
	}

}

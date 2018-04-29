package multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ConnectionInterface, Runnable {

	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean connected = false;
	private InputStreamReader ir;
	private BufferedReader input;
	private PrintStream output;
	
	public Server(int port) {
		this.port = port;
		start();
	}
	
	private void start() {
		
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			connected = true;
			ir = new InputStreamReader(socket.getInputStream());
			input = new BufferedReader(ir);
			
			//String msg = input.readLine();
			//System.out.println(msg);
			
			//if(msg != null) {
				output = new PrintStream(socket.getOutputStream());
				//output.println("Hei fra serveren.");
			//}
			
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
	
	public String waitForResponse() {
		
		try {
			
			String response = null;	
			
			System.out.println("Server venter på respons");
			response = input.readLine();
			
			System.out.println(response);
			return response;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

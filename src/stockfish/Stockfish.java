package stockfish;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 

public class Stockfish {
	private Process engineProcess;
	private BufferedReader processReader;
	private OutputStreamWriter processWriter;
	private static final String PATH = "stockfish_9_x32.exe";
	
	public boolean startEngine() {
		
		try {
			engineProcess = Runtime.getRuntime().exec(PATH);
			processReader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
			processWriter = new OutputStreamWriter(engineProcess.getOutputStream());
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public void stopEngine() {
		
		try {
			sendCommand("quit");
			processReader.close();
			processWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendCommand(String command) {
		
		try {
			processWriter.write(command + "\n");
			processWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getOutput(int waitTime) {
		StringBuffer buffer = new StringBuffer();
		
		try {
			Thread.sleep(waitTime);
			sendCommand("isready");
			
			while (true) {
				String text = processReader.readLine();
				
				if (text.equals("readyok")) {
					break;
				}
				else {
					buffer.append(text + "\n");
				}	
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}
	
	public void sendFen(String fen) {
		sendCommand("position fen " + fen);
		System.out.println(fen + "ble mekka");
	}
	
	public String getBestMove(int waitTime) {
		sendCommand("go movetime " + waitTime);
		return getOutput(waitTime + 20).split("bestmove ")[1].split(" ")[0];
	}
	
}

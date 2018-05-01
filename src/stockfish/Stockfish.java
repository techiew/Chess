package stockfish;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 

public class Stockfish {
	//En veldig stor del av klassen er lånt av Rahul og du finner hans kildekode her:
	//https://www.programcreek.com/java-api-examples/index.php?source_dir=chess-misc-master/JavaStockfish/src/com/rahul/stockfish/Stockfish.java#
	//mere detaljer står i den tekniske manualen. 
	private Process engineProcess;
	private BufferedReader processReader;
	private OutputStreamWriter processWriter;
	private static final String PATH = "src/stockfishengine/stockfish_9_x32.exe";
	
	public boolean startEngine() { //Metoden er skrevet og lånt fra Rahul.
		
		try {
			engineProcess = Runtime.getRuntime().exec(PATH);
			processReader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
			processWriter = new OutputStreamWriter(engineProcess.getOutputStream());
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public void stopEngine() { //Metoden er skrevet og lånt fra Rahul.
		
		try {
			sendCommand("quit");
			processReader.close();
			processWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendCommand(String command) { //Metoden er skrevet og lånt fra Rahul.
		
		try {
			processWriter.write(command + "\n");
			processWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getOutput(int waitTime) { //Metoden er skrevet og lånt fra Rahul.
		
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
	
	public void sendFen(String fen) { //Kode skrevet av oss, men benytter oss av Rahul's sendCommand metode. 
		sendCommand("position fen " + fen);
	}
	
	public String getBestMove(int waitTime) { //Metoden er skrevet og lånt fra Rahul.
		sendCommand("go movetime " + waitTime);
		return getOutput(waitTime + 20).split("bestmove ")[1].split(" ")[0];
	}
	
	 public float getEvalScore(String fen, int waitTime) { 
		  sendCommand("position fen " + fen); 
		  sendCommand("go movetime " + waitTime); 
		 
		  float evalScore = 0.0f; 
		  String[] dump = getOutput(waitTime + 20).split("\n"); 
		  //System.out.println(dump[21]);
		 // System.out.println(dump.length);
		  for (int i = 1; i >= 0; i--) { 
		   if (dump[i].startsWith("info depth ")) { 
		    try { 
		    evalScore = Float.parseFloat(dump[i].split("score cp ")[1] 
		      .split(" nodes")[0]); 
		    } catch(Exception e) { 
		     evalScore = Float.parseFloat(dump[i].split("score cp ")[1] 
		       .split(" upperbound nodes")[0]); 
		    } 
		   } 
		  } 
		  return evalScore/100; 
		 }
	
}

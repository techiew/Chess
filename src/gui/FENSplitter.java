package gui;

//FENSplitter klassen tar imot en String som inneholder en FEN nøkkel. Deretter splitter han opp FEN nøkkelen flere ganger og legger det i et array. Hvert element i arrayet representerer en rute. 
public class FENSplitter {
	private String[] completeSplitFenCode = new String[64];
	private int arrayCounter = 0;
	
	public FENSplitter(String fen) {			
		String trimmedFenCode = "";
		String[] incompleteSplitFenCode;
		int counter = 0;
		String FENstring = fen;
		String[] fenArray = FENstring.split("/");
		String[] splitEnd = fenArray[7].split(" ");
		
		for (int i = 0; i<7; i++) {
			trimmedFenCode = trimmedFenCode + fenArray[i];
		}
		
		trimmedFenCode = trimmedFenCode + splitEnd[0];
		incompleteSplitFenCode = trimmedFenCode.split(""); 
		
		for (int i = 0; i<incompleteSplitFenCode.length; i++) {
			
			if (isANumber(incompleteSplitFenCode[i])) {
				int emptySpaceNumber = Integer.parseInt(incompleteSplitFenCode[i]);
				
				for (int y = 0; y < emptySpaceNumber; y++) {
					completeSplitFenCode[counter] = "0";
					counter++;
					arrayCounter++;
				}
				
			} else {
				completeSplitFenCode[counter] = incompleteSplitFenCode[i];
				counter++;
				arrayCounter++;
			}
	
		}
		
	}
	
	//Sjekker om arrayet ble fylt ut, stopper ugyldige FEN nøkler som ikke inneholder informasjon om alle rutene. 
	public boolean checkFenArray() {
		
		if (arrayCounter == 64){
			return true;
		} else {
			return false;
		}
		
	}
	
	//Metode som returnerer arrayet som inneholder all informasjonen om FEN koden. 
	public String[] getFenArray() {
		return completeSplitFenCode;
	}
	
	
	//Metode hentet fra stackoverflow https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
	//Sjekker om verdien tilsendt er et tall. 
	private boolean isANumber(String incompleteSplitFenCode) {
		
	    try {
	    	double check = Double.parseDouble(incompleteSplitFenCode);
	    } catch(NumberFormatException nfe) {
	    	return false;
	    }
	    
	    return true; 
	}
	
}

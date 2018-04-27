package gui;

public class FENbombe {
	private String[] completeSplitFenCode = new String[64];
	public FENbombe(String fen) {			// skal motta string
		String trimmedFenCode = "";
		String[] incompleteSplitFenCode;
		int counter = 0;
		String FENstring = fen;
		String[] fenArray = FENstring.split("/");
		String[] splitEnd = fenArray[7].split(" ");
		for (int i = 0; i<7; i++)
		{
			trimmedFenCode = trimmedFenCode + fenArray[i];
		}
		trimmedFenCode = trimmedFenCode + splitEnd[0];

		incompleteSplitFenCode = trimmedFenCode.split(""); 
		
		
		for (int i = 0; i<incompleteSplitFenCode.length; i++)
		{
			if (isANumber(incompleteSplitFenCode[i])) {
				int emptySpaceNumber = Integer.parseInt(incompleteSplitFenCode[i]);
				for (int y = 0; y < emptySpaceNumber; y++)
				{
					completeSplitFenCode[counter] = "0";
					counter++;
				}
			}
			else {
				completeSplitFenCode[counter] = incompleteSplitFenCode[i];
				counter++;
			}
		
		}


		
	}
	

	public String[] getFenArray()
	{
		return completeSplitFenCode;
	}
	
	private static boolean isANumber(String incompleteSplitFenCode)
	  {
	    try
	    {
	      double check = Double.parseDouble(incompleteSplitFenCode);
	    }
	    catch(NumberFormatException nfe)
	    {
	      return false;
	    }
	    return true;
	  }
	
	
	
	
	
}

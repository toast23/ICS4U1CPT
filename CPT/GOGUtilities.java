import java.io.*;
public interface GOGUtilities{
	public static String[][] loadArray(String strCSVFile){
		String strArray[][];
		String strTempArray[];
		String strLine;
		int intCnt;
		int intCnt2;
		int intRow=0;
		BufferedReader csv = null;
		
		//Count number of rows
		try{
			csv = new BufferedReader(new FileReader(strCSVFile));
		}catch(FileNotFoundException e){
			System.out.println("No such csv file exists!");
		}
		try{
			while(csv.readLine()!=null){
				intRow++;
			}
		}catch(IOException e){
			System.out.println("Input Error");
		}
		try{
			csv.close();
		}catch(IOException e){
			System.out.println("Unable to print");
		}
		strArray = new String[intRow][9];
		strTempArray = new String[8];
		//Load it
		try{
			csv = new BufferedReader(new FileReader(strCSVFile));
		}catch(FileNotFoundException e){
			System.out.println("No such csv file exists!");
		}
		
		for(intCnt = 0; intCnt < intRow; intCnt++){
			try{
				strTempArray = csv.readLine().split(",");;
			}catch(IOException e){
				System.out.println("Invalid Input");
				for(intCnt = 0; intCnt < 8; intCnt++){
					 strTempArray[intCnt] = "0";
				 }
			}
			for(intCnt2 = 0; intCnt2 < 9; intCnt2++){
				strArray[intCnt][intCnt2] = strTempArray[intCnt2];
			}
		}
		return strArray;
	}
	public static int getOriginalPositionX(String strMessage){
		int intOriginalPositionX=0;
		char chrCharacter=strMessage.charAt(0);
		//Format will be like this A1_A2
		if(chrCharacter=='A'){
			intOriginalPositionX=0;
		}else if(chrCharacter=='B'){
			intOriginalPositionX=1;
		}else if(chrCharacter=='C'){
			intOriginalPositionX=2;
		}else if(chrCharacter=='D'){
			intOriginalPositionX=3;
		}else if(chrCharacter=='E'){
			intOriginalPositionX=4;
		}else if(chrCharacter=='F'){
			intOriginalPositionX=5;
		}else if(chrCharacter=='G'){
			intOriginalPositionX=6;
		}else if(chrCharacter=='H'){
			intOriginalPositionX=7;
		}else if(chrCharacter=='I'){
			intOriginalPositionX=8;
		}
		return intOriginalPositionX;
	}
	public static int getOriginalPositionY(String strMessage){
		int intOriginalPositionY=0;
		char chrCharacter=strMessage.charAt(1);
		//Format will be like this A1_A2
		if(chrCharacter=='1'){
			intOriginalPositionY=0;
		}else if(chrCharacter=='2'){
			intOriginalPositionY=1;
		}else if(chrCharacter=='3'){
			intOriginalPositionY=2;
		}else if(chrCharacter=='4'){
			intOriginalPositionY=3;
		}else if(chrCharacter=='5'){
			intOriginalPositionY=4;
		}else if(chrCharacter=='6'){
			intOriginalPositionY=5;
		}else if(chrCharacter=='7'){
			intOriginalPositionY=6;
		}else if(chrCharacter=='8'){
			intOriginalPositionY=7;
		}
		return intOriginalPositionY;
	}
	public static int getNewPositionX(String strMessage){
		int intNewPositionX=0;
		char chrCharacter=strMessage.charAt(3);
		//Format will be like this A1_A2
		if(chrCharacter=='A'){
			intNewPositionX=0;
		}else if(chrCharacter=='B'){
			intNewPositionX=1;
		}else if(chrCharacter=='C'){
			intNewPositionX=2;
		}else if(chrCharacter=='D'){
			intNewPositionX=3;
		}else if(chrCharacter=='E'){
			intNewPositionX=4;
		}else if(chrCharacter=='F'){
			intNewPositionX=5;
		}else if(chrCharacter=='G'){
			intNewPositionX=6;
		}else if(chrCharacter=='H'){
			intNewPositionX=7;
		}else if(chrCharacter=='I'){
			intNewPositionX=8;
		}
		return intNewPositionX;
	}
	public static int getNewPositionY(String strMessage){
		int intNewPositionY=0;
		char chrCharacter=strMessage.charAt(4);
		//Format will be like this A1_A2
		if(chrCharacter=='1'){
			intNewPositionY=0;
		}else if(chrCharacter=='2'){
			intNewPositionY=1;
		}else if(chrCharacter=='3'){
			intNewPositionY=2;
		}else if(chrCharacter=='4'){
			intNewPositionY=3;
		}else if(chrCharacter=='5'){
			intNewPositionY=4;
		}else if(chrCharacter=='6'){
			intNewPositionY=5;
		}else if(chrCharacter=='7'){
			intNewPositionY=6;
		}else if(chrCharacter=='8'){
			intNewPositionY=7;
		}
		return intNewPositionY;
	}
	public static int rankConversion(String strArray[][], int intPosX, int intPosY){
		String strRank = strArray[intPosY][intPosX];
		int intRank=0;
		if(strRank.equals("P1Flag") || strRank.equals("P2Flag")){
			intRank = 0;
		}else if(strRank.equals("P1Private") || strRank.equals("P2Private")){
			intRank = 1;
		}else if(strRank.equals("P1Spy") || strRank.equals("P2Spy")){
			intRank = 2;
		}else if(strRank.equals("P1Seargent") || strRank.equals("P1Seargent")){
			intRank = 3;
		}else if(strRank.equals("P12nd_Lieutenant") || strRank.equals("P22nd_Lieutenant")){
			intRank = 4;
		}else if(strRank.equals("P11st_Lieutenant") || strRank.equals("P21st_Lieutenant")){
			intRank = 5;
		}else if(strRank.equals("P1Captain") || strRank.equals("P2Captain")){
			intRank = 6;
		}else if(strRank.equals("P1Major") || strRank.equals("P2Major")){
			intRank = 7;
		}else if(strRank.equals("P1Lieutenant_Colonel") || strRank.equals("P2Lieutenant_Colonel")){
			intRank = 8;
		}else if(strRank.equals("P1Colonel") || strRank.equals("P2Colonel")){
			intRank = 9;
		}else if(strRank.equals("P1Colonel") || strRank.equals("P2Colonel")){
			intRank = 10;
		}else if(strRank.equals("P1Brigadier_General") || strRank.equals("P2Brigadier_General")){
			intRank = 11;
		}else if(strRank.equals("P1Major_General") || strRank.equals("P2Major_General")){
			intRank = 12;
		}else if(strRank.equals("P1Lieutenant") || strRank.equals("P2Lieutenant")){
			intRank = 13;
		}else if(strRank.equals("P1General") || strRank.equals("P2General")){
			intRank = 14;
		}else if(strRank.equals("P1General_of_the_Army") || strRank.equals("P2General_of_the_Army")){
			intRank = 15;
		}
		return intRank;
	}
	public static String[][] flagMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strArray[intOGPosY][intOGPosX]==null){
		}else if(strArray[intOGPosY][intOGPosX].equals("P1Flag")){
			//If it's moves in any of the cardinal directions, move the pices
			if((intOGPosY+1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY-1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX+1==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX-1==intNewPosX)){
				//if there's nothing in the way, let flag go to new position
				if(strArray[intNewPosY][intNewPosX]==null || strArray[intNewPosY][intNewPosX].equals(" ")){
					//remove flag from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the flag move to new position
					strArray[intNewPosY][intNewPosX]="P1Flag";
				//if the enemy flag(value of 0) is in the way, kill it
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) == rankConversion(strArray, intOGPosX, intOGPosY)){
					//remove flag from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the flag move to new position
					strArray[intNewPosY][intNewPosX]="P1Flag";
				//if there's ANYTHING ELSE there, kill the flag
				}else if(rankConversion(strArray, intOGPosX, intOGPosY)<rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
				}
			}
		}
		return strArray;
	}
	public static String[][] privateMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strArray[intOGPosY][intOGPosX]==null){
		}else if(strArray[intOGPosY][intOGPosX].equals("P1Private")){
			//If it's moves in any of the cardinal directions, move the pices
			if((intOGPosY+1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY-1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX+1==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX-1==intNewPosX)){
				//if there's nothing in the way, let private go to new position
				if(strArray[intNewPosY][intNewPosX]==null || strArray[intNewPosY][intNewPosX].equals(" ")){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]="P1Private";
				//if the flag is there, kill it
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) > rankConversion(strArray, intNewPosX, intNewPosY)){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]="P1Private";
				//if there's a private there, kill both
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) == rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
					strArray[intNewPosY][intNewPosX]=null;
				//if there's a spy(value of 2) there, kill the spy!
				}else if(rankConversion(strArray, intNewPosX, intNewPosY) == 2){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]="P1Private";
				//if there's anything of higher rank, kill the private
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) < rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
				}
			}
		}
		return strArray;
	}
	public static String[][] spyMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strArray[intOGPosY][intOGPosX]==null){
		}else if(strArray[intOGPosY][intOGPosX].equals("P1Spy")){
			//If it's moves in any of the cardinal directions, move the pices
			if((intOGPosY+1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY-1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX+1==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX-1==intNewPosX)){
				//if there's nothing in the way, let spy go to new position
				if(strArray[intNewPosY][intNewPosX]==null || strArray[intNewPosY][intNewPosX].equals(" ")){
					//remove spy from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the spy move to new position
					strArray[intNewPosY][intNewPosX]="P1Spy";
				//if another spy is there, both die
				}else if(rankConversion(strArray, intNewPosX, intNewPosY)==2){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]=null;
				//if there's anything else is there, kill it
				}else if(16 > rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
					strArray[intNewPosY][intNewPosX]=null;
				//if there's anything of lower rank, kill it
				}
			}
		}
		return strArray;
	}
	public static String[][] otherPieceMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strArray[intOGPosY][intOGPosX]==null){
		}else if(!strArray[intOGPosY][intOGPosX].equals("P1Flag") && !strArray[intOGPosY][intOGPosX].equals("P2Flag") && !strArray[intOGPosY][intOGPosX].equals("P1Private") && !strArray[intOGPosY][intOGPosX].equals("P2Private") && !strArray[intOGPosY][intOGPosX].equals("P1Spy") && !strArray[intOGPosY][intOGPosX].equals("P2Spy")){
			//If it's moves in any of the cardinal directions, move the pices
			if((intOGPosY+1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY-1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX+1==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX-1==intNewPosX)){
				//if there's nothing in the way, let the piece go to new position
				if(strArray[intNewPosY][intNewPosX]==null || strArray[intNewPosY][intNewPosX].equals(" ")){
					strArray[intNewPosY][intNewPosX]=strArray[intOGPosY][intOGPosX];
					strArray[intOGPosY][intOGPosX]=null;
				//if there's a spy there, kill the piece
				}else if(rankConversion(strArray, intNewPosX, intNewPosY)==2){
					//remove piece from spot
					strArray[intOGPosY][intOGPosX]=null;
				//if there's a piece higher than you, it dies
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) < rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
				//if there's the same rank, both die
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) == rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intOGPosY][intOGPosX]=null;
					strArray[intNewPosY][intNewPosX]=null;
				//if there's anything of lower rank, kill it
				}else if(rankConversion(strArray, intOGPosX, intOGPosY) > rankConversion(strArray, intNewPosX, intNewPosY)){
					strArray[intNewPosY][intNewPosX]=strArray[intOGPosY][intOGPosX];
					strArray[intOGPosY][intOGPosX]=null;
				}
			}
		}
		return strArray;
	}
}

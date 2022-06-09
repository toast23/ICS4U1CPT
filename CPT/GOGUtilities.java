import java.io.*;
public interface GOGUtilities{
	/** The makeEmptyBoardArray method returns us a 9 rows by 8 columns array that is not null. It is " " */
	public static String[][] makeEmptyBoardArray(){
		String[][] strGOGArray = new String[8][9];
		for(int intRow=0; intRow<8;intRow++){
			for(int intClm=0; intClm<9;intClm++){
				strGOGArray[intRow][intClm]=" ";
			}
		}
		return strGOGArray;
	}
	/** The loadRankArray method returns a 1 dimensional array filled with the names of images that we can use in other panels when initializing images */
	public static String[] loadRankArray(String strCSVFile){
		String strArray[];
		String strLine;
		int intCnt;
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
		strArray = new String[intRow];
		//Load it
		try{
			csv = new BufferedReader(new FileReader(strCSVFile));
		}catch(FileNotFoundException e){
			System.out.println("No such csv file exists!");
		}
		
		for(intCnt = 0; intCnt < intRow; intCnt++){
			try{
				strArray[intCnt] = csv.readLine();
			}catch(IOException e){
				System.out.println("Invalid Input");
				strArray[intCnt] = " ";
			}
		}
		return strArray;
	}
	/** The rankConversion method returns us the value or tier of the piece relative to the rank of other pieces on the board */
	public static int rankConversion(String strPiece){
		int intRank=0;
		if(strPiece.equals("P1Flag") || strPiece.equals("P2Flag")){
			intRank = 0;
		}else if(strPiece.equals("P1Private") || strPiece.equals("P2Private")){
			intRank = 1;
		}else if(strPiece.equals("P1Spy") || strPiece.equals("P2Spy")){
			intRank = 2;
		}else if(strPiece.equals("P1Sergeant") || strPiece.equals("P2Sergeant")){
			intRank = 3;
		}else if(strPiece.equals("P12nd Lieutenant") || strPiece.equals("P22nd Lieutenant")){
			intRank = 4;
		}else if(strPiece.equals("P11st Lieutenant") || strPiece.equals("P21st Lieutenant")){
			intRank = 5;
		}else if(strPiece.equals("P1Captain") || strPiece.equals("P2Captain")){
			intRank = 6;
		}else if(strPiece.equals("P1Major") || strPiece.equals("P2Major")){
			intRank = 7;
		}else if(strPiece.equals("P1L.Colonel") || strPiece.equals("P2L.Colonel")){
			intRank = 8;
		}else if(strPiece.equals("P1Colonel") || strPiece.equals("P2Colonel")){
			intRank = 9;
		}else if(strPiece.equals("P11*General") || strPiece.equals("P21*General")){
			intRank = 10;
		}else if(strPiece.equals("P12*General") || strPiece.equals("P22*General")){
			intRank = 11;
		}else if(strPiece.equals("P13*General") || strPiece.equals("P23*General")){
			intRank = 12;
		}else if(strPiece.equals("P14*General") || strPiece.equals("P24*General")){
			intRank = 13;
		}else if(strPiece.equals("P15*General") || strPiece.equals("P25*General")){
			intRank = 14;
		}
		return intRank;
	}
	/** The flagMovement method returns us an updated array when the moving piece is a flag*/
	public static String[][] flagMovement(String strArray[][],       String strPlayerTurn, int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY, String strActivePiece, String strNextPositionPiece){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strActivePiece.equals(" ")){
		}else if(strActivePiece.equals(strPlayerTurn+"Flag")){
			//If it's moves in any of the cardinal directions, move the pices
			//if there's nothing in the way, let flag go to new position
			if(strArray[intNewPosY][intNewPosX].equals(" ")){
				//remove flag from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the flag move to new position
				strArray[intNewPosY][intNewPosX]=strActivePiece;
			//if the enemy flag(value of 0) is in the way, kill it
			}else if(rankConversion(strActivePiece) == rankConversion(strNextPositionPiece)){
				//remove flag from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the flag move to new position
				strArray[intNewPosY][intNewPosX]=strActivePiece;
			//if there's ANYTHING ELSE there, kill the flag
			}else if(rankConversion(strActivePiece)<rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
			}
		}
		return strArray;
	}
	/** The privateMovement method returns us an updated array when the moving piece is a private*/
	public static String[][] privateMovement(String strArray[][],    String strPlayerTurn, int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY, String strActivePiece, String strNextPositionPiece){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strActivePiece.equals(" ")){
		}else if(strActivePiece.equals(strPlayerTurn+"Private")){
			//If it's moves in any of the cardinal directions, move the pices
			//if there's nothing in the way, let private go to new position
			if(strArray[intNewPosY][intNewPosX].equals(" ")){
				//remove pawn from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the pawn move to new position
				strArray[intNewPosY][intNewPosX]=strActivePiece;
			//if the flag is there, kill it
			}else if(rankConversion(strNextPositionPiece)==0){
				//remove pawn from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the pawn move to new position
				strArray[intNewPosY][intNewPosX]=strActivePiece;
			//if there's a private there, kill both
			}else if(rankConversion(strActivePiece) == rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
				strArray[intNewPosY][intNewPosX]=" ";
			//if there's a spy(value of 2) there, kill the spy!
			}else if(rankConversion(strNextPositionPiece) == 2){
				//remove pawn from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the pawn move to new position
				strArray[intNewPosY][intNewPosX]=strActivePiece;
			//if there's anything of higher rank, kill the private
			}else if(rankConversion(strArray[intOGPosY][intOGPosX]) < rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
			}
			System.out.println(strArray[intNewPosY][intNewPosX]);
		}
		return strArray;
	}
	/** The spyMovement method returns us an updated array when the moving piece is a spy */
	public static String[][] spyMovement(String strArray[][],        String strPlayerTurn, int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY, String strActivePiece, String strNextPositionPiece){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strActivePiece.equals(" ")){
		}else if(strActivePiece.equals(strPlayerTurn+"Spy")){
			//If it's moves in any of the cardinal directions, move the pices
			//if there's nothing in the way, let spy go to new position
			if(strArray[intNewPosY][intNewPosX].equals(" ")){
				//remove spy from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the spy move to new position
				strArray[intNewPosY][intNewPosX]=strPlayerTurn+"Spy";
			//if another spy is there, both die
			}else if(rankConversion(strNextPositionPiece)==2){
				//remove pawn from previous spot
				strArray[intOGPosY][intOGPosX]=" ";
				//make the pawn move to new position
				strArray[intNewPosY][intNewPosX]=" ";
			//if there's anything else is there, kill it
			}else if(16 > rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
				strArray[intNewPosY][intNewPosX]=strPlayerTurn+"Spy";
			}
			System.out.println("spy");
		}
		return strArray;
	}
	/** the otherPieceMovement method returns us an updated array when the moving piece is not a flag, private, or spy */
	public static String[][] otherPieceMovement(String strArray[][], String strPlayerTurn, int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY, String strActivePiece, String strNextPositionPiece){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strActivePiece.equals(" ")){
		}else if(!strActivePiece.equals(strPlayerTurn+"Flag") && !strActivePiece.equals(strPlayerTurn+"Private") && !strActivePiece.equals(strPlayerTurn+"Spy")){
			//If it's moves in any of the cardinal directions, move the pices
			//if there's nothing in the way, let the piece go to new position
			if(strArray[intNewPosY][intNewPosX].equals(" ")){
				strArray[intNewPosY][intNewPosX]=strActivePiece;
				strArray[intOGPosY][intOGPosX]=" ";
				System.out.println(strArray[intNewPosY][intNewPosX]);
			//if there's a spy there, kill the piece
			}else if(rankConversion(strNextPositionPiece)==2){
				//remove piece from spot
				strArray[intOGPosY][intOGPosX]=" ";
			//if there's a piece higher than you, it dies
			}else if(rankConversion(strActivePiece) < rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
			//if there's the same rank, both die
			}else if(rankConversion(strActivePiece) == rankConversion(strNextPositionPiece)){
				strArray[intOGPosY][intOGPosX]=" ";
				strArray[intNewPosY][intNewPosX]=" ";
			//if there's anything of lower rank, kill it
			}else if(rankConversion(strActivePiece) > rankConversion(strNextPositionPiece)){
				strArray[intNewPosY][intNewPosX]=strActivePiece;
				strArray[intOGPosY][intOGPosX]=" ";
			}
			System.out.println("Moving other pieces");
		}
		return strArray;
	}
	/** The checkWinCondition returns us the result as to whether a winner has been decided based on the pieces and the rows they are in */
	public static String checkWinCondition(String strArray[][], String strPlayerTurn, String strActivePiece, String strNextPositionPiece){
		String strVictor=" ";
		if(strActivePiece.equals(strPlayerTurn+"Flag")){
			//If P1 Flag is about to move on P2 Flag, P1 wins
			if(strPlayerTurn.equals("P1") && strNextPositionPiece.equals("P2Flag")){
				strVictor="P1";
				System.out.println("Player 1 Wins");
				
			//If P2 Flag is about to move on P1 Flag, P2 wins
			}else if(strPlayerTurn.equals("P2") && strNextPositionPiece.equals("P1Flag")){
				strVictor="P2";
				System.out.println("Player 2 Wins");
			}
		}
		for (int intCol = 0; intCol < strArray[0].length; intCol++) {
			if (strPlayerTurn.equals("P1")) {
				if (strArray[0][intCol].equals("P1Flag")) {
					strVictor = "P1";
				}
			}
			else if (strPlayerTurn.equals("P2")) {
				if (strArray[0][intCol].equals("P2Flag")) {
					strVictor = "P2";
				}
			}
		}
		return strVictor;
	}
}

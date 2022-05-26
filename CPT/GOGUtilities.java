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
		strArray = new String[intRow][8];
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
			for(intCnt2 = 0; intCnt2 < 8; intCnt2++){
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
	public static String[][] pawnMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//First condition states that if the thing on the tile is pawn
		//Second condition is that if you tell the pawn to move up by 1 row
		//Third condition is that if you tell the pawn it’s in the same column since it can’t move diagonally for now
		if(strArray[intOGPosY][intOGPosX]==null){
			//in case it's null, skip
		}else if(strArray[intOGPosY][intOGPosX].equals("p") && intOGPosY+1==intNewPosY && intOGPosX==intNewPosX){
		//remove pawn from previous spot
		strArray[intOGPosY][intOGPosX]=null;

		//make the pawn move to new position
		strArray[intNewPosY][intNewPosX]="p";
		}
		return strArray;
	}
	public static String[][] rookMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//for the rook, we want to make sure nothing is blocking its path
		//We will make a variable to check that nothing is obstructing its path
		boolean blnClearPath=true;
		int intCnt;
		int intCnt2;
		//First condition is if it’s a rook
		//Second condition is if you tell it to move in the same row or column, that works
		//Third condition states it can’t be in both the same row and column as before
		if(strArray[intOGPosY][intOGPosX]==null){
			//in case it's null, skip
		}else if(strArray[intOGPosY][intOGPosX].equals("r") && (intOGPosY==intNewPosY || intOGPosX==intNewPosX)){
			//from the original spot to the new spot, we can check all the squares in its way
			//make a a check that checks if all tiles in the path are clear
			//If all rows it crosses are clear, let blnClearPath stay true
			//If all columns it crosses are clear, let blnClearPath stay true
			//Use for loops to check distinct rows and columns
			for(intCnt = intNewPosY-intOGPosY ; intCnt >= 0 ; intCnt--){
				 //intCnt = 2-0 ; 2 >= 0, 2-1 each time
				if(strArray[intNewPosY-intCnt][intNewPosX]==null){
					
				}else if(strArray[intNewPosY-intCnt][intNewPosX].equals("p")){
					blnClearPath=false;
					System.out.println("woohoo");
				}
			}
			if(blnClearPath==true){
				//remove pawn from previous spot
				strArray[intOGPosY][intOGPosX]=null;
				
				//make the pawn move to new position
				strArray[intNewPosY][intNewPosX]="r";
			}
		}
		return strArray;
	}
	public static String[][] knightMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//First condition is if it’s a knight
		//All other conditions are possible places we allow it to move
		if(strArray[intOGPosY][intOGPosX]==null){
			//in case it's null, skip
		}else if(strArray[intOGPosY][intOGPosX]== "k" && ((intNewPosY==intOGPosY+2 && intNewPosX==intOGPosX-1) || (intNewPosY==intOGPosY+2 && intNewPosX==intOGPosX+1) || (intNewPosY==intOGPosY-2 && intNewPosX==intOGPosX-1) || (intNewPosY==intOGPosY-2 && intNewPosX==intOGPosX+1) || (intNewPosY==intOGPosY+1 && intNewPosX==intOGPosX-2) || (intNewPosY==intOGPosY+1 && intNewPosX==intOGPosX+2) ||  (intNewPosY==intOGPosY-1 && intNewPosX==intOGPosX-2) || (intNewPosY==intOGPosY-1 && intNewPosX==intOGPosX+2))){
			//remove pawn from previous spot
		strArray[intOGPosY][intOGPosX]=null;
		//make the knight move to new position
		strArray[intNewPosY][intNewPosX]="k";
		}
		return strArray;
	}
	public static String[][] bishopMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		return strArray;
	}
	public static String[][] KingMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		return strArray;
	}
	public static String[][] QueenMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		return strArray;
	}
}

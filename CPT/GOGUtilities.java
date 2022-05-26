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
	public static String[][] privateMovement(String strArray[][], int intOGPosX, int intOGPosY, int intNewPosX, int intNewPosY){
		//If it's null, it crashes so the first if statement checks if you say null
		if(strArray[intOGPosY][intOGPosX]==null){
		}else if(strArray[intOGPosY][intOGPosX].equals("P1p")){
			//If it's moves in any of the cardinal directions, move the pices
			if((intOGPosY+1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY-1==intNewPosY && intOGPosX==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX+1==intNewPosX) || (intOGPosY==intNewPosY && intOGPosX-1==intNewPosX)){
				//if there's nothing in the way, let private go to new position
				if(strArray[intNewPosY][intNewPosX]==null || strArray[intNewPosY][intNewPosX].equals(" ")){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]="P1p";
				//if there's a private there, kill both
				}else if(strArray[intNewPosY][intNewPosX].equals("P2p")){
					strArray[intOGPosY][intOGPosX]=null;
					strArray[intNewPosY][intNewPosX]=null;
				//if the flag is there, kill the flag
				}else if(strArray[intNewPosY][intNewPosX].equals("P2f")){
					//remove pawn from previous spot
					strArray[intOGPosY][intOGPosX]=null;
					//make the pawn move to new position
					strArray[intNewPosY][intNewPosX]="P1p";
				}
				System.out.println(strArray[intOGPosY][intOGPosX]+""+intOGPosY+""+intOGPosX);
				System.out.println(strArray[intNewPosY][intNewPosX]+""+intNewPosY+""+intNewPosX);
			}
		}
		return strArray;
	}
}

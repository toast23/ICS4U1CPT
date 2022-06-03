public class GOGModel{
	//Properties
	public String strArray[][];
	public String strRankArray[][];
	public int intOGClm;
	public int intOGRow;
	public int intNewClm;
	public int intNewRow;
	public int intP1TimeLeft=600;
	public int intP2TimeLeft=600;
	public String strPlayerTurn="P1";
	public String strVictor = null;
	public String strActivePiece;
	public String strNextPositionPiece;
	//Methods
	public void updateTime(){
		if(strPlayerTurn.equals("P1")){
			intP1TimeLeft--;
		}else if(strPlayerTurn.equals("P2")){
			intP2TimeLeft--;
		}
		
		if(intP1TimeLeft<=0){
			strVictor = "P2";
		}else if(intP2TimeLeft<=0){
			strVictor = "P1";
		}
	}
	public void checkPieceMovement(){
		strNextPositionPiece=strArray[intNewRow][intNewClm];
		System.out.println("["+intOGRow+"]"+"["+intOGClm+"]"+"["+intNewRow+"]"+"["+intNewClm+"]");
			//update array file
		if((intOGRow+1==intNewRow && intOGClm==intNewClm) || (intOGRow-1==intNewRow && intOGClm==intNewClm) || (intOGRow==intNewRow && intOGClm+1==intNewClm) || (intOGRow==intNewRow && intOGClm-1==intNewClm)){
			if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Flag")){
				System.out.println("Flag is active");
				strArray = GOGUtilities.flagMovement(      strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
			}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Private")){
				System.out.println("Private is active");
				strArray = GOGUtilities.privateMovement(   strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
			}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Spy")){
				System.out.println("Spy is active");
				strArray = GOGUtilities.spyMovement(       strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
			}else{
				System.out.println("Other is active");
				strArray = GOGUtilities.otherPieceMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
			}
		}
	}
	//Constructor
	public GOGModel(){
		this.strArray = GOGUtilities.loadPieceArray("Board.csv");
		this.strRankArray = GOGUtilities.loadDataArray("RankingData.csv");
	}
}

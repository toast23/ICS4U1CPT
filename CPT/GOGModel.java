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
	public boolean blnOnSameTeam=false;
	public boolean blnSendArrayData=false;
	public boolean blnReceiveArrayData=false;
	public boolean blnGetName=false;
	public String strPlayer1Name;
	public String strPlayer2Name;
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
		//update array file
		//if it is not null, then we check length
		if(strNextPositionPiece!=null && !strNextPositionPiece.equals(" ")){
			//If both are the same team, don't let it move
			if( (strNextPositionPiece.substring(0,2)).equals(strPlayerTurn.substring(0,2)) ){
				blnOnSameTeam=true;
				System.out.println("On same team");
			}else{
				blnOnSameTeam=false;
				System.out.println("Not on same team");
			}
		//if it null, then not on same team
		}else{
			blnOnSameTeam=false;
			System.out.println("Nothing there");
		}
		System.out.println(blnOnSameTeam);
		
		//if they're not on the same team, then compare and move
		if(blnOnSameTeam == false){
			if((intOGRow+1==intNewRow && intOGClm==intNewClm) || (intOGRow-1==intNewRow && intOGClm==intNewClm) || (intOGRow==intNewRow && intOGClm+1==intNewClm) || (intOGRow==intNewRow && intOGClm-1==intNewClm)){
				if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Flag")){
					System.out.println("Flag is active");
					strArray = GOGUtilities.flagMovement(      strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Private")){
					System.out.println("Private is active");
					strArray = GOGUtilities.privateMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Spy")){
					System.out.println("Spy is active");
					strArray = GOGUtilities.spyMovement(       strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}else{
					System.out.println("Other is active");
					strArray = GOGUtilities.otherPieceMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}
			}
		}
	}
	//Constructor
	public GOGModel(){
		this.strArray = GOGUtilities.loadPieceArray("Board.csv");
		this.strRankArray = GOGUtilities.loadDataArray("RankingData.csv");
	}
}

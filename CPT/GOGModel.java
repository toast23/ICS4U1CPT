public class GOGModel{
	//Properties
	public String strArray[][] = GOGUtilities.makeEmptyBoardArray();
	public int intOGClm;
	public int intOGRow;
	public int intNewClm;
	public int intNewRow;
	public int intP1TimeLeft=600;
	public int intP2TimeLeft=600;
	public String strPlayerTurn="P1";
	public boolean blnSwitchTurn = false;
	public String strVictor = " ";
	public String strActivePiece;
	public String strNextPositionPiece;
	public boolean blnOnSameTeam=false;
	public boolean blnSendArrayData=false;
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
		//if it is not empty, then...
		if(!strNextPositionPiece.equals(" ")){
			//If both are the same team, don't let it move
			if(strNextPositionPiece.substring(0,2).equals(strPlayerTurn)){
				blnOnSameTeam=true;
				strArray[intOGRow][intOGClm]=strActivePiece;
			}else{
				blnOnSameTeam=false;
			}
		//if it is empty, then not on same team
		}else{
			blnOnSameTeam=false;
		}
		
		//if they're not on the same team, then compare and move
		if(blnOnSameTeam == false){
			if((intOGRow+1==intNewRow && intOGClm==intNewClm) || (intOGRow-1==intNewRow && intOGClm==intNewClm) || (intOGRow==intNewRow && intOGClm+1==intNewClm) || (intOGRow==intNewRow && intOGClm-1==intNewClm)){
				if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Flag")){
					strArray = GOGUtilities.flagMovement(      strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
					strVictor=GOGUtilities.checkWinCondition(strArray, strPlayerTurn, strActivePiece,strNextPositionPiece);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Private")){
					strArray = GOGUtilities.privateMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Spy")){
					strArray = GOGUtilities.spyMovement(       strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}else{
					strArray = GOGUtilities.otherPieceMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece);
				}
				blnSwitchTurn=true;
				//After their turn, switch player turn
				if(this.strPlayerTurn.equals("P1")){
					this.strPlayerTurn="P2";
				}else if(this.strPlayerTurn.equals("P2")){
					this.strPlayerTurn="P1";
				}
			}else{
				strArray[intOGRow][intOGClm]=strActivePiece;
			}
		}
	}
	//Constructor
	public GOGModel(){
		for(int intRow=0; intRow<8;intRow++){
			for(int intClm=0; intClm<9;intClm++){
				strArray[intRow][intClm]=" ";
			}
		}
	}
}

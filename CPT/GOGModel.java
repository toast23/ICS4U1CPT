public class GOGModel{
	//Properties
	public String strArray[][] = GOGUtilities.makeEmptyBoardArray();
	public String strInfoArray[][] = GOGUtilities.loadArray("ImageNames.csv");
	public int intOGClm;
	public int intOGRow;
	public int intNewClm;
	public int intNewRow;
	public int intP1TimeLeft=7200;
	public int intP2TimeLeft=7200;
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
	String strWarning=" ";
	//Methods
	/** The updateTime method allows us to update the player time */
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
	/** The checkPieceMovement method allows us to check which piece moves during a match. It will update our array accordingly based on where it moves */
	public void checkPieceMovement(){
		//update array file
		/**if the array file is not empty, run this*/
		if(!strNextPositionPiece.equals(" ")){
			//If both are the same team, don't let it move
			if(strNextPositionPiece.substring(0,2).equals(strPlayerTurn)){
				blnOnSameTeam=true;
				strArray[intOGRow][intOGClm]=strActivePiece;
			}else{
				blnOnSameTeam=false;
			}
		/**if it is empty, then the pieces are not on same team*/
		}else{
			blnOnSameTeam=false;
		}
		
		/**if they're not on the same team, then compare and move*/
		if(blnOnSameTeam == false){
			if((intOGRow+1==intNewRow && intOGClm==intNewClm) || (intOGRow-1==intNewRow && intOGClm==intNewClm) || (intOGRow==intNewRow && intOGClm+1==intNewClm) || (intOGRow==intNewRow && intOGClm-1==intNewClm)){
				if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Flag")){
					strArray = GOGUtilities.flagMovement(      strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece,strInfoArray);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Private")){
					strArray = GOGUtilities.privateMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece,strInfoArray);
				}else if(strActivePiece.equalsIgnoreCase(strPlayerTurn+"Spy")){
					strArray = GOGUtilities.spyMovement(       strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece,strInfoArray);
				}else{
					strArray = GOGUtilities.otherPieceMovement(strArray,strPlayerTurn,intOGClm,intOGRow,intNewClm,intNewRow,strActivePiece,strNextPositionPiece,strInfoArray);
				}
				strVictor=GOGUtilities.checkWinCondition(strArray, strPlayerTurn, strActivePiece,strNextPositionPiece);
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
	/** The checkWarnings method tells the program whether the player should be allowed to move the piece or be warned */
	public void checkWarnings(){
		//if they move to an impossible spot
		
		
		if(!(intOGRow==intNewRow && intOGClm+1==intNewClm)
			&& !(intOGRow==intNewRow && intOGClm-1==intNewClm)
			&& !(intOGRow+1==intNewRow && intOGClm==intNewClm)
			&& !(intOGRow-1==intNewRow && intOGClm==intNewClm)
		){
			strWarning = "MovementWarning";
			
		//If they move nowhere
		}else if(intOGRow==intNewRow && intOGClm==intNewClm){
			strWarning = "NoMovementWarning";
		}
		
		if(strNextPositionPiece.equals(" ")){
			
		}else if(strNextPositionPiece.substring(0,2).equals(strPlayerTurn)){
			strWarning = "SameTeamWarning";
		} 
	}
	//Constructor
	/** the GOGModel constructor method allows us to create this whole class that we can use to order the array around */
	public GOGModel(){}
}

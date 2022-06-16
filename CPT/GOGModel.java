/** The GOGModel class creates a class to store board data and player data */
public class GOGModel{
	//Properties
	/** The strArray array is used to track the pieces on the board */
	public String strArray[][] = GOGUtilities.makeEmptyBoardArray();
	/** The strInfoArray is used to load info from name of pieces, ranks, and image names */
	public String strInfoArray[][] = GOGUtilities.loadArray("ImageNames.csv");
	/** the ingOGClm variable is used to keep track of the original column the piece that is picked up was in */
	public int intOGClm;
	/** the intOGRow variable is used to keep track of the original row the piece that is picked up was in */
	public int intOGRow;
	/** the ingNewClm variable is used to keep track of the new column the piece that is dropped in */
	public int intNewClm;
	/** the ingNewRow variable is used to keep track of the new row the piece that is dropped in */
	public int intNewRow;
	/** The intP1TimeLeft variable is used to keep track of how much time player 1 has */
	public int intP1TimeLeft=7200;
	/** The intP2TimeLeft variable is used to keep track of how much time player 2 has */
	public int intP2TimeLeft=7200;
	/** the strPlayerTurn variable tells us whose turn it is */
	public String strPlayerTurn="P1";
	/** The blnSwitchTurn variable tells us whether we should switch or not */
	public boolean blnSwitchTurn = false;
	/** The strVictor variable tells us whether someone won or not */
	public String strVictor = " ";
	/** The strActivePiece variable tells us what piece is actively being dragged */
	public String strActivePiece;
	/** The strNextPositionPiece variable tells us what piece is in the coordinate that the active piece was dragged too */
	public String strNextPositionPiece;
	/** The blnOnSameTeam variable tells us whether the piece we are going to is on the same team as us */
	public boolean blnOnSameTeam=false;
	/** The blnSendArrayData variable tells us whether we should send array data to the other player once their turn ends */
	public boolean blnSendArrayData=false;
	/** The blnGetName variable tells us when we should get the names of the other player */
	public boolean blnGetName=false;
	/** The strPlayer1Name variable stores the name of player 1 */
	public String strPlayer1Name;
	/** The strPlayer2Name variable stores the name of player 2 */
	public String strPlayer2Name;
	/** The strWarning variable stores the type of warning and will help warn the player if they do anything illegal */
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

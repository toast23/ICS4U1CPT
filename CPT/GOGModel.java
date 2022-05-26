public class GOGModel{
	//Properties
	public String strArray[][];
	public int intOriginalPositionX;
	public int intOriginalPositionY;
	public int intNewPositionX;
	public int intNewPositionY;
	public int intP1TimeLeft=600;
	public int intP2TimeLeft=600;
	public String strPlayerTurn="P1";
	public String strVictor = null;
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
	public void getPositions(String strMessage){
		intOriginalPositionX = GOGUtilities.getOriginalPositionX(strMessage);
		intOriginalPositionY = GOGUtilities.getOriginalPositionY(strMessage);
		intNewPositionX = GOGUtilities.getNewPositionX(strMessage);
		intNewPositionY = GOGUtilities.getNewPositionY(strMessage);
	}
	
	public void checkPieceMovement(){
		//update array file
		strArray = GOGUtilities.pawnMovement(strArray,intOriginalPositionX,intOriginalPositionY,intNewPositionX,intNewPositionY);
	}
	//Constructor
	public GOGModel(){
		this.strArray = GOGUtilities.loadArray("Board.csv");
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class GOGServer extends GOGView implements ActionListener{
	//Properties
	public SuperSocketMaster ssm;
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==theTimer){
			theViewPanel.repaint();
			if(theGamePanel.blnMessageSending==true){
				theGamePanel.theTextArea.append(theGamePanel.theTextField.getText() + "\n");
				ssm.sendText(theGamePanel.theTextField.getText());
				theGamePanel.blnMessageSending=false;
			}else if(theGamePanel.blnSwitchTurn==true){
				theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
				ssm.sendText(theModel.strPlayerTurn);
				theGamePanel.blnSwitchTurn=false;
				theModel.blnSendArrayData=true;
			}else if(theModel.blnSendArrayData==true){
				ssm.sendText("Sending Data");
				theModel.blnSendArrayData=false;
				int intRow;
				int intClm;
				int intLength;
				for(intRow=0;intRow<8;intRow++){
					for(intClm=0;intClm<9;intClm++){
						ssm.sendText("Row:"+intRow);
						ssm.sendText("Clm:"+intClm);
						ssm.sendText("Index:"+theModel.strArray[intRow][intClm]);
						System.out.println("Index:"+theModel.strArray[intRow][intClm]);
					}
				}
				ssm.sendText("Done Sending Data");
			}
		}
		if(evt.getSource()==theGameClockTimer){
			if(panelToReturn.equals("game")){
				theModel.updateTime();
				theGamePanel.drawNewTime(theModel.strPlayerTurn, theModel.intP1TimeLeft, theModel.intP2TimeLeft);
			}
		}
		
		// Network
		if(evt.getSource() == ssm){
			String strText;
			if(theModel.blnReceiveArrayData==true){
				strText = ssm.readText();
				int intLength;
				if(strText.equals("Done Sending Data")){
					theModel.blnReceiveArrayData=false;
				}else if(strText.substring(0,4).equals("Row:")){
					theModel.intOGRow=Integer.parseInt(strText.substring(4,5));
				}else if(strText.substring(0,4).equals("Clm:")){
					theModel.intOGClm=Integer.parseInt(strText.substring(4,5));
				}else{
					intLength=strText.length();
					theModel.strArray[theModel.intOGRow][theModel.intOGClm]=strText.substring(6,intLength);
					theGamePanel.strGOGArray[theModel.intOGRow][theModel.intOGClm]=strText.substring(6,intLength);
					System.out.println(strText);
				}
			}else{
				//Recieve Info
				strText = ssm.readText();
				if(strText.equals("P1") || strText.equals("P2")){
					theModel.strPlayerTurn=strText;
					theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
				}else if(strText.equals("Sending Data")){
					theModel.blnReceiveArrayData=true;
				}else{
					theGamePanel.theTextArea.append(strText + "\n");
				}
			}
		}
		
		// Buttons
		if(evt.getSource()==theServerButton){
			setSSM("server");
			gameSetup();
			this.theGamePanel.strGOGArray=this.theModel.strArray;
			strPlayer="P1";
			
		}
		else if(evt.getSource()==theClientButton){
			setSSM("client");
			gameSetup();
			this.theGamePanel.strGOGArray=(theModel.strArray);
			strPlayer="P2";
		}
		
		//If they click the help button, switch the screen to the help panel
		if (evt.getSource() == theLobbyHelpButton || evt.getSource() == theGameHelpButton) {
			helpSetup();
		}
		
		//if they click the return button, return to lobby or game
		if (evt.getSource() == theHelpReturnButton || evt.getSource() == theRanksReturnButton) {
			if (panelToReturn.equals("lobby")) {
				lobbySetup();
			}
			else if (panelToReturn.equals("game")) {
				gameSetup();
			}
			else if (panelToReturn.equals("help")) {
				helpSetup();
			}	
		}
		
		//If they clickt the ranks button, show them ranks
		if (evt.getSource() == theHelpRanksButton) {	
				ranksSetup();
		}
	}
	
	public void setSSM(String strSSMType) {
		// Get the name of the player
		theLobbyPanel.strName = theLobbyPanel.theNameTextField.getText();
		
		// Get the port number
		try{
			theLobbyPanel.intPortNumber = Integer.parseInt(theLobbyPanel.thePortTextField.getText());
		}
		catch(NumberFormatException e){
			theLobbyPanel.thePortTextField.setText("Invalid Number");
		}
		if (strSSMType.equals("server")) {

			ssm = new SuperSocketMaster(theLobbyPanel.intPortNumber, this);
		}
		else if (strSSMType.equals("client")) {
			// Get ip address
			try{
				theLobbyPanel.strIPAddress = theLobbyPanel.theIPAddressTextField.getText();
			}
			catch(NumberFormatException e){
				theLobbyPanel.thePortTextField.setText("Invalid Number");
			}
			ssm = new SuperSocketMaster(theLobbyPanel.strIPAddress, theLobbyPanel.intPortNumber, this);
		}
		

		
		
		
		ssm.connect();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	public GOGServer() {
		theServerButton.addActionListener(this);
		theClientButton.addActionListener(this);
		theLobbyHelpButton.addActionListener(this);
		theGameHelpButton.addActionListener(this);
		theHelpReturnButton.addActionListener(this);	
		theRanksReturnButton.addActionListener(this);	
		theHelpRanksButton.addActionListener(this);
		
		theTimer.start();
		theGameClockTimer.start();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	//MainMethod
	public static void main(String[] args){
		new GOGServer();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

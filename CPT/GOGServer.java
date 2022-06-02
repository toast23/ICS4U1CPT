import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class GOGServer extends GOGView implements ActionListener{
	//Properties
	public GOGModel theModel = new GOGModel();
	public SuperSocketMaster ssm;
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	public void actionPerformed(ActionEvent evt){
		// Network
		if(evt.getSource() == ssm){
			//get text from player 2
			String strText = ssm.readText();
			//get positions with this method
			theModel.getPositions(strText);
			//move the pices
			theModel.checkPieceMovement();
			//show this on the scroll plane
			theGamePanel.theTextArea.append(strText + "\n");
		}
		
		// Buttons
		if (evt.getSource()==theServerButton){
			setSSM("server");
			gameSetup();
		}
		else if(evt.getSource()==theClientButton){
			setSSM("client");
			gameSetup();
		}
		if (evt.getSource() == theLobbyHelpButton || evt.getSource() == theGameHelpButton) {
			helpSetup();
		}
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

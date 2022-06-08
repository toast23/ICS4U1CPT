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
		//Ever 1/60th of a second, the timer goes off and we can repaint and do any checks
		if(evt.getSource()==theTimer){
			//Repaint based on whichever panel we're on
			theViewPanel.repaint();
			
			
			//If both are ready and it's player 2, give half array to other player
			if(thePrepPanel.blnPlayer1Ready==true && thePrepPanel.blnPlayer2Ready==true){				
				if(strPlayer.equals("P2")){
					int intReverseRow=0;
					for(int intRow=7;intRow>3;intRow--){
						for(int intClm=0;intClm<9;intClm++){
							//If it isn't blank, then send it over
							if(!thePrepPanel.strGOGArray[intRow][intClm].equals(" ")){
								ssm.sendText("CurrentlySendingClientHalfOfArray"+","+intReverseRow+","+intClm+","+"P2"+thePrepPanel.strGOGArray[intRow][intClm]);
							}
						}
						intReverseRow++;
					}
					ssm.sendText("DoneSendingClientHalfOfArray");
				}
				thePrepPanel.blnPlayer1Ready=false;
				thePrepPanel.blnPlayer2Ready=false;
			}else if(theModel.blnSwitchTurn==true){
				//Say it's time to switch turns in chatbox
				theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
				
				//Then send the updated player turn so it knows which turn it is
				ssm.sendText("SwitchTurn,"+theModel.strPlayerTurn);
				
				//After this, we no longer need to switch a turn since it's already switched so falsify
				theModel.blnSwitchTurn=false;
				
				//After we switch turns, we should also update the array. If we switch turns, then
				//We're also the ones sending data so blnSendArrayData becomes true.
				//We create a loop that will send data in each column
				int intRow;
				int intRowInverse=7;
				int intClm;
				for(intRow=0;intRow<8;intRow++){
					for(intClm=0;intClm<9;intClm++){
						//send them something they can split
						ssm.sendText("SendArray,"+intRow+","+intClm+","+theModel.strArray[intRowInverse][intClm]);
					}
					intRowInverse--;
				}
				
				//Now that we are done updating the other player's array, we can stop sending data
				theModel.blnSendArrayData=false;
			}else if(theGamePanel.blnMessageSending==true){
				//Put that message in our chatbox
				if(strPlayer.equals("P1")){
					theGamePanel.theTextArea.append("Chat"+","+theModel.strPlayer1Name + "," + theGamePanel.theTextField.getText() + "\n");
				}else if(strPlayer.equals("P2")){
					theGamePanel.theTextArea.append("Chat"+","+theModel.strPlayer2Name + "," + theGamePanel.theTextField.getText() + "\n");
				}
				
				//Then send the message inside the spot we entered
				ssm.sendText(theGamePanel.theTextField.getText());
				
				//We'll also blank the text field after they clicked enter
				theGamePanel.theTextField.setText(" ");
				
				//After we sent the message, it's not time to send a message anymore
				theGamePanel.blnMessageSending=false;
				
				
			//If it's time to switch turns, then...
			}
		}
		
		//Ever second, we...
		if(evt.getSource()==theGameClockTimer){
			if(panelToReturn.equals("game")){
				//Update the time based on who's turn it is
				theModel.updateTime();
				
				//Then we show that time in the game panel
				theGamePanel.drawNewTime(theModel.strPlayerTurn, theModel.intP1TimeLeft, theModel.intP2TimeLeft);
			}
		}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
		//This is used for receiving network messages
		if(evt.getSource() == ssm){
			//We create a local variable that will help us store the sent message
			String strText = ssm.readText();				
			String[] strIndex = strText.split(",");
			if(strIndex[0].equals("CurrentlySendingClientHalfOfArray")){
				theModel.intOGRow=Integer.parseInt(strIndex[1]);
				theModel.intOGClm=Integer.parseInt(strIndex[2]);
				theModel.strArray[theModel.intOGRow][theModel.intOGClm]=strIndex[3];
			}else if(strIndex[0].equals("DoneSendingClientHalfOfArray")){
				//load server half into array
				for(int intRow=5;intRow<8;intRow++){
					for(int intClm=0;intClm<9;intClm++){
						if(thePrepPanel.strGOGArray[intRow][intClm].equals(" ")){
							theModel.strArray[intRow][intClm]=" ";
						}else if(!thePrepPanel.strGOGArray[intRow][intClm].equals(" ")){
							theModel.strArray[intRow][intClm]="P1"+thePrepPanel.strGOGArray[intRow][intClm];
						}
					}
				}
				//load stuff into the game panel array
				theGamePanel.strGOGArray=theModel.strArray;
				
				
				int intReverseRow=7;
				//give my array to the other player
				for(int intRow=0;intRow<8;intRow++){
					for(int intClm=0;intClm<9;intClm++){
						ssm.sendText("SendingPreparedArray"+","+intRow+","+intClm+","+theModel.strArray[intReverseRow][intClm]);
					}
					intReverseRow--;
				}
				gameSetup();
				ssm.sendText("Done making model array");
			
			}else if(strIndex[0].equals("SendingPreparedArray")){
				theModel.intOGRow=Integer.parseInt(strIndex[1]);
				theModel.intOGClm=Integer.parseInt(strIndex[2]);
				theModel.strArray[theModel.intOGRow][theModel.intOGClm]=strIndex[3];
			}else if(strIndex[0].equals("Done making model array")){
				theGamePanel.strGOGArray=theModel.strArray;
				gameSetup();
			//If it is time to receive array data information, then...
			}else if(strIndex[0].equals("SendArray")){
				theModel.intOGRow=Integer.parseInt(strIndex[1]);
				theModel.intOGClm=Integer.parseInt(strIndex[2]);
				theModel.strArray[theModel.intOGRow][theModel.intOGClm]=strIndex[3];
			
			//if it's time to get a name, then...
			}else if(theModel.blnGetName==true){
				//If this is player 1, then...
				if(strPlayer.equals("P1")){
					//get player 2's name
					theModel.strPlayer2Name=strText;
					//Now it's the client's turn to catch player 1's name
					ssm.sendText("Get Name");
					ssm.sendText(theModel.strPlayer1Name);
					//Now that the server is done getting a name, it falsifies
					theModel.blnGetName=false;
					//Last, we set the label
					theGamePanel.thePlayer2Label.setText("Player 2: "+strText);
				
				//If this is player 2, then...
				}else if(strPlayer.equals("P2")){
					//load player 2's name in
					theModel.strPlayer1Name=strText;
					//Last, we set the label
					theGamePanel.thePlayer1Label.setText("Player 1: "+strText);
					//Now that the server is done getting a name, it falsifies
					theModel.blnGetName=false;
				}
			}else if(strIndex[0].equals("SwitchTurn")){
				//We update player turn here
				theModel.strPlayerTurn=strIndex[1];
				
				//We will also tell the user in the chatbox that it is their turn now
				theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
				
			//If it says Sending Data, then it is time to receive data
			
			//If none of this is the case, then we will put what is said in chatbox
			}else if(strIndex[0].equals("Get Name")){
				//We turn receivedata on
				theModel.blnGetName=true;
			//If none of this is the case, then we will put what is said in chatbox
			}else if(strIndex[0].equals("P1 Ready")){
				thePrepPanel.blnPlayer1Ready=true;
			}else if(strIndex[0].equals("P2 Ready")){
				thePrepPanel.blnPlayer2Ready=true;
			}else if(strIndex[0].equals("Chat")){
				theGamePanel.theTextArea.append(strIndex[1]+","+strIndex[2]);
			}
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
		// If user sends a message 
		if(evt.getSource()==theGamePanel.theTextField){
			theGamePanel.blnMessageSending=true;
		}
		
		//If the server button is clicked, then...
		if(evt.getSource()==theServerButton){
			//We will set up the supersocketmaster server
			setSSM("server");
			
			//We will also switch to game panel
			prepSetup();
			//gameSetup();
			
			//We will also make the game panel's array board the same as the model's
			this.theGamePanel.strGOGArray=this.theModel.strArray;
			
			//we also get the name of the player
			
			
			//Last, the server is always player 1
			strPlayer="P1";
			theGamePanel.strPiecesToPaint="P1";
		}
		
		//If the client button is clicked
		else if(evt.getSource()==theClientButton){
			//We will set up the supersocketmaster client
			setSSM("client");
			
			//We will also switch to game panel
			prepSetup();
			//gameSetup();
			
			//We will also make the game panel's array board the same as the model's
			this.theGamePanel.strGOGArray=(theModel.strArray);
			
			//Last, the client is always player 2
			strPlayer="P2";
			theGamePanel.strPiecesToPaint="P2";
		}
		
		//If they click the help button, switch the screen to the help panel
		if (evt.getSource() == theLobbyHelpButton || evt.getSource() == theGameHelpButton || evt.getSource() == thePrepHelpButton) {
			helpSetup();
		}
		
		//if they click the return button, return to lobby or game
		if (evt.getSource() == theHelpReturnButton || evt.getSource() == theRanksReturnButton) {
			if (panelToReturn.equals("lobby")) {
				lobbySetup();
			}
			else if (panelToReturn.equals("prep")) {
				prepSetup();
			}
			else if (panelToReturn.equals("game")) {
				gameSetup();
			}
			else if (panelToReturn.equals("help")) {
				helpSetup();
			}	
		}
		
		//If they click the ranks button, then...
		if (evt.getSource() == theHelpRanksButton){	
			//Set up the panel with the marks
			ranksSetup();
		}
		
		if (evt.getSource() == theReadyButton) {
			if(strPlayer.equals("P1")){
				thePrepPanel.blnPlayer1Ready=true;
			}else if(strPlayer.equals("P2")){
				thePrepPanel.blnPlayer2Ready=true;
			}
			ssm.sendText(strPlayer+" Ready");
		}
	}
	
	//This method sets up the server based on the SSMType
	public void setSSM(String strSSMType) {
		// Get the port number
		try{
			theLobbyPanel.intPortNumber = Integer.parseInt(theLobbyPanel.thePortTextField.getText());
		}
		catch(NumberFormatException e){
			theLobbyPanel.thePortTextField.setText("Invalid Number");
		}
		
		//If this is a server, then...
		if (strSSMType.equals("server")) {
			//set up the server using the port number
			ssm = new SuperSocketMaster(theLobbyPanel.intPortNumber, this);
			// Get the name of the player
			theModel.strPlayer1Name = theLobbyPanel.theNameTextField.getText();
			theGamePanel.thePlayer1Label.setText("Player 1: "+theModel.strPlayer1Name);
			//After we are done setting up ssm, we may connect
			ssm.connect();
			strPlayer="P1";
			
		//If this is a client, then...
		}else if (strSSMType.equals("client")) {
			// Get ip address
			try{
				theLobbyPanel.strIPAddress = theLobbyPanel.theIPAddressTextField.getText();
			}
			catch(NumberFormatException e){
				theLobbyPanel.thePortTextField.setText("Invalid Number");
			}
			//Set up the client using the port number and IP Address
			ssm = new SuperSocketMaster(theLobbyPanel.strIPAddress, theLobbyPanel.intPortNumber, this);
			
			//After we are done setting up ssm, we may connect
			ssm.connect();
			
			// Get the name of the player
			theModel.strPlayer2Name = theLobbyPanel.theNameTextField.getText();
			theGamePanel.thePlayer2Label.setText("Player 2: "+theModel.strPlayer2Name);
			ssm.sendText("Get Name");
			ssm.sendText(theModel.strPlayer2Name);
			strPlayer="P2";
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	public GOGServer() {
		//Here, we set up action listeners
		theServerButton.addActionListener(this);
		theClientButton.addActionListener(this);
		theLobbyHelpButton.addActionListener(this);
		theGameHelpButton.addActionListener(this);
		thePrepHelpButton.addActionListener(this);
		theHelpReturnButton.addActionListener(this);	
		theRanksReturnButton.addActionListener(this);	
		theHelpRanksButton.addActionListener(this);
		theReadyButton.addActionListener(this);
		theGamePanel.theTextField.addActionListener(this);
		
		//We start the clock here
		theTimer.start();
		theGameClockTimer.start();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	//Main Method
	public static void main(String[] args){
		new GOGServer();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

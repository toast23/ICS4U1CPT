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
			
			
			//If it's time to send a message, then.....
			if(theGamePanel.blnMessageSending==true){
				//Put that message in our chatbox
				if(strPlayer.equals("P1")){
					theGamePanel.theTextArea.append(theModel.strPlayer1Name + "," + theGamePanel.theTextField.getText() + "\n");
				}else if(strPlayer.equals("P2")){
					theGamePanel.theTextArea.append(theModel.strPlayer2Name + "," + theGamePanel.theTextField.getText() + "\n");
				}
				
				//Then send the message inside the spot we entered
				ssm.sendText(theGamePanel.theTextField.getText());
				
				//We'll also blank the text field after they clicked enter
				theGamePanel.theTextField.setText("");
				
				//After we sent the message, it's not time to send a message anymore
				theGamePanel.blnMessageSending=false;
				
				
			//If it's time to switch turns, then...
			}else if(theGamePanel.blnSwitchTurn==true){
				//Say it's time to switch turns in chatbox
				theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
				
				//Then send the updated player turn so it knows which turn it is
				ssm.sendText(theModel.strPlayerTurn);
				
				//After this, we no longer need to switch a turn since it's already switched so falsify
				theGamePanel.blnSwitchTurn=false;
				
				//After we switch turns, we should also update the array. If we switch turns, then
				//We're also the ones sending data so blnSendArrayData becomes true.
				theModel.blnSendArrayData=true;
				
				
			//If it's time to update the other player's array, then...
			}else if(theModel.blnSendArrayData==true){
				//First, we will send this message that activates blnReceiveArrayData to true
				//Which tells the receiver to receive array data.
				ssm.sendText("Sending Data");
				
				//We create a loop that will send data in each column
				int intRow;
				int intRowInverse=7;
				int intClm;
				for(intRow=0;intRow<8;intRow++){
					for(intClm=0;intClm<9;intClm++){
						//For them to know which index it is, we send the row and column to
						//pinpoint the location in the array
						ssm.sendText("Row:"+intRow);
						ssm.sendText("Clm:"+intClm);
						//then we send them information
						ssm.sendText("Index:"+theModel.strArray[intRowInverse][intClm]);
						
						//This isn't needed but it can be used to check
						System.out.println("Index:"+theModel.strArray[intRow][intClm]);
					}
					intRowInverse--;
				}
				
				//Now that we are done updating the other player's array, we can stop sending data
				theModel.blnSendArrayData=false;
				
				//We send this message which activates a command that turns the other player's
				//blnReceiveArrayData variable off
				ssm.sendText("Done Sending Data");
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
			String strText;
			
			//If it is time to receive array data information, then...
			if(theModel.blnReceiveArrayData==true){
				//We first store the send text inside strText
				strText = ssm.readText();
				
				//If the sent message tell us that it is done sending info, then ...
				if(strText.equals("Done Sending Data")){
					//We now know that is is time to stop receiving information
					theModel.blnReceiveArrayData=false;
					
				//If the first 4 characters day row:, then...
				}else if(strText.substring(0,4).equals("Row:")){
					//We can use intOGRow to store the row
					theModel.intOGRow=Integer.parseInt(strText.substring(4,5));
					
				
				//If the first 4 characters day clm:, then...
				}else if(strText.substring(0,4).equals("Clm:")){
					//We can use intOGClm to store the clm
					theModel.intOGClm=Integer.parseInt(strText.substring(4,5));
				
				//If neither of those are the case, then it means this is a piece
				}else{
					//We may read the length
					int intLength=strText.length();
					
					//We store the index in the strArray
					theModel.strArray[theModel.intOGRow][theModel.intOGClm]=strText.substring(6,intLength);
					theGamePanel.strGOGArray[theModel.intOGRow][theModel.intOGClm]=strText.substring(6,intLength);
					
					//This is not really important but will be kept to check in terminal
					System.out.println(strText);
				}
			
			//if it's time to get a name, then...
			}else if(theModel.blnGetName==true){
				//Load the name into a variable
				strText = ssm.readText();
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
			}else{
				//Read the text that was sent
				strText = ssm.readText();
				
				//If it says P1 or P2, that means it is time to update the player turn
				if(strText.equals("P1") || strText.equals("P2")){
					//We update player turn here
					theModel.strPlayerTurn=strText;
					
					//We will also tell the user in the chatbox that it is their turn now
					theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn now" + "\n");
					
				//If it says Sending Data, then it is time to receive data
				}else if(strText.equals("Sending Data")){
					//We turn receivedata on
					theModel.blnReceiveArrayData=true;
				//If none of this is the case, then we will put what is said in chatbox
				}else if(strText.equals("Get Name")){
					//We turn receivedata on
					theModel.blnGetName=true;
				//If none of this is the case, then we will put what is said in chatbox
				}else{
					if(strPlayer.equals("P1")){
						theGamePanel.theTextArea.append(theModel.strPlayer2Name + "," + strText + "\n");
					} else if(strPlayer.equals("P2")){
						theGamePanel.theTextArea.append(theModel.strPlayer1Name + "," + strText + "\n");
					}
				}
			}
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
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
		
		//If they click the ranks button, then...
		if (evt.getSource() == theHelpRanksButton){	
			//Set up the panel with the marks
			ranksSetup();
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
		theHelpReturnButton.addActionListener(this);	
		theRanksReturnButton.addActionListener(this);	
		theHelpRanksButton.addActionListener(this);
		
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

//We import necessary libraries for functionality
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class GOGView implements ActionListener {
	//Properties
	public JFrame theFrame = new JFrame("Game of the Generals");
	public CardLayout card = new CardLayout(); 
	public JPanel theViewPanel = new JPanel(); 
	public lobbyPanel theLobbyPanel = new lobbyPanel();
	public gamePanel theGamePanel = new gamePanel(); 
	public helpPanel theHelpPanel = new helpPanel();
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
	public GOGModel theModel = new GOGModel();
	public JButton theHelpButton = new JButton("Help!");
	public JButton theReturnButton = new JButton("Return");
	public JButton theServerButton = new JButton("Server");
	public JButton theClientButton = new JButton("Client");	
	public String panelToReturn = "lobby";
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods 
	public void actionPerformed(ActionEvent evt){
		//Ever 1/60th of a second, repaint
		if(evt.getSource() == theTimer){
			//we update the panel's array with the model array data
			theGamePanel.strGOGArray=theModel.strArray;
			theViewPanel.repaint();
		}
		if(evt.getSource()==theGameClockTimer){
			theModel.updateTime();
			//This line has no purpose. I just wanted to save this so we know how to append the thing later
			theGamePanel.drawNewTime(theModel.strPlayerTurn, theModel.intP1TimeLeft, theModel.intP2TimeLeft);
		}
		
		// Buttons
		if(evt.getSource()==theServerButton || evt.getSource()==theClientButton){
			//Get the name of the player
			theLobbyPanel.strName = theLobbyPanel.theNameTextField.getText();
			
			//Get the port number
			try{
				theLobbyPanel.intPortNumber = Integer.parseInt(theLobbyPanel.thePortTextField.getText());
			}
			catch(NumberFormatException e){
				theLobbyPanel.thePortTextField.setText("Invalid Number");
			}
			
			//Get the IP Address in string form
			theLobbyPanel.strIPAddress = theLobbyPanel.theIPAddressTextField.getText();
			
			gameSetup();
		}
		System.out.println(theLobbyPanel.strName);
		System.out.println(""+theLobbyPanel.intPortNumber);
		System.out.println(theLobbyPanel.strIPAddress);
		if (evt.getSource() == theHelpButton) {
			helpSetup();
		}
		if (evt.getSource() == theReturnButton) {
			if (panelToReturn.equals("lobby")) {
				lobbySetup();
			}
			else if (panelToReturn.equals("game")) {
				gameSetup();
			}
		}
	}	
	
	public void lobbySetup() {
		card.show(theViewPanel, "lobby");
		theFrame.requestFocus();
	}
	
	public void gameSetup() {
		card.show(theViewPanel, "game");
		theFrame.requestFocus();
	}
	
	public void helpSetup() {
		card.show(theViewPanel, "help");
		theFrame.requestFocus();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	//Constructor
	public GOGView(){
		super();
		theViewPanel.setLayout(card); 
		theViewPanel.add(theLobbyPanel, "lobby");
		theViewPanel.add(theGamePanel, "game");
		theViewPanel.add(theHelpPanel, "help");
		theViewPanel.setPreferredSize(new Dimension(1280,720));
		
		theServerButton.addActionListener(this);
		theServerButton.setBounds(10,10,200,50);
		theLobbyPanel.add(theServerButton);
		
		theClientButton.addActionListener(this);
		theClientButton.setBounds(1070,10,200,50);
		theLobbyPanel.add(theClientButton);
		
		theHelpButton.setBounds(490,500,300,50);
		theHelpButton.addActionListener(this);
		theLobbyPanel.add(theHelpButton);
		
		theReturnButton.setBounds(1100, 20, 120, 50);
		theReturnButton.addActionListener(this);
		theHelpPanel.add(theReturnButton);
		
		theFrame.setContentPane(theViewPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		

		
		theTimer.start();
		theGameClockTimer.start();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

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
public class GOGView extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
	//Properties
	public JFrame theFrame = new JFrame("Game of the Generals");
	public CardLayout card = new CardLayout(); 
	public JPanel theViewPanel = new JPanel(); 
	public lobbyPanel theLobbyPanel = new lobbyPanel();
	public gamePanel theGamePanel = new gamePanel(); 
	public helpPanel theHelpPanel = new helpPanel();
	public ranksPanel theRanksPanel = new ranksPanel();
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
	public GOGModel theModel = new GOGModel();
	public JButton theLobbyHelpButton = new JButton("Help!");
	public JButton theGameHelpButton = new JButton("Help!");
	public JButton theHelpReturnButton = new JButton("Return");
	public JButton theRanksReturnButton = new JButton("Return");
	public JButton theServerButton = new JButton("Server");
	public JButton theClientButton = new JButton("Client");	
	public JButton theHelpRanksButton = new JButton("Ranks");
	public String panelToReturn = "lobby";
	
	
	ImageIcon image = new ImageIcon("private.png");
	final int Width = image.getIconWidth();
	final int Height = image.getIconHeight();
	Point imageLocation;
	Point prevPt;
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
	
	public void mouseMoved(MouseEvent evt) {

	}
	public void mouseDragged(MouseEvent evt) {
				Point currentPt = evt.getPoint();
				
				imageLocation.translate(
					(int)(currentPt.getX() - prevPt.getX()),
					(int)(currentPt.getY() - prevPt.getY())
				
				);
				prevPt = currentPt;
				repaint();
		}
	public void mouseExited(MouseEvent evt) {

	}
	public void mouseEntered(MouseEvent evt) {

	}
	public void mouseReleased(MouseEvent evt) {	
		System.out.println("released");	
	}
	public void mouseClicked(MouseEvent evt) {

	}
	public void mousePressed(MouseEvent evt) {	
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		image.paintIcon(this,g, 500, 400);
	} 
	
	public void lobbySetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "lobby");
	}
	
	public void gameSetup() {
		theFrame.requestFocus();
		panelToReturn = "game";
		card.show(theViewPanel, "game");
	}
	
	public void helpSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "help");
	}
	
	public void ranksSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "ranks");
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
		theViewPanel.add(theRanksPanel, "ranks");
		theViewPanel.setPreferredSize(new Dimension(1280,720));
		
		theServerButton.addActionListener(this);
		theServerButton.setBounds(10,10,200,50);
		theLobbyPanel.add(theServerButton);
		
		theClientButton.addActionListener(this);
		theClientButton.setBounds(1070,10,200,50);
		theLobbyPanel.add(theClientButton);
		
		theLobbyHelpButton.setBounds(490,500,300,50);
		theLobbyHelpButton.addActionListener(this);
		theLobbyPanel.add(theLobbyHelpButton);
		
		theGameHelpButton.setBounds(900,90,110,50);
		theGameHelpButton.addActionListener(this);
		theGamePanel.add(theGameHelpButton);
		
		theHelpReturnButton.setBounds(1100, 20, 120, 50);
		theHelpReturnButton.addActionListener(this);
		theHelpPanel.add(theHelpReturnButton);
		
		theRanksReturnButton.setBounds(1100, 20, 120, 50);
		theRanksReturnButton.addActionListener(this);
		theRanksPanel.add(theRanksReturnButton);
		
		theHelpRanksButton.setBounds(1100, 600, 120, 50);
		theHelpRanksButton.addActionListener(this);
		theHelpPanel.add(theHelpRanksButton);
		
		theFrame.setContentPane(theViewPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		
<<<<<<< HEAD
		
=======
>>>>>>> a33455bea75ced29f19fcd941b7b7c3860c8272b
		theTimer.start();
		theGameClockTimer.start();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

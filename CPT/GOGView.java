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
public class GOGView extends JPanel implements MouseMotionListener, MouseListener {
	//Properties
	String[][] strImageArray = GOGUtilities.loadArray("ImageNames.csv");
	Font theGOGFont = this.loadFont("BEARPAW.TTF",40);
	/**Frame and Window Stuff*/
	public JFrame theFrame = new JFrame("Game of the Generals");
	public CardLayout card = new CardLayout(); 
	public JPanel theViewPanel = new JPanel(); 
	public IntroAnimationPanel theIntroAnimationPanel = new IntroAnimationPanel();
	public lobbyPanel theLobbyPanel = new lobbyPanel();
	public GOGPrepPanel thePrepPanel = new GOGPrepPanel(strImageArray);
	public gamePanel theGamePanel = new gamePanel(strImageArray); 
	public helpPanel theHelpPanel = new helpPanel();
	public ranksPanel theRanksPanel = new ranksPanel(strImageArray);
	public GOGModel theModel = new GOGModel();
	public GOGFatePanel theFatePanel = new GOGFatePanel();
	
	/**Buttons used in the First Screen*/
	public JButton theLobbyHelpButton = new JButton("Help!");
	public JButton theGameHelpButton = new JButton("Help!");
	public JButton thePrepHelpButton = new JButton("Help!");
	public JButton theHelpReturnButton = new JButton("Return");
	public JButton theRanksReturnButton = new JButton("Return");
	public JButton theServerButton = new JButton("Server");
	public JButton theClientButton = new JButton("Client");	
	public JButton theHelpRanksButton = new JButton("Ranks");
	public JButton theReadyButton = new JButton("Ready");
	public String panelToReturn = "lobby";
	public String strPlayer;
	public boolean blnGameEnd = false;
	
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//These methods mainly pertain to setting up and switching between panels
	/** The paintComponent method allows us to repaint based on the screen we are on */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	} 
	/** The introSetup method allows us to go to intro animtion panel*/
	public void introSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "intro");
	}
	/** The lobbySetup method allows us to switch focus to the lobby panel */
	public void lobbySetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "lobby");
	}
	/** The prepSetup method allows us to switch focus to the preparation panel */
	public void prepSetup() {
		theFrame.requestFocus();
		panelToReturn = "prep";
		card.show(theViewPanel, "prep");
	}
	/** The  gameSetup method allows us to switch focus to the game panel */
	public void gameSetup() {
		theFrame.requestFocus();
		panelToReturn = "game";
		card.show(theViewPanel, "game");
	}
	/** The  helpSetup method allows us to switch focus to the help panel */
	public void helpSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "help");
	}
	/** The  ranksSetup method allows us to switch focus to the ranks help panel */
	public void ranksSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "ranks");
	}
	public void fateSetup() {
		theFrame.requestFocus();
		card.show(theViewPanel, "fate");
	}
	/** The  gameover method allows us to decide when to end the game */
	public void gameOver(String strVictor) {
		if (!blnGameEnd) {
			if (strVictor.equals("P1")) {
				System.out.println("P1 won");
				System.out.println("P2 lost");
		}
			else if (strVictor.equals("P2")) {
				System.out.println("P2 won");
				System.out.println("P1 lost");
			}
			blnGameEnd = true;
		}
	}
	/** This methods allows us to use a specific font in our folder*/
	public Font loadFont(String strFileName, int intSize){    
		Font theFont = null;
		// Try to load the font
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(strFileName)); 
			return theFont.deriveFont(Font.PLAIN, intSize);
		}catch(Exception e){
		//System.out.println(e.toString());
		}
    
		/** Then load the font from the local filesystem*/
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(strFileName)); 
			return theFont.deriveFont(Font.PLAIN, intSize);
		}catch(Exception e){
			System.out.println("Unable to load font file \""+strFileName+"\". Setting default font"); 
		}
    
		/** If the font does not load, we load the default font*/
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("Hack-Regular.ttf")); 
			return theFont.deriveFont(Font.PLAIN, 20);
		}catch(Exception e){
			//System.out.println(e.toString());
			System.out.println("Unable to load default font file \"Hack-Regular.tff\".  Will default to Java's native font for your OS");
		}
		return theFont;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//These methods pertain mouse interaction in the game panel 
	/** The mousePressed method allows us to detect mouse presses and act based on where they pressed */
	public void mousePressed(MouseEvent evt){
		//If they press the mouse in game panel, then...
		if(evt.getSource()==theGamePanel){
			//If they are player 1 and it is player 1's turn, then...
			//or they are player 2 and it is player 2's turn, then...
			if((strPlayer.equals("P1") && theModel.strPlayerTurn.equals("P1"))||(strPlayer.equals("P2") && theModel.strPlayerTurn.equals("P2"))){
				//If there is not active movement prior, then...
				if(theGamePanel.blnActive==false){
					//Get rid of the block in that spot
					theModel.strActivePiece=theGamePanel.checkBlock(evt.getX(), evt.getY(),theModel.strPlayerTurn, strPlayer);
					//These will be the coordinates of the actively moved block
					theGamePanel.intImgX = evt.getX()-35;
					theGamePanel.intImgY = evt.getY()-35;
				}
			}
		
		//If they press the mouse in the prep panel, then...
		}else if(evt.getSource()==thePrepPanel){
			if(thePrepPanel.blnActive==false){
				//Get rid of the block in that spot
				thePrepPanel.checkBlock(evt.getX(), evt.getY());
				//These will be the coordinates of the actively moved block
				thePrepPanel.intImgX = evt.getX()-35;
				thePrepPanel.intImgY = evt.getY()-35;
				
				//We can still check whether it's in the stock too
				thePrepPanel.takeStock(evt.getX(), evt.getY());
			}
		}
	}
	/** The mouseDragged method allows us to get the x and y pixel positions of the pieces */
	public void mouseDragged(MouseEvent evt) {
		//If they drag acorss the game panel, then...
		if(evt.getSource()==theGamePanel){
			//If it is actively being moved, then...
			if(theGamePanel.blnActive==true){
				//Get the x and y coordinates
				theGamePanel.intImgX = evt.getX()-35;
				theGamePanel.intImgY = evt.getY()-35;
				//In the repaint command, we will paint with these coordinates in the panel
			}
			
			
		//If they press the drag in the prep panel, then...
		}else if(evt.getSource()==thePrepPanel){
			//If it is actively being moved, then...
			if(thePrepPanel.blnActive==true){
				//Get the x and y coordinates
				thePrepPanel.intImgX = evt.getX()-35;
				thePrepPanel.intImgY = evt.getY()-35;
				//In the repaint command, we will paint with these coordinates in the panel
			}
		}
	}
	/** The mouseReleased method helps us decide when to stop being active(stop painting the piece) and put it down at the location */
	public void mouseReleased(MouseEvent evt){
		//If they release the mouse in the game panel, then...
		if(evt.getSource()==theGamePanel){
			//If a piece is actively being moved, then...
			if(theGamePanel.blnActive==true){
				//get the new row and column in game panel's row and column
				theGamePanel.getNewPosition();
				//We will update the coordinates for the model here
				theModel.intOGClm=theGamePanel.intOGClm;
				theModel.intOGRow=theGamePanel.intOGRow;
				theModel.intNewClm=theGamePanel.intNewClm;
				theModel.intNewRow=theGamePanel.intNewRow;
				theModel.strNextPositionPiece=theModel.strArray[theModel.intNewRow][theModel.intNewClm];
				
				theModel.checkWarnings();
				
				if(theModel.strWarning.equals("SameTeamWarning")){
					theGamePanel.theTextArea.append("Robot Referee, Warning, Cannot attack pieces on same team \n");
					theGamePanel.strGOGArray[theGamePanel.intOGRow][theGamePanel.intOGClm]=theGamePanel.strActivePiece;
				}else if(theModel.strWarning.equals("MovementWarning")){
					theGamePanel.theTextArea.append("Robot Referee, Warning, Cannot move further than 1 tile \n");
					theGamePanel.strGOGArray[theGamePanel.intOGRow][theGamePanel.intOGClm]=theGamePanel.strActivePiece;
				}else if(theModel.strWarning.equals("NoMovementWarning")){
					theGamePanel.theTextArea.append("Robot Referee, Warning, Cannot move to same spot \n");
					theGamePanel.strGOGArray[theGamePanel.intOGRow][theGamePanel.intOGClm]=theGamePanel.strActivePiece;
				//If that isn't the case, then...
				}else{
					//Check where the piece has moved and make the appropriate edits to the
					//model's array with the checkPieceMovement method
					theModel.checkPieceMovement();
				}
				//At the end of the day, we make the game panel's array the same as the model's array
				theGamePanel.strGOGArray=theModel.strArray;
			}
		
		//If they press on the prep panel instead, then...
		}else if(evt.getSource()==thePrepPanel){
			//If a piece is actively being moved, then...
			if(thePrepPanel.blnActive==true){
				thePrepPanel.getNewPosition();
				//If they move stock, then...
				if(thePrepPanel.blnMovingStock==true){
					//If there's no piece there, allow it to move
					if(thePrepPanel.strGOGArray[thePrepPanel.intNewRow][thePrepPanel.intNewClm].equals(" ")){
						//Check where the piece has moved and make the appropriate edits to the
						//model's array with the checkPieceMovement method
						thePrepPanel.placePiece();
					}
				}else if(thePrepPanel.blnMovingPiece==true){
					//If the model has not moved from its original row to a new row and has not moved from its original column to a new column, then...
					if(thePrepPanel.intOGRow==thePrepPanel.intNewRow && thePrepPanel.intOGClm==thePrepPanel.intNewClm){
						//Don't change anything. 
						thePrepPanel.strGOGArray[thePrepPanel.intOGRow][thePrepPanel.intOGClm]=thePrepPanel.strActivePiece;	
					
					//If there's a piece there, don't do anything
					}else if(!thePrepPanel.strGOGArray[thePrepPanel.intNewRow][thePrepPanel.intNewClm].equals(" ")){
						//thePrepPanel.strGOGArray[thePrepPanel.intOGRow][thePrepPanel.intOGClm]=thePrepPanel.strActivePiece;	
						//If that isn't the case, then...
						thePrepPanel.strGOGArray[thePrepPanel.intOGRow][thePrepPanel.intOGClm]=thePrepPanel.strActivePiece;
					}else if(thePrepPanel.strGOGArray[thePrepPanel.intNewRow][thePrepPanel.intNewClm].equals(" ")){
						thePrepPanel.movePiece();
					}
				}
			}
		}
		thePrepPanel.blnUpdatingPrepArray=true;
		thePrepPanel.blnMovingStock=false;
		thePrepPanel.blnMovingPiece=false;
		thePrepPanel.blnActive=false;
		theGamePanel.blnActive=false;
		theModel.strWarning=" ";
	}
	
	/**These are unused commands that we need to override*/
	public void mouseClicked(MouseEvent evt){}
	public void mouseMoved(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt){}
	public void mouseEntered(MouseEvent evt){}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	/** The GOGView Constructor method allows us to switch between panels and use buttons */
	public GOGView(){
		super();
		
	/**The layout of the View Panel, the location of where everything is*/
		theViewPanel.setLayout(card);
		theViewPanel.add(theIntroAnimationPanel,"intro");
		theViewPanel.add(theLobbyPanel, "lobby");
		theViewPanel.add(thePrepPanel, "prep");
		theViewPanel.add(theGamePanel, "game");
		theViewPanel.add(theHelpPanel, "help");
		theViewPanel.add(theRanksPanel, "ranks");
		theViewPanel.add(theFatePanel, "fate");
		theViewPanel.setPreferredSize(new Dimension(1280,720));
		
		/**The Buttons in the panel*/
		//Server Button
		theServerButton.setFont(theGOGFont);
		theServerButton.setBounds(10,10,200,50);
		theLobbyPanel.add(theServerButton);
		
		//Client Button
		theClientButton.setFont(theGOGFont);
		theClientButton.setBounds(1070,10,200,50);
		theLobbyPanel.add(theClientButton);
		
		//Help Button (in the First Screen)
		theLobbyHelpButton.setFont(theGOGFont);
		theLobbyHelpButton.setBounds(490,550,300,50);
		theLobbyPanel.add(theLobbyHelpButton);
		
		//Help Button (When the server and client connect)
		theGameHelpButton.setFont(theGOGFont);
		theGameHelpButton.setBounds(750,100,480,50);
		theGamePanel.add(theGameHelpButton);
		
		//Prepare Help button
		thePrepHelpButton.setFont(theGOGFont);
		thePrepHelpButton.setBounds(1000,90,270,50);
		thePrepPanel.add(thePrepHelpButton);
		
		//Return Button (Help screen to the game)
		theHelpReturnButton.setFont(theGOGFont);
		theHelpReturnButton.setBounds(1100, 20, 120, 50);
		theHelpPanel.add(theHelpReturnButton);
		
		//Rank Return Button (from rank screen to the help screen)
		theRanksReturnButton.setFont(theGOGFont);
		theRanksReturnButton.setBounds(1100, 20, 120, 50);
		theRanksPanel.add(theRanksReturnButton);
		
		//The Rank Button (Shows the rank for the game)
		theHelpRanksButton.setFont(theGOGFont);
		theHelpRanksButton.setBounds(1100, 600, 120, 50);
		theHelpPanel.add(theHelpRanksButton);
		
		//Ready button to start the game
		theReadyButton.setFont(theGOGFont);
		theReadyButton.setBounds(720,90,260,50);
		thePrepPanel.add(theReadyButton);
		
		theFrame.setContentPane(theViewPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		
		//Adding Mouse and Motion Listeners for both the game and prep panel
		theGamePanel.addMouseMotionListener(this);
		theGamePanel.addMouseListener(this);
		thePrepPanel.addMouseMotionListener(this);
		thePrepPanel.addMouseListener(this);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

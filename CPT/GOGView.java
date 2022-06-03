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
	
	//Frame and Window Stuff
	public JFrame theFrame = new JFrame("Game of the Generals");
	public CardLayout card = new CardLayout(); 
	public JPanel theViewPanel = new JPanel(); 
	public lobbyPanel theLobbyPanel = new lobbyPanel();
	public gamePanel theGamePanel = new gamePanel(); 
	public helpPanel theHelpPanel = new helpPanel();
	public ranksPanel theRanksPanel = new ranksPanel();
	public GOGModel theModel = new GOGModel();
	
	//Button Stuff
	public JButton theLobbyHelpButton = new JButton("Help!");
	public JButton theGameHelpButton = new JButton("Help!");
	public JButton theHelpReturnButton = new JButton("Return");
	public JButton theRanksReturnButton = new JButton("Return");
	public JButton theServerButton = new JButton("Server");
	public JButton theClientButton = new JButton("Client");	
	public JButton theHelpRanksButton = new JButton("Ranks");
	public String panelToReturn = "lobby";
	public String theCurrentPanel = "lobby";
	public String strSocketType;
	
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	// Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//image.paintIcon(this,g, 500, 400);
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
	
	public void mousePressed(MouseEvent evt){
		if(evt.getSource()==theGamePanel){
			if((strSocketType.equals("P1") && theModel.strPlayerTurn.equals("P1"))||(strSocketType.equals("P2") && theModel.strPlayerTurn.equals("P2"))){
				if(theGamePanel.blnActive==false){
					//Get rid of the block in that spot so we can paint another block that we can move
					theModel.strActivePiece=theGamePanel.ridBlock(evt.getX(), evt.getY());
					theGamePanel.intImgX = evt.getX()-35;
					theGamePanel.intImgY = evt.getY()-35;
				}
			}
		}
	}
	public void mouseDragged(MouseEvent evt) {
		if(evt.getSource()==theGamePanel){
			//Get the x and y coordinates
			//In the repaint command, we will paint with these coordinates in the panel
			if(theGamePanel.blnActive==true){
				theGamePanel.intImgX = evt.getX()-35;
				theGamePanel.intImgY = evt.getY()-35;
			}
		}
	}
	public void mouseReleased(MouseEvent evt){
		if(evt.getSource()==theGamePanel){
			if(theGamePanel.blnActive==true){
				//check which area in the grid your piece is in
				//make that area true in the array to signify that part of the grid is filled
				theGamePanel.getNewPosition();
				theModel.intOGClm=theGamePanel.intOGClm;
				theModel.intOGRow=theGamePanel.intOGRow;
				theModel.intNewClm=theGamePanel.intNewClm;
				theModel.intNewRow=theGamePanel.intNewRow;
				if(theModel.intOGRow==theModel.intNewRow && theModel.intOGClm==theModel.intNewClm){
					//System.out.println("didnt move");
					// restore removed block to previous block
					theGamePanel.strGOGArray[theGamePanel.intOGRow][theGamePanel.intOGClm]=theGamePanel.strActivePiece;
				}else{
					theModel.checkPieceMovement();
					
					//After their turn, switch player turn
					if(theModel.strPlayerTurn.equals("P1")){
						theModel.strPlayerTurn="P2";
					}else if(theModel.strPlayerTurn=="P2"){
						theModel.strPlayerTurn="P1";
					}
					theGamePanel.theTextArea.append(theModel.strPlayerTurn+" Turn"+"\n");
				}
				theGamePanel.strGOGArray=theModel.strArray;
			}
		}
		/*System.out.println();
		for (int i=0;i<theGamePanel.strGOGArray.length;i++) {
			System.out.println();
			for (int j=0;j<theGamePanel.strGOGArray[0].length;j++) {
				System.out.print("|"+theGamePanel.strGOGArray[i][j]);
			}
		}*/
	}
	public void mouseClicked(MouseEvent evt){}
	public void mouseMoved(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt){}
	public void mouseEntered(MouseEvent evt){}
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
		
		theServerButton.setBounds(10,10,200,50);
		theLobbyPanel.add(theServerButton);
		
		theClientButton.setBounds(1070,10,200,50);
		theLobbyPanel.add(theClientButton);
		
		theLobbyHelpButton.setBounds(490,500,300,50);
		theLobbyPanel.add(theLobbyHelpButton);
		
		theGameHelpButton.setBounds(900,90,110,50);
		theGamePanel.add(theGameHelpButton);
		
		theHelpReturnButton.setBounds(1100, 20, 120, 50);
		theHelpPanel.add(theHelpReturnButton);
		
		theRanksReturnButton.setBounds(1100, 20, 120, 50);
		theRanksPanel.add(theRanksReturnButton);
		
		theHelpRanksButton.setBounds(1100, 600, 120, 50);
		theHelpPanel.add(theHelpRanksButton);
		
		theFrame.setContentPane(theViewPanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		
		theGamePanel.addMouseMotionListener(this);
		theGamePanel.addMouseListener(this);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

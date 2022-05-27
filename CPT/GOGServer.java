import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class GOGServer implements ActionListener{
	//Properties
	public JFrame theFrame = new JFrame("Game of the Generals");
	public GOGView thePanel = new GOGView();
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
	public GOGModel theModel = new GOGModel();
	public SuperSocketMaster ssm;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	public void actionPerformed(ActionEvent evt){
		//Ever 1/60th of a second, repaint
		if(evt.getSource() == theTimer){
			//we update the panel's array with the model array data
			thePanel.strGOGArray=theModel.strArray;
			thePanel.repaint();
		}
		
		if(evt.getSource()==theGameClockTimer){
			theModel.updateTime();
			//This line has no purpose. I just wanted to save this so we know how to append the thing later
			thePanel.drawNewTime(theModel.strPlayerTurn, theModel.intP1TimeLeft, theModel.intP2TimeLeft);
		}
		if(evt.getSource() == ssm){
			//get text from player 2
			String strText = ssm.readText();
			//get positions with this method
			theModel.getPositions(strText);
			//move the pices
			theModel.checkPieceMovement();
			//show this on the scroll plane
			thePanel.theTextArea.append(strText + "\n");
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	//Constructor
	public GOGServer(){
		thePanel.setPreferredSize(new Dimension(1280,720));
		theFrame.setContentPane(thePanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		
		theTimer.start();
		theGameClockTimer.start();
		ssm = new SuperSocketMaster(9001,this); //Server Constructor
		ssm.connect();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//MainMethod
	public static void main(String[] args){
		new GOGServer();
	}

}

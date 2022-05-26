import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class ChessMain implements ActionListener{
	//Properties
	public JFrame theFrame = new JFrame("Game of the Generals");
	public GOGView thePanel = new GOGView();
	public Timer theTimer = new Timer(1000/60, this);
	public Timer theGameClockTimer=new Timer(1000,this);
	public ChessModel theModel = new ChessModel();
	public SuperSocketMaster ssm;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thePanel.strChessboardArray=theModel.strArray;
			thePanel.repaint();
		}
		if(evt.getSource()==theGameClockTimer){
			theModel.updateTime();
			//This line has no purpose. I just wanted to save this so we know how to append the thing later
			thePanel.drawNewTime(theModel.strPlayerTurn, theModel.intP1TimeLeft, theModel.intP2TimeLeft);
		}
		if(evt.getSource() == ssm){
			String strText = ssm.readText();
			theModel.getPositions(strText);
			theModel.checkPieceMovement();
			thePanel.theTextArea.append(strText + "\n");
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////	
	//Constructor
	public ChessMain(){
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
		new ChessMain();
	}

}

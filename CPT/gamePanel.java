//We import necessary libraries for functionality
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class gamePanel extends JPanel{  
	public String strGOGArray[][] = new String[8][9];
	public BufferedImage imgBoard = null;
	
	//Timer stuff
	public JLabel theGameClockLabel=new JLabel("Time");
	
	//Text area stuff
	public JTextArea theTextArea;
	public JScrollPane theScroll;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
	}
	
	public void paintCharacters(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("1",110+70*0,50);
		g.drawString("2",110+70*1,50);
		g.drawString("3",110+70*2,50);
		g.drawString("4",110+70*3,50);
		g.drawString("5",110+70*4,50);
		g.drawString("6",110+70*5,50);
		g.drawString("7",110+70*6,50);
		g.drawString("8",110+70*7,50);
		g.drawString("9",110+70*8,50);
	}
	
	public void paintPieces(Graphics g){
		int intRow;
		int intColumn;
		g.setColor(Color.WHITE);
		for(intRow=0;intRow<8;intRow++){
			for(intColumn=0;intColumn<9;intColumn++){
				if(strGOGArray[intRow][intColumn] == null){
				}else if(strGOGArray[intRow][intColumn].equals("P1Private")){
					g.drawString("p",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P1Spy")){
					g.drawString("s",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P1Flag")){
					g.drawString("f",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P1 Lieutenant 1st")){
					g.drawString("L1",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P1 Lieutenant 2nd")){
					g.drawString("L2",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P2Private")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P2Spy")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}else if(strGOGArray[intRow][intColumn].equals("P2Flag")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}
			}
		}
	}
	
	public void drawNewTime(String strPlayerTurn, int intP1TimeLeft, int intP2TimeLeft){
		if(strPlayerTurn.equals("P1Turn")){
			int intMinutes = intP1TimeLeft/60;
			int intSeconds = intP1TimeLeft%60;
			theGameClockLabel.setText("Player 1 Time: "+intMinutes+":"+intSeconds);
		}else if(strPlayerTurn.equals("P2Turn")){
			int intMinutes = intP2TimeLeft/60;
			int intSeconds = intP2TimeLeft%60;
			theGameClockLabel.setText("Player 2 Time: "+intMinutes+":"+intSeconds);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintBoard(g);	
		paintCharacters(g);	
		paintPieces(g);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	public gamePanel(){
		super();
		try{
			imgBoard = ImageIO.read(new File("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		theGameClockLabel.setBounds(860,50,200,50);
		this.add(theGameClockLabel);
		
		theTextArea = new JTextArea();
		theScroll = new JScrollPane(theTextArea);
		theScroll.setBounds(720,150,550,500);
		this.add(theScroll);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}








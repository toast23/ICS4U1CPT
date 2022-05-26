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
public class GOGView extends JPanel{
	//Properties
	public String strChessboardArray[][] = new String[9][8];
	public BufferedImage imgChessboard = null;
	
	//Timer stuff
	public JLabel theGameClockLabel=new JLabel("OwO");
	
	//Text area stuff
	public JTextArea theTextArea;
	public JScrollPane theScroll;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void paintBackground(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1280,720);
	}
	
	public void paintChessboard(Graphics g){
		g.drawImage(imgChessboard,80,80,null);
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
				if(strChessboardArray[intRow][intColumn] == null){
				}else if(strChessboardArray[intRow][intColumn].equals("P1p")){
					g.drawString("p",110+70*intColumn,120+70*intRow);
				}else if(strChessboardArray[intRow][intColumn].equals("P1s")){
					g.drawString("s",110+70*intColumn,120+70*intRow);
				}else if(strChessboardArray[intRow][intColumn].equals("P1f")){
					g.drawString("f",110+70*intColumn,120+70*intRow);
				}else if(strChessboardArray[intRow][intColumn].equals("P2p")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}else if(strChessboardArray[intRow][intColumn].equals("P2s")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}else if(strChessboardArray[intRow][intColumn].equals("P2f")){
					g.drawString("???",110+70*intColumn,120+70*intRow);
				}
			}
		}
	}
	
	public void drawNewTime(String strPlayerTurn, int intP1TimeLeft, int intP2TimeLeft){
		if(strPlayerTurn.equals("P1")){
			int intMinutes = intP1TimeLeft/60;
			int intSeconds = intP1TimeLeft%60;
			theGameClockLabel.setText("Player 1 Time: "+intMinutes+":"+intSeconds);
		}else if(strPlayerTurn.equals("P2")){
			int intMinutes = intP2TimeLeft/60;
			int intSeconds = intP2TimeLeft%60;
			theGameClockLabel.setText("Player 2 Time: "+intMinutes+":"+intSeconds);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Background
		paintBackground(g);
		paintChessboard(g);	
		paintCharacters(g);	
		paintPieces(g);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	public GOGView(){
		super();
		try{
			imgChessboard = ImageIO.read(new File("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		this.setLayout(null);
		
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

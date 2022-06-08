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
public class gamePanel extends JPanel {  
	public String strGOGArray[][] = new String[8][9];
	public BufferedImage imgBoard = null;
	public BufferedImage imgFlag = null;
	public BufferedImage imgPrivate = null;
	public BufferedImage imgFogOfWar = null;
	
	//Timer stuff
	public JLabel theGameClockLabel=new JLabel("Time");
	
	//Information about player names
	public JLabel thePlayer1Label = new JLabel("Player 1:");
	public JLabel thePlayer2Label = new JLabel("Player 2:");
	
	//Text area stuff
	public JTextArea theTextArea;
	public JScrollPane theScroll;
	public JTextField theTextField=new JTextField("Enter Code here");
	
	int intImgX;
	int intImgY;
	
	
	//This variable tells us that no piece movement is currently happening
	boolean blnActive=false;
	String strActivePiece;
	
	//This will help us determine whether we can move to new row or not
	int intOGRow;
	int intOGClm;
	int intNewRow;
	int intNewClm;
	String strPiecesToPaint;

	public boolean blnMessageSending = false;
	public boolean blnSwitchTurn = false;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void paintPieces(Graphics g){
		g.setColor(Color.WHITE);
		
		for(int intRow=0;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				//if it's player 1, paint player 2 pieces foggy
				if(strPiecesToPaint.equals("P1")){
					if(strGOGArray[intRow][intColumn].equals(" ")){
					}else if(strGOGArray[intRow][intColumn].equals("P1Flag")){
						g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P1Private")){
						g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P1Spy")){
						g.drawString("s",110+70*intColumn,120+70*intRow);
					}else if(strGOGArray[intRow][intColumn].equals("P2Flag")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P2Private")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P2Spy")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}
				}else if(strPiecesToPaint.equals("P2")){
					if(strGOGArray[intRow][intColumn].equals(" ")){
					}else if(strGOGArray[intRow][intColumn].equals("P1Flag")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P1Private")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P1Spy")){
						g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P2Flag")){
						g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P2Private")){
						g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
					}else if(strGOGArray[intRow][intColumn].equals("P2Spy")){
						g.drawString("s",110+70*intColumn,120+70*intRow);
					}
				}
			}
		}
	}
	
	//When they press on an area with a block, we do the following...
	public String checkBlock(int IntPosX, int IntPosY, String strPlayerTurn, String strPlayer){
		int intClm;
		int intRow;
		
		//Inside a certain row and column...
		for(intRow=0;intRow<8;intRow++){
			for(intClm=0;intClm<9;intClm++){
				//if the x and y position is...
				//-more than the left side wall
				//-less than the right side wall
				//-more than the top side wall
				//-less than the bottom side wall
				//then...
				if(IntPosX>intClm*70+80 && IntPosX<(intClm+1)*70+80 && IntPosY>intRow*70+80 && IntPosY<(intRow+1)*70+80){
					//If in that spot, there is already a block there(true), then...
					if(!this.strGOGArray[intRow][intClm].equals(" ")){
						//Get rid of that block so we can replace it with an active block that we paint
						this.strActivePiece=this.strGOGArray[intRow][intClm];
						this.strGOGArray[intRow][intClm]=" ";
						//We set the boolean active to true because we are now moving a block
						this.blnActive=true;
						//We note the original row and column
						intOGRow=intRow;
						intOGClm=intClm;
					}
				}
			}
		}
		//if they try to move the enemy pieces, they aren't allowed to, then...
		if(this.strActivePiece.equals(" ")){
			this.blnActive=false;
			this.strGOGArray[intOGRow][intOGClm]=this.strActivePiece;
		}else if(this.strActivePiece.substring(0,2).equals("P1") && strPlayerTurn.equals("P2") && strPlayer.equals("P2")){
			//don't let them move it
			this.blnActive=false;
			this.strGOGArray[intOGRow][intOGClm]=this.strActivePiece;
		}else if(this.strActivePiece.substring(0,2).equals("P2") && strPlayerTurn.equals("P1") && strPlayer.equals("P1")){
			//don't let them move it
			this.blnActive=false;
			this.strGOGArray[intOGRow][intOGClm]=this.strActivePiece;
		}
		return strActivePiece;
	}
	
	//When they release their mouse, we use this method
	public void getNewPosition(){	
		//Inside a certain row and column...
		for(int intRow=0;intRow<8;intRow++){
			for(int intClm=0;intClm<9;intClm++){
				//if the x and y position is...
				//-more than the left side wall
				//-less than the right side wall
				//-more than the top side wall
				//-less than the bottom side wall
				//then...
				if(intImgX+35>intClm*70+80 && intImgX+35<(intClm+1)*70+80 && intImgY+35>intRow*70+80 && intImgY+35<(intRow+1)*70+80){
					//we note this new row and column
						intNewRow=intRow;
						intNewClm=intClm;
					this.blnActive=false;
				}
			}
		}
	}
	//We call this in our repaint method
	public void paintActiveBlock(Graphics g){
		//If active is true, which means user is dragging, then we draw the moving block
		if(blnActive==true){
			//We draw the block
			g.setColor(Color.BLACK);
			if(strActivePiece.equals("P1Flag") || strActivePiece.equals("P2Flag")){
				g.drawImage(imgFlag, intImgX, intImgY,null);
			}else if(strActivePiece.equals("P1Private") || strActivePiece.equals("P2Private")){
				g.drawImage(imgPrivate, intImgX, intImgY,null);
			}
		}
	}

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
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintBoard(g);	
		paintCharacters(g);	
		paintPieces(g);
		paintActiveBlock(g);
}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	public gamePanel(){
		super();
		for(int intRow=0; intRow<8;intRow++){
			for(int intClm=0; intClm<9;intClm++){
				strGOGArray[intRow][intClm]=" ";
			}
		}
		try{
			imgBoard = ImageIO.read(new File("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgPrivate = ImageIO.read(new File("private.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgFlag = ImageIO.read(new File("flag.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgFogOfWar = ImageIO.read(new File("FogOfWar.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		theGameClockLabel.setBounds(960,50,200,50);
		this.add(theGameClockLabel);
		
		thePlayer1Label.setBounds(750,160,200,50);
		this.add(thePlayer1Label);
		
		thePlayer2Label.setBounds(1040,160,200,50);
		this.add(thePlayer2Label);
		
		theTextArea = new JTextArea();
		theScroll = new JScrollPane(theTextArea);
		theScroll.setBounds(720,200,550,350);
		this.add(theScroll);
		
		theTextField.setBounds(720,550,550,100);
		this.add(theTextField);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}








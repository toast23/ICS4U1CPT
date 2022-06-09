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
	Font theGOGFont = null; 
	public String strGOGArray[][] = GOGUtilities.makeEmptyBoardArray();
	public BufferedImage imgBoard = null;
	public BufferedImage imgFlag = null;
	public BufferedImage imgPrivate = null;
	public BufferedImage imgSpies = null;
	public BufferedImage imgFogOfWar = null;
	public BufferedImage imgGeneral5 = null;
	public BufferedImage imgGeneral4 = null;
	public BufferedImage imgGeneral3 = null;
	public BufferedImage imgGeneral2 = null;
	public BufferedImage imgGeneral1 = null;
	public BufferedImage imgColonel = null;
	public BufferedImage imgLColonel = null;
	public BufferedImage imgMajor = null;
	public BufferedImage imgCaptain = null;
	public BufferedImage imgLieutenant1 = null;
	public BufferedImage imgLieutenant2 = null;
	public BufferedImage imgSergeant = null;
	
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
	String strActivePiece=" ";
	
	//This will help us determine whether we can move to new row or not
	int intOGRow;
	int intOGClm;
	int intNewRow;
	int intNewClm;
	String strPiecesToPaint;

	public boolean blnMessageSending = false;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods for painting
	public void loadImages(String[] strImageArray){
		try{
			imgBoard = ImageIO.read(new File("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFlag = ImageIO.read(new File(strImageArray[0]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgPrivate = ImageIO.read(new File(strImageArray[1]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSpies = ImageIO.read(new File(strImageArray[2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSergeant = ImageIO.read(new File(strImageArray[3]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant2 = ImageIO.read(new File(strImageArray[4]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant1 = ImageIO.read(new File(strImageArray[5]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgCaptain = ImageIO.read(new File(strImageArray[6]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgMajor = ImageIO.read(new File(strImageArray[7]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLColonel = ImageIO.read(new File(strImageArray[8]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgColonel = ImageIO.read(new File(strImageArray[9]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral1 = ImageIO.read(new File(strImageArray[10]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral2 = ImageIO.read(new File(strImageArray[11]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral3 = ImageIO.read(new File(strImageArray[12]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral4 = ImageIO.read(new File(strImageArray[13]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral5 = ImageIO.read(new File(strImageArray[14]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFogOfWar = ImageIO.read(new File(strImageArray[15]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
	public Font loadFont(String strFileName, int intSize){    
		Font theFont = null;
		// Try to load the font
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(strFileName)); 
			return theFont.deriveFont(Font.PLAIN, intSize);
		}catch(Exception e){
		//System.out.println(e.toString());
		}
    
		// Then try to load the font from the local filesystem
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(strFileName)); 
			return theFont.deriveFont(Font.PLAIN, intSize);
		}catch(Exception e){
			//System.out.println(e.toString());
			System.out.println("Unable to load font file \""+strFileName+"\". Setting default font"); 
		}
    
		// Then load the default font if all else fails
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("Hack-Regular.ttf")); 
			return theFont.deriveFont(Font.PLAIN, 20);
		}catch(Exception e){
			//System.out.println(e.toString());
			System.out.println("Unable to load default font file \"Hack-Regular.tff\".  Will default to Java's native font for your OS");
		}
		return theFont;
	}
	public void changeFont(){
		theGameClockLabel.setFont(theGOGFont);
		theGameClockLabel.setForeground(Color.RED);
		
		thePlayer1Label.setFont(theGOGFont);
		thePlayer1Label.setForeground(Color.RED);
		
		thePlayer2Label.setFont(theGOGFont);
		thePlayer2Label.setForeground(Color.BLUE);
		
		theTextArea.setFont(theGOGFont);
		theTextArea.setForeground(Color.ORANGE);
		
		theTextField.setFont(theGOGFont);
		theTextField.setForeground(Color.PINK);
	}
	public void paintPieces(Graphics g){
		g.setColor(Color.WHITE);
		
		for(int intRow=0;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				//if it's player 1, paint player 2 pieces foggy
				if(strPiecesToPaint.equals("P1")){
					if(strGOGArray[intRow][intColumn].equals("P1Flag")){
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
	public void paintCharacters(Graphics g){
		g.setFont(theGOGFont);
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
	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
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
	public gamePanel(String[] strImageArray){
		super();
		theGOGFont = this.loadFont("BEARPAW.TTF", 20);	
		this.loadImages(strImageArray);
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		theGameClockLabel.setFont(theGOGFont);
		theGameClockLabel.setForeground(Color.RED);
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








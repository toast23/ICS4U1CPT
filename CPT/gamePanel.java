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
	Font theGOGFont = this.loadFont("BEARPAW.TTF", 20);	
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
	/** The loadImages method allows us to use a specific font in our folder*/
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
			imgGeneral1 =ImageIO.read(new File(strImageArray[10]));
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
	/** The loadFont method allows us to take a specific font from the folder and use it in texts */
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
	/** The changeFont method allows us to change the font of label s*/
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
	/** The paintPieces method allows us to paint privates, spies, flags, and other pieces onto the screen */
	public void paintPieces(Graphics g){
		g.setColor(Color.WHITE);
		
		for(int intRow=0;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				//if there's nothing in the way, then change nothing
				if(strGOGArray[intRow][intColumn].equals(" ")){
					
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Flag")){
					g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Private")){
					g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Spy")){
					g.drawImage(imgSpies, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Sergeant")){
					g.drawImage(imgSergeant, 80+70*intColumn,80+70*intRow, null);
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"2nd Lieutenant")){
					g.drawImage(imgLieutenant2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"1st Lieutenant")){
					g.drawImage(imgLieutenant1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Captain")){
					g.drawImage(imgCaptain, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Major")){
					g.drawImage(imgMajor, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"L.Colonel")){
					g.drawImage(imgLColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"Colonel")){
					g.drawImage(imgColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"1*General")){
					g.drawImage(imgGeneral1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"2*General")){
					g.drawImage(imgGeneral2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"3*General")){
					g.drawImage(imgGeneral3, 80+70*intColumn,80+70*intRow, null);
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"2*General")){
					g.drawImage(imgGeneral2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"4*General")){
					g.drawImage(imgGeneral4, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPiecesToPaint+"5*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}else{
					g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
				}
			}
		}
	}
	/** The paintActiveBlock method allows us to paint the block while it is being dragged */
	public void paintActiveBlock(Graphics g){
		//If active is true, which means user is dragging, then we draw the moving block
		if(blnActive==true){
			//We draw the block
			if(strActivePiece.equals(strPiecesToPaint+"Flag")){
				g.drawImage(imgFlag, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Private")){
				g.drawImage(imgPrivate, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Spy")){
				g.drawImage(imgSpies, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Sergeant")){
				g.drawImage(imgSergeant, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"2nd Lieutenant")){
				g.drawImage(imgLieutenant2, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"1st Lieutenant")){
				g.drawImage(imgLieutenant1, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Captain")){
				g.drawImage(imgCaptain, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Major")){
				g.drawImage(imgMajor, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"L.Colonel")){
				g.drawImage(imgLColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"Colonel")){
				g.drawImage(imgColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"1*General")){
				g.drawImage(imgGeneral1, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"2*General")){
				g.drawImage(imgGeneral2, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"3*General")){
				g.drawImage(imgGeneral3, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"4*General")){
				g.drawImage(imgGeneral4, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPiecesToPaint+"5*General")){
				g.drawImage(imgGeneral5, intImgX, intImgY,null);
			}
		}
	}
	/** The paintCharacters method allows us to paint the numbers on the top of the screen */
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
	/** The paintBoard method allows us to paint the 9 row by 8 column array on the panel */
	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
	}
	/** the drawNewTime method allows us to update the time in hours, minutes, and seconds for the clock label */
	public void drawNewTime(String strPlayerTurn, int intP1TimeLeft, int intP2TimeLeft){
		if(strPlayerTurn.equals("P1")){
			int intHours = intP1TimeLeft/3600;
			int intMinutes = (intP1TimeLeft-intHours*3600)/60;
			int intSeconds = intP1TimeLeft%60;
			theGameClockLabel.setText("Player 1 Time: "+intHours+":"+intMinutes+":"+intSeconds);
		}else if(strPlayerTurn.equals("P2")){
			int intHours = intP2TimeLeft/3600;
			int intMinutes = (intP2TimeLeft-intHours*3600)/60;
			int intSeconds = intP2TimeLeft%60;
			theGameClockLabel.setText("Player 2 Time: "+intHours+":"+intMinutes+":"+intSeconds);
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//When they press on an area with a block, we do the following...
	/** The checkBlock method allows us to check whether the point that was pressed is empty or contains a piece on the same team as you, which subsequently tells the panel to be active */
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
	/** The getNewPosition method allows us to get the row and column that is determined based on coordinates */
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
	/** The paintComponent command alloss us to paint the board, pieces, and characters onto the panel */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintBoard(g);	
		paintCharacters(g);	
		paintPieces(g);
		paintActiveBlock(g);
}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	/** The gamePanel constructor method allows us to create the game panel, which we can use during gameplay */
	public gamePanel(String[] strImageArray){
		super();
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








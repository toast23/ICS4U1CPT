//We will import necessary libraries for functionality
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
public class GOGPrepPanel extends JPanel {  
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
	
	//Information about player names
	public JLabel thePlayer1Label = new JLabel("Player 1");
	public JLabel thePlayer2Label = new JLabel("Player 2");
	
	int intImgX;
	int intImgY;
	
	
	//This variable tells us that no piece movement is currently happening
	boolean blnActive=false;
	String strActivePiece=null;
	
	//This will help us determine whether we can move to new row or not
	int intOGRow=0;
	int intOGClm=0;
	int intNewRow=0;
	int intNewClm=0;
	
	boolean blnPlayer1Ready=false;
	boolean blnPlayer2Ready=false;
	boolean blnMakingArray=false;
	
	int intFlag=1;
	int intPrivate=6;
	int intSpy=2;
	int intGeneral5=1;
	int intGeneral4=1;
	int intGeneral3=1;
	int intGeneral2=1;
	int intGeneral1=1;
	int intColonel=1;
	int intLColonel=1;
	int intMajor=1;
	int intCaptain=1;
	int intLieutenant1=1;
	int intLieutenant2=1;
	int intSergeant=1;
	
	
	//We need to distinguish 
	boolean blnMovingStock=false;
	boolean blnMovingPiece=false;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Paint Background and Pieces Methods
	public void loadImages(String[] strImageArray){
		try{
			imgBoard = ImageIO.read(this.getClass().getResourceAsStream("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFlag = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[0]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgPrivate = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[1]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSpies = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSergeant = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[3]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant2 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[4]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant1 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[5]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgCaptain = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[6]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgMajor = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[7]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLColonel = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[8]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgColonel = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[9]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral1 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[10]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral2 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[11]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral3 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[12]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral4 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[13]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral5 = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[14]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFogOfWar = ImageIO.read(this.getClass().getResourceAsStream(strImageArray[15]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
	//We call this in our repaint method
	public void paintActiveBlock(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
		//If active is true, which means user is dragging, then we draw the moving block
		if(blnActive==true){
			//We draw the block
			g.setColor(Color.BLACK);
			if(strActivePiece.equals("Flag")){
				g.drawImage(imgFlag, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Private")){
				g.drawImage(imgPrivate, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Spy")){
				g.drawImage(imgSpies, intImgX, intImgY,null);
			}else if(strActivePiece.equals("5*General")){
				g.drawImage(imgGeneral5, intImgX, intImgY,null);
			}else if(strActivePiece.equals("4*General")){
				g.drawImage(imgGeneral4, intImgX, intImgY,null);
			}else if(strActivePiece.equals("3*General")){
				g.drawImage(imgGeneral3, intImgX, intImgY,null);
			}else if(strActivePiece.equals("2*General")){
				g.drawImage(imgGeneral2, intImgX, intImgY,null);
			}else if(strActivePiece.equals("1*General")){
				g.drawImage(imgGeneral1, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Colonel")){
				g.drawImage(imgColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals("L.Colonel")){
				g.drawImage(imgLColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Major")){
				g.drawImage(imgMajor, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Captain")){
				g.drawImage(imgCaptain, intImgX, intImgY,null);
			}else if(strActivePiece.equals("1st Lieutenant")){
				g.drawImage(imgLieutenant1, intImgX, intImgY,null);
			}else if(strActivePiece.equals("2nd Lieutenant")){
				g.drawImage(imgLieutenant2, intImgX, intImgY,null);
			}else if(strActivePiece.equals("Sergeant")){
				g.drawImage(imgSergeant, intImgX, intImgY,null);
			}else{
				g.fillRect(intImgX,intImgY,70,70);
			}
		}
	}
	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
	}
	public void paintStock(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
		//Draw number of flags
		g.drawString("x"+intFlag+" Flags",720,200);
		g.drawRect(720,215,70,70);
		//If we have at least 1 flag left, then we draw it
		if(intFlag>0){
			g.drawImage(imgFlag,720,215,null);
		}
		
		//Draw number of privates
		g.drawString("x"+intPrivate+" Privates",720,300);
		g.drawRect(720,315,70,70);
		if(intPrivate>0){
			g.drawImage(imgPrivate,720,315,null);
		}
		
		//Draw number of spies
		g.drawString("x"+intSpy+" Spies",720,400);
		g.drawRect(720,415,70,70);
		if(intSpy>0){
			g.drawImage(imgSpies,720,415,null);
		}
		
		//Draw number of 5 star generals
		g.drawString("x"+intGeneral5+" 5 star general",720,500);
		g.drawRect(720,515,70,70);
		if(intGeneral5>0){
			g.drawImage(imgGeneral5,720,515,null);
		}
		
		//Draw number of 4 star generals
		g.drawString("x"+intGeneral4+" 4 star general",720,600);
		g.drawRect(720,615,70,70);
		if(intGeneral4>0){
			g.drawImage(imgGeneral4,720,615,null);
		}
		
		//Draw number of 3 star generals
		g.drawString("x"+intGeneral3+" 3 star general",920,200);
		g.drawRect(920,215,70,70);
		if(intGeneral3>0){
			g.drawImage(imgGeneral3,920,215,null);
		}
		
		//Draw number of 2 star generals
		g.drawString("x"+intGeneral2+" 2 star general",920,300);
		g.drawRect(920,315,70,70);
		if(intGeneral2>0){
			g.drawImage(imgGeneral2,920,315,null);
		}
		
		//Draw number of 1 star generals
		g.drawString("x"+intGeneral1+" 1 star general",920,400);
		g.drawRect(920,415,70,70);
		if(intGeneral1>0){
			g.drawImage(imgGeneral1,920,415,null);
		}
		
		//Draw number of Colonel
		g.drawString("x"+intColonel+" Colonel ",920,500);
		g.drawRect(920,515,70,70);
		if(intColonel>0){
			g.drawImage(imgColonel,920,515,null);
		}
		
		//Draw number of Lieutenant Colonel
		g.drawString("x"+intLColonel+" L.Colonel ",920,600);
		g.drawRect(920,615,70,70);
		if(intLColonel>0){
			g.drawImage(imgLColonel,920,615,null);
		}
		
		//Draw number of Major
		g.drawString("x"+intMajor+" Major ",1120,200);
		g.drawRect(1120,215,70,70);
		if(intMajor>0){
			g.drawImage(imgMajor,1120,215,null);
		}
		
		//Draw number of captain
		g.drawString("x"+intCaptain+" Captain ",1120,300);
		g.drawRect(1120,315,70,70);
		if(intCaptain>0){
			g.drawImage(imgCaptain,1120,315,null);
		}
		
		//Draw number of 1st Lieutenant
		g.drawString("x"+intLieutenant1+" 1st Lieutenant ",1120,400);
		g.drawRect(1120,415,70,70);
		if(intLieutenant1>0){
			g.drawImage(imgLieutenant1,1120,415,null);
		}
		
		//Draw number of 2nd Lieutenant
		g.drawString("x"+intLieutenant2+" 2nd Lieutenant ",1120,500);
		g.drawRect(1120,515,70,70);
		if(intLieutenant2>0){
			g.drawImage(imgLieutenant2,1120,515,null);
		}
		
		//Draw number of Sergeant
		g.drawString("x"+intSergeant+" Sergeant ",1120,600);
		g.drawRect(1120,615,70,70);
		if(intSergeant>0){
			g.drawImage(imgSergeant,1120,615,null);
		}
	}
	public void paintPieces(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.WHITE);
		//for the first 3 rows, just paint fog
		for(int intRow=0;intRow<3;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
			}
		}
		
		for(int intRow=5;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				if(strGOGArray[intRow][intColumn].equals(" ")){
				}else if(strGOGArray[intRow][intColumn].equals("Flag")){
					g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Private")){
					g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Spy")){
					g.drawImage(imgSpies, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("5*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("4*General")){
					g.drawImage(imgGeneral4, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("3*General")){
					g.drawImage(imgGeneral3, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("2*General")){
					g.drawImage(imgGeneral2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("1*General")){
					g.drawImage(imgGeneral1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Colonel")){
					g.drawImage(imgColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("L.Colonel")){
					g.drawImage(imgLColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Major")){
					g.drawImage(imgMajor, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Captain")){
					g.drawImage(imgCaptain, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("1st Lieutenant")){
					g.drawImage(imgLieutenant1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("2nd Lieutenant")){
					g.drawImage(imgLieutenant2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Sergeant")){
					g.drawImage(imgSergeant, 80+70*intColumn,80+70*intRow, null);
				}
			}
		}
	}
	
	public void paintCharacters(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
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
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//These methods are for interaction
	//When they press on an area with a block, we do the following...
	public void checkBlock(int IntPosX, int IntPosY){
		//Inside a certain row and column...
		for(int intRow=5;intRow<8;intRow++){
			for(int intClm=0;intClm<9;intClm++){
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
						this.blnMovingPiece=true;
						//We note the original row and column
						intOGRow=intRow;
						intOGClm=intClm;
					}
				}
			}
		}
	}
	
	public void takeStock(int intPosX, int intPosY){
		//if they click on the flag stock area, then...
		if(intPosX>720 && intPosX<790 && intPosY>200 && intPosY<270 && intFlag>0){
			this.strActivePiece="Flag";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
			
		
		//if they click on the private stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>300 && intPosY<370 && intPrivate>0){
			this.strActivePiece="Private";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		
		//if they click on the spy stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>400 && intPosY<470 && intSpy>0){
			this.strActivePiece="Spy";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		
		}else if(intPosX>720 && intPosX<790 && intPosY>500 && intPosY<570 && intGeneral5>0){
			this.strActivePiece="5*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>720 && intPosX<790 && intPosY>600 && intPosY<670 && intGeneral4>0){
			this.strActivePiece="4*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>200 && intPosY<270 && intGeneral3>0){
			this.strActivePiece="3*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>300 && intPosY<370 && intGeneral2>0){
			this.strActivePiece="2*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>400 && intPosY<470 && intGeneral1>0){
			this.strActivePiece="1*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>500 && intPosY<570 && intColonel>0){
			this.strActivePiece="Colonel";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>600 && intPosY<670 && intLColonel>0){
			this.strActivePiece="L.Colonel";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>200 && intPosY<270 && intMajor>0){
			this.strActivePiece="Major";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>300 && intPosY<370 && intCaptain>0){
			this.strActivePiece="Captain";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>400 && intPosY<470 && intLieutenant1>0){
			this.strActivePiece="1st Lieutenant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>500 && intPosY<570 && intLieutenant2>0){
			this.strActivePiece="2nd Lieutenant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>600 && intPosY<670 && intSergeant>0){
			this.strActivePiece="Sergeant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
	}
}
	//When they release their mouse, we use this method
	public void getNewPosition(){	
		//Inside a certain row and column...
		for(int intRow=5;intRow<8;intRow++){
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
				}
			}
		}
	}
	public void placePiece(){
		//If it's in the correct rows, then go for it
		if(intNewRow>4){
			strGOGArray[intNewRow][intNewClm]=strActivePiece;
			if(strActivePiece.equals("Flag")){
				intFlag--;
			}else if(strActivePiece.equals("Private")){
				intPrivate--;
			}else if(strActivePiece.equals("Spy")){
				intSpy--;
			}else if(strActivePiece.equals("5*General")){
				intGeneral5--;
			}else if(strActivePiece.equals("4*General")){
				intGeneral4--; 
			}else if(strActivePiece.equals("3*General")){
				intGeneral3--;
			}else if(strActivePiece.equals("2*General")){
				intGeneral2--;
			}else if(strActivePiece.equals("1*General")){
				intGeneral1--;
			}else if(strActivePiece.equals("Colonel")){
				intColonel--;
			}else if(strActivePiece.equals("L.Colonel")){
				intLColonel--;
			}else if(strActivePiece.equals("Major")){
				intMajor--;
			}else if(strActivePiece.equals("Captain")){
				intCaptain--;
			}else if(strActivePiece.equals("1st Lieutenant")){
				intLieutenant1--;
			}else if(strActivePiece.equals("2nd Lieutenant")){
				intLieutenant2--;
			}else if(strActivePiece.equals("Sergeant")){
				intSergeant--;
			}
		}
	}
	
	public void movePiece(){
		strGOGArray[intNewRow][intNewClm]=strActivePiece;
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

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintBoard(g);
		paintStock(g);
		paintCharacters(g);	
		paintPieces(g);
		paintActiveBlock(g);
}
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	public GOGPrepPanel(String[] strImageArray){
		super();
		
		theGOGFont = this.loadFont("BEARPAW.TTF", 20);
		
		this.loadImages(strImageArray);
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		thePlayer1Label.setBounds(750,160,200,50);
		this.add(thePlayer1Label);
		
		thePlayer2Label.setBounds(1040,160,200,50);
		this.add(thePlayer2Label);
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

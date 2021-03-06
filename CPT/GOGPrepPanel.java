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
/** The GOGPrepPanel class creates a panel that is specialized in allowing the player to drag on drop pieces onto the board during the preparation phase */
public class GOGPrepPanel extends JPanel {  
	/** The theGOGFont variable stores the bearpaw font in size 20 */
	Font theGOGFont = this.loadFont("BEARPAW.TTF", 20);
	/** The strGOGArray allows us to keep track of where the player placed the pieces */
	public String strGOGArray[][] = GOGUtilities.makeEmptyBoardArray();
	/** This is the image for the Board */
	public BufferedImage imgBoard = null;
	/** This is the image for the Flag */
	public BufferedImage imgFlag = null;
	/** This is the image for the Private */
	public BufferedImage imgPrivate = null;
	/** This is the image for the Spies */
	public BufferedImage imgSpies = null;
	/** This is the image for the Fog so that the player cannot see the pieces of the opponent*/
	public BufferedImage imgFogOfWar = null;
	/** This is the image for the 5 star general */
	public BufferedImage imgGeneral5 = null;
	/** This is the image for the 4 star general */
	public BufferedImage imgGeneral4 = null;
	/** This is the image for the 3 star general */
	public BufferedImage imgGeneral3 = null;
	/** This is the image for the 2 star general */
	public BufferedImage imgGeneral2 = null;
	/** This is the image for the 1 star general */
	public BufferedImage imgGeneral1 = null;
	/** This is the image for the Colonel */
	public BufferedImage imgColonel = null;
	/** This is the image for the Lieutenant Colonel */
	public BufferedImage imgLColonel = null;
	/** This is the image for the major */
	public BufferedImage imgMajor = null;
	/** This is the image for the Captain */
	public BufferedImage imgCaptain = null;
	/** This is the image for the 1st Lieutenant */
	public BufferedImage imgLieutenant1 = null;
	/** This is the image for the 2nd Lieutenant */
	public BufferedImage imgLieutenant2 = null;
	/** This is the image for the Sergeant */
	public BufferedImage imgSergeant = null;
	
	//Information about player names
	/** The thePlayer1Label will display the name of player 1 */
	public JLabel thePlayer1Label = new JLabel("Player 1");
	/** The thePlayer1Label will display the name of player 2 */
	public JLabel thePlayer2Label = new JLabel("Player 2");
	
	/** The intImgX variable allows us to track the x coordinate of where the image is and where it should be drawn */
	int intImgX;
	/** The intImgX variable allows us to track the y coordinate of where the image is and where it should be drawn */
	int intImgY;
	
	
	//This variable tells us that no piece movement is currently happening
	/** The blnActive variable tells us whether a piece is being dragged or not */
	boolean blnActive=false;
	/** The strActivePiece tells us the name of the piece that is being dragged */
	String strActivePiece=null;
	
	//This will help us determine whether we can move to new row or not
	/** The intOGRow  */
	/** the ingOGClm variable is used to keep track of the original column the piece that is picked up was in */
	public int intOGClm=0;
	/** the intOGRow variable is used to keep track of the original row the piece that is picked up was in */
	public int intOGRow=0;
	/** the ingNewClm variable is used to keep track of the new column the piece that is dropped in */
	public int intNewClm=0;
	/** the ingNewRow variable is used to keep track of the new row the piece that is dropped in */
	public int intNewRow=0;
	
	/** The blnPlayer1Ready variable tells us whether player 1 is ready or not */
	boolean blnPlayer1Ready=false;
	/** The blnPlayer2Ready variable tells us whether player 2 is ready or not */
	boolean blnPlayer2Ready=false;
	/** The blnUpdatingPrepArray variable tells us whether it is time to start updating the prepared array */
	boolean blnUpdatingPrepArray=false;
	/** The blnMakingArray variable tells us whether we should finalize and make the array or not */
	boolean blnMakingArray=false;
	
	/** The ingFlag variable tells us how many flag pieces are in the inventory */
	int intFlag=1;
	/** The intPrivate variable tells us how many Private pieces are in the inventory */
	int intPrivate=6;
	/** The intSpy variable tells us how many Spy pieces are in the inventory */
	int intSpy=2;
	/** The intGeneral5 variable tells us how many 5 star general pieces are in the inventory */
	int intGeneral5=1;
	/** The intGeneral4 variable tells us how many 4 star general pieces are in the inventory */
	int intGeneral4=1;
	/** The intGeneral3 variable tells us how many 3 star general pieces are in the inventory */
	int intGeneral3=1;
	/** The intGeneral2 variable tells us how many 2 star general pieces are in the inventory */
	int intGeneral2=1;
	/** The intGeneral1 variable tells us how many 1 star general pieces are in the inventory */
	int intGeneral1=1;
	/** The intColonel variable tells us how many colonel pieces are in the inventory */
	int intColonel=1;
	/** The intLColonel variable tells us how many lieutenant colonel pieces are in the inventory */
	int intLColonel=1;
	/** The intMajor variable tells us how many major pieces are in the inventory */
	int intMajor=1;
	/** The intCaptain variable tells us how many captan pieces are in the inventory */
	int intCaptain=1;
	/** The intLieutenant1 variable tells us how many first lieutenant pieces are in the inventory */
	int intLieutenant1=1;
	/** The intLieutenant2 variable tells us how many second lieutenant pieces are in the inventory */
	int intLieutenant2=1;
	/** The sergeant variable tells us how many sergeant pieces are in the inventory */
	int intSergeant=1;
	
	
	//We need to distinguish 
	/** The blnMovingStock variable tells us if we are moving a piece from the inventory */
	boolean blnMovingStock=false;
	/** The blnMovingPiece variable tells us if we are moving a piece from the board to another part of the board */
	boolean blnMovingPiece=false;
	
	/** The strPlayer variable stores info on whether it's player 1 or 2 */
	String strPlayer;
	/** The strOtherPlayer variable stores info on whether the other player is player 1 or 2 */
	String strOtherPlayer;
	
	/** The theWarningLabel variable stores info on what type of warning we should be giving*/
	JLabel theWarningLabel = new JLabel(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Paint Background and Pieces Methods
	/** The loadImages method allows us to use a specific font in our folder*/
	public void loadImages(String[][] strImageArray){
		try{
			imgBoard = ImageIO.read(new File("board.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFlag = ImageIO.read(new File(strImageArray[0][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgPrivate = ImageIO.read(new File(strImageArray[1][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSpies = ImageIO.read(new File(strImageArray[2][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSergeant = ImageIO.read(new File(strImageArray[3][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant2 = ImageIO.read(new File(strImageArray[4][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant1 = ImageIO.read(new File(strImageArray[5][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgCaptain = ImageIO.read(new File(strImageArray[6][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgMajor = ImageIO.read(new File(strImageArray[7][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLColonel = ImageIO.read(new File(strImageArray[8][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgColonel = ImageIO.read(new File(strImageArray[9][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral1 =ImageIO.read(new File(strImageArray[10][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral2 = ImageIO.read(new File(strImageArray[11][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral3 = ImageIO.read(new File(strImageArray[12][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral4 = ImageIO.read(new File(strImageArray[13][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral5 = ImageIO.read(new File(strImageArray[14][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFogOfWar = ImageIO.read(new File(strImageArray[15][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
	/** The paintActiveBlock method allows us to paint the block while it's being dragged by the mouse */
	public void paintActiveBlock(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
		//If active is true, which means user is dragging, then we draw the moving block
		if(blnActive==true){
			//We draw the block
			g.setColor(Color.BLACK);
			if(strActivePiece.equals(strPlayer+"Flag")){
				g.drawImage(imgFlag, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Private")){
				g.drawImage(imgPrivate, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Spy")){
				g.drawImage(imgSpies, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"5*General")){
				g.drawImage(imgGeneral5, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"4*General")){
				g.drawImage(imgGeneral4, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"3*General")){
				g.drawImage(imgGeneral3, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"2*General")){
				g.drawImage(imgGeneral2, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"1*General")){
				g.drawImage(imgGeneral1, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Colonel")){
				g.drawImage(imgColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"L.Colonel")){
				g.drawImage(imgLColonel, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Major")){
				g.drawImage(imgMajor, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Captain")){
				g.drawImage(imgCaptain, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"1st Lieutenant")){
				g.drawImage(imgLieutenant1, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"2nd Lieutenant")){
				g.drawImage(imgLieutenant2, intImgX, intImgY,null);
			}else if(strActivePiece.equals(strPlayer+"Sergeant")){
				g.drawImage(imgSergeant, intImgX, intImgY,null);
			}else{
				g.fillRect(intImgX,intImgY,70,70);
			}
		}
	}
	/** The paintBoard method allows us to paint the board */
	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
	}
	/** The paintStock method allows us to paint the stock/inventory that they they can grab from the right side */
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
	/** The paintPieces method allows us to paint pieces onto the board */
	public void paintPieces(Graphics g){
		g.setFont(theGOGFont);
		g.setColor(Color.WHITE);
		for(int intRow=0;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				if(strGOGArray[intRow][intColumn].equals(" ")){
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Flag")){
					g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Private")){
					g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Spy")){
					g.drawImage(imgSpies, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"5*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"4*General")){
					g.drawImage(imgGeneral4, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"3*General")){
					g.drawImage(imgGeneral3, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"2*General")){
					g.drawImage(imgGeneral2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"1*General")){
					g.drawImage(imgGeneral1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Colonel")){
					g.drawImage(imgColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"L.Colonel")){
					g.drawImage(imgLColonel, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Major")){
					g.drawImage(imgMajor, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Captain")){
					g.drawImage(imgCaptain, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"1st Lieutenant")){
					g.drawImage(imgLieutenant1, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"2nd Lieutenant")){
					g.drawImage(imgLieutenant2, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals(strPlayer+"Sergeant")){
					g.drawImage(imgSergeant, 80+70*intColumn,80+70*intRow, null);
				}else{
					g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null);
				}
			}
		}
	}
	/** The paintCharacters method allows us to paint the grid's numbers */
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
	/** The checkBlock method allows us to check whether the user is grabbing at nothing or a piece in a specific row and column. */
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
	/** The takeStock method checks whether we take inventory/stock from the right side*/
	public void takeStock(int intPosX, int intPosY){
		//if they click on the flag stock area, then...
		if(intPosX>720 && intPosX<790 && intPosY>200 && intPosY<270 && intFlag>0){
			this.strActivePiece=strPlayer+"Flag";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
			
		
		//if they click on the private stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>300 && intPosY<370 && intPrivate>0){
			this.strActivePiece=strPlayer+"Private";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		
		//if they click on the spy stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>400 && intPosY<470 && intSpy>0){
			this.strActivePiece=strPlayer+"Spy";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		
		}else if(intPosX>720 && intPosX<790 && intPosY>500 && intPosY<570 && intGeneral5>0){
			this.strActivePiece=strPlayer+"5*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>720 && intPosX<790 && intPosY>600 && intPosY<670 && intGeneral4>0){
			this.strActivePiece=strPlayer+"4*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>200 && intPosY<270 && intGeneral3>0){
			this.strActivePiece=strPlayer+"3*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>300 && intPosY<370 && intGeneral2>0){
			this.strActivePiece=strPlayer+"2*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>400 && intPosY<470 && intGeneral1>0){
			this.strActivePiece=strPlayer+"1*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>500 && intPosY<570 && intColonel>0){
			this.strActivePiece=strPlayer+"Colonel";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>920 && intPosX<990 && intPosY>600 && intPosY<670 && intLColonel>0){
			this.strActivePiece=strPlayer+"L.Colonel";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>200 && intPosY<270 && intMajor>0){
			this.strActivePiece=strPlayer+"Major";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>300 && intPosY<370 && intCaptain>0){
			this.strActivePiece=strPlayer+"Captain";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>400 && intPosY<470 && intLieutenant1>0){
			this.strActivePiece=strPlayer+"1st Lieutenant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>500 && intPosY<570 && intLieutenant2>0){
			this.strActivePiece=strPlayer+"2nd Lieutenant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>1120 && intPosX<1190 && intPosY>600 && intPosY<670 && intSergeant>0){
			this.strActivePiece=strPlayer+"Sergeant";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
	}
}
	/** The getNewPosition method allows us to determine which new row and column that the piece was dropped into */
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
	/** The placePiece method allows us to place down a piece if it was grabbed from the inventory and place it into an emptry row and column on the user's side of the screen */
	public void placePiece(){
		//If it's in the correct rows, then go for it
		if(intNewRow>4){
			strGOGArray[intNewRow][intNewClm]=strActivePiece;
			if(strActivePiece.equals(strPlayer+"Flag")){
				intFlag--;
			}else if(strActivePiece.equals(strPlayer+"Private")){
				intPrivate--;
			}else if(strActivePiece.equals(strPlayer+"Spy")){
				intSpy--;
			}else if(strActivePiece.equals(strPlayer+"5*General")){
				intGeneral5--;
			}else if(strActivePiece.equals(strPlayer+"4*General")){
				intGeneral4--; 
			}else if(strActivePiece.equals(strPlayer+"3*General")){
				intGeneral3--;
			}else if(strActivePiece.equals(strPlayer+"2*General")){
				intGeneral2--;
			}else if(strActivePiece.equals(strPlayer+"1*General")){
				intGeneral1--;
			}else if(strActivePiece.equals(strPlayer+"Colonel")){
				intColonel--;
			}else if(strActivePiece.equals(strPlayer+"L.Colonel")){
				intLColonel--;
			}else if(strActivePiece.equals(strPlayer+"Major")){
				intMajor--;
			}else if(strActivePiece.equals(strPlayer+"Captain")){
				intCaptain--;
			}else if(strActivePiece.equals(strPlayer+"1st Lieutenant")){
				intLieutenant1--;
			}else if(strActivePiece.equals(strPlayer+"2nd Lieutenant")){
				intLieutenant2--;
			}else if(strActivePiece.equals(strPlayer+"Sergeant")){
				intSergeant--;
			}
		}
	}
	/** the movePiece method allows us to move pieces on a board based on whether there is already a piece in the way yet */
	public void movePiece(){
		strGOGArray[intNewRow][intNewClm]=strActivePiece;
	}
	/** The loadFont method allows us to use a specific font in our folder*/
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
	/** the paintComponent method allows us to pain the board, pieces, and more onto the panel for user viewing */
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
	/** the GOGPrePanel method creates a panel that allows the user to prepare their pieces on */
	public GOGPrepPanel(String[][] strImageArray){
		super();
		
		this.loadImages(strImageArray);
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		thePlayer1Label.setBounds(750,160,200,50);
		this.add(thePlayer1Label);
		
		thePlayer2Label.setBounds(1040,160,200,50);
		this.add(thePlayer2Label);
		
		theWarningLabel.setBounds(800, 140,200,50);
		this.add(theWarningLabel);
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

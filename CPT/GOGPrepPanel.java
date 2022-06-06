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
public class GOGPrepPanel extends JPanel implements ActionListener {  
	public String strGOGArray[][] = new String[8][9];
	public BufferedImage imgBoard = null;
	public BufferedImage imgFlag = null;
	public BufferedImage imgPrivate = null;
	public BufferedImage imgSpies = null;
	public BufferedImage imgFogOfWar = null;
	public BufferedImage imgGeneral5 = null;
	public BufferedImage imgGeneral4 = null;
	public BufferedImage imgGeneral3 = null;
	public JButton theReadyButton = new JButton("Ready");
	
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
	
	int intFlag=1;
	int intPrivate=6;
	int intSpy=2;
	int intGenerals=1;
	
	//We need to distinguish 
	boolean blnMovingStock=false;
	boolean blnMovingPiece=false;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
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
					if(this.strGOGArray[intRow][intClm]!=null && !this.strGOGArray[intRow][intClm].equals("")){
						//Get rid of that block so we can replace it with an active block that we paint
						this.strActivePiece=this.strGOGArray[intRow][intClm];
						this.strGOGArray[intRow][intClm]=null;
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
		
		}else if(intPosX>720 && intPosX<790 && intPosY>500 && intPosY<570 && intGenerals>0){
			this.strActivePiece="5*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>720 && intPosX<790 && intPosY>600 && intPosY<670 && intGenerals>0){
			this.strActivePiece="4*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}else if(intPosX>820 && intPosX<890 && intPosY>200 && intPosY<270 && intGenerals>0){
			this.strActivePiece="3*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			this.blnMovingStock=true;
		}
	}
	
	public void placePiece(){
		strGOGArray[intNewRow][intNewClm]=strActivePiece;
		if(strActivePiece.equals("Flag")){
			intFlag--;
		}else if(strActivePiece.equals("Private")){
			intPrivate--;
		}else if(strActivePiece.equals("Spy")){
			intSpy--;
		}else if(strActivePiece.equals("5*General")){
			intGenerals--;
		}  
		intOGRow=0;
		intOGClm=0;
		intNewRow=0;
		intNewClm=0;
	}
	
	public void movePiece(){
		
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
	
	//We call this in our repaint method
	public void paintActiveBlock(Graphics g){
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
			}else{
				g.fillRect(intImgX,intImgY,70,70);
			}
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == theReadyButton) {
			// temp print statement to test button activation
		}
	}

	public void paintBoard(Graphics g){
		g.drawImage(imgBoard,80,80,null);
	}
	public void paintStock(Graphics g){
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
		g.drawRect(720,415,0,70);
		if(intSpy>0){
			g.drawImage(imgSpies,720,415,null);
		}
		
		//Draw number of 5 star generals
		g.drawString("x"+intGenerals+" 5 star general",720,500);
		g.drawRect(720,515,0,70);
		if(intGenerals>0){
			g.drawImage(imgGeneral5,720,515,null);
		}
		
		//Draw number of 4 star generals
		g.drawString("x"+intGenerals+" 4 star general",720,600);
		g.drawRect(720,615,0,70);
		if(intGenerals>0){
			g.drawImage(imgGeneral4,720,615,null);
		}
		
		//Draw number of 3 star generals
		g.drawString("x"+intGenerals+" 3 star general",820,200);
		g.drawRect(820,215,0,70);
		if(intGenerals>0){
			g.drawImage(imgGeneral3,820,215,null);
		}
	}
	public void paintPieces(Graphics g){
		g.setColor(Color.WHITE);
		//for the first 3 rows, just paint fog
		for(int intRow=0;intRow<3;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				g.drawImage(imgFogOfWar, 80+70*intColumn,80+70*intRow, null); 
			}
		}
		
		for(int intRow=5;intRow<8;intRow++){
			for(int intColumn=0;intColumn<9;intColumn++){
				if(strGOGArray[intRow][intColumn] == null){
				}else if(strGOGArray[intRow][intColumn].equals("Flag")){
					g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Private")){
					g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("Spy")){
					g.drawImage(imgSpies, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("5*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("4*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("3*General")){
					g.drawImage(imgGeneral5, 80+70*intColumn,80+70*intRow, null); 
				}
			}
		}
	}

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
					if(this.strGOGArray[intRow][intClm]!=null && !this.strGOGArray[intRow][intClm].equals("")){
						//Get rid of that block so we can replace it with an active block that we paint
						this.strActivePiece=this.strGOGArray[intRow][intClm];
						this.strGOGArray[intRow][intClm]=null;
						//We set the boolean active to true because we are now moving a block
						this.blnActive=true;
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
			
		
		//if they click on the private stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>300 && intPosY<370 && intPrivate>0){
			this.strActivePiece="Private";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			
		
		//if they click on the spy stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY>400 && intPosY<470 && intSpy>0){
			this.strActivePiece="Spy";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
		
		}else if(intPosX>720 && intPosX<790 && intPosY>500 && intPosY<570 && intGenerals>0){
			this.strActivePiece="5*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
		}else if(intPosX>720 && intPosX<790 && intPosY>600 && intPosY<670 && intGenerals>0){
			this.strActivePiece="4*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
		}else if(intPosX>820 && intPosX<890 && intPosY>200 && intPosY<270 && intGenerals>0){
			this.strActivePiece="3*General";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
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
	
	public void placePiece(String strPlayer){
		if(strActivePiece.substring(0,2).equals("P1") || strActivePiece.substring(0,2).equals("P2")){
			int intLength = strActivePiece.length();
			strActivePiece=strActivePiece.substring(2,intLength);
		}
		strGOGArray[intNewRow][intNewClm]=strPlayer+strActivePiece;
		if(strActivePiece.equals("Flag")){
			intFlag--;
		}else if(strActivePiece.equals("Private")){
			intPrivate--;
		}else if(strActivePiece.equals("Spy")){
			intSpy--;
		}else if(strActivePiece.equals("5*General")){
			intGenerals--;
		}else if(strActivePiece.equals("4*General")){
			intGenerals--; 
		}else if(strActivePiece.equals("3*General")){
			intGenerals--;
		}    
		intOGRow=0;
		intOGClm=0;
		intNewRow=0;
		intNewClm=0;
	}
	
	public void movePiece(String strPlayer){
		
	}
	
	//We call this in our repaint method
	public void paintActiveBlock(Graphics g){
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
			}else{
				g.fillRect(intImgX,intImgY,70,70);
			}
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == theReadyButton) {
			// temp print statement to test button activation
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
	public GOGPrepPanel(){
		super();
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
		
		try{
			imgSpies = ImageIO.read(new File("Spies.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgGeneral5 = ImageIO.read(new File("General5.jpg"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgGeneral4 = ImageIO.read(new File("General4.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		try{
			imgGeneral3 = ImageIO.read(new File("General3.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		theReadyButton.setBounds(720,90,280,50);
		theReadyButton.addActionListener(this);
		this.add(theReadyButton);
		
		thePlayer1Label.setBounds(750,160,200,50);
		this.add(thePlayer1Label);
		
		thePlayer2Label.setBounds(1040,160,200,50);
		this.add(thePlayer2Label);
		
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}








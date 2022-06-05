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
	public BufferedImage imgFogOfWar = null;
	public JButton theReadyButton = new JButton("Ready");
	
	//Timer stuff
	
	//Information about player names
	public JLabel thePlayer1Label = new JLabel("Player 1");
	public JLabel thePlayer2Label = new JLabel("Player 2");
	
	int intImgX;
	int intImgY;
	
	
	//This variable tells us that no piece movement is currently happening
	boolean blnActive=false;
	String strActivePiece;
	
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
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void paintStock(Graphics g){
		//Draw number of flags
		g.drawString("x"+intFlag+" Flags",720,200);
		//If we have at least 1 flag left, then we draw it
		if(intFlag>0){
			g.drawImage(imgFlag,900,180,null);
		}
		
		//Draw number of privates
		g.drawString("x"+intPrivate+" Privates",720,400);
		if(intPrivate>0){
			g.drawImage(imgPrivate,900,380,null);
		}
		
		//Draw number of flags
		g.drawString("x"+intSpy+" Spies",720,600);
		if(intSpy>0){
			g.drawImage(imgPrivate,900,580,null);
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
				}else if(strGOGArray[intRow][intColumn].equals("P1Flag") || strGOGArray[intRow][intColumn].equals("P2Flag")){
					g.drawImage(imgFlag, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("P1Private") || strGOGArray[intRow][intColumn].equals("P2Private")){
					g.drawImage(imgPrivate, 80+70*intColumn,80+70*intRow, null); 
				}else if(strGOGArray[intRow][intColumn].equals("P1Spy") || strGOGArray[intRow][intColumn].equals("P2Spy")){
					g.drawString("s",110+70*intColumn,120+70*intRow);
				}
			}
		}
	}
	
	//When they press on an area with a block, we do the following...
	public String checkBlock(int IntPosX, int IntPosY){
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
		//if they try to move nothing, then don't change a thing
		if(this.strActivePiece.equals(null) || this.strActivePiece==null || this.strActivePiece.equals(" ") || this.strActivePiece.equals("")){
			this.blnActive=false;
			this.strGOGArray[intOGRow][intOGClm]=this.strActivePiece;
		}
		return strActivePiece;
	}
	
	public void takeStock(int intPosX, int intPosY){
		//if they click on the flag stock area, then...
		if(intPosX>720 && intPosX<790 && intPosY<270 && intPosY>200){
			this.strActivePiece="Flag";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			
		
		//if they click on the private stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY<470 && intPosY>400){
			this.strActivePiece="Private";
			//We set the boolean active to true because we are now moving a block
			this.blnActive=true;
			
		
		//if they click on the spy stock area, then...
		}else if(intPosX>720 && intPosX<790 && intPosY<670 && intPosY>600){
			this.strActivePiece="Spy";
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
		paintStock(g);
		paintBoard(g);	
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








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
public class gamePanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {  
	public String strGOGArray[][] = new String[8][9];
	public BufferedImage imgBoard = null;
	public BufferedImage imgPrivate = null;
	public JButton theReadyButton = new JButton("Ready");
	
	//Timer stuff
	public JLabel theGameClockLabel=new JLabel("Time");
	
	//Text area stuff
	public JTextArea theTextArea;
	public JScrollPane theScroll;
	public JTextField theTextField=new JTextField("Enter Code here");
	
	int intX;
	int intY;

	
	//We create an array. Imagine this as a mini GOGBoard array
	boolean blnArray[][] = new boolean[8][9];
	
	//This variable tells us that no piece movement is currently happening
	boolean blnActive=false;
	
	//This will help us determine whether we can move to new row or not
	int intOGRow;
	int intOGClm;
	int intNewRow;
	int intNewClm;


///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
		public void paintBlocks(Graphics g){
		int intClm;
		int intRow;
		for(intRow=0;intRow<8;intRow++){
			for(intClm=0;intClm<9;intClm++){
				//if in a certain row and certain column, a block is there(true), then...
				if(blnArray[intRow][intClm]==true){
					//Paint the block at that location
					g.fillRect(intClm*200,intRow*200,200,200);
				}
			}
		}
	}
	
	//When they press on an area with a block, we do the following...
	public void ridBlock(int IntPosX, int IntPosY){
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
				if(IntPosX>intClm*200 && IntPosX<(intClm+1)*200 && IntPosY>intRow*200 && IntPosY<(intRow+1)*200){
					//If in that spot, there is already a block there(true), then...
					if(this.blnArray[intRow][intClm]==true){
						//Get rid of that block so we can replace it with an active block that we paint
						this.blnArray[intRow][intClm]=false;
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
	
	//When they release their mouse, we use this method
	public void placeInSlot(){
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
				if(intX+100>intClm*200 && intX+100<(intClm+1)*200 && intY+100>intRow*200 && intY+100<(intRow+1)*200){
					//we note this new row and column
					intNewRow=intRow;
					intNewClm=intClm;
					
					//If it's in the same row but moves right by 1, then...
					if(intOGRow==intNewRow && intOGClm+1==intNewClm){
						//place the block in the new row and column
						blnArray[intRow][intClm]=true;
					//If it's in the same row but moves left by 1, then...
					}else if(intOGRow==intNewRow && intOGClm-1==intNewClm){
						//place the block in the new row and column
						blnArray[intRow][intClm]=true;
					//If it's in the same column but moves down by 1, then...
					}else if(intOGRow+1==intNewRow && intOGClm==intNewClm){
						//place the block in the new row and column
						blnArray[intRow][intClm]=true;
					//If it's in the same column but moves up by 1, then...
					}else if(intOGRow-1==intNewRow && intOGClm==intNewClm){
						//place the block in the new row and column
						blnArray[intRow][intClm]=true;
					//If none of this is true, then move it back to the original position before
					//it was pressed
					}else{
						blnArray[intOGRow][intOGClm]=true;
					}
					//Either way, we use this method when they let go so once they let go, no more drag
					//that means it's inative(false)
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
			g.drawRect(intX,intY,70,70);
			
			//We draw white dot to let them know where they are
			g.setColor(Color.WHITE);
			g.fillRect(intX+95,intY+95,10,10);
			
			//Meh
			g.setColor(Color.BLACK);
		}
	}
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == theReadyButton) {
			// temp print statement to test button activation
			System.out.println("ready");
		}
	}
	public void mouseDragged(MouseEvent evt) {

		}
	public void mousePressed(MouseEvent evt){
	
	}
	
	public void mouseMoved(MouseEvent evt){
		
	}
	public void mouseExited(MouseEvent evt) {

	}
	public void mouseEntered(MouseEvent evt) {

	}
	public void mouseClicked(MouseEvent evt) {

	}
	public void mouseReleased(MouseEvent evt) {	

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
		paintBlocks(g);
		
		paintActiveBlock(g);
		g.drawImage(imgPrivate, 80,150, null); 
 
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
		
		try{
			imgPrivate = ImageIO.read(new File("private.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		theGameClockLabel.setBounds(860,50,200,50);
		this.add(theGameClockLabel);
		
		theTextArea = new JTextArea();
		theScroll = new JScrollPane(theTextArea);
		theScroll.setBounds(720,150,550,400);
		this.add(theScroll);
		
		
		theTextField.setBounds(720,550,550,100);
		this.add(theTextField);
		theReadyButton.setBounds(750,90,110,50);
		theReadyButton.addActionListener(this);
		this.add(theReadyButton);
	

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		

	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}








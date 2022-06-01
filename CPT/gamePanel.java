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
	public JButton theReadyButton = new JButton("Ready");
	
	//Timer stuff
	public JLabel theGameClockLabel=new JLabel("Time");
	
	//Text area stuff
	public JTextArea theTextArea;
	public JScrollPane theScroll;
	
	//Chess Pieces
	ImageIcon imagep1 = new ImageIcon("private.png");
	ImageIcon imagep2 = new ImageIcon("private.png");
	final int Width = imagep1.getIconWidth();
	final int Height = imagep1.getIconHeight();
	final int Widthp2 = imagep2.getIconWidth();
	final int Heightp2 = imagep2.getIconHeight();
	
	Point imageLocationp1;
	Point imageLocationp2;
	
	
	Point prevPt;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == theReadyButton) {
			// temp print statement to test button activation
			System.out.println("ready");
		}
	}
	public void mouseDragged(MouseEvent evt) {
				Point currentPt = evt.getPoint();
				
				imageLocationp1.translate(
					(int)(currentPt.getX() - prevPt.getX()),
					(int)(currentPt.getY() - prevPt.getY())
				
				);
				
				prevPt = currentPt;
				repaint();
				
				imageLocationp2.translate(
					(int)(currentPt.getX() - prevPt.getX()),
					(int)(currentPt.getY() - prevPt.getY())
				
				);
				
				prevPt = currentPt;
				repaint();
		}
	public void mousePressed(MouseEvent evt){
			prevPt = evt.getPoint();
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
		//System.out.println("released");	
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
		imagep1.paintIcon(this,g, (int)imageLocationp1.getX(), (int)imageLocationp1.getY()); 
		imagep2.paintIcon(this,g, (int)imageLocationp2.getX(), (int)imageLocationp2.getY()); 
	 
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
		
		theReadyButton.setBounds(750,90,110,50);
		theReadyButton.addActionListener(this);
		this.add(theReadyButton);
		
		imageLocationp1 = new Point(500,400);
		imageLocationp2 = new Point(100,400);
		

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}








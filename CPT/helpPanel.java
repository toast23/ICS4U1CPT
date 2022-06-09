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
public class helpPanel extends JPanel{  
	//Text area stuff
	Font theGOGFont = this.loadFont("BEARPAW.TTF", 30);
	public JTextArea theTextArea;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
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
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	/** The paintComponent method draws the instructions that can be given */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Draw boxes
		g.setFont(theGOGFont);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(100,50,1080,180);
		g.fillRect(100,250,1080,180);
		g.fillRect(100,450,1080,180);
		
		//Then place the words on top
		g.setColor(Color.BLACK);
		g.drawString("Set Up:",100,100);
		g.drawString("-Put your pieces in the first 3 rows of your side",100,140);
		g.drawString("-You cannot see your enemy’s piece ranks and they cannot see yours",100,180);
		
		g.drawString("Gameplay:",100,300);
		g.drawString("-Drag pieces up, down, left or right",100,340);
		g.drawString("-The spy outranks everyone except the private and other spies",100,380);
		
		g.drawString("Win Condition:",100,500);
		g.drawString("Take down the Enemy Flag",100,540);
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	/** The helpPanel constructor method creates the help panel! */
	public helpPanel(){
		super();
		this.setLayout(null);
		this.setBackground(Color.WHITE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}




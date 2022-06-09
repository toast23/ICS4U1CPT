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
public class ranksPanel extends JPanel{  
	//Properties
	Font theGOGFont = null;



///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
		g.drawString("Pieces                            Ranks",100,40);
		g.drawString("Flag                                  1",100,85);
		g.drawString("Spy                                   2",100,130);
		g.drawString("Private                             3",100,175);
		g.drawString("Seargent                          4",100,220);
		g.drawString("2nd_Lieutenant               5",100,265);
		g.drawString("1st_Lieutenant                 6",100,305);
		g.drawString("Captain                            7",100,355);
		g.drawString("Major                               8",100,400);
		g.drawString("Lieutenant_Colonel         9",100,455);
		g.drawString("Colonel                            10",100,490);
		g.drawString("Brigadier_General          11",100,535);
		g.drawString("Major_General               12",100,580);
		g.drawString("Lieutenant General          13",100,625);
		g.drawString("General                            14",100,670);
		g.drawString("General_of_the_Army   15",100,710);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	public ranksPanel(){
		super();
		this.setLayout(null);
		theGOGFont = this.loadFont("BEARPAW.TTF", 50);
		this.setBackground(Color.WHITE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

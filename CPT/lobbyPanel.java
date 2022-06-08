//We import necessary libraries for functionality
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
public class lobbyPanel extends JPanel {  
	//Properties
	
	//We want cool looking words
	Font theGOGFont = null;
	
	//This set of JComponents is for labels and ipAddress
	JLabel theNameLabel = new JLabel("Enter Name Below");
	TextField theNameTextField = new TextField("");
	JLabel thePortLabel = new JLabel("Enter Port Number Below");
	TextField thePortTextField = new TextField("");
	int intPortNumber;
	JLabel theIPAddressLabel = new JLabel("Enter IP Address Below");
	TextField theIPAddressTextField = new TextField("127.0.0.1");
	String strIPAddress = "";
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
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
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////

	//Constructor
	public lobbyPanel(){
		this.setLayout(null);
		//With the bigger font, we'll make the labels first
		theGOGFont = this.loadFont("BEARPAW.TTF",80);
		theNameLabel.setFont(theGOGFont);
		theNameLabel.setBounds(400,150,600,55);
		this.add(theNameLabel);
		thePortLabel.setFont(theGOGFont);
		thePortLabel.setBounds(300,270,800,55);
		this.add(thePortLabel);	
		theIPAddressLabel.setFont(theGOGFont);
		theIPAddressLabel.setBounds(300,400,800,55);
		this.add(theIPAddressLabel);
		
		//With the smaller font, we'll make the text fields	
		theGOGFont = this.loadFont("BEARPAW.TTF",40);
		theNameTextField.setFont(theGOGFont);
		theNameTextField.setBounds(40,200,1200,50);
		this.add(theNameTextField);
		thePortTextField.setFont(theGOGFont);
		thePortTextField.setBounds(40,325,1200,50);
		this.add(thePortTextField);
		theIPAddressTextField.setFont(theGOGFont);
		theIPAddressTextField.setBounds(40,470,1200,50);
		this.add(theIPAddressTextField);
	}
} 

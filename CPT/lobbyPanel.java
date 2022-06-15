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
	
	/**The font used in the panel*/
	Font theGOGFont = null;
	
	/**This set of JComponents is for labels and ipAddress*/
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
	/** The loadFont method allows us to load a font from our folder into the an object in the font class */
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
	/** The changeFont method changes the font of labels and characters in text fields */
	public void changeFont(){
		theGOGFont = this.loadFont("BEARPAW.TTF",80);
		
		theNameLabel.setForeground(Color.RED);
		theNameLabel.setFont(theGOGFont);
		
		thePortLabel.setForeground(Color.RED);
		thePortLabel.setFont(theGOGFont);
		
		theIPAddressLabel.setForeground(Color.RED);
		theIPAddressLabel.setFont(theGOGFont);
		
		theGOGFont = this.loadFont("BEARPAW.TTF",40);
		theNameTextField.setForeground(Color.RED);
		theNameTextField.setFont(theGOGFont);
		thePortTextField.setForeground(Color.RED);
		thePortTextField.setFont(theGOGFont);
		theIPAddressTextField.setForeground(Color.RED);
		theIPAddressTextField.setFont(theGOGFont);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	/** The lobbyPanel constructor method creates the lobby panel with the labels and textfields in them! */
	public lobbyPanel(){
		this.setLayout(null);
		//Name (Label)
		theNameLabel.setBounds(400,130,600,55);
		this.add(theNameLabel);
		//Port Number (Label)
		thePortLabel.setBounds(300,270,800,55);
		this.add(thePortLabel);	
		//IP Address (Label)
		theIPAddressLabel.setBounds(300,400,800,55);
		this.add(theIPAddressLabel);
		
		//Input your name
		theNameTextField.setBounds(40,200,1200,50);
		this.add(theNameTextField);
		//Input the port number
		thePortTextField.setBounds(40,325,1200,50);
		this.add(thePortTextField);
		//Input your IP Address
		theIPAddressTextField.setBounds(40,470,1200,50);
		this.add(theIPAddressTextField);
		//Change the font of the all the things in the lobby
		this.changeFont();
	}
} 

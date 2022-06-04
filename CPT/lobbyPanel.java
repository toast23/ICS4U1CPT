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
	//Constructor
	public lobbyPanel(){
		this.setLayout(null);
		
		theNameLabel.setBounds(540,150,400,40);
		this.add(theNameLabel);
		
		theNameTextField.setBounds(40,200,1200,50);
		this.add(theNameTextField);
		
		thePortLabel.setBounds(600,250,200,40);
		this.add(thePortLabel);
		
		thePortTextField.setBounds(40,300,1200,50);
		this.add(thePortTextField);
		
		theIPAddressLabel.setBounds(600,350,200,40);
		this.add(theIPAddressLabel);
		
		theIPAddressTextField.setBounds(40,400,1200,50);
		this.add(theIPAddressTextField);
	}
} 

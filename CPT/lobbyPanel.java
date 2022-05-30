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
public class lobbyPanel extends JPanel implements ActionListener{  
	//Properties
	//This set of JComponents is for labels and ipAddress
	JLabel theNameLabel = new JLabel("Enter Name Below");
	TextField theNameTextField = new TextField("");
	String strName = "Seamus";
	JLabel thePortLabel = new JLabel("Enter Port Number Below");
	TextField thePortTextField = new TextField("");
	int intPortNumber = 0;
	JLabel theIPAddressLabel = new JLabel("Enter IP Address Below");
	TextField theIPAddressTextField = new TextField("");
	String strIPAddress = "127.0.0.0";
	
	//These 2 buttons are for who wants to be the server and who wants to be the client
	JButton theServerButton = new JButton("Server");
	JButton theClientButton = new JButton("Client");

	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==theServerButton || evt.getSource()==theClientButton){
			//Get the name of the player
			strName = theNameTextField.getText();
			
			//Get the port number
			try{
				intPortNumber = Integer.parseInt(thePortTextField.getText());
			}catch(NumberFormatException e){
				thePortTextField.setText("Invalid Number");
			}
			
			//Get the IP Address in string form
			strIPAddress = theIPAddressTextField.getText();
		}
		System.out.println(strName);
		System.out.println(""+intPortNumber);
		System.out.println(strIPAddress);
	}
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
		
		theServerButton.addActionListener(this);
		theServerButton.setBounds(10,10,200,50);
		this.add(theServerButton);
		
		theClientButton.addActionListener(this);
		theClientButton.setBounds(1070,10,200,50);
		this.add(theClientButton);
	}
} 

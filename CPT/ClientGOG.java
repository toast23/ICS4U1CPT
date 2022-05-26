import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientGOG implements ActionListener{
	//Properties
	JFrame theFrame = new JFrame("Client Game of Generals");
	JPanel thePanel = new JPanel();
	JButton theEnterButton = new JButton("Enter");
	JTextField theField = new JTextField("");
	SuperSocketMaster ssm;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theEnterButton){
			ssm.sendText(theField.getText());
		}
	}
	
	//Constructor
	public ClientGOG(){
		theFrame.setLayout(null);
		thePanel.setLayout(null);
		thePanel.setPreferredSize(new Dimension(400, 300));
		
		theEnterButton.addActionListener(this);
		theEnterButton.setBounds(10,50,180,100);
		thePanel.add(theEnterButton);
		theField.setBounds(210,50,180,100);
		thePanel.add(theField);
		
		theFrame.setContentPane(thePanel);
		theFrame.pack();
		theFrame.setVisible(true);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		
		ssm = new SuperSocketMaster("127.0.0.1",9001,this); //Client Constructor
		ssm.connect();
		System.out.println(ssm.connect()); //Attempt to connect
	}
	
	//Main Method
	public static void main(String[] args){
		new ClientGOG();
	}
	
}

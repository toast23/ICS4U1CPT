import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics.*;

public class GOGClient implements ActionListener, MouseMotionListener, MouseListener {
	//Properties
	JFrame theFrame = new JFrame("Game of the Generals Client");
	JPanel thePanel = new JPanel();
	JButton theEnterButton = new JButton("Enter");
	JTextField theField = new JTextField("");
	SuperSocketMaster ssm;
	
	int intMouseX;
	int intMouseY;
	
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theEnterButton){
			ssm.sendText(theField.getText());
		}
	}
	public void mouseMoved(MouseEvent evt) {

	}
	public void mouseDragged(MouseEvent evt) {
	
	}
	public void mouseExited(MouseEvent evt) {

	}
	public void mouseEntered(MouseEvent evt) {

	}
	public void mouseReleased(MouseEvent evt) {	
		System.out.println("released");	
	}
	public void mouseClicked(MouseEvent evt) {

	}
	public void mousePressed(MouseEvent evt) {	
		
	}
	
	//Constructor
	public GOGClient(){
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
		theFrame.addMouseMotionListener(this);
		theFrame.addMouseListener(this);
		theFrame.setVisible(true);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		
		ssm = new SuperSocketMaster("127.0.0.1",9001,this); //Client Constructor
		ssm.connect();
	}
	
	//Main Method
	public static void main(String[] args){
		new GOGClient();
	}
	
}

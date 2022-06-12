//We will import necessary libraries for functionality
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GOGFatePanel extends JPanel{
	//Properties
	public String strFate = " ";
	public BufferedImage imgWin = null;
	public BufferedImage imgLose = null;
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(strFate.equals("victory")){
			g.drawImage(imgWin,0,0,null);
		}else if(strFate.equals("defeat")){
			g.drawImage(imgLose,0,0,null);
		}
	}
	
	//Constructor
	public GOGFatePanel(){
		try{
			imgWin = ImageIO.read(new File("Win background.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLose = ImageIO.read(new File("Defeat.png"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
}

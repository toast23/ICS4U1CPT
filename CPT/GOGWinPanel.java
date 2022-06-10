//We will import necessary libraries for functionality
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GOGWinPanel extends JPanel{
	//Properties
	public BufferedImage imgWin = null;
	public BufferedImage imgLose = null;
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgWin,0,0,null);
	}
	
	//Constructor
	public GOGWinPanel(){
		try{
			imgWin = ImageIO.read(this.getClass().getResourceAsStream("Win background.jpg"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLose = ImageIO.read(this.getClass().getResourceAsStream("Defeat.jpg"));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
}

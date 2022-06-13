//We import necessary libraries for animation
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//Our class will be a child class of JPanel
public class IntroAnimationPanel extends JPanel{
	//Properties
	//This variable is for the form strings drawn on the screen will take
	Font normalFont  = new Font("SANS_SERIF",Font.BOLD,80);
	//the Final Font will be initiated later
	Font finalFont = null;
	//These variables are for the movement of the images on the x axis(horizontal motion)
	int intFrameCount=-300;
	int intPhoenixX=intFrameCount;
	int intFireBallX=1200;
	int intNebulaBallX=1200;
	//Later on, we will be animating certain pictures(movement)
	BufferedImage imgPhoenix = null;
	BufferedImage imgFireball = null;
	BufferedImage imgNebulaball = null;
	int intRed=255;
	int intGreen=255;
	int intBlue=255;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
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
	
	//This method draws the phoenix streaking across the screen to form letters
	public void drawFirstPhase(Graphics g){
		//This method will be executed for the first 1100 frames.
		if(intFrameCount<1700){
			//Of course, we set the font of the strings
			g.setFont(normalFont);
			//We draw something offscreen so the program doesn't pause halfway through
			g.drawString("a",-300,340);
			//At 70 pixels on the x axis, draw the m
			if(intPhoenixX>20){
				g.drawString("J",50,340);
			}
			//At 150 pixels on the x axis, draw the e
			if(intPhoenixX>50){
				g.drawString("O",90,340);
			}
			//At 210 pixels on the x axis, draw the h.
			if(intPhoenixX>80){
				g.drawString("H",160,340);
			}
			//At 360 pixels on the x axis, draw the i
			if(intPhoenixX>150){
				g.drawString("N",230,340);
			}
			//At 390 pixels on the x axis, draw the n
			if(intPhoenixX>280){
				g.drawString("T",360,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>340){
				g.drawString("O",420,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>470){
				g.drawString("N",490,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>530){
				g.drawString("Y",560,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>660){
				g.drawString("N",700,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>730){
				g.drawString("I",770,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>760){
				g.drawString("C",800,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>820){
				g.drawString("K",860,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>930){
				g.drawString("I",970,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>940){
				g.drawString("N",1000,340);
			}
			//At 450 pixels on the x axis, draw the c.
			if(intPhoenixX>1020){
				g.drawString("C.",1060,340);
			}
				
			
			//Draw the phoenix here
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			
			//Move the picture 10 pixels to the right per frame
			intFrameCount+=20;
			intPhoenixX=intFrameCount;
		}
	}

	//This method draws a pause of nothing happening besides the phoenix 
	public void drawPhoenixLookingBack(Graphics g){
		//This method will be executed from the 1100th frame to before the 1500th frame.
		if(intFrameCount>=1700 && intFrameCount < 2200){
			//We draw the letters with black colour
			g.setFont(normalFont);
			g.drawString("J",50,340);
			g.drawString("O",90,340);
			g.drawString("H",160,340);
			g.drawString("N",230,340);
			
			g.drawString("T",360,340);
			g.drawString("O",420,340);
			g.drawString("N",490,340);
			g.drawString("Y",560,340);
			
			g.drawString("N",700,340);
			g.drawString("I",770,340);
			g.drawString("C",800,340);
			g.drawString("K",860,340);
			
			g.drawString("I",970,340);
			g.drawString("N",1000,340);
			g.drawString("C.",1060,340);
			
			intPhoenixX-=20;
			intFrameCount+=20;
			//We draw the phoenix looking back
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
		}
	}
	
	//This pause mthod represents the phoenix seeing something wrong
	public void drawFirstPause(Graphics g){
		//This method will be executed from the 1500th frame to before the 2200th frame.
		if(intFrameCount>=2200 && intFrameCount<2700){
			//We draw the letters with black colour
			g.setFont(normalFont);
			g.drawString("J",50,340);
			g.drawString("O",90,340);
			g.drawString("H",160,340);
			g.drawString("N",230,340);
			
			g.drawString("T",360,340);
			g.drawString("O",420,340);
			g.drawString("N",490,340);
			g.drawString("Y",560,340);
			
			g.drawString("N",700,340);
			g.drawString("I",770,340);
			g.drawString("C",800,340);
			g.drawString("K",860,340);
			
			g.drawString("I",970,340);
			g.drawString("N",1000,340);
			g.drawString("C.",1060,340);
			//We draw the phoenix looking back
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			intFrameCount+=20;
		}
	}
	
	//This method has the phoenix shoot a fireball and convert the normal font of the letters to a special font
	public void drawLetterTransformation(Graphics g){
		//This method will be executed from the 2200th frame to before the 3200th frame
		if(intFrameCount>=2700 && intFrameCount<4200){
			//We initialize the font and make sure there is not pauses due to loading of fonts
			g.setFont(finalFont);
			g.setColor(Color.BLACK);
			g.drawString("a",-300,-300);
			
			//Al these else if statements basically do the following:
			//If the fireball picture location(based on the x axis) covers the letter, then switch the font
			if(intFireBallX<20){
				g.setFont(finalFont);
				g.drawString("J",50,340);
			}else{
				g.setFont(normalFont);
				g.drawString("J",50,340);
			}
			
			//At 150 pixels on the x axis, draw the e
			if(intFireBallX<50){
				g.setFont(finalFont);
				g.drawString("O",90,340);
			}else{
				g.setFont(normalFont);
				g.drawString("O",90,340);
			}
			
			//At 210 pixels on the x axis, draw the h.
			if(intFireBallX<80){
				g.setFont(finalFont);
				g.drawString("H",160,340);
			}else{
				g.setFont(normalFont);
				g.drawString("H",160,340);	
			}
			
			//At 360 pixels on the x axis, draw the i
			if(intFireBallX<150){
				g.setFont(finalFont);
				g.drawString("N",230,340);
			}else{
				g.setFont(normalFont);
				g.drawString("N",230,340);
			}
			
			//At 390 pixels on the x axis, draw the n
			if(intFireBallX<280){
				g.setFont(finalFont);
				g.drawString("T",360,340);
			}else{
				g.setFont(normalFont);
				g.drawString("T",360,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<340){
				g.setFont(finalFont);
				g.drawString("O",420,340);
			}else{
				g.setFont(normalFont);
				g.drawString("O",420,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<470){
				g.setFont(finalFont);
				g.drawString("N",490,340);
			}else{
				g.setFont(normalFont);
				g.drawString("N",490,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<530){
				g.setFont(finalFont);
				g.drawString("Y",560,340);
			}else{
				g.setFont(normalFont);
				g.drawString("Y",560,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<660){
				g.setFont(finalFont);
				g.drawString("N",700,340);
			}else{
				g.setFont(normalFont);
				g.drawString("N",700,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<730){
				g.setFont(finalFont);
				g.drawString("I",770,340);
			}else{
				g.setFont(normalFont);
				g.drawString("I",770,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<760){
				g.setFont(finalFont);
				g.drawString("C",800,340);
			}else{
				g.setFont(normalFont);
				g.drawString("C",800,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<820){
				g.setFont(finalFont);
				g.drawString("K",860,340);
			}else{
				g.setFont(normalFont);
				g.drawString("K",860,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<930){
				g.setFont(finalFont);
				g.drawString("I",970,340);
			}else{
				g.setFont(normalFont);
				g.drawString("I",970,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<940){
				g.setFont(finalFont);
				g.drawString("N",1000,340);
			}else{
				g.setFont(normalFont);
				g.drawString("N",1000,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intFireBallX<1020){
				g.setFont(finalFont);
				g.drawString("C.",1060,340);
			}else{
				g.setFont(normalFont);
				g.drawString("C.",1060,340);
			}
			
			//Here, we draw the phoenix and fireball
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			g.drawImage(imgFireball,intFireBallX,235,null);
			
			//Here, we put the movement of the fireball
			intFireBallX-=20;
			intFrameCount+=20;
		}
	}
	
	//This method draws the pause which is with the final font and only black
	public void drawSecondPause(Graphics g){
		//This method will be executed from the 3200th frame to below the 3300th frame
		if(intFrameCount>=4200 && intFrameCount<4300){
			g.setFont(finalFont);
			g.setColor(Color.BLACK);
			g.drawString("J",50,340);
			g.drawString("O",90,340);
			g.drawString("H",160,340);
			g.drawString("N",230,340);
			
			g.drawString("T",360,340);
			g.drawString("O",420,340);
			g.drawString("N",490,340);
			g.drawString("Y",560,340);
			
			g.drawString("N",700,340);
			g.drawString("I",770,340);
			g.drawString("C",800,340);
			g.drawString("K",860,340);
			
			g.drawString("I",970,340);
			g.drawString("N",1000,340);
			g.drawString("C.",1060,340);
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			intFrameCount+=20;
		}
	}
	
	//This method shoots the colorful nebula ball to replace the letters with color.
	public void drawColorTransformation(Graphics g){
		//This method will be executed from the 330th frame to below the 4500th frame.
		if(intFrameCount>=4300 && intFrameCount<5800){
			//We initialize the font and make sure there is not pauses due to loading of fonts
			g.setFont(finalFont);
			g.drawString("a",-300,-300);
			
			//If the nebula ball picture location(based on the x axis) covers the letter, then switch the colour
			if(intNebulaBallX>20){
				g.setColor(Color.BLACK);
				g.drawString("J",50,340);
			}else{
				g.setColor(Color.RED);
				g.drawString("J",50,340);
			}
			
			//At 150 pixels on the x axis, draw the e
			if(intNebulaBallX>50){
				g.setColor(Color.BLACK);
				g.drawString("O",90,340);
			}else{
				g.setColor(Color.ORANGE);
				g.drawString("O",90,340);
			}
			
			//At 210 pixels on the x axis, draw the h.
			if(intNebulaBallX>80){
				g.setColor(Color.BLACK);
				g.drawString("H",160,340);
			}else{
				g.setColor(Color.YELLOW);
				g.drawString("H",160,340);	
			}
			
			//At 360 pixels on the x axis, draw the i
			if(intNebulaBallX>150){
				g.setColor(Color.BLACK);
				g.drawString("N",230,340);
			}else{
				g.setColor(Color.GREEN);
				g.drawString("N",230,340);
			}
			
			//At 390 pixels on the x axis, draw the n
			if(intNebulaBallX>280){
				g.setColor(Color.BLACK);
				g.drawString("T",360,340);
			}else{
				g.setColor(Color.CYAN);
				g.drawString("T",360,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>340){
				g.setColor(Color.BLACK);
				g.drawString("O",420,340);
			}else{
				g.setColor(Color.BLUE);
				g.drawString("O",420,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>470){
				g.setColor(Color.BLACK);
				g.drawString("N",490,340);
			}else{
				g.setColor(Color.MAGENTA);
				g.drawString("N",490,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>530){
				g.setColor(Color.BLACK);
				g.drawString("Y",560,340);
			}else{
				g.setColor(Color.RED);
				g.drawString("Y",560,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>660){
				g.setColor(Color.BLACK);
				g.drawString("N",700,340);
			}else{
				g.setColor(Color.ORANGE);
				g.drawString("N",700,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>730){
				g.setColor(Color.BLACK);
				g.drawString("I",770,340);
			}else{
				g.setColor(Color.YELLOW);
				g.drawString("I",770,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>760){
				g.setColor(Color.BLACK);
				g.drawString("C",800,340);
			}else{
				g.setColor(Color.GREEN);
				g.drawString("C",800,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>820){
				g.setColor(Color.BLACK);
				g.drawString("K",860,340);
			}else{
				g.setColor(Color.CYAN);
				g.drawString("K",860,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>930){
				g.setColor(Color.BLACK);
				g.drawString("I",970,340);
			}else{
				g.setColor(Color.BLUE);
				g.drawString("I",970,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>940){
				g.setColor(Color.BLACK);
				g.drawString("N",1000,340);
			}else{
				g.setColor(Color.MAGENTA);
				g.drawString("N",1000,340);
			}
			
			//At 450 pixels on the x axis, draw the c.
			if(intNebulaBallX>1020){
				g.setColor(Color.BLACK);
				g.drawString("C.",1060,340);
			}else{
				g.setColor(Color.RED);
				g.drawString("C.",1060,340);
			}
			
			//Here, we draw the phoenix and fireball
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			g.drawImage(imgNebulaball,intNebulaBallX,235,null);
			
			//Here, we put the movement of the nebula ball and frame count increases
			intNebulaBallX-=20;
			intFrameCount+=20;
		}
	}
	
	//This method draws the final pause so the user can appreciate the way the final picture looks
	public void drawFinalPause(Graphics g){
		//This method will be executed from the 4500th frame to below the 4700th frame
		if(intFrameCount>=5800 && intFrameCount<6000){
			//We draw all the colour letters with bearpaw font
			g.setFont(finalFont);
			g.drawString("a",-300,-300);
			
			g.setColor(Color.RED);
			g.drawString("J",50,340);
			g.setColor(Color.ORANGE);
			g.drawString("O",90,340);
			g.setColor(Color.YELLOW);
			g.drawString("H",160,340);
			g.setColor(Color.GREEN);
			g.drawString("N",230,340);
			
			g.setColor(Color.CYAN);
			g.drawString("T",360,340);
			g.setColor(Color.BLUE);
			g.drawString("O",420,340);
			g.setColor(Color.MAGENTA);
			g.drawString("N",490,340);
			g.setColor(Color.RED);
			g.drawString("Y",560,340);
			
			g.setColor(Color.ORANGE);
			g.drawString("N",700,340);
			g.setColor(Color.YELLOW);
			g.drawString("I",770,340);
			g.setColor(Color.GREEN);
			g.drawString("C",800,340);
			g.setColor(Color.CYAN);
			g.drawString("K",860,340);
			
			g.setColor(Color.BLUE);
			g.drawString("I",970,340);
			g.setColor(Color.MAGENTA);
			g.drawString("N",1000,340);
			g.setColor(Color.RED);
			g.drawString("C.",1060,340);
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			intFrameCount+=20;
		}
	}
	
	//This method makes the screen fade to black
	public void fadeToBlack(Graphics g){
		//This method will be executed from the 4500th frame to below the 4700th frame
		if(intFrameCount>=6000 && intFrameCount<6300){
			g.setFont(finalFont);
			g.setColor(Color.RED);
			g.drawString("J",50,340);
			g.setColor(Color.ORANGE);
			g.drawString("O",90,340);
			g.setColor(Color.YELLOW);
			g.drawString("H",160,340);
			g.setColor(Color.GREEN);
			g.drawString("N",230,340);
			
			g.setColor(Color.CYAN);
			g.drawString("T",360,340);
			g.setColor(Color.BLUE);
			g.drawString("O",420,340);
			g.setColor(Color.MAGENTA);
			g.drawString("N",490,340);
			g.setColor(Color.RED);
			g.drawString("Y",560,340);
			
			g.setColor(Color.ORANGE);
			g.drawString("N",700,340);
			g.setColor(Color.YELLOW);
			g.drawString("I",770,340);
			g.setColor(Color.GREEN);
			g.drawString("C",800,340);
			g.setColor(Color.CYAN);
			g.drawString("K",860,340);
			
			g.setColor(Color.BLUE);
			g.drawString("I",970,340);
			g.setColor(Color.MAGENTA);
			g.drawString("N",1000,340);
			g.setColor(Color.RED);
			g.drawString("C.",1060,340);
			
			g.drawImage(imgPhoenix,intPhoenixX,110,null);
			
			intRed-=1;
			intGreen-=1;
			intBlue-=1;
			Color fadingColor = new Color(intRed,intGreen,intBlue);
			g.setColor(fadingColor);
			g.fillRect(0,0,1280,720);
			intFrameCount+=20;
		}
	}
	
	//Our repaint method will paint the logo
	public void paintComponent(Graphics g){
		//This draws the initial picture of the phoenix making letters by moving right
		this.drawFirstPhase(g);
		
		//After that, the phoenix needs to look back so we use a picture of the phoenix that is flipped horizontally
		//Do this only at the 1100th frame though
		if(intFrameCount==1700){
			try{
				imgPhoenix = ImageIO.read(new File("DoubleBackPhoenix.png"));
			}catch(IOException e){
				System.out.println("Image not found");
			}
		}
		
		//This method tells us to make the phoenix double back
		this.drawPhoenixLookingBack(g);		
		
		//This method tells the panel to paint a pic of the phoenix just standing there
		this.drawFirstPause(g);	
		
		//This method tells the panel to make the phoenix shoot a fireball that transforms the font
		this.drawLetterTransformation(g);		
		
		//This method tells the panel to draw a short pause in between the fireball and nebula ball
		this.drawSecondPause(g);		
		
		//This method tells the panel to make the phoenix shoot a nebula ball that transforms the color
		this.drawColorTransformation(g);
		
		//This method tells the pnale to pause after all the transformations are over
		this.drawFinalPause(g);
		
		this.fadeToBlack(g);
		
	}
	
	//Constructor
	IntroAnimationPanel(){
		//The class will inherit all the properties of the JPanel component
		super();
		//We load the final font since it's not a default font
		finalFont = this.loadFont("BEARPAW.TTF",160);
		
		//These classes basically tell the program to load the pictures from the folder into the buffered image variables
		try{
			imgPhoenix = ImageIO.read(new File("Phoenix.png"));
		}catch(IOException e){
			System.out.println("Image not found");
		}
		
		try{
			imgFireball = ImageIO.read(new File("fireball.png"));
		}catch(IOException e){
			System.out.println("Image not found");
		}
		try{
			imgNebulaball = ImageIO.read(new File("NebulaBall.png"));
		}catch(IOException e){
			System.out.println("Image not found");
		}
	}
	
}

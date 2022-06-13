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
	Font theGOGFont = this.loadFont("BEARPAW.TTF",50);
	public BufferedImage imgFlag = null;
	public BufferedImage imgPrivate = null;
	public BufferedImage imgSpies = null;
	public BufferedImage imgFogOfWar = null;
	public BufferedImage imgGeneral5 = null;
	public BufferedImage imgGeneral4 = null;
	public BufferedImage imgGeneral3 = null;
	public BufferedImage imgGeneral2 = null;
	public BufferedImage imgGeneral1 = null;
	public BufferedImage imgColonel = null;
	public BufferedImage imgLColonel = null;
	public BufferedImage imgMajor = null;
	public BufferedImage imgCaptain = null;
	public BufferedImage imgLieutenant1 = null;
	public BufferedImage imgLieutenant2 = null;
	public BufferedImage imgSergeant = null;
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Method
	public void loadImages(String[][] strImageArray){
		try{
			imgFlag = ImageIO.read(new File(strImageArray[0][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgPrivate = ImageIO.read(new File(strImageArray[1][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSpies = ImageIO.read(new File(strImageArray[2][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgSergeant = ImageIO.read(new File(strImageArray[3][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant2 = ImageIO.read(new File(strImageArray[4][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLieutenant1 = ImageIO.read(new File(strImageArray[5][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgCaptain = ImageIO.read(new File(strImageArray[6][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgMajor = ImageIO.read(new File(strImageArray[7][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgLColonel = ImageIO.read(new File(strImageArray[8][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgColonel = ImageIO.read(new File(strImageArray[9][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral1 =ImageIO.read(new File(strImageArray[10][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral2 = ImageIO.read(new File(strImageArray[11][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral3 = ImageIO.read(new File(strImageArray[12][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral4 = ImageIO.read(new File(strImageArray[13][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgGeneral5 = ImageIO.read(new File(strImageArray[14][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
		try{
			imgFogOfWar = ImageIO.read(new File(strImageArray[15][2]));
		}catch(IOException e){
			System.out.println("Error finding image");
		}
	}
	
	/** The loadFont method allows us to use a specific font in our folder*/
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
	/** This method will paint the first half of the pieces */
	public void drawFirstHalf(Graphics g){
		g.drawString("Pieces                            Ranks",50,50);
		
		g.drawString("Flag                                  1",50,130);
		g.drawImage(imgFlag, 500, 100,null);
		g.drawString("Spy                                   2",50,220);
		g.drawImage(imgSpies, 500, 190,null);
		g.drawString("Private                             3",50,305);
		g.drawImage(imgPrivate, 500, 275,null);
		g.drawString("Sergeant                          4",50,400);
		g.drawImage(imgSergeant, 500, 370,null);
		g.drawString("2nd_Lieutenant               5",50,490);
		g.drawImage(imgLieutenant2, 500, 460,null);
		g.drawString("1st_Lieutenant                 6",50,580);
		g.drawImage(imgLieutenant1, 500, 550,null);
		g.drawString("Captain                            7",50,670);
		g.drawImage(imgCaptain, 500, 640,null);
	}
	
	/** This method will paint the second half of the pieces */
	public void drawSecondHalf(Graphics g){
		g.drawString("Pieces                            Ranks",690,85);
		g.drawString("Major                               8",690,130);
		g.drawImage(imgMajor,1200,100,null);
		g.drawString("Lieutenant_Colonel         9",690,205);
		g.drawImage(imgColonel,1200,175,null);
		g.drawString("Colonel                            10",690,280);
		g.drawImage(imgColonel,1200,250,null);
		g.drawString("Brigadier_General          11",690,355);
		g.drawImage(imgGeneral1,1200,325,null);
		g.drawString("Major_General               12",690,430);
		g.drawImage(imgGeneral2,1200,400,null);
		g.drawString("Lieutenant General          13",690,505);
		g.drawImage(imgGeneral3,1200,475,null);
		g.drawString("General                            14",690,580);
		g.drawImage(imgGeneral4,1200,550,null);
		g.drawString("General_of_the_Army   15",690,655);
		g.drawImage(imgGeneral5,1200,625,null);
	}
	
	/** This method allows us to draw the piece names, ranks, and images */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(theGOGFont);
		g.setColor(Color.BLUE);
		this.drawFirstHalf(g);
		this.drawSecondHalf(g);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	/** This method constructs the panel that will allow us to see the ranks*/
	public ranksPanel(String[][] strImageArray){
		super();
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.loadImages(strImageArray);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
}

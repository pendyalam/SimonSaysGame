import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.RadialGradientPaint;

import javax.swing.JComponent;

public class GameSquare extends JComponent{
	private int x;
	private int y;

	private Position pos = null;
	private Color squareColor1;
	private Color squareColor2;

	
	private int index = 0;
	protected boolean isSelected = false;
	protected boolean inPattern = false;

	public GameSquare (int row, int col, Color squareColor1, Color squareColor2)
	{
		super ();

		pos = new Position (row, col); // Instantiates a Position object
		this.squareColor1 = squareColor1; // Sets squareColor
		this.squareColor2 = squareColor2;
	}

	public GameSquare (Position pos, Color squareColor1, Color squareColor2) {
		this (pos.r, pos.c, squareColor1, squareColor2);
	}
	
	/**
	   * This method is used to get the Position of a GameSquare.
	   * @return Position This returns the Position of a GameSquare.
	   */
	
	public Position getPosition () {
		return pos;
	}
	
	/**
	   * This method is set a GameSquare as selected.
	   */
	
	public void setSquareSelected(){
		isSelected = true;
	}
	
	/**
	   * This method is to check if a GameSquare is selected.
	   * @return boolean This returns the isSelected state.
	   */
	
	public boolean isSquareSelected(){
		return isSelected;
	}
	
	/**
	   * This method is set a GameSquare as in the pattern.
	   */
	
	public void setSquareInPattern(){
		inPattern = true;
	}
	
	/**
	   * This method is to check if a GameSquare is in the pattern.
	   * @return boolean This returns the inPattern state.
	   */
	
	public boolean isSquareInPattern(){
		return inPattern;
	}
	
	/**
	   * This method is to set a GameSquare as not in the pattern.
	   */
	
	public void notInPattern(){
		inPattern = false;
	}
	
	/**
	   * This method is used to set the graphics of the GameSquares
	   * @param g This is the first parameter to paintComponent method
	   */
		
	public void paintComponent (Graphics g) // Sets graphics of the squares of the game
	{
		super.paintComponents (g);

		Graphics2D g2 = (Graphics2D) g;

		int height = getHeight();
		int width = getWidth();

		Rectangle2D.Double bkgnd = new Rectangle2D.Double (x, y, width, height);
		GradientPaint gradient = new GradientPaint(x, y, squareColor1, x + width, y + height, squareColor2);
		g2.setPaint(gradient);
		g2.fill(bkgnd);
		
		g2.setStroke(new BasicStroke(8));
		g2.setColor(new Color(0,0,0));
		g2.draw(bkgnd);
		
		if(inPattern){
			Point2D center1 = new Point2D.Float((x + width)/2, (y + height)/2);
			Color[] colors1 = {new Color(115,117,129), new Color(59,63,85)};
			float[] dist1 = {0, 1};
			RadialGradientPaint gradient2 = new RadialGradientPaint(center1, width/2, dist1, colors1);
			g2.setPaint(gradient2);
			g2.fill(bkgnd);
			
			g2.setColor(new Color(0,0,0));
			g2.draw(bkgnd);
		}
		
		if(isSelected){
			Point2D center2 = new Point2D.Float((x + width)/2, (y + height)/2);
			Color[] colors2 = {new Color(59,63,85), new Color(115,117,129)};
			float[] dist2 = {0, 1};
			RadialGradientPaint gradient3 = new RadialGradientPaint(center2, width/2, dist2, colors2);
			g2.setPaint(gradient3);
			g2.fill(bkgnd);
			
			g2.setColor(new Color(0,0,0));
			g2.draw(bkgnd);
		}
		
		if (index == 4){
			index = 0;
		}
		else{
			index++;
		}
		
			
	}  
	
}



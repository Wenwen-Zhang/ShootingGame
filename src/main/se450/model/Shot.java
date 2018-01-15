package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.interfaces.IShot;
import main.se450.interfaces.IStrategy;

/**
 * The Shot class constructs shots with the properties passed by the Playership class and the configuration settings.
 * @author wenwenzhang
 *
 */
public class Shot extends Circle implements IShot 
{
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
	private int lifetime;
	
	/**
	 * Created a shot object to be displayed using the parameters.
	 * 
	 * @param nLifetime the amounts of frames that the shots would last.
	 * @param nLeft the upper left coordinate of the shot object in x direction.
	 * @param nTop the upper left coordinate of the shot object in y direction. 
	 * @param nRight the lower right coordinate of the shot object in x direction.
	 * @param nBottom the lower right coordinate of the shot object in y direction.
	 * @param nX the movement of the shot in x direction.
	 * @param nY the movement of the shot in y direction.
	 * @param nRotation the rotaion amount of the shot.
	 * @param cColor the color of the shot.
	 * @param iStrategy the border strategy of the shot.
	 * 
	 */
	public Shot(int nLifetime, float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		lifetime = nLifetime;		
	}
	
	/**
	 * Draw the shots, the shots would be filled with the setting color.
	 */
	@Override
	public void draw(Graphics graphics)
	{
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D)(graphics);
		g2d.setColor(getColor());
		g2d.fill(circle);
		g2d.draw(circle);
	}
	
	/**
	 * Update the lifetime of shot object, and its new position on the panel as well.
	 */
	@Override
	public void update()
	{
		--lifetime;
		super.update();
	}
	
	/**
	 * Check whether the shot had expired or not.
	 * @return a boolean: true if the shot had expired.
	 */	
	@Override
	public boolean isExpired() 
	{
		return (lifetime < 0);
	}
	
	
}


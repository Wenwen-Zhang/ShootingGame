package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.interfaces.IStrategy;


/**
 * The CircleShield class creates a protection shield around the playership when the playership observes a shield key is pressed. 
 * @author wenwenzhang
 *
 */
public class CircleShield extends Circle 
{
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
	
	//The lifetime of the shield that would last, measured in frames. 
	private int lifetime = 250;

	/**
	 * Create a shield using the parameter passed by the PlayerShip class, the shield, in fact, is a circle around the playership.
	 * 
	 * @param nLeft the upper left coordinate of the shield object in x direction. 
	 * @param nTop the upper left coordinate of the shield object in y direction.
	 * @param nRight the lower right coordinate of the shield object in x direction.
	 * @param nBottom the lower right coordinate of the shield object in y direction.
	 * @param nX the movement of the shield in x direction.
	 * @param nY the movement of the shield in y direction.
	 * @param nRotation the rotaion amount of the shield.
	 * @param cColor the color of the shield.
	 * @param iStrategy the border strategy of the shield.
	 *
	 */
	public CircleShield(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);		
	}

	/**
	 * Draw the shield on the panel.
	 */
	@Override
	public void draw(Graphics graphics)
	{
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D)(graphics);
		g2d.setColor(getColor());
		g2d.draw(circle);
	}
	
	/**
	 * Update the lifetime of shield object, and its new position on the panel as well.
	 */
	@Override
	public void update()
	{
		--lifetime;
		super.update();
	}
	
	/**
	 * Check whether the shield had expired or not.
	 * @return a boolean: true if the shield had expired.
	 */
	public boolean isExpired() 
	{
		return (lifetime < 0);
	}

	
	
}

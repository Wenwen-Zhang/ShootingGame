package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

/**
 * Create a circle shape object using the parameter values, and a circle would have a square bounding box around it which is made up of four lines.
 * @author wenwenzhang
 *
 */
public class Circle extends Shape
{
	private Line2D    line   = new Line2D.Float(0.0f,0.0f,0.0f,0.0f);
	private	Ellipse2D circle = new Ellipse2D.Float(0.0f,0.0f,0.0f,0.0f);
	
	private LineCollection lineCollection = new LineCollection();
		
	public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		createLines();
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
		line.setLine(getCenterX(), getCenterY(), getX1(), getY1());
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
  		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(circle);
  		g2d.draw(line);
	}

	@Override
	public float getMinX() 
	{
		return getCenterX() - getRadius();
	}

	@Override
	public float getMinY() 
	{
		return getCenterY() - getRadius();
	}

	@Override
	public float getMaxX() 
	{
		return getCenterX() + getRadius();
	}

	@Override
	public float getMaxY() 
	{
		return getCenterY() + getRadius();
	}
	
	public float getCenterX()
	{
		return getMidpointX1X3();
	}

	public float getCenterY()
	{
		return getMidpointY1Y3();
	}

	public float getRadius()
	{
		return getWidth() * 0.5f; //getWidth == getHeight for circle
	}
	
	private void createLines()
	{
		lineCollection.clear();
		addSides(lineCollection);
	}
	
	public void addSides(LineCollection nlineCollection)
	{
		if(nlineCollection != null)
		{
			nlineCollection.add(new Line(getMinX(), getMinY(), getMaxX(), getMinY(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        nlineCollection.add(new Line(getMaxX(), getMinY(), getMaxX(), getMaxY(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        nlineCollection.add(new Line(getMaxX(), getMaxY(), getMinX(), getMaxY(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        nlineCollection.add(new Line(getMinX(), getMaxY(), getMinX(), getMinY(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        
		}
	}
	
	@Override
	public LineCollection getLineCollection() {
		
		return lineCollection;
	}

	
	@Override
	public void update()
	{
		super.update();
		createLines();
	}
}

      
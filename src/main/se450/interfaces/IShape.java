package main.se450.interfaces;

import java.awt.Color;
import java.awt.Graphics;

import main.se450.collections.LineCollection;

/**
 * When implement, an object is an IShape, it has to define the concrete contents in the methods.
 * @author wenwenzhang
 *
 */
public interface IShape 
{
	void update();
	
	void draw(Graphics g);
	
	float getMinX();
	
	float getMinY();
	
	float getMaxX();
	
	float getMaxY();
	
	String getSize();
	
	int getScore();
	
	int getMultiplier();
	
	int getChildren();
	
	void setSize(String size);
	
	void setScore(int score);
	
	void setMultiplier(int multiplier);
	
	void setChildren(int children);
	
	LineCollection getLineCollection();

	float getX();

	float getY();
	
	void setX(float nX);

	void setY(float nY);

	float getRotation();

	Color getColor();

	IStrategy getStrategy();
}
      
package main.se450.interfaces;

import java.awt.Graphics;

import main.se450.collections.LineCollection;

/**
 * When implement, an object is an IShot, it has to define the concrete contents in the methods.
 * @author wenwenzhang
 *
 */
public interface IShot
{	
	boolean isExpired();
	void update();
	void draw(Graphics graphics);
	LineCollection getLineCollection();
}

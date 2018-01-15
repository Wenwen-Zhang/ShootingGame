package main.se450.model;

import java.awt.Color;

import main.se450.interfaces.IStrategy;

/**
 * This Configuration class would get the values of various settings when the configuration file is loaded.
 * Because those values are loaded from the configuration file, therefore they are read-only, only getters needed in this class.
 * @author wenwenzhang
 *
 */
public class Configuration 
{
	
	private int framesPerSecond;	
	private int repeatkeyspeed;
	private int width;
	private int height;
	private int shapes;
	private int shipwidth;
	private int shipheight;
	private float shotspeed;
	private float shotdiameter;
	private int shotlifetime;
	private float forwardthrust;
	private float reversethrust;
	private float friction;
	private float leftright;
	private Color color;
	private IStrategy borders;
	
	
	/**
	 * Created a configuration object to store the settings read from the congiguration file.
	 * @param FramesPerSecond the number of frames per second, this is the overall game speed, and would be used in Motion class.
	 * @param Repeatkeyspeed the keyboard repeat rate, would be used in Keyboard class.
	 * @param Width the width of the main window frame.
	 * @param Height the height of the main window frame.
	 * @param Shapes the amounts of shapes that would be shown in the game.
	 * @param Shipwidth the width of the playership.
	 * @param Shipheight the height of the playership.
	 * @param ShotSpeed the speed at which the shots would travel.
	 * @param Shotdiameter the diameter of the shots.
	 * @param Shotlifetime the amounts of frames that the shots would last.
	 * @param ForwardThrust the increased speed of the playership per key input.
	 * @param ReverseThrust the decreased speed of the playership per key input.
	 * @param Friction the friction which slows down the playership.
	 * @param LeftRight the amount at which the playership rotates left or right per key input. 
	 * @param nColor  the color of the playership.
	 * @param iStrategy the border strategy of the player ship.
	 * 
	 */
	public Configuration(int FramesPerSecond, int Repeatkeyspeed, int Width, int Height, int Shapes, int Shipwidth, int Shipheight, 
			float ShotSpeed, float Shotdiameter, int Shotlifetime, float ForwardThrust, float ReverseThrust, float Friction, float LeftRight, Color nColor, IStrategy iStrategy) {
		
		framesPerSecond = FramesPerSecond;
		repeatkeyspeed = Repeatkeyspeed;
		width = Width;
		height = Height;
		shapes = Shapes;
		shipwidth = Shipwidth;
		shipheight = Shipheight;
		shotspeed = ShotSpeed;
		shotdiameter = Shotdiameter;
		shotlifetime = Shotlifetime;
		forwardthrust = ForwardThrust;
		reversethrust = ReverseThrust;
		friction = Friction;
		leftright = LeftRight;
		color = nColor;
		borders = iStrategy;
	}

	public int getFramesPerSecond() 
	{
		return framesPerSecond;
	}

	public int getRepeatkeyspeed() 
	{
		return repeatkeyspeed;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
	}

	public int getShapes() 
	{
		return shapes;
	}

	public int getShipwidth() 
	{
		return shipwidth;
	}

	public int getShipheight() 
	{
		return shipheight;
	}

	public float getShotspeed() 
	{
		return shotspeed;
	}

	public float getShotdiameter() 
	{
		return shotdiameter;
	}

	public int getShotlifetime() 
	{
		return shotlifetime;
	}

	public float getForwardthrust() 
	{
		return forwardthrust;
	}

	public float getReversethrust() 
	{
		return reversethrust;
	}

	public float getFriction() 
	{
		return friction;
	}

	public float getLeftright() 
	{
		return leftright;
	}

	public Color getColor() 
	{
		return color;
	}

	public IStrategy getBorders() 
	{
		return borders;
	}


	

}

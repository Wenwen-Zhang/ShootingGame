package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import main.se450.collections.LineCollection;
import main.se450.factories.ChildrenFactory;
import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.ILeftObservable;
import main.se450.interfaces.IReverseThrustObservable;
import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IStrategy;
import main.se450.observable.Fire;
import main.se450.observable.ForwardThrust;
import main.se450.observable.HyperSpace;
import main.se450.observable.Left;
import main.se450.observable.ReverseThrust;
import main.se450.observable.Right;
import main.se450.observable.Score;
import main.se450.observable.Shield;
import main.se450.observable.Stop;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;
import main.se450.singletons.SoundManager;
import main.se450.utilities.Collide;
import main.se450.utilities.SizeConverter;

/**
 * The PlayerShip class constructs the playerShip and defines its behavior when different control keys are pressed.  
 * This class implements several different observable interfaces so the playership could be an observer when various control keys are pressed. 
 * @author Wenwen Zhang
 * 
 */

public class PlayerShip extends Ship implements IForwardThrustObservable, IReverseThrustObservable, ILeftObservable, IRightObservable, IStopObservable, IHyperSpaceObservable, IFireObservable, IShieldObservable
{
	private float forwardThrust;
	private float reverseThrust;
	private float friction;
	private float leftRight;

	private final static float SHIP_WIDTH = ConfigurationManager.getConfigurationManager().getConfiguration().getShipwidth();
	private final static float SHIP_HEIGHT = ConfigurationManager.getConfigurationManager().getConfiguration().getShipheight();
	private final static float LEFT = (DisplayManager.getDisplayManager().getWidth()-SHIP_WIDTH)/2;
	private final static float TOP = (DisplayManager.getDisplayManager().getHeight() - SHIP_HEIGHT)/2;
	
	private float shotSpeed;
	private int shotLifetime = ConfigurationManager.getConfigurationManager().getConfiguration().getShotlifetime();
	private float shotDiameter = ConfigurationManager.getConfigurationManager().getConfiguration().getShotdiameter();	

	private CircleShield circleShield;
	private boolean shieldTurnOn = false;
	
	/**
	 * Create a playerShip by the parameters passed by the ConfigurationManager, 
	 * and have this playership start observing the different keypressed controls.
	 * 
	 * @param nShotSpeed the speed of fired shots.
	 * @param nForwardThrust the increased speed of the playership per key input.
	 * @param nReverseThrust the decreased speed of the playership per key input.
	 * @param nFriction the friction which slows down the playership.
	 * @param nLeftRight the amount at which the playership rotates left or right per key input. 
	 * @param nX the movement of the playership in x direction.
	 * @param nY the movement of the playership in y direction.
	 * @param nRotation the rotaion amount of the playership.
	 * @param cColor the color of the playership.
	 * @param iStrategy the border strategy of the playership.
	 */
	
	public PlayerShip(float nShotSpeed, float nForwardThrust, float nReverseThrust, float nFriction, float nLeftRight, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy) 
	{	
		super(LEFT, TOP, LEFT+SHIP_WIDTH, TOP+SHIP_HEIGHT, nX, nY, nRotation, cColor, iStrategy);
		
		shotSpeed = nShotSpeed;
		forwardThrust = nForwardThrust;
		reverseThrust = nReverseThrust;
		friction = nFriction;
		leftRight = nLeftRight;
			
		ForwardThrust.startObserving(this);
		ReverseThrust.startObserving(this);
		Fire.startObserving(this);
		Stop.startObserving(this);
		Left.startObserving(this);
		Right.startObserving(this);
		HyperSpace.startObserving(this);
		Shield.startObserving(this);
	}
	
	/**
	 * Stop the playship's observations of the key inputs.
	 */
	@Override
	public void finalize() 
	{		
		ForwardThrust.stopObserving(this);
		ReverseThrust.stopObserving(this);
		Fire.stopObserving(this);
		Stop.stopObserving(this);
		Left.stopObserving(this);
		Right.stopObserving(this);
		HyperSpace.stopObserving(this);
		Shield.stopObserving(this);
	}
	
	/**
	 * Draw the playership, the shots fired, and the circleshield if the shield status is ON. 
	 */
	@Override
	public void draw(Graphics graphics) 
	{	
		super.draw(graphics);
		
		if(shieldTurnOn)
		{
			circleShield.draw(graphics);
		}
		
		final ArrayList<IShot> iShotList = ShotList.getShotList().getShots();
		if (iShotList != null)
		{
			Iterator<IShot> iiShots = iShotList.iterator();
			while (iiShots.hasNext())
			{
				IShot iShots = iiShots.next();
				if (iShots != null)
				{
					iShots.draw(graphics);
				}
			}
		}	
	}
	
	/**
	 * Update the objects on the panel, including:<br> 
	 * (1) the position of the playership according to the friction and control key inputs;<br> 
	 * (2) check the lifetime of shots, remove expired shots and update the positions of remaining shots;<br> 
	 * (3) check if any shapes got shot, update both the shots and the shape arraylist;<br> 
	 * (4) check the lifetime of the circle shield, turn off the shield status if it expires;<br> 
	 * (5) if the shield status is ON, update the position of the circleshield, and check the collision between the shield and the shapes;<br> 
	 * (6) if the shield status is OFF, the playership is without protection now, check the collision between the playership and the shapes;<br> 
	 */
	public void update() 
	{		
 		setX(friction(getX()));
		setY(friction(getY()));
		super.update(); 
		
		setRotation(0.0f); 
		
		updateShotsLifetime();
		checkShotCollision();
		updateShield();
	
		if (shieldTurnOn)
		{								
			updateShieldPosition();
			circleShield.update();
			checkShieldCollision();
		}
		else
		{
			checkShipCollision();
		}
		
		final ArrayList<IShot> iShotList = ShotList.getShotList().getShots();
		if (iShotList != null)
		{
			Iterator<IShot> iiShots = iShotList.iterator();
			while (iiShots.hasNext())
			{
				IShot iShots = iiShots.next();
				if (iShots != null)
				{
					iShots.update();
				}
			}
		}

	}
	
	/**
	 * check the lifetime of shots, remove the shots that had expired.
	 */
	private void updateShotsLifetime()
	{
		ArrayList<IShot> iShots = ShotList.getShotList().getShots();
		if (iShots != null)
		{
			ListIterator<IShot> iiShots = iShots.listIterator();
			while (iiShots.hasNext())
			{
				IShot iiShot = iiShots.next();
				if (iiShot.isExpired())
				{
					iiShots.remove();
				}

			}
		}
	}
	
	/**
	 * Check collisions between shots and shapes, if any shapes got shot, based on its size, it would be either disappeared from the panel or exploded into 
	 * smaller pieces along with different sound effects, and the score would be updated accordingly, as well.
	 */
	private void checkShotCollision() 
	{	
		ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		ArrayList<IShape> iChildList = new ArrayList<IShape>();
		
		if(iShapeList != null)
		{
			ListIterator<IShape> iiShapes = iShapeList.listIterator();
				
			boolean collided = false;
				
			while(iiShapes.hasNext())
			{
				IShape someShape = iiShapes.next();
					
				ArrayList<IShot> iShotList = ShotList.getShotList().getShots();
					
				collided = true;
					
				if(iShotList != null)
				{
					ListIterator<IShot> iiShots = iShotList.listIterator();
					while (collided && iiShots.hasNext())
					{
						IShot singleShot = iiShots.next();
						if(Collide.collided(someShape.getLineCollection(), singleShot.getLineCollection()))
						{
								
							Score.changeScoreBy(someShape.getScore());
								
							int size = SizeConverter.toIntConverter(someShape.getSize());
								
							playExplosionSound(size);
								
							size--;
								
							iiShots.remove();
							iiShapes.remove();
								
							collided = false;
								
							if (size > 0)
							{
								iChildList.addAll(ChildrenFactory.makeChild(someShape, size));
							}															
						}
					}
				}
			}			
		ShapeList.getShapeList().addShapes(iChildList);
		}
	}
	
	
	/**
	 * Based on the size of the collided shape, play different sound effects.
	 * @param nSize the size of the shape that got collided.
	 */
	private void playExplosionSound(int nSize)
	{
		if (nSize == 1)
			SoundManager.getSoundManager().playSmallExplosion();
		else if (nSize == 2)
			SoundManager.getSoundManager().playMediumExplosion();
		else if (nSize == 3)
			SoundManager.getSoundManager().playBigExplosion();		
	}
	
	/**
	 * Check collisions between the playership and the shapes. If the playership is hit by shapes, it would return back to the initial position, 
	 * crash sound effect would be played, and the score would be deducted by 100 per collision.
	 * 
	 */
	private void checkShipCollision() 
	{	
		ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		if(iShapeList != null)
		{
			Iterator<IShape> iiShapes = iShapeList.iterator();
			while (iiShapes.hasNext())
			{
				IShape someShape = iiShapes.next();
				LineCollection shapeLineCollection = someShape.getLineCollection();
				LineCollection shipLineCollection = this.getLineCollection();
				
				if (Collide.collided(shapeLineCollection, shipLineCollection))
				{
					Score.changeScoreBy(-100);
					SoundManager.getSoundManager().playShipExplosion();
					reset();		
				}	
			}								
		}					
	}
	
	/**
	 * Check collisions between the circleshield and the shapes when the shield status is ON. When shapes collide with the shield, they would be 
	 * bounced back, then the playership could be upder protection.
	 */
	public void checkShieldCollision()
	{
		if(shieldTurnOn)
		{
			ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
			if(iShapeList != null)
			{
				Iterator<IShape> iiShapes = iShapeList.iterator();
				while (iiShapes.hasNext())
				{
					IShape someShape = iiShapes.next();
					LineCollection shapeLineCollection = someShape.getLineCollection();
					LineCollection shieldLineCollection = circleShield.getLineCollection();
				
					if (Collide.collided(shapeLineCollection, shieldLineCollection))
					{
						someShape.setX(someShape.getX()*-1);
						someShape.setY(someShape.getY()*-1);
					}	
				}								
			}
		}			
	}
	
	/**
	 * When the shield key is pressed, a circle shield that lasts for 250 frames would be created around the playership, 
	 * and the shield status then would be switched to ON.
	 * @see CircleShield
	 */
	@Override
	public void shield() 
	{
		circleShield = new CircleShield (this.getX1(), this.getY1(), this.getX3(), this.getY3(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy());
		shieldTurnOn = true;
	}
	
	
	/**
	 * Update the circle shield lifetime, if it expires, set the circleshield to null, and the status would be switched to OFF.
	 */
	private void updateShield() 
	{
		if (shieldTurnOn)
		{
			if (circleShield.isExpired())
			{
				circleShield = null;
				shieldTurnOn = false;
			}			
		}
	}
	
	/**
	 * Update the position of the circle shield according to the playership's new position.
	 */	
	private void updateShieldPosition()
	{
		circleShield.setX1(this.getX1());
		circleShield.setY1(this.getY1());
		circleShield.setX3(this.getX3());
		circleShield.setY3(this.getY3());
		circleShield.setX(this.getX());
		circleShield.setY(this.getY());
	}
	
	/**
	 * When the forwardthrust key is pressed, the playership would move forward at any direction with the sound effect playing.
	 */	
	@Override
	public void forwardThrust() 
	{		
		SoundManager.getSoundManager().playForwardThrust();		
		thrust(forwardThrust);		
	}
	
	/**
	 * Calculate the actual values of x and y for the playership based on the parameter and the last position.
	 * @param fThrust the speed on y direction.
	 */
	private void thrust(float fThrust) 
	{		
		float xB = getMidpointX1X2();
		float xA = getMidpointX1X3();
		float yB = getMidpointY1Y2();
		float yA = getMidpointY1Y3();
		
		float xDiff = xB - xA;
		float yDiff = yB - yA;
		
		float fX = 0.0f;
		float fY = 0.0f;
		
		if (xDiff == 0.0f)
		{
			fY += fThrust * Math.signum(yDiff);
		}
		else if (yDiff == 0.0f)
		{
			fX += fThrust * Math.signum(xDiff);
		}
		else
		{
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += fThrust * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += fThrust * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
		}
		
		setX(getX() + fX);
		setY(getY() + fY);		
	}
	
	/**
	 * Calculate the speed based on the friction values, the playership would be either slowed down or come to a complete stop.
	 * @param nF
	 * @return the new speed of the playership.
	 */	
	private float friction (float nF) 
	{
		nF = (nF * (1.0f - friction));		
		if (Math.abs(nF) < friction) 
		{		
			nF = 0.0f;
		}		
		return nF;
	}	
	
	/**
	 * When the reversethrust key is pressed, if the playership is moving forward, it would be slowed down until stop, and then move backward.
	 */
	@Override
	public void reverseThrust() {
		
		SoundManager.getSoundManager().playReverseThrust();
		thrust(-reverseThrust); 
		
	}
	
	/**
	 * When the left rotation key is pressed, the playership would rotate counter-clockwise.
	 */
	@Override
	public void left() 
	{	
		setRotation(-leftRight - (getRotation()/60.0f));
	}
	
	/**
	 * When the right rotation key is pressed, the playership would rotate clockwise.
	 */
	@Override
	public void right() 
	{		
		setRotation(leftRight - (getRotation()/60.0f));
	}
	
	/**
	 * When the stop key is pressed, the playership would come to an immediately stop.
	 */
	@Override
	public void stop() 
	{	
		setX(0.0f);
		setY(0.0f);
	}
	
	/**
	 * When the hyperspace key is pressed, the playership would be teleported to a random position in the panel.
	 */
	@Override
	public void hyperSpace() 
	{		
		translateXY(-getMinX() + (float)Math.random() * (DisplayManager.getDisplayManager().getWidth()),
					-getMinY() + (float)Math.random() * (DisplayManager.getDisplayManager().getHeight()));
	}	

	/**
	 * When the fire key is pressed, the playership would fire, along with the fire sound effect playing, a shot would be created and added to 
	 * the shot arraylist with the previously defined shot speed and shot diameter values per key input, and its moving direction, color and 
	 * border strategy would be the same as the playership.
	 * @see Shot
	 */
	@Override
	public void fire()
	{	
		SoundManager.getSoundManager().playFire();
				
		float xB = getMidpointX1X2(); //tip of ship
		float xA = getMidpointX1X3(); //bottom of ship
		float yB = getMidpointY1Y2(); //tip of ship
		float yA = getMidpointY1Y3(); //bottom of ship
		
		float xDiff = xB - xA;
		float yDiff = yB - yA;	
		
		float fX = 0.0f;
		float fY = 0.0f;
		
		if (xDiff == 0.0f) 
		{	
			fY += shotSpeed * Math.signum(yDiff);
		}
		
		else if (yDiff == 0.0f) 
		{			
			fX += shotSpeed * Math.signum(xDiff);
		}
		
		else 
		{	
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += shotSpeed * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += shotSpeed * Math.sin(Math.toRadians(90 * yDiff / ffDiff));			
		}
		
		float l = xB - shotDiameter;
		float t = yB - shotDiameter;
		float r = xB + shotDiameter;
		float b = yB + shotDiameter;
		
		ShotList.getShotList().addShots(new Shot(shotLifetime, l, t, r, b, fX, fY, 0, ConfigurationManager.getConfigurationManager().getConfiguration().getColor(), ConfigurationManager.getConfigurationManager().getConfiguration().getBorders()));		
	}
		
	/**
	 * Reset the playership to the initial position, which is the middle of the panel.
	 */
	public void reset()
	{	
		shieldTurnOn = false;
		setX1(LEFT);
		setY1(TOP);
		setX2(LEFT+SHIP_WIDTH);
		setY2(TOP);
		setX3(LEFT+SHIP_WIDTH);
		setY3(TOP+SHIP_HEIGHT);
		setX4(LEFT);
		setY4(TOP+SHIP_HEIGHT);
		setX(0.0f);
		setY(0.0f);
		setRotation(0.0f);
	}	
}

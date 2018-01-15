package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


import javax.swing.JPanel;

import main.se450.factories.JSONFileShapeListFactory;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStartObservable;
import main.se450.model.Configuration;
import main.se450.model.PlayerShip;
import main.se450.observable.Score;
import main.se450.observable.Start;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;

/*
 * Name     : Wenwen Zhang
 * Depaul#  : 1542969
 * Class    : SE 450
 * Project  : Final
 * Due Date : 03/19/2017
 *
 * class ShapeDisplay
 *
 */

/**
 * The ShapeDisplay class is an observer of the start key input, once the game is started, it keeps drawing the updated shapes and playership on the panel, 
 * including the shots and circleshields of the playership, if any.
 * @author Wenwen Zhang
 *
 */
public class ShapeDisplay extends JPanel implements IStartObservable
{

	private static final long serialVersionUID = 1L;
	private PlayerShip playerShip = null;
	
	/**
	 * Start observing the start key input when a shapeDisplay object is constructed.
	 */
	public ShapeDisplay()
	{
		Start.startObserving(this);		
	}
	
	/** 
	 * @see java.awt.Container#validateTree()
	 */
	@Override
	public void validateTree()
	{
		super.validateTree();

		Dimension dimension = getSize();
		
		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}
	
	/**
	 * Update and draw the mostly current shapes and playership on the panel, including the shots and circleshields of the playership, if any.
	 */

	public void paint(Graphics graphics) 
  	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(), DisplayManager.getDisplayManager().getHeight());		
		
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		
		if (iShapeList != null)
		{
			Iterator<IShape> iiShapes = iShapeList.iterator();
			while (iiShapes.hasNext())
			{
				IShape iShape = iiShapes.next();
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
		
		if (playerShip != null) 
		{
			playerShip.update(); 
			playerShip.draw(graphics);
		}
				
    }  	
	/**
	 * Repaint the panel per update.
	 */
	@Override
	public void update() 
	{
		repaint();		
	}	
	
	/**
	 * When the start key is pressed, this shape display loads shapes, places playership, therefore starts the game, 
	 * and it also starts the score observable event so that the score could be updated.
	 */
	@Override
	public void start() 
	{				
		placeShip();
		loadShapes();
		Score.score();
		Score.clearScore();
		//Motion.startObserving(this); 
	}
	
	/**
	 * When this method is called, clear the shapelist and shotlist to make sure the panel is clean, load shapes from the shape json 
	 * file, according to configuration settings, the randomly add the desired amount of shapes to the shapelist so that the shapes could be drawn 
	 * on the panel. 
	 */
	private void loadShapes() 
	{
		final ArrayList<IShape> iShapes = JSONFileShapeListFactory.makeShape("shapes.json");
		ArrayList<IShape> tempShapes = new ArrayList<IShape>();
		
		ShapeList.getShapeList().getShapes().clear();
		ShotList.getShotList().getShots().clear();
		
		if(ShapeList.getShapeList().getShapes().isEmpty())
		{
			Collections.shuffle(iShapes);
			int shapesNumber = ConfigurationManager.getConfigurationManager().getConfiguration().getShapes();
			
			if (shapesNumber < iShapes.size())
			{
				for(int i = 0; i < shapesNumber; i++)
				{
					tempShapes.add(iShapes.get(i));
				}
			}
			else
			{
				tempShapes.addAll(iShapes);
			}
			
			ShapeList.getShapeList().addShapes(tempShapes);
		}		
		
	}
	
	/**
	 * If no playership exists, create a playership based on the configuration settings, otherwise, place the existing playership to the initial position.
	 */
	private void placeShip()
	{
		if(playerShip != null)
		{
			playerShip.reset();
		}
		else
		{
			ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManager();
			
			if(configurationManager != null) 
			{
				Configuration configuration = configurationManager.getConfiguration();
				
				if (configuration != null) 
				{				
					playerShip = new PlayerShip(configuration.getShotspeed(), configuration.getForwardthrust(), configuration.getReversethrust(), configuration.getFriction(), configuration.getLeftright(), 
							0.0f, 0.0f, 0.0f, configuration.getColor(), configuration.getBorders());
				}
			}	
		}		
	}
	
}





      
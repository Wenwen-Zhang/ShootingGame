package test.se450.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.se450.interfaces.IShape;
import main.se450.model.CircleShield;
import main.se450.model.PlayerShip;
import main.se450.model.Square;
import main.se450.parser.ConfigurationParser;
import main.se450.singletons.ShapeList;
import main.se450.utilities.Collide;;

public class CollisionTest {
	
	PlayerShip ship;	
	CircleShield shield;
	IShape someShape;
	IShape shapeToRebound;
	
	public void makeTestModels()
	{
		ConfigurationParser.loadConfiguration("configuration.json");
		ship = new PlayerShip(0, 0, 0, 0, 0, 0, 0, 0, null, null);
		shield = new CircleShield(100.0f, 100.0f, 150.0f, 150.0f, -5.0f, -3.0f, 4.0f, null, null);
		someShape = new Square(50.0f, 50.0f, 100.0f, 100.0f, -5.0f, -3.0f, 4.0f, null, null);
		shapeToRebound = new Square(ship.getX1(), ship.getY1(), ship.getX3(), ship.getY3(), -5.0f, -3.0f, 4.0f, null, null);
		ShapeList.getShapeList().getShapes().add(shapeToRebound);
	}
	
	
	@Test
	public void testShieldShapeCollided() 
	{		
		makeTestModels();
		boolean collided = Collide.collided(shield.getLineCollection(), someShape.getLineCollection());
		
		assertTrue(collided == true);
	}
	
	@Test
	public void testShipShapecollision() 
	{		
		makeTestModels();
		boolean collided = Collide.collided(ship.getLineCollection(), shapeToRebound.getLineCollection());
		
		assertTrue(collided == true);
	}
	
	@Test
	public void testShapeReboundWhenCollidedWithShield()
	{	
		makeTestModels();
		float newX = (shapeToRebound.getX()*-1.0f);
		ship.shield();
		ship.checkShieldCollision();
		assertFalse(newX == shapeToRebound.getX());		
	}

}

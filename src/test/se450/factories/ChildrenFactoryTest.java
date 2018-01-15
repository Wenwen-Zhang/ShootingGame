package test.se450.factories;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.se450.interfaces.IShape;
import main.se450.model.Square;
import main.se450.utilities.SizeConverter;
import main.se450.factories.ChildrenFactory;

public class ChildrenFactoryTest {
	
	IShape someShape;
	ArrayList<IShape> childList;
	int someShapeSize;
	int childSize;

	public void makeTestModels()
	{
		someShape = new Square(100.0f, 100.0f, 150.0f, 150.0f, -5.0f, -3.0f, 4.0f, null, null);
		
		someShape.setChildren(5);
		someShape.setMultiplier(10);
		someShape.setScore(100);
		someShape.setSize("large");
		someShapeSize = SizeConverter.toIntConverter(someShape.getSize());
		childSize = someShapeSize - 1;
		childList = ChildrenFactory.makeChild(someShape, childSize);
	}
	
	
	@Test
	public void testChildrenNumber() 
	{
		makeTestModels();
		assertTrue(childList.size() == someShape.getChildren());
	}
	
	@Test
	public void testChildScore() 
	{
		makeTestModels();		
		assertTrue((childList.get(0).getScore()) == (someShape.getScore()* someShape.getMultiplier()));
	}
	
	@Test
	public void testChildSize() 
	{
		makeTestModels();
		
		String stringSize = SizeConverter.toStringConverter(childSize);
		
		assertTrue(stringSize.equals("medium"));
	}

}

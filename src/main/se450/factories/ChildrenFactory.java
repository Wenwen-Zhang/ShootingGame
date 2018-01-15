package main.se450.factories;

import java.util.ArrayList;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.utilities.SizeConverter;


/**
 * The ChildrenFactory would make smaller shapes when large shapes got hit by shots, 
 * it would call the shapefactory to make children of different specific shape types.
 * @author wenwenzhang
 *
 */
public class ChildrenFactory {
		
	private ChildrenFactory()
	{
	}
	
	/**
	 * Make child shapes when a mother shape got shot. The amount of children and the score each child would carry are defined in the json file.
	 * @param someShape the shape that got shot, would be break into smaller shapes.
	 * @param nSize the size of the shape.
	 * @return a static arraylist of smaller shapes.
	 */
	public static ArrayList<IShape> makeChild (IShape someShape, int nSize)
	{	
		ArrayList<IShape> childrenList = new ArrayList<IShape>();
		
		String newSize = SizeConverter.toStringConverter(nSize);
		int newScore = someShape.getScore()*someShape.getMultiplier();
		
		float left = someShape.getMinX();
		float top = someShape.getMinY();
		float width = (someShape.getMaxX() - someShape.getMinX())*0.5f;
		float height = (someShape.getMaxY() - someShape.getMinY())*0.5f;  
		int sign = (int)Math.random()*2 - 1;
		String shapeType=someShape.getClass().getSimpleName();

		for (int child = someShape.getChildren(); child > 0; child-- )
		{
			try 
			{
				childrenList.add(ShapeFactory.makeShape(shapeType, newSize, newScore, 
														someShape.getMultiplier(), someShape.getChildren(),  
														left, top, left+width, top+height, 
														someShape.getX()*((float)Math.random())*sign, someShape.getY()*((float)Math.random())*sign, 
														someShape.getRotation()*((float)Math.random())*sign, someShape.getColor(), someShape.getStrategy()));
				
			} catch (BadShapeException | UnsupportedShapeException e) 
			{
				
				System.out.println(e);
			}
		}	
		return childrenList;
		
	}

}

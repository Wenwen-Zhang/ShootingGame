package main.se450.factories;

import java.awt.Color;

import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Circle;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;

/**
 * This ShapeFactory class would make specific shapes based on different parameters, is mainly used in the JSONFileShapeListFactory and 
 * the childrenFactory.
 * @author wenwenzhang
 *
 */
public class ShapeFactory
{
	private ShapeFactory()
	{
	}
	
	/**
	 * According to the parameters read from the json file, make different shapes.
	 * 
	 * @param type the specific shape type.
	 * @param nSize the original size of the shape.
	 * @param nScore the original scores the shape values.
	 * @param nMultiplier the multiplier to calculate the score of smaller shapes when the shape got shot.
	 * @param nChildren the amount of children that could be made when the shape got shot.
	 * @param nLeft the upper left coordinate of the shape in x direction.
	 * @param nTop the upper left coordinate of the shape in y direction.
	 * @param nRight the lower right coordinate of the shape in x direction.
	 * @param nBottom the lower right coordinate of the shape in y direction.
	 * @param x the movement of the shape in x direction.
	 * @param y the movement of the shape in y direction.
	 * @param rotation the rotaion amount of the shape.
	 * @param cColor the color of the shape.
	 * @param iStrategy the border strategy of the shape.
	 * @return a static IShape that would be added into the shapelist and displayed on the panel.
	 * @throws BadShapeException when bad shape found.
	 * @throws UnsupportedShapeException when unsupported shape found.
	 * 
	 */
	public static IShape makeShape(final String type, String nSize, int nScore, int nMultiplier, int nChildren, float nLeft, float nTop, float nRight, float nBottom, float x, float y, float rotation, Color cColor, IStrategy iStrategy) throws BadShapeException, UnsupportedShapeException
	{
		IShape iShape = null;

    	if (type.equals("Circle"))
    	{
    		iShape = new Circle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setChildren(nChildren);
    		iShape.setMultiplier(nMultiplier);
    		iShape.setScore(nScore);
    		iShape.setSize(nSize);
    	}
        else if (type.equals("Square"))
    	{
    		iShape = new Square(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setChildren(nChildren);
    		iShape.setMultiplier(nMultiplier);
    		iShape.setScore(nScore);
    		iShape.setSize(nSize);
    	}
    	else if (type.equals("Line"))
    	{
    		throw new UnsupportedShapeException(type);
    	}
    	else if (type.equals("Triangle"))
    	{
    		iShape = new Triangle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setChildren(nChildren);
    		iShape.setMultiplier(nMultiplier);
    		iShape.setScore(nScore);
    		iShape.setSize(nSize);
    	}
    	else if (type.equals("Ship"))
    	{
    		iShape = new Ship(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setChildren(nChildren);
    		iShape.setMultiplier(nMultiplier);
    		iShape.setScore(nScore);
    		iShape.setSize(nSize);
    	}
    	else
    	{
    		throw new BadShapeException(type);
    	}
		
		return iShape;
	}
}
      
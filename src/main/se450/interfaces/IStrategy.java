package main.se450.interfaces;

import main.se450.model.Shape;

/**
 * When implement, an object is an IStrategy, it has to define how the Shape could execute in the execute method.
 */
public interface IStrategy 
{
	void execute(Shape shape);
}
      
package CS355.LWJGL.models;

import CS355.LWJGL.Line3D;
import CS355.LWJGL.Point3D;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Brennan Smith
 */
public abstract class Renderable
{

	private double theta = 0.0;
	private Point3D translate = new Point3D(0, 0, 0);
	private Color color = Color.YELLOW;

	public Renderable(double alpha, Point3D translate)
	{
		this.theta = alpha;
		this.translate = translate;
	}

	public Renderable(double alpha, Point2D translate)
	{
		this(alpha, new Point3D(translate.getX(), 0, translate.getY()));
	}

	public double getRotation()
	{
		return theta;
	}

	public Point3D getTranslate()
	{
		return translate;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Collection<Renderable> getSubmodels()
	{
		return Collections.EMPTY_LIST;
	}
}

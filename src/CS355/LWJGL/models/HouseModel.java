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
public class HouseModel extends Renderable implements IWireFrame
{
    private final List<Line3D> lines = new ArrayList<>();	
	
	private void initModel()
	{
		lines.add(new Line3D(new Point3D(-5,0,-5), new Point3D(5,0,-5)));
        lines.add(new Line3D(new Point3D(5,0,-5), new Point3D(5,0,5)));
        lines.add(new Line3D(new Point3D(5,0,5), new Point3D(-5,0,5)));
        lines.add(new Line3D(new Point3D(-5,0,5), new Point3D(-5,0,-5)));
        //Ceiling
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(5,5,-5)));
        lines.add(new Line3D(new Point3D(5,5,-5), new Point3D(5,5,5)));
        lines.add(new Line3D(new Point3D(5,5,5), new Point3D(-5,5,5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(-5,5,-5)));
        //Walls
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(-5,0,-5)));
        lines.add(new Line3D(new Point3D(5,5,-5), new Point3D(5,0,-5)));
        lines.add(new Line3D(new Point3D(5,5,5), new Point3D(5,0,5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(-5,0,5)));        
        //Roof
        lines.add(new Line3D(new Point3D(-5,5,-5), new Point3D(0,8,-5)));
        lines.add(new Line3D(new Point3D(0,8,-5), new Point3D(5,5,-5)));
        lines.add(new Line3D(new Point3D(-5,5,5), new Point3D(0,8,5)));
        lines.add(new Line3D(new Point3D(0,8,5), new Point3D(5,5,5)));
        lines.add(new Line3D(new Point3D(0,8,-5), new Point3D(0,8,5)));
        //Door
        lines.add(new Line3D(new Point3D(1,0,5), new Point3D(1,3,5)));
        lines.add(new Line3D(new Point3D(-1,0,5), new Point3D(-1,3,5)));
        lines.add(new Line3D(new Point3D(1,3,5), new Point3D(-1,3,5)));
	}
	
	public HouseModel(double alpha, Point3D translate)
	{
		super(alpha, translate);
		initModel();
	}
	
	public HouseModel(double alpha, Point2D translate)
	{
		super(alpha, translate);
		initModel();
	}

	@Override
	public Collection<Line3D> getLines()
	{
		return Collections.unmodifiableCollection(lines);
	}    
    
}

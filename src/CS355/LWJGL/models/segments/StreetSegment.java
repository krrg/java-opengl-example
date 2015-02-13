/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS355.LWJGL.models.segments;

import CS355.LWJGL.Point3D;
import CS355.LWJGL.models.HouseFactory;
import CS355.LWJGL.models.Renderable;
import static CS355.LWJGL.models.HouseFactory.getPoint;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A segment of 8 houses, 4 on each side.
 *
 * @author krr428
 */
public class StreetSegment extends Renderable
{
	private Collection<Renderable> houses = null;

	public StreetSegment(double alpha, Point3D translate)
	{
		super(alpha, translate);
		initHouses();
	}

	public StreetSegment(double alpha, Point2D translate)
	{
		super(alpha, translate);
		initHouses();
	}

	private void initHouses()
	{
		houses = new ArrayList<Renderable>(HouseFactory.createHouses(generateRotations(), generateTranslations()));
	}

	private List<Double> generateRotations()
	{
		return Arrays.asList(180.0, 180.0, 180.0, 180.0, 0.0, 0.0, 0.0, 0.0);
	}

	private List<Point2D> generateTranslations()
	{
		return Arrays.asList(
				getPoint(-10, 15), getPoint(-25, 15), getPoint(10, 15), getPoint(25, 15),
				getPoint(-10, -15), getPoint(-25, -15), getPoint(10, -15), getPoint(25, -15)
		);
	}

	@Override
	public Collection<Renderable> getSubmodels()
	{
		return houses;
	}
	
	

}

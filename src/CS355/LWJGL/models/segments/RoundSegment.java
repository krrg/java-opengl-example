/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS355.LWJGL.models.segments;

import CS355.LWJGL.Point3D;
import CS355.LWJGL.models.HouseFactory;
import static CS355.LWJGL.models.HouseFactory.getPoint;
import CS355.LWJGL.models.Renderable;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author krr428
 */
public class RoundSegment extends Renderable
{

	private Collection<Renderable> houses = null;

	public RoundSegment(double alpha, Point3D translate)
	{
		super(alpha, translate);
		initHouses();
	}

	public RoundSegment(double alpha, Point2D translate)
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
		return Arrays.asList( 30.0, 60.0, 120.0, 150.0,-120.0, -150.0, -60.0, -30.0);
	}

	private List<Point2D> generateTranslations()
	{
		List<Point2D> translations = new ArrayList<>();
		List<Double> rotations = generateRotations();
		for (int i = 0; i < rotations.size(); i++)
		{
			double theta = rotations.get(i);
			
			double thetaRad = (Math.PI / 180.0) * (theta);

			double x = 30 * Math.cos(thetaRad);
			double z = 30 * Math.sin(thetaRad);
			
			if ((i / 2) % 2 == 0)
			{
				x *= -1;
				z *= -1;
			}

			translations.add(getPoint(x, z));
		}

		return translations;
	}

	@Override
	public Collection<Renderable> getSubmodels()
	{
		return houses;
	}
}

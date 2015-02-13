/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CS355.LWJGL.models;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author krr428
 */
public class HouseFactory
{
	public static HouseModel createHouse(double alpha, Point2D translation, Color color)
	{
		HouseModel house = new HouseModel(alpha, translation);
		house.setColor(getRandColor());
		return house;
	}
	
	public static Point2D getPoint(double x, double z)
	{
		return new Point2D.Double(x, z);
	}
	
	private static Color getRandColor()
	{
		return new Color(0.5F * (float) Math.random(), 0.5F + 0.5F * (float) Math.random(), 0.5F * (float) Math.random());
	}
		
	public static Collection<HouseModel> createHouses(List<Double> rotations, List<Point2D> translations)
	{	
		ArrayList<HouseModel> models = new ArrayList<>();

		for (int i = 0; i < rotations.size(); i++)
		{
			double alpha = rotations.get(i);
			Point2D translation = translations.get(i);			
			models.add(createHouse(alpha, translation, getRandColor()));
		}
		
		return models;

	}
}

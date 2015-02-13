/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS355.LWJGL.models.segments;

import CS355.LWJGL.Point3D;
import static CS355.LWJGL.models.HouseFactory.getPoint;
import CS355.LWJGL.models.Renderable;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author krr428
 */
public class GridSegment extends Renderable
{

	private Collection<Renderable> subsegments = null;
	
	private final int NUM_BLOCKS = 24;
	private final int NUM_GRID_SEGMENTS = NUM_BLOCKS * 2;

	public GridSegment(double alpha, Point3D translate)
	{
		super(alpha, translate);
		initSubsegments();
	}

	public GridSegment(double alpha, Point2D translate)
	{
		super(alpha, translate);
		initSubsegments();
	}

	private void initSubsegments()
	{		
		Queue<Point2D> translations = new LinkedList<>(generateTranslations());
		subsegments = new ArrayList<>();

		for (int i = 0; i <= NUM_GRID_SEGMENTS; i++)
		{
			for (int j = 0; j <= NUM_GRID_SEGMENTS; j++)
			{
				if (i % 2 == 0 && j % 2 == 0)
				{
					RoundSegment segment = new RoundSegment(0.0, translations.poll());
					subsegments.add(segment);
				}				
				else if (i % 2 == 0)
				{
					StreetSegment segment = new StreetSegment(90, translations.poll());
					subsegments.add(segment);
				}
				else if (j % 2 == 0)
				{
					StreetSegment segment = new StreetSegment(0, translations.poll());
					subsegments.add(segment);
				}
			}
		}
	}	

	private List<Point2D> generateTranslations()
	{
		List<Point2D> translations = new ArrayList<>();

		for (int i = -NUM_BLOCKS; i <= NUM_BLOCKS; i++)
		{
			for (int j = -NUM_BLOCKS; j <= NUM_BLOCKS; j++)
			{
				double x = i * 65;
				double z = j * 65;

				if (Math.abs(i) % 2 != 1 || Math.abs(j) % 2 != 1)
				{
					translations.add(getPoint(x, z));
				}
			}
		}

		return translations;
	}

	@Override
	public Collection<Renderable> getSubmodels()
	{
		return subsegments;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CS355.LWJGL.models;

import CS355.LWJGL.Line3D;
import java.util.Collection;

/**
 *
 * @author krr428
 */
public interface IWireFrame
{
	public Collection<Line3D> getLines();
}

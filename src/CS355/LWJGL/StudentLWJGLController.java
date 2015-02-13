package CS355.LWJGL;

import CS355.LWJGL.models.HouseModel;
import CS355.LWJGL.models.Renderable;
import CS355.LWJGL.models.segments.GridSegment;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.Project.gluPerspective;

/**
 *
 * @author Ken Reese
 */
public class StudentLWJGLController implements CS355LWJGLController
{

	private Renderable superRenderable = null;

	public StudentLWJGLController()
	{
		superRenderable = new GridSegment(0, new Point2D.Double());
	}

	//This method is called to "resize" the viewport to match the screen.
	//When you first start, have it be in perspective mode.
	@Override
	public void resizeGL()
	{
		final int WIDTH = LWJGLSandbox.DISPLAY_WIDTH;
		final int HEIGHT = LWJGLSandbox.DISPLAY_HEIGHT;

		glViewport(0, 0, WIDTH, HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
//		gluPerspective(60, (float)LWJGLSandbox.DISPLAY_WIDTH / (float)LWJGLSandbox.DISPLAY_HEIGHT, 1.5F, 125.0F);
//		GL11.glTranslated(0.0, -1.0, 0.0);
		glMatrixMode(GL_MODELVIEW);

	}

	private void renderRenderable(Renderable renderable)
	{
		glPushMatrix();

		GL11.glTranslated(renderable.getTranslate().x, renderable.getTranslate().y, renderable.getTranslate().z);
		GL11.glRotated(renderable.getRotation(), 0, 1, 0);

		//Base case is when getSubmodels() is empty.
		for (Renderable subpart : renderable.getSubmodels())
		{
			renderRenderable(subpart);
		}

		if (renderable instanceof HouseModel)
		{
			renderHouse((HouseModel) renderable);
		}

		glPopMatrix();
	}

	private void renderHouse(HouseModel house)
	{
		float[] rgb = house.getColor().getRGBComponents(null);
		GL11.glColor3f(rgb[0], rgb[1], rgb[2]);

		GL11.glBegin(GL11.GL_LINES);
		for (Line3D l3d : house.getLines())
		{
			renderLine(l3d);
		}
		GL11.glEnd();
	}

	@Override
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		placeCamera();
		glMatrixMode(GL_MODELVIEW);

		renderRenderable(superRenderable);
	}

	@Override
	public void update()
	{
		//Do nothing in Lab 5....?
	}

	//This is called every frame, and should be responsible for keyboard updates.
	//An example keyboard event is captured below.
	//The "Keyboard" static class should contain everything you need to finish
	// this up.
	@Override
	public void updateKeyboard()
	{
//		System.out.println("Updating keyboard.");
		for (int key : getKeyHandlersInstance().keyhandlers.keySet())
		{
			if (Keyboard.isKeyDown(key))
			{
				getKeyHandlersInstance().keyhandlers.get(key).keyDown(key);
			}
		}
	}

	private double theta = 0.0;
	private double camX = 0.0;
	private double camY = -1.0;
	private double camZ = 15.0;
	private boolean orthoViewOn = false;

	private void placeCamera()
	{
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		GL11.glTranslated(camX, camY, camZ);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		if (orthoViewOn)
		{
			GL11.glOrtho(-10, 10, -10, 10, 1.5, 60.0F);
		}
		else
		{
			final float aspectRatio = 640.0F / 480.0F;
			final float viewAngle = 60;

			gluPerspective(viewAngle, aspectRatio, 1.5F, 600.0F);
		}

		GL11.glRotated(-theta, 0, 1, 0);
	}

	private void renderLine(Line3D line)
	{
		Point3D p1 = line.start;
		Point3D p2 = line.end;

		GL11.glVertex3d(p1.x, p1.y, p1.z);
		GL11.glVertex3d(p2.x, p2.y, p2.z);
	}

	private interface GLKeyPressedHandler
	{

		public void keyDown(int key);
	}

	private KeyboardHandlers getKeyHandlersInstance()
	{
		if (_keyHandleInstance == null)
		{
			_keyHandleInstance = new KeyboardHandlers();
		}

		return _keyHandleInstance;
	}

	private KeyboardHandlers _keyHandleInstance = null;

	private class KeyboardHandlers
	{

		private Map<Integer, GLKeyPressedHandler> keyhandlers = null;

		public KeyboardHandlers()
		{
			this.keyhandlers = new HashMap<>();
			initHandlers();
		}

		private void initHandlers()
		{
			keyhandlers.put(Keyboard.KEY_A, new MoveLeftHandler());
			keyhandlers.put(Keyboard.KEY_D, new MoveRightHandler());
			keyhandlers.put(Keyboard.KEY_W, new MoveForwardHandler());
			keyhandlers.put(Keyboard.KEY_S, new MoveBackwardsHandler());
			keyhandlers.put(Keyboard.KEY_Q, new TurnLeftHandler());
			keyhandlers.put(Keyboard.KEY_E, new TurnRightHandler());
			keyhandlers.put(Keyboard.KEY_R, new MoveUpHandler());
			keyhandlers.put(Keyboard.KEY_F, new MoveDownHandler());
			keyhandlers.put(Keyboard.KEY_O, new SwitchOrthographicHandler());
			keyhandlers.put(Keyboard.KEY_P, new SwitchPerspectiveHandler());
		}
	}

	private class TurnLeftHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			theta += 1;
			theta %= 360;
		}

	}

	private class TurnRightHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			theta -= 1;
			theta %= 360;
		}

	}

	private class MoveForwardHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			//How much do we move in z and x?
			double thetaRad = (Math.PI / 180.0) * (theta);
			double deltaZ = Math.cos(thetaRad);
			double deltaX = Math.sin(thetaRad);

			System.out.println("deltaZ = " + deltaZ);
			System.out.println("deltaX = " + deltaX);
			System.out.println("");

			camX += deltaX;
			camZ += deltaZ;
		}

	}

	private class MoveBackwardsHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			glMatrixMode(GL_MODELVIEW);

			double thetaRad = (Math.PI / 180.0) * (theta - 180);
			double deltaZ = Math.cos(thetaRad);
			double deltaX = Math.sin(thetaRad);

			camX += deltaX;
			camZ += deltaZ;
		}

	}

	private class MoveLeftHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			glMatrixMode(GL_MODELVIEW);

			double thetaRad = (Math.PI / 180.0) * (theta + 90);
			double deltaZ = Math.cos(thetaRad);
			double deltaX = Math.sin(thetaRad);

			camX += deltaX;
			camZ += deltaZ;
		}

	}

	private class MoveRightHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			glMatrixMode(GL_MODELVIEW);

			double thetaRad = (Math.PI / 180.0) * (theta - 90);
			double deltaZ = Math.cos(thetaRad);
			double deltaX = Math.sin(thetaRad);

			camX += deltaX;
			camZ += deltaZ;
		}

	}

	private class MoveUpHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			camY -= 1;
		}

	}

	private class MoveDownHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			camY += 1;
		}

	}

	private class SwitchOrthographicHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			orthoViewOn = true;
		}

	}

	private class SwitchPerspectiveHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			orthoViewOn = false;
		}

	}

	private class ResetViewHandler implements GLKeyPressedHandler
	{

		@Override
		public void keyDown(int key)
		{
			theta = 0.0;
			camX = 0.0;
			camY = -1.0;
			camZ = 15.0;
			orthoViewOn = false;
		}

	}

}

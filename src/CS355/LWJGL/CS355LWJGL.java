package CS355.LWJGL;

import org.lwjgl.LWJGLException;

/**
 *
 * @author Brennan Smith
 */
public class CS355LWJGL
{

	public static void main(String[] args)
	{
		LWJGLSandbox main = null;
		try
		{
			main = new LWJGLSandbox();
			main.create(new StudentLWJGLController());
			main.run();
		}
		catch (LWJGLException ex)
		{
			System.out.println(ex);
		}
		finally
		{
			if (main != null)
			{
				main.destroy();
			}
		}
	}

}

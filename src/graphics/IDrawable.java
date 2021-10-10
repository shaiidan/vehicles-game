package graphics;


import java.awt.Graphics;
import vehicles.Color;
/**
 * @author Shai Idan
 * @version 2
 */
public interface IDrawable 
{
	 public final static String PICTURE_PATH = ".\\src\\graphics_image\\";
	 public void loadImages();
	 public void drawObject (Graphics g);
	 public Color getColor();
}

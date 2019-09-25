package server;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BoardComponent extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3954213753986046747L;
	
	private int horizontal;
	private int vertical;

	private BufferedImage image;

	public BoardComponent(int h, int v)
	{
		super();

		horizontal = h;
		vertical = v;
		image = new BufferedImage(horizontal, vertical, BufferedImage.TYPE_INT_RGB);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D graphics2d = (Graphics2D) g;

		graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

		int width = getWidth();
		int height = getHeight();
		
		g.drawImage(image, 0, 0, width, height, 0, 0, horizontal, vertical, null);

	}

	public void SetPixelCount(int h, int v)
	{
		horizontal = h;
		vertical = v;
	}
	
	public void SetPixel(int x, int y, int rgb)
	{
		image.setRGB(x, y, rgb);
		repaint();
	}
}

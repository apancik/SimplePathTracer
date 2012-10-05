package cz.muni.fi.pathtracer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cz.muni.fi.pathtracer.sceneobjects.Plane;
import cz.muni.fi.pathtracer.sceneobjects.Sphere;

/*
 *   |y
 *   |
 *   |_______x
 *  /
 * /z
 */
class JPanelRenderer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6976480800159064635L;
	private final BufferedImage image;

	public JPanelRenderer(final int width, final int height) throws IOException {

		super();
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		final Scene scene = new Scene(new Camera(new Vector3D(0, 1, 15), new Vector3D(0, 1, 0), 38.5, new Vector3D(0, 1, 0)));

		// simple cornells box
		// MATERIAL LIBRARY

		Material white = new Material(new Color(1, 1, 1), new Color(0, 0, 0));
        Material mirror = new Material(new Color(1, 1, 1), new Color(0, 0, 0));
        Material red = new Material(new Color(1, 0.1, 0.1), new Color(0.1, 0.1, 0.1));
        Material green = new Material(new Color(0.1, 1, 0.1), new Color(0.1, 0.1, 0.1));
        Material blue = new Material(new Color(0.1, 0.1, 1), new Color(0.1, 0.1, 0.1));
        Material light = new Material(new Color(1, 1, 1), new Color(1, 1, 1));
        Material blueLight = new Material(new Color(0, 0, 0), new Color(0, 0, 1));
        Material sun = new Material(new Color(1, 1, 1), new Color(1, 1, 0.8));
        Material glass = new Material(new Color(1, 1, 1), new Color(0, 0, 0));

		// ADD YOUR SCENE DEFINITION HERE
		scene.getObjects().add(new Plane(new Vector3D(0, 1, 0), 0, green));
		scene.getObjects().add(new Plane(new Vector3D(0, -1, 0), 5, light));
		scene.getObjects().add(new Plane(new Vector3D(0, 0, 1), 10, red));
		scene.getObjects().add(new Plane(new Vector3D(-1, 0, 0), 5, blue));
		scene.getObjects().add(new Plane(new Vector3D(1, 0, 0), 5, blue));

		Sphere sphere = new Sphere(new Vector3D(-2.0f, 2.0f, 0), 1.0f, white);
		scene.getObjects().add(sphere);
		
		sphere = new Sphere(new Vector3D(0, 2.0f, 0), 1.0f, red);
		scene.getObjects().add(sphere);
		
		sphere = new Sphere(new Vector3D(2.0f, 2.0f, 0), 1.0f, white);
		scene.getObjects().add(sphere);

		final PathTracer pathTracer = new PathTracer(width, height, new Color(111.0 / 255.0, 154.0 / 255.0, 232.0 / 256.0), scene);

		// Render animation
		long x;
		for (int i = 0; i < 1; i++) {
			sphere.setCenter(new Vector3D(2.0f + (double) i / 10, 2.0f, 0));
			x = Calendar.getInstance().getTimeInMillis();
			System.out.println("Begin:" + new Date());
			pathTracer.render(this.image);
			System.out.println("End:" + new Date() + " " + (Calendar.getInstance().getTimeInMillis() - x));
			ImageIO.write(this.image, "png", new File("fast" + i + ".png"));
		}

	}

	@Override
	protected void paintComponent(final Graphics g) {
		g.drawImage(this.image, 0, 0, this);
	}
}

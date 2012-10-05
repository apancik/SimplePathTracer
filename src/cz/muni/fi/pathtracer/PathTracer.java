package cz.muni.fi.pathtracer;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PathTracer {

	private static final int MAX_DEPTH = 1;
	private static final int SAMPLES = 5;
	private final double screenWidth;
	private final double screenHeight;
	private final Color backgroundColor;
	private final Scene scene;
	private final Random randomGenerator = new Random();

	public PathTracer(final int screenWidth, final int screenHeight, final Color backgroundColor, final Scene scene) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.backgroundColor = backgroundColor;
		this.scene = scene;
	}

	private Vector3D pickRandomDirectionOnHemisphere(final Vector3D normal) {
		final Vector3D randomDirection = new Vector3D(this.randomGenerator.nextDouble() * 2 - 1, this.randomGenerator.nextDouble() * 2 - 1, this.randomGenerator.nextDouble() * 2 - 1).normalize();
		if (randomDirection.dot(normal) < 0.0) {
			return randomDirection.times(-1);
		} else {
			return randomDirection;
		}
	}

	private Color radiance(final Ray ray, int depth) {
		final Intersection intersection = this.scene.getNearestIntersection(ray);

		// Uncomment for different render modes
		// return intersection.getSceneObject().getMaterial().getReflectance().times(-ray.getDirection().dot(intersection.getNormal()));
		// return new Color( Math.min(intersection.getDistance()/40,1.0), Math.min(intersection.getDistance()/40,1), Math.min(intersection.getDistance()/40,1));
		// return new Color(Math.abs(intersection.getNormal().getX()), Math.abs(intersection.getNormal().getY()), Math.abs(intersection.getNormal().getZ()));

		if (intersection == null) {
			return this.backgroundColor;
		} else {
			final Material material = intersection.getSceneObject().getMaterial();
			Color finalRadiance = material.getEmmision();

			if (depth > 0) {
				// Standard diffuse
				final Ray randomRay = new Ray(intersection.getPoint(), this.pickRandomDirectionOnHemisphere(intersection.getNormal()));

				final double diffuseConstant = randomRay.getDirection().dot(intersection.getNormal());

				finalRadiance = finalRadiance.plus(this.radiance(randomRay, --depth).times(diffuseConstant).times(material.getReflectance()));
			}

			return finalRadiance;
		}
	}

	public void render(final BufferedImage result) {
		final Vector3D screenCenter = this.scene.getCamera().getPosition().plus(this.scene.getCamera().getLookAt());
		final Vector3D equator = this.scene.getCamera().getLookAt().cross(this.scene.getCamera().getUp()).normalize();
		final Vector3D up = equator.cross(this.scene.getCamera().getLookAt()).normalize().times(this.screenHeight / this.screenWidth);

		for (int y = (int) (-this.screenHeight / 2); y < this.screenHeight / 2; y++) {
			for (int x = (int) (-this.screenWidth / 2); x < this.screenWidth / 2; x++) {
				if (y % 2 == 0) {
					final Vector3D pixelPosition = screenCenter.minus(equator.times(x / this.screenWidth)).minus(up.times(y / this.screenHeight));

					// result.setRGB((int) (x + screenWidth / 2), (int) (y + screenHeight / 2), radiance(new
					// Ray(scene.getCamera().getPosition(), pixelPosition.minus(scene.getCamera().getPosition())),
					// MAX_DEPTH).getDrawingColor().getRGB());

					Color sumRadiance = new Color(0, 0, 0);
					for (int i = 0; i < SAMPLES; i++) {
						sumRadiance = sumRadiance.plus(this.radiance(new Ray(this.scene.getCamera().getPosition(), pixelPosition.minus(this.scene.getCamera().getPosition())), MAX_DEPTH));
					}

					result.setRGB((int) (x + this.screenWidth / 2), (int) (y + this.screenHeight / 2), sumRadiance.times(1.0 / SAMPLES).getDrawingColor().getRGB());
					result.setRGB((int) (x + this.screenWidth / 2), (int) (y + this.screenHeight / 2 + 1), sumRadiance.times(1.0 / SAMPLES).getDrawingColor().getRGB());
				}
			}
		}

	}
}

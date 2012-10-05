package cz.muni.fi.pathtracer.sceneobjects;

import java.util.Random;

import cz.muni.fi.pathtracer.Intersection;
import cz.muni.fi.pathtracer.Material;
import cz.muni.fi.pathtracer.Ray;
import cz.muni.fi.pathtracer.Vector3D;

/**
 * 
 * @author Andrej Pancik
 */
public class Sphere implements IRenderable {

	private Vector3D center;
	private final double radius;
	private final Material surface;
	private final Random randomGenerator = new Random();

	public Sphere(final Vector3D center, final double radius, final Material surface) {
		this.center = center;
		this.radius = radius;
		this.surface = surface;
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		final Vector3D vector = this.center.minus(ray.getOrigin());

		if (vector.length() < this.radius) {
			return null; // ray origin is inside of the sphere
		}

		final double t0 = vector.dot(ray.getDirection());

		final double d2 = vector.dot(vector) - Math.pow(t0, 2);

		final double td2 = Math.pow(this.radius, 2) - d2;

		if (td2 >= 0) {
			final double td = Math.sqrt(td2);

			return new Intersection(ray, this, Math.min(t0 + td, t0 - td));
		} else {
			return null;
		}
	}

	@Override
	public Material getMaterial() {
		return this.surface;
	}

	@Override
	public Vector3D getNormal(final Vector3D pos) {
		return pos.minus(this.center).normalize();
	}

	// TODO zmaazaaat
	public void setCenter(final Vector3D center) {
		this.center = center;
	}

}

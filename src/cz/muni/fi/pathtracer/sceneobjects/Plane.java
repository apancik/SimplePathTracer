package cz.muni.fi.pathtracer.sceneobjects;

import cz.muni.fi.pathtracer.Intersection;
import cz.muni.fi.pathtracer.Material;
import cz.muni.fi.pathtracer.Ray;
import cz.muni.fi.pathtracer.Vector3D;

/**
 * 
 * @author Andrej Pancik
 */
public class Plane implements IRenderable {

	private final Material surface;
	private final Vector3D normalVector;
	private final double offset;

	public Plane(final Vector3D normal, final double offset, final Material surface) {
		this.normalVector = normal.normalize();
		this.offset = offset;
		this.surface = surface;
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		final double denom = this.normalVector.dot(ray.getDirection());
		if (denom > 0) {
			return null;
		}

		return new Intersection(ray, this, (ray.getOrigin().dot(this.normalVector) + this.offset) / -denom);
	}

	@Override
	public Material getMaterial() {
		return this.surface;
	}

	@Override
	public Vector3D getNormal(final Vector3D pos) {
		return this.normalVector;
	}
}

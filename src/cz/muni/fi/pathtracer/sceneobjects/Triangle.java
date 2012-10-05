package cz.muni.fi.pathtracer.sceneobjects;

import cz.muni.fi.pathtracer.Intersection;
import cz.muni.fi.pathtracer.Material;
import cz.muni.fi.pathtracer.Ray;
import cz.muni.fi.pathtracer.Vector3D;

public class Triangle implements IRenderable {

	private final Vector3D firstPoint;
	private final Vector3D edge1;
	private final Vector3D edge2;
	private final Vector3D normalVector;
	private final double epsilon = 10e-6;
	private final double planeOffset;
	private final Material surface;

	public Triangle(final Vector3D pos1, final Vector3D pos2, final Vector3D pos3, final Material surface) {
		this.firstPoint = pos1;
		this.surface = surface;

		this.edge1 = pos2.minus(pos1);
		this.edge2 = pos3.minus(pos1);

		this.normalVector = this.edge1.cross(this.edge2).normalize();
		this.planeOffset = -pos1.dot(this.normalVector);
	}

	@Override
	public Intersection getIntersection(final Ray ray) {
		// Fast, Minimum Storage Ray Triangle Intersection, Moller-Trumbore Method

		final Vector3D p = ray.getDirection().cross(this.edge2);

		final double det = this.edge1.dot(p);

		if (Math.abs(det) < this.epsilon) {
			return null;
		}

		final double inv_det = 1.0 / det;

		final Vector3D t = ray.getOrigin().minus(this.firstPoint);

		final double u = t.dot(p) * inv_det;
		if (u < 0.0 || u > 1.0) {
			return null;
		}

		final Vector3D q = t.cross(this.edge1);

		final double v = ray.getDirection().dot(q) * inv_det;
		if (v < 0.0 || u + v > 1.0) {
			return null;
		}

		return new Intersection(ray, this, (ray.getOrigin().dot(this.normalVector) + this.planeOffset) / -this.normalVector.dot(ray.getDirection()));
	}

	@Override
	public Material getMaterial() {
		return this.surface;
	}

	@Override
	public Vector3D getNormal(final Vector3D position) {
		return this.normalVector;
	}
}

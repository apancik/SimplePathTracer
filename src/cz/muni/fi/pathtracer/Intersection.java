package cz.muni.fi.pathtracer;

import cz.muni.fi.pathtracer.sceneobjects.IRenderable;

/**
 * Intersection class containing information about ray, scene object and intersection point
 * 
 * @author Andrej Pancik
 */
public class Intersection {

	private final Ray ray;
	private final double distance;
	private final IRenderable sceneObject;
	private final Vector3D intersectionPoint;

	/**
	 * Basic Intersection contructor
	 * 
	 * @param ray
	 *            the ray
	 * @param sceneObject
	 *            the scene object
	 * @param distance
	 *            multiplier determining distance of intersection point from the ray origin
	 */
	public Intersection(final Ray ray, final IRenderable sceneObject, final double distance) {
		this.ray = ray;
		this.sceneObject = sceneObject;
		this.distance = distance;
		this.intersectionPoint = this.ray.getOrigin().plus(this.ray.getDirection().times(this.distance)); // pozor zapuzdrit
																											// to
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * 
	 * @return the surface normal at intersection point
	 */
	public Vector3D getNormal() {
		return this.getSceneObject().getNormal(this.getPoint());
	}

	/**
	 * 
	 * @return intersection point
	 */
	public Vector3D getPoint() {
		return this.intersectionPoint;
	}

	/**
	 * @return the ray
	 */
	public Ray getRay() {
		return this.ray;
	}

	/**
	 * @return the scene object
	 */
	public IRenderable getSceneObject() {
		return this.sceneObject;
	}
}

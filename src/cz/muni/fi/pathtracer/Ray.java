package cz.muni.fi.pathtracer;

/**
 * Ray class containing information about ray origin and direction.
 * 
 * @author Andrej Pancik
 */
public class Ray {

	private final Vector3D origin;
	private final Vector3D direction;

	/**
	 * Basic ray class construcor
	 * 
	 * @param origin
	 *            the ray origin
	 * @param direction
	 *            the ray direction
	 */
	public Ray(final Vector3D origin, final Vector3D direction) {
		this.origin = origin;
		this.direction = direction.normalize();
	}

	/**
	 * @return the direction
	 */
	public Vector3D getDirection() {
		return this.direction;
	}

	/**
	 * @return the origin
	 */
	public Vector3D getOrigin() {
		return this.origin;
	}
}

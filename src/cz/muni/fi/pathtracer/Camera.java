package cz.muni.fi.pathtracer;

/**
 * A camera class containing information about a camera position, a relative position of view window and a general upward
 * direction
 * 
 * @author Andrej Pancik
 */
public class Camera {

	private final Vector3D position;
	private final Vector3D lookAt;
	private final Vector3D up;

	/**
	 * Primary Camera constructor
	 * 
	 * @param position
	 *            the camera position
	 * @param lookAtPosition
	 *            the position to which the camera is oriented
	 * @param fovDegrees
	 *            field of view angle in degrees
	 * @param top
	 *            the vector of general upward direction
	 */
	public Camera(final Vector3D position, final Vector3D lookAtPosition, final double fovDegrees, final Vector3D top) {
		this.position = position;
		this.up = top;

		final double fov = Math.PI / 180.0 * fovDegrees;
		final double f = 0.5 / Math.tan(fov / 2.0);
		this.lookAt = lookAtPosition.minus(position).normalize().times(f);
	}

	/**
	 * Alternative Camera constructor with direct value insertion
	 * 
	 * @param position
	 *            the camera position
	 * @param lookAt
	 *            the vector from camera position to the center of the view window
	 * @param top
	 *            the vector of general upward direction
	 */
	public Camera(final Vector3D position, final Vector3D lookAt, final Vector3D top) {
		this.position = position;
		this.lookAt = lookAt;
		this.up = top;
	}

	/**
	 * @return the vector from camera position to the center of the view window
	 */
	public Vector3D getLookAt() {
		return this.lookAt;
	}

	/**
	 * @return the camera position
	 */
	public Vector3D getPosition() {
		return this.position;
	}

	/**
	 * @return the vector of general upward direction
	 */
	public Vector3D getUp() {
		return this.up;
	}
}

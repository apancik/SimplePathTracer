package cz.muni.fi.pathtracer;

public class Vector3D {

	private static final double EPS = 1.0E-4;
	private final double x;
	private final double y;
	private final double z;

	public Vector3D(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D cross(final Vector3D vector) {
		return new Vector3D(this.getY() * vector.getZ() - this.getZ() * vector.getY(), this.getZ() * vector.getX() - this.getX() * vector.getZ(), this.getX() * vector.getY() - this.getY() * vector.getX());
	}

	public double dot(final Vector3D vector) {
		return this.getX() * vector.getX() + this.getY() * vector.getY() + this.getZ() * vector.getZ();
	}

	/**
	 * @return the X
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return the Y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * @return the Z
	 */
	public double getZ() {
		return this.z;
	}

	public double length() {
		return Math.sqrt(this.dot(this));
	}

	public Vector3D minus(final Vector3D subtrahend) {
		return new Vector3D(this.getX() - subtrahend.getX(), this.getY() - subtrahend.getY(), this.getZ() - subtrahend.getZ());
	}

	public Vector3D normalize() {
		final double length = this.length();
		return this.times(Math.abs(length) < EPS ? Double.POSITIVE_INFINITY : 1 / length); // TODO Check
	}

	public Vector3D plus(final Vector3D addend) {
		return new Vector3D(this.getX() + addend.getX(), this.getY() + addend.getY(), this.getZ() + addend.getZ());
	}

	public Vector3D times(final double multiplier) {
		return new Vector3D(this.getX() * multiplier, this.getY() * multiplier, this.getZ() * multiplier);
	}

	@Override
	public String toString() {
		return "(x: " + this.x + " y: " + this.y + " z: " + this.z + ")";
	}
}

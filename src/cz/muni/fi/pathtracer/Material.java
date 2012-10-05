package cz.muni.fi.pathtracer;

public class Material {

	private final Color reflectance;
	private final Color emmision;

	public Material(final Color reflectance, final Color emmision) {
		this.reflectance = reflectance;
		this.emmision = emmision;
	}

	public Color getEmmision() {
		return this.emmision;
	}

	public Color getReflectance() {
		return this.reflectance;
	}
}

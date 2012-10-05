package cz.muni.fi.pathtracer.sceneobjects;

import cz.muni.fi.pathtracer.Intersection;
import cz.muni.fi.pathtracer.Material;
import cz.muni.fi.pathtracer.Ray;
import cz.muni.fi.pathtracer.Vector3D;

public interface IRenderable {

	public Intersection getIntersection(Ray ray);

	public Material getMaterial();

	public Vector3D getNormal(Vector3D position);
}

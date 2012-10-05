package cz.muni.fi.pathtracer;

/**
 * A color class that contains color information in three (RGB) channels. Each is represented by double value from 0 to 1.
 * Color channels are not implicitly trimmed for higher precision.
 * 
 * @author Andrej Pancik
 */
public class Color {

	private final double red;
	private final double green;
	private final double blue;

	/**
	 * Constructor without value checks (for speed up). Be aware of valid channel values (from 0 to 1).
	 * 
	 * @param red
	 *            Red channel value
	 * @param green
	 *            Green channel value
	 * @param blue
	 *            Blue channel value
	 */
	public Color(final double red, final double green, final double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * @return the Blue channel
	 */
	public double getBlue() {
		return this.blue;
	}

	/**
	 * 
	 * @return trimmed java.awt.Color ready to be drawn on screen
	 */
	public java.awt.Color getDrawingColor() {
		return new java.awt.Color((int) (this.trimColor(this.getRed()) * 255), (int) (this.trimColor(this.getGreen()) * 255), (int) (this.trimColor(this.getBlue()) * 255));
	}

	/**
	 * @return the Green channel
	 */
	public double getGreen() {
		return this.green;
	}

	/**
	 * @return the Red channel
	 */
	public double getRed() {
		return this.red;
	}

	/**
	 * 
	 * @param subtrahend
	 *            color to be subtracted from original color
	 * @return the result of subtraction
	 */
	public Color minus(final Color subtrahend) {
		return new Color(this.getRed() - subtrahend.getRed(), this.getGreen() - subtrahend.getGreen(), this.getBlue() - subtrahend.getBlue());
	}

	/**
	 * 
	 * @param addend
	 *            the color to be added to original color
	 * @return the sum of both colors
	 */
	public Color plus(final Color addend) {
		return new Color(this.getRed() + addend.getRed(), this.getGreen() + addend.getGreen(), this.getBlue() + addend.getBlue());
	}

	/**
	 * 
	 * @param multiplier
	 *            multiplier
	 * @return new color based on original color (which is multiplicand) multiplied by multiplier parameter
	 */
	public Color times(final Color multiplier) {
		return new Color(this.getRed() * multiplier.getRed(), this.getGreen() * multiplier.getGreen(), this.getBlue() * multiplier.getBlue());
	}

	/**
	 * 
	 * @param multiplier
	 *            multiplier
	 * @return new color based on original color (which is multiplicand) multiplied by multiplier parameter
	 */
	public Color times(final double multiplier) {
		return new Color(this.getRed() * multiplier, this.getGreen() * multiplier, this.getBlue() * multiplier);
	}

	@Override
	public String toString() {
		return this.red + " " + this.green + " " + this.blue;
	}

	private double trimColor(final double d) {
		return d > 1 ? 1 : d;
	}

}

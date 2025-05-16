package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a solid rectangular member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added rectangular section constructor 
 * - added compresive member support
 * 
 */
public class RectSection {

	/**
	 * How many axies of symetry
	 */
	int symetric;

	/**
	 * The material iit is made out off 1=Cold formed steel, 2=hot formed steel
	 */
	int material;

	/**
	 * Diamater
	 */
	double d;
	/**
	 * Base
	 */
	double b;
	/**
	 * Cross-sectional area
	 */
	double A;
	/**
	 * distance form center of mass to top of cross-section
	 */
	double c;
	/**
	 * length for the different cross-section oriantations should be the same for
	 * each
	 */
	double L[] = new double[3];
	/**
	 * effective length factor sould be the same for each
	 */
	double k[] = new double[3];
	/**
	 * Radius if gyration
	 */
	double r[] = new double[2];

	/**
	 * the compression information about the member
	 */
	public CompressionMember CompMember;

	/**
	 * CONSTRUCTOR, passes and calculates information about the member
	 * 
	 * @param d        Diamater (Vertical Side Length)
	 * @param b        Base
	 * @param material Material made out of
	 * @param L        Length
	 * @param k        Effective Length Factor
	 */
	public RectSection(double d, double b, int material, double L, double k) {
		this.b = b;
		this.d = d;
		A = d*b;
		c = d / 2;

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		r[0] = d / Math.sqrt(12);
		r[1] = b / Math.sqrt(12);

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {

		double J = 1 / 3 * b*Math.pow(d, 3);
		double Cw = 0;

		CompMember = new CompressionMember(symetric, material, A, r, L, k, 0, 0, Cw, J);

		return CompMember.Cr;
	}

}

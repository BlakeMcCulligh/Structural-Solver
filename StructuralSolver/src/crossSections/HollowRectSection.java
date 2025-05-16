package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a hollow rectangular member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added hollowrectangular section constructor 
 * - added compresive member support
 * 
 */
public class HollowRectSection {

	/**
	 * How many axies of symetry
	 */
	int symetric;

	/**
	 * The material iit is made out off 1=Cold formed steel, 2=hot formed steel
	 */
	int material;

	/**
	 * Outer Diamater
	 */
	double d;
	/**
	 * Outer Base
	 */
	double b;
	/**
	 * Inner Diamater
	 */
	double d1;
	/**
	 * Inner Base
	 */
	double b1;
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
	 * @param d        Outer Diamater (Vertical Side Length)
	 * @param b        Outer Base
	 * @param d1       Inner Diamater (Vertical Side Length)
	 * @param b1       Inner Base
	 * @param material Material made out of
	 * @param L        Length
	 * @param k        Effective Length Factor
	 */
	public HollowRectSection(double d, double b, double d1, double b1, int material, double L, double k) {
		this.b = b;
		this.d = d;
		this.b1 = b1;
		this.d1 = d1;
		A = b * d - b1 * d1;
		c = d / 2;

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		r[0] = Math.sqrt(((b * Math.pow(d, 3)) - (b1 * Math.pow(d1, 3)) / (12 * A)));
		r[1] = Math.sqrt(((d * Math.pow(b, 3)) - (d1 * Math.pow(b1, 3)) / (12 * A)));

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {
		double t = (d - d1) / 2;

		double J = (2 * t * t * Math.pow(b - 2, 2) * Math.pow(d - t, 2)) / (d * t + b * t - 2 * t * t);
		double Cw = 0;

		CompMember = new CompressionMember(symetric, material, A, r, L, k, 0, 0, Cw, J);

		return CompMember.Cr;
	}

}

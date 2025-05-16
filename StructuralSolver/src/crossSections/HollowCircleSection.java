package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a hollow circle member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added hollow circle section constructor 
 * - added compresive member support
 * 
 */
public class HollowCircleSection {
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
	 * Inner Diamater
	 */
	double d1;
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
	 * @param d        Outer Diamater
	 * @param d1       Inner Diamater
	 * @param material Material made out of
	 * @param L        Length
	 * @param k        Effective Length Factor
	 */
	public HollowCircleSection(double d, double d1, int material, double L, double k) {

		this.d = d;
		this.d1 = d1;
		A = Math.PI * (Math.pow(d, 2) - Math.pow(d1, 2)) / 4;
		c = d / 2;

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		r[0] = Math.sqrt(Math.pow(d, 2) - Math.pow(d1, 2)) / 4;
		r[1] = r[0];

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {

		double J = 1 / 2 * Math.PI * (Math.pow(d / 2, 4) - Math.pow(d1 / 2, 4));
		double Cw = 0;

		CompMember = new CompressionMember(symetric, material, A, r, L, k, 0, 0, Cw, J);

		return CompMember.Cr;
	}
}

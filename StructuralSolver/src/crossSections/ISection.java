package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a I member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added I section constructor 
 * - added compresive member support
 * 
 */
public class ISection {

	/**
	 * How many axies of symetry
	 */
	int symetric;

	/**
	 * The material iit is made out off 1=Cold formed steel, 2=hot formed steel
	 */
	int material;

	/**
	 * Hight
	 */
	double d;
	/**
	 * Width of flang
	 */
	double b;
	/**
	 * thikness of flange
	 */
	double t;
	/**
	 * thikness of inner web
	 */
	double w;
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
	 * Moment of Inertia
	 */
	double Ix, Iy;

	/**
	 * the compression information about the member
	 */
	public CompressionMember CompMember;

	/**
	 * CONSTRUCTOR, passes and calculates information about the member
	 * 
	 * @param d        Hight
	 * @param b        Width of flange
	 * @param t        Thickness of flange
	 * @param w        Thickness of inner web
	 * @param material Material made out of
	 * @param L        Length
	 * @param k        Effective Length Factor
	 */
	public ISection(double d, double b, double t, double w, int material, double L, double k) {

		this.d = d;
		this.b = b;
		this.t = t;
		this.w = w;
		A = 2 * b * t + w * (d - 2 * t);
		c = d / 2;

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		Ix = 1 / 12 * (b * Math.pow(d, 3) - (b - w) * Math.pow(d - 2 * t, 3));
		Iy = 1 / 12 * (2 * t * b * b * b + (d - 2 * t) * w * w * w);

		r[0] = Math.sqrt(Ix / A);
		r[1] = Math.sqrt(Iy / A);

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {

		double J = 1 / 3 * (2 * b * Math.pow(t, 3) + (Math.pow(w, 3) * (d - t)));
		double Cw = 1 / 24 * Math.pow(d - t, 2) * Math.pow(b, 3) * t;

		CompMember = new CompressionMember(symetric, material, A, r, L, k, 0, 0, Cw, J);

		return CompMember.Cr;
	}
}

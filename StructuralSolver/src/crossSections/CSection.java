package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a C member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added C section constructor 
 * - added compresive member support
 * 
 */
public class CSection {

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
	 * Cordanits of the Center of mass
	 */
	double x, y;

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
	public CSection(double d, double b, double t, double w, int material, double L, double k) {

		this.d = d;
		this.b = b;
		this.t = t;
		this.w = w;
		A = d * w + 2 * (b - w) * t;
		c = d / 2;

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		x = 1 / 2 * A * ((d - 2 * t) * w * w + 2 * t * b * b);

		Ix = 1 / 12 * (b * Math.pow(d, 3) - (b - w) * Math.pow(d - 2 * t, 3));
		Iy = 1 / 3 * (d * x * x * x + 2 * t * Math.pow(b - x, 3) - (d - 2 * t) * Math.pow(x - w, 2));

		r[0] = Math.sqrt(Ix / A);
		r[1] = Math.sqrt(Iy / A);

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {

		double J = 1 / 3 * (2 * (b - w / 2) * t * t * t + (d - t) * w * w * w);
		double a = 1 / (2 + ((d - t) * w) / (3 * (b - w / 2) * t));
		double Cw = (d - t) * (d - t) * (b - w / 2) * (b - w / 2) * (b - w / 2) * t
				* ((1 - 3 * a) / 6 + (a * a) / 2 * (1 + ((d - t) * w) / (6 * (b - w / 2) * t)));

		double xo = (x + (b - w / 2) * a - w / 2);

		CompMember = new CompressionMember(symetric, material, A, r, L, k, -xo, 0, Cw, J);

		return CompMember.Cr;
	}
}

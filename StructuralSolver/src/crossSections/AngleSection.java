package crossSections;

import memberStates.CompressionMember;

/**
 * Object for a Angle member
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added Angle section constructor 
 * - added compresive member support
 * 
 */
public class AngleSection {

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
	 * Location of center of mass
	 */
	double x, y;
	/**
	 * Moment of Inertia
	 */
	double Ix,Iy;
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
	 * @param d        Hight
	 * @param b        Width
	 * @param t        Thickness
	 * @param material Material made out of
	 * @param L        Length
	 * @param k        Effective Length Factor
	 */
	public AngleSection(double d, double b, double t, int material, double L, double k) {

		this.d = d;
		this.b = b;
		this.t = t;
		A = t * (b + c);

		this.material = material;
		symetric = 2;

		for (int i = 0; i < 3; i++) {
			this.L[i] = L;
			this.k[i] = k;
		}

		x = (b * b + (d - t) * t) / (2 * (b + (d - t)));
		y = (d * d + (b - t) * t) / (2 * (b + (d - t)));

		Ix = 1 / 3 * (t * Math.pow(d - y, 3) + b * Math.pow(y, 3) - (b - t) * Math.pow(y - t, 3));
		Iy = 1 / 3 * (t * Math.pow(b - x, 3) + d * Math.pow(x, 3) - (d - t) * Math.pow(x - t, 3));

		r[0] = Math.sqrt(Ix / A);
		r[1] = Math.sqrt(Iy / A);

	}

	/**
	 * Calaculates the members Compression resistance
	 * 
	 * @return compretion resistance
	 */
	public double CompresiveMember() {

		double J = 1 / 3 * ((d - t / 2) + (b - t / 2) * t * t * t);
		double Cw = Math.pow(t, 3) / 36 * (Math.pow(d - t / 2, 3) + Math.pow(b - t / 2, 3));

		double xo = x - t / 2;
		double yo = y - t / 2;

		CompMember = new CompressionMember(symetric, material, A, r, L, k, -xo, -yo, Cw, J);

		return CompMember.Cr;
	}
}

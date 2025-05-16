package partObjects;

/**
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16, 2025 
 * - added support to claculate compression resistance of a member
 * 
 */
public class CompressionMember {

	int Symetric;

	double A;
	double r[] = new double[2];
	double xo;
	double yo;

	// the z component is tortion buckling
	double L[] = new double[3];
	double k[] = new double[3];

	double Cw = 0;
	double J = 0;

	double Fy;
	double n;
	double E;
	double G;
	double Phi;

	public double Cr;

	/**
	 * Constructor passes and calculates information about the member being
	 * calculated.
	 * 
	 * @param Symetric number of axies of symatry
	 * @param Material what material is it made of
	 * @param A        cross-section area
	 * @param r        radius of gyration is both axies (2 by array)
	 * @param L        length (3 by array)
	 * @param k        effective length constant (3 by array)
	 * @param xo       cordinal distance from center of mass to center of shear
	 * @param yo       cordinal distance from center of mass to center of shear
	 * @param Cw       Warping torional constant
	 * @param J        St. Venant torsional constant
	 */
	public CompressionMember(int Symetric, int Material, double A, double[] r, double[] L, double[] k, double xo,
			double yo, double Cw, double J) {
		this.Symetric = Symetric;
		this.A = A;
		this.r = r;
		this.xo = xo;
		this.yo = yo;

		this.L = L;
		this.k = k;

		this.Cw = Cw;
		this.J = J;

		if (Material == 1) {
			this.Fy = 210;
			this.n = 1.34;
			this.E = 200000;
			this.G = 77000;
			this.Phi = 0.9;
		}

		Cr = findCompresiveResistance();
	}

	/**
	 * Calaculates the compresive resistance of a member
	 * 
	 * @return
	 */
	private double findCompresiveResistance() {
		double Fe = 0;

		double ro2 = Math.pow(xo, 2) + Math.pow(yo, 2) + Math.pow(r[0], 2) + Math.pow(r[1], 2);

		double Fex = (Math.pow(Math.PI, 2) * E) / Math.pow((k[0] * L[0] / r[0]), 2);
		double Fey = (Math.pow(Math.PI, 2) * E) / Math.pow((k[1] * L[1] / r[1]), 2);
		double Fez = ((Math.pow(Math.PI, 2) * E * Cw) / Math.pow((k[2] * L[2]), 2) + G * J) * (1 / A * ro2);

		if (Symetric == 2) {
			Fe = doubleSymetric(Fex, Fey, Fez);

		} else if (Symetric == 1) {
			Fe = singleSymetric(Fex, Fey, Fez, ro2);

		} else {
			Fe = notSymetric(Fex, Fey, Fez, ro2);

		}

		double lambda = Math.sqrt(Fy / Fe);
		double Cr = (Phi * A * Fy) / Math.pow((1 + Math.pow(lambda, 2 * n)), 1 / n);

		return Cr;
	}

	/**
	 * calculates Fe for a double symetric member
	 * 
	 * @param Fex
	 * @param Fey
	 * @param Fez
	 * @return Fe
	 */
	private static double doubleSymetric(double Fex, double Fey, double Fez) {

		double Fe = Fex;

		if (Fe < Fey) {
			Fe = Fey;
		}

		if (Fe < Fez) {
			Fe = Fez;
		}

		return Fe;
	}

	/**
	 * calculates Fe for a singular symetric member
	 * 
	 * @param Fex
	 * @param Fey
	 * @param Fez
	 * @param ro2
	 * @return Fe
	 */
	private double singleSymetric(double Fex, double Fey, double Fez, double ro2) {

		double gamma = 1 - (Math.pow(xo, 2) + Math.pow(yo, 2)) / ro2;

		double Fe = 0;

		Fe = ((Fey + Fez) / (2 * gamma))
				* Math.pow((1 - (1 - (4 * Fey * Fez * gamma) / Math.pow((Fey + Fez), 2))), 0.5);

		return Fe;
	}

	/**
	 * calculates Fe for a not symetric member
	 * 
	 * @param Fex
	 * @param Fey
	 * @param Fez
	 * @param ro2
	 * @return Fe
	 */
	private double notSymetric(double Fex, double Fey, double Fez, double ro2) {
		double Fe = Double.POSITIVE_INFINITY;

		double a = 1 - (Math.pow(xo, 2) - Math.pow(yo, 2)) / ro2;
		double b = -1 * (Fex + Fey + Fez);
		double c = Fex * Fey + Fex * Fez + Fey * Fez;
		double d = -1 * (Fex * Fey * Fez + Fex * (Math.pow(yo, 2) / ro2) - Fey * (Math.pow(xo, 2) / ro2));
		double roots[] = solveCubic(a, b, c, d);

		for (int i = 0; i == roots.length; i++) {
			if (roots[i] < Fe) {
				Fe = roots[i];
			}
		}

		return Fe;
	}

	/**
	 * finds the roots of a cubic function. used for calculatig Fe for a not
	 * symetric member
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return the roots of the cubic
	 */
	private double[] solveCubic(double a, double b, double c, double d) {
		if (a == 0) {
			double soutions[] = { ((-c + Math.sqrt(Math.pow(c, 2) - 4 * a * c)) / 2),
					(-c - Math.sqrt(Math.pow(c, 2) - 4 * a * c)) / 2 };
			return soutions;
		}

		double p = (3 * a * c - b * b) / (3 * a * a);
		double q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);
		double delta = (q * q / 4) + (p * p * p / 27);

		if (delta > 0) {
			double u = Math.cbrt(-q / 2 + Math.sqrt(delta));
			double v = Math.cbrt(-q / 2 - Math.sqrt(delta));
			double y1 = u + v;
			double soutions[] = { (y1 - b / (3 * a)) };
			return soutions;

		} else if (delta == 0) {
			double y1 = 2 * Math.cbrt(-q / 2);
			double y2 = -Math.cbrt(-q / 2);
			double soutions[] = { (y1 - b / (3 * a)), (y2 - b / (3 * a)) };
			return soutions;

		} else {
			double theta = Math.acos((-q / 2) / Math.sqrt(-(p * p * p) / 27));
			double y1 = 2 * Math.sqrt(-p / 3) * Math.cos(theta / 3);
			double y2 = 2 * Math.sqrt(-p / 3) * Math.cos((theta + 2 * Math.PI) / 3);
			double y3 = 2 * Math.sqrt(-p / 3) * Math.cos((theta + 4 * Math.PI) / 3);
			double soutions[] = { (y1 - b / (3 * a)), (y2 - b / (3 * a)), (y3 - b / (3 * a)) };
			return soutions;
		}
	}

}

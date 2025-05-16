package flatPlates;

/**
 * Solves for the Bending stress, defflection, and Max Reaction of a rectangular plate that is simply supported
 * 
 * @author Blake M.
 * 
 * @version 1.0
 * @date May 12, 2025
 * 
 * 
 */
public class RectPlateSimplySuported {

	/**
	 * 
	 * @param length
	 * @param width
	 * @param thikness
	 * @param distributedForce
	 * @param youngsModules
	 * @return 0: Bending Stress, 1: Defflection, 2: Max Reaction
	 */
	public static double[] Solver(double length, double width, double thikness, double distributedForce, double youngsModules) {
		
		double answers[] = new double[3];
		
		answers[1] = UniformRecSimpSupportStress(length, width, distributedForce, thikness);
		answers[2] = UniformRecSimpSupportDefflection(length, width, distributedForce, thikness, youngsModules);
		answers[3] = UniformRecSimpSupportMaxReacton(length, width, distributedForce);	
		
		return answers;
	}
	
	private static double UniformRecSimpSupportStress(double a, double b, double q, double t) {
		
		double ratio = a/b;
		
		double B = 0.75;
		
		double ratioSimp = Math.round(ratio*10)/10;
		
		if (ratioSimp == 1.0) {
			B = 0.2874;
		} else if (ratioSimp == 1.1){
			B = 0.3318;
		} else if (ratioSimp == 1.2){
			B = 0.3762;
		} else if (ratioSimp == 1.3){
			B = 0.4146;
		} else if (ratioSimp == 1.4){
			B = 0.453;
		} else if (ratioSimp == 1.5){
			B = 0.4851;
		} else if (ratioSimp == 1.6){
			B = 0.5172;
		} else if (ratioSimp == 1.7){
			B = 0.543;
		} else if (ratioSimp == 1.8){
			B = 0.5688;
		} else if (ratioSimp == 1.9){
			B = 0.5895;
		} else if (ratioSimp == 2.0){
			B = 0.6102;
		} else if (ratioSimp > 2.0 && ratioSimp <= 2.5){
			B = 0.6618;
		} else if (ratioSimp > 2.5 && ratioSimp <= 3.0){
			B = 0.7134;
		} else if (ratioSimp > 3.0 && ratioSimp <= 3.5){
			B = 0.7272;
		} else if (ratioSimp > 3.5 && ratioSimp <= 4.5){
			B = 0.741;
		} else if (ratioSimp > 4.5 && ratioSimp <= 5.0){
			B = 0.7476;
		}
		
		double sigma = (B*q*Math.pow(b, 2))/Math.pow(t,2);
		
		return sigma;		
	}
	
	private static double UniformRecSimpSupportDefflection(double a, double b, double q, double t, double E) {
		
		double ratio = a/b;
		
		double alpha = 0.1421;
		
		double ratioSimp = Math.round(ratio*10)/10;
		
		if (ratioSimp == 1.0) {
			alpha = 0.0444;
		} else if (ratioSimp == 1.1){
			alpha = 0.053;
		} else if (ratioSimp == 1.2){
			alpha = 0.0616;
		} else if (ratioSimp == 1.3){
			alpha = 0.0693;
		} else if (ratioSimp == 1.4){
			alpha = 0.0770;
		} else if (ratioSimp == 1.5){
			alpha = 0.0838;
		} else if (ratioSimp == 1.6){
			alpha = 0.0902;
		} else if (ratioSimp == 1.7){
			alpha = 0.09615;
		} else if (ratioSimp == 1.8){
			alpha = 0.1017;
		} else if (ratioSimp == 1.9){
			alpha = 0.10635;
		} else if (ratioSimp == 2.0){
			alpha = 0.111;
		} else if (ratioSimp > 2.0 && ratioSimp <= 2.5){
			alpha = 0.12225;
		} else if (ratioSimp > 2.5 && ratioSimp <= 3.0){
			alpha = 0.13335;
		} else if (ratioSimp > 3.0 && ratioSimp <= 3.5){
			alpha = 0.13675;
		} else if (ratioSimp > 3.5 && ratioSimp <= 4.5){
			alpha = 0.14;
		} else if (ratioSimp > 4.5 && ratioSimp <= 5.0){
			alpha = 0.1417;
		}
		
		double DeltaY = (-1*alpha*q*Math.pow(b,4))/(E*Math.pow(t,2));
		
		return DeltaY;
	}
	
	private static double UniformRecSimpSupportMaxReacton(double a, double b, double q) {
		
		double ratio = a/b;
		
		double y = 0.5;
		
		double ratioSimp = Math.round(ratio*15)/15;
		
		if (ratioSimp == 1.0) {
			y = 0.42;
		} else if (ratioSimp == 1.2){
			y = 0.455;
		} else if (ratioSimp == 1.4){
			y = 0.478;
		} else if (ratioSimp == 1.6){
			y = 0.491;
		} else if (ratioSimp == 1.8){
			y = 0.499;
		} else if (ratioSimp == 2.0){
			y = 0.503;
		} else if (ratioSimp > 2.0 && ratioSimp <= 2.5){
			y = 0.503;
		} else if (ratioSimp > 2.5 && ratioSimp <= 3.5){
			y = 0.505;
		} else if (ratioSimp > 3.50 && ratioSimp <= 4.5){
			y = 0.502;
		} else if (ratioSimp > 4.5 && ratioSimp <= 5.0){
			y = 0.501;
		}
		
		double R = y*q*b;
		
		return R;
	}
}

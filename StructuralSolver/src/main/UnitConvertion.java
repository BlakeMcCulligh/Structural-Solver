package main;

/**
 * Converts units
 * 
 * @author Blake M.
 * 
 * @version 1.0 
 * May 16 2025 
 * - added Linear to mm convertion
 * 
 */
public class UnitConvertion {

	/**
	 * Coverts a linear unit to mm
	 * 
	 * @param InValue the double to be converted
	 * @param unit    the curent unit
	 * @return the double in units of mm
	 */
	public static double linearTomm(double InValue, String unit) {

		if (unit.equals("mm")) {
			return InValue;
		} else if (unit.equals("m")) {
			return InValue * 1000;
		} else if (unit.equals("in")) {
			return InValue * 25.4;
		} else {
			return InValue * 304.8;
		}
	}
}

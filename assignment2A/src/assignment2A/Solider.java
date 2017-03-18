/**
 * Project: assignment2A
 * File: Solider.java
 * Date: 2015年11月18日
 * Time: 下午1:23:58
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Solider extends Piece {
	public static String side;

	public Solider(String side) {
		super(side, "p");
		setSide(side);
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy,
			String side) {
		/* first step */
		if ( side.equals("b")) {
			if(fy==1){
				if (sx == fx && (sy == fy + 2 || sy == fy + 1)) {
					return true;
				} else {
					return false;
				}
			}else{
				if (sx == fx && sy == fy + 1) {
					return true;
				} else {
					return false;
				}
			}
			
		} else if ( side.equals("w")){
			if(fy==6){
				if (sx == fx && (sy == fy - 2 || sy == fy - 1)) {
					return true;
				} else {
					return false;
				}
			}else{
				if (sx == fx && sy == fy - 1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}

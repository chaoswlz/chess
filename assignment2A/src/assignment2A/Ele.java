/**
 * Project: assignment2A
 * File: Ele.java
 * Date: 2015年11月18日
 * Time: 下午1:26:35
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Ele extends Piece {
	public String side;

	public Ele(String side) {
		super(side, "x");
		setSide(side);
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
		if((fx-sx)==(fy-sy)){
			return true;
		}else if ((fx - sx)==(sy-fy)){
			return true;
		}else{
			return false;
		}
	}
}

/**
 * Project: assignment2A
 * File: Queen.java
 * Date: 2015年11月18日
 * Time: 下午1:25:38
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Queen extends Piece{
	public String side;

	public Queen(String side) {
		super(side,"q");
		setSide(side);
	}
	
	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
		if((fx-sx)==(fy-sy)){
			return true;
		}else if ((fx - sx)==(sy-fy)){
			return true;
		}if (fx == sx && fy != sy) {
			return true;
		} else if (fx != sx && fy == sy) {
			return true;
		} else {
			return false;
		}
	}
}


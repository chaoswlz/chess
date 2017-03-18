/**
 * Project: assignment2A
 * File: Castle.java
 * Date: 2015年11月18日
 * Time: 下午1:24:51
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Castle extends Piece {
	public String side;

	public Castle(String side) {
		super(side, "r");
		setSide(side);
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
		if (fx == sx && fy != sy) {
			return true;
		} else if (fx != sx && fy == sy) {
			return true;
		} else {
			return false;
		}
	}

}

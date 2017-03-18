/**
 * Project: assignment2A
 * File: King.java
 * Date: 2015年11月18日
 * Time: 下午1:22:05
 */

package assignment2A;

import javax.swing.ImageIcon;

/**
 * @author Lize Wu A00917368
 *
 */
public class King extends Piece {
	public String side;

	public King(String side) {
		super(side, "k");
		setSide(side);
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
		/* 1-1 */
		if ((sx == fx - 1 || sx == fx + 1) && (sy == fy - 1 || sy == fy + 1)) {
			return true;
			/* 1-0 */
		} else if (sx == fx && (sy == fy - 1 || sy == fy + 1)) {
			return true;
			/* 0-1 */
		} else if (sy == fy && (sx == fx - 1 || sx == fx + 1)) {
			return true;
		} else {
			return false;
		}
	}
}

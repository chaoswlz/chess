/**
 * Project: assignment2A
 * File: Knights.java
 * Date: 2015年11月18日
 * Time: 下午12:35:04
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Knights extends Piece {
	public String side;
	public Position[] ps = new Position[8];
	public Position p;

	public Knights(String side) {
		super(side, "n");
		setSide(side);
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
            /* 2-1 */
         if ((sx == fx - 2 || sx == fx + 2)
                && (sy == fy - 1 || sy == fy + 1)) {
            return true;
            /* 1-2 */
        } else if ((sx == fx - 1 || sx == fx + 1)
                && (sy == fy - 2 || sy == fy + 2)) {
            return true;
        } else {
            return false;
        }
    }

}

/**
 * Project: assignment2A
 * File: Square.java
 * Date: 2015年11月18日
 * Time: 下午1:27:16
 */

package assignment2A;

/**
 * @author Lize Wu A00917368
 *
 */
public class Square extends Piece{
	public String side="";

	public Square(String side) {
		super("","");
	}
	
	@Override
	public String getSide(){
		return side;
	}
	
}


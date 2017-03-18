/**
 * Project: assignment2A
 * File: Piece.java
 * Date: 2015年11月7日
 * Time: 下午2:44:55
 */

package assignment2A;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Lize Wu A00917368
 *
 */
public class Piece extends JLabel{
	public String name;
	public String side;
	private final static Dimension preferredSize=new Dimension(100,100);
	transient ImageIcon im ;
	public Position[] ps =new Position[0];

	/**
	 * @param name
	 */
	public Piece(String side,String name) {
		super();
		setName(name);
		setSide(side);
		im =new ImageIcon("image\\"+this.side+this.name+".gif");
		setIcon(im);
		this.setPreferredSize(preferredSize);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setSide(String side) {
		this.side=side;
	}

	public String getSide(){
		return side;	
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void eat(Piece temp){
		setSide(temp.getSide());
		setName(temp.getName());
		im = new ImageIcon("image\\"+this.side+this.name+".gif");
		setIcon(im);
		
	}
	
	public Position[] getPossible(int pi, int pj){
		return ps;
	}

	public static boolean isValidMove(int fx, int fy, int sx, int sy) {
		
		return true;
	}
	
	public static boolean isValidMove(int fx, int fy, int sx, int sy,String side) {
		
		return true;
	}
	
}

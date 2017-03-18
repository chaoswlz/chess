package assignment2A;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Board extends JFrame implements Serializable {
	/**
	 * 
	 */
	private final JFileChooser LoadFile = new JFileChooser();
	private final JFileChooser saveFile = new JFileChooser();
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private Piece beEat = new Piece("m", "s");
	JPanel[][] panel = new JPanel[8][8];
	Piece[][] piece = new Piece[8][8];
	Piece[][] t = new Piece[8][8];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Board() {
		setResizable(false);
		setTitle("Chese");
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new MigLayout("",
				"[100]0[100]0[100]0[100]0[100]0[100]0[100]0[100]",
				"[100]0[100]0[100]0[100]0[100]0[100]0[100]0[100]"));
		setContentPane(contentPane);

		HandlerClass hand = new HandlerClass();
		beEat.addMouseListener(hand);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				panel[i][j] = new JPanel();
				if ((i + j) % 2 == 0) {
					panel[i][j].setBackground(Color.BLACK);
				}
				Piece p;
				if (j == 1) {
					p = new Solider("b");
				} else if (j == 6) {
					p = new Solider("w");
				} else if (j == 0 && (i == 0 || i == 7)) {
					p = new Castle("b");
				} else if (j == 7 && (i == 0 || i == 7)) {
					p = new Castle("w");
				} else if (j == 0 && (i == 1 || i == 6)) {
					p = new Knights("b");
				} else if (j == 7 && (i == 1 || i == 6)) {
					p = new Knights("w");
				} else if (j == 0 && (i == 2 || i == 5)) {
					p = new Ele("b");
				} else if (j == 7 && (i == 2 || i == 5)) {
					p = new Ele("w");
				} else if (j == 0 && i == 3) {
					p = new Queen("b");
				} else if (j == 7 && i == 3) {
					p = new Queen("w");
				} else if (j == 0 && i == 4) {
					p = new King("b");
				} else if (j == 7 && i == 4) {
					p = new King("w");
				} else {
					p = new Piece("m", "s");
				}

				p.addMouseListener(new MouseAdapter() {

				});
				piece[i][j] = p;
				piece[i][j].addMouseListener(hand);

				panel[i][j].validate();
				contentPane.add(panel[i][j], "cell " + i + " " + j + ",grow");

			}

		}
		setPiece(piece);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JFileChooser.APPROVE_OPTION == LoadFile
						.showOpenDialog(null)) {// 用户点击了确定
					System.out.println(LoadFile.getSelectedFile()
							.getAbsolutePath());
					try {
						FileInputStream fileIn = new FileInputStream(LoadFile
								.getSelectedFile().getAbsolutePath());
						ObjectInputStream in = new ObjectInputStream(fileIn);
						t = (Piece[][]) in.readObject();
						for (int i = 0; i < 8; i++) {
							for (int j = 0; j < 8; j++) {
								piece[i][j].eat(t[i][j]);
								System.out.println(t[i][j].side + t[i][j].name);
							}
						}
						setPiece(piece);
						in.close();
						fileIn.close();
					} catch (FileNotFoundException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					} catch (ClassNotFoundException e) {

						e.printStackTrace();
					}

				}
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JFileChooser.APPROVE_OPTION == saveFile
						.showSaveDialog(null)) {// 用户点击了确定
					System.out.println(saveFile.getSelectedFile()
							.getAbsolutePath());
					try {
						FileOutputStream fileout = new FileOutputStream(
								saveFile.getSelectedFile().getAbsolutePath());
						ObjectOutputStream out = new ObjectOutputStream(fileout);
						out.writeObject(piece);
						out.close();
						fileout.close();

					} catch (FileNotFoundException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}

			}
		});
		mnFile.add(mntmSave);
	}

	public void setPiece(Piece[][] piece) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				panel[i][j].add(piece[i][j]);
			}
		}
	}
	//check whole line between two points
	public boolean checkEle(int x, int y, int xx, int yy) {
		if (x < xx) {
			if (y < yy) {
				for (int i = y + 1, j = x + 1; i < yy && j < xx; i++, j++) {
					System.out
							.printf("%d %d %s\n", j, i, piece[j][i].getName());
					if (!piece[j][i].getName().equals("s")) {
						return false;

					}

				}
			} else {
				for (int i = yy + 1, j = xx - 1; i < y && x < j; i++, j--) {
					System.out
							.printf("%d %d %s\n", j, i, piece[j][i].getName());
					if (!piece[j][i].getName().equals("s")) {
						return false;

					}

				}
			}
		} else if (xx < x) {
			if (y < yy) {
				for (int i = yy - 1, j = xx + 1; y < i && j < x; i--, j++) {
					System.out
							.printf("%d %d %s\n", j, i, piece[j][i].getName());
					if (!piece[j][i].getName().equals("s")) {
						return false;

					}

				}
			} else {
				for (int i = yy + 1, j = xx + 1; i < y && j < x; i++, j++) {
					System.out
							.printf("%d %d %s\n", j, i, piece[j][i].getName());
					if (!piece[j][i].getName().equals("s")) {
						return false;

					}

				}
			}
		}

		return true;
	}

	public boolean checkCastle(int x, int y, int xx, int yy) {
		if (x == xx) {
			if (y < yy) {
				for (int i = y + 1; i < yy; i++) {
					if (!piece[x][i].getName().equals("s")) {
						return false;
					}
				}
			} else {
				for (int i = yy + 1; i < y; i++) {
					if (!piece[x][i].getName().equals("s")) {
						return false;
					}
				}
			}
		} else if (yy == y) {
			if (x < xx) {
				for (int i = x + 1; i < xx; i++) {
					if (!piece[i][y].getName().equals("s")) {
						return false;
					}
				}
			} else {
				for (int i = xx + 1; i < x; i++) {
					if (!piece[i][y].getName().equals("s")) {
						return false;
					}
				}
			}
		}
		return true;
	}
	// mouse listener
	private class HandlerClass implements MouseListener {
		private int count = 0;
		private int counter = 0;
		private Piece temp = new Piece("m", "s");

		int x;
		int y;
		int xx;
		int yy;

		@Override
		public void mouseClicked(MouseEvent arg0) {
			Piece p = (Piece) arg0.getSource();
			System.out.println(p.getName());
			if (counter % 2 == 0) {
				if (p.getSide().equalsIgnoreCase("w") && count % 2 == 0) {
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							if (piece[i][j] == p) {
								x = i;
								y = j;
								System.out.printf("%d %d\n", x, y);
							}
						}
					}
					temp = p;
					count++;
					counter++;
				} else if (p.getSide().equalsIgnoreCase("b") && count % 2 == 1) {
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							if (piece[i][j] == p) {
								x = i;
								y = j;
								System.out.printf("%d %d\n", x, y);
							}
						}
					}
					temp = p;
					count++;
					counter++;
				}
			} else {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (piece[i][j] == p) {
							xx = i;
							yy = j;
							System.out.printf("%d %d\n", xx, yy);
						}
					}
				}

				if (temp.getName().contains("n")) {
					if (temp.getSide() != p.getSide()
							&& Knights.isValidMove(x, y, xx, yy)) {
						p.eat(temp);
						temp.eat(beEat);
						counter++;
					}
				} else if (temp.getName().contains("k")) {
					if (temp.getSide() != p.getSide()
							&& King.isValidMove(x, y, xx, yy)) {
						p.eat(temp);
						temp.eat(beEat);
						counter++;
					}
				} else if (temp.getName().contains("p")) {
					if (temp.getSide() != p.getSide()
							&& Solider
									.isValidMove(x, y, xx, yy, temp.getSide())) {
						p.eat(temp);
						temp.eat(beEat);
						counter++;
					}
				} else if (temp.getName().contains("r")) {
					if (Castle.isValidMove(x, y, xx, yy)) {
						if (temp.getSide() != p.getSide()
								&& checkCastle(x, y, xx, yy)) {
							p.eat(temp);
							temp.eat(beEat);
							counter++;
						}
					}
				} else if (temp.getName().contains("x")) {
					if (Ele.isValidMove(x, y, xx, yy)) {
						if (temp.getSide() != p.getSide()
								&& checkEle(x, y, xx, yy)) {
							p.eat(temp);
							temp.eat(beEat);
							counter++;
						}

					}
				}else if(temp.getName().contains("q")){
					if (Ele.isValidMove(x, y, xx, yy)) {
						if (temp.getSide() != p.getSide()
								&& checkEle(x, y, xx, yy)) {
							p.eat(temp);
							temp.eat(beEat);
							counter++;
						}

					}else if (Castle.isValidMove(x, y, xx, yy)) {
						if (temp.getSide() != p.getSide()
								&& checkCastle(x, y, xx, yy)) {
							p.eat(temp);
							temp.eat(beEat);
							counter++;
						}
					}
				}

			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}

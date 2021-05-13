package data;

import java.awt.Image;
import java.awt.Toolkit;

import Board.GameBoard;

public class Images {

	private static float miltMulti = 1.05f;
	
	public static Image milt = Toolkit.getDefaultToolkit().getImage("src/data/milt.jpg")
			.getScaledInstance((int)(GameBoard.cellSide*miltMulti), (int)(GameBoard.cellSide*miltMulti), Image.SCALE_FAST);
	
	
	
	public static void reload()
	{
		milt = Toolkit.getDefaultToolkit().getImage("src/data/milt.jpg")
				.getScaledInstance((int)(GameBoard.cellSide*miltMulti), (int)(GameBoard.cellSide*miltMulti), Image.SCALE_FAST);
	}
}

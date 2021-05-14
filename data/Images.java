package data;

import java.awt.Image;
import java.awt.Toolkit;

import Board.GameBoard;

public class Images {

	private static float miltMulti = 1.05f;
	private static float guaranaMulti = 1.05f;
	private static float wolfBMulti = 1.05f;
	private static float borschtMulti = 1.05f;
	private static float grassMulti = 1.05f;
	private static float sheepMulti = 1.07f;
	
	public static Image milt = Toolkit.getDefaultToolkit().getImage("src/data/milt.png")
			.getScaledInstance((int)(GameBoard.cellSide*miltMulti), (int)(GameBoard.cellSide*miltMulti), Image.SCALE_SMOOTH);
	public static Image guarana = Toolkit.getDefaultToolkit().getImage("src/data/guarana.png")
			.getScaledInstance((int)(GameBoard.cellSide*guaranaMulti), (int)(GameBoard.cellSide*guaranaMulti), Image.SCALE_SMOOTH);
	public static Image wolfBerries = Toolkit.getDefaultToolkit().getImage("src/data/wolfBerries.png")
			.getScaledInstance((int)(GameBoard.cellSide*wolfBMulti), (int)(GameBoard.cellSide*wolfBMulti), Image.SCALE_SMOOTH);
	public static Image borscht = Toolkit.getDefaultToolkit().getImage("src/data/borstch.png")
			.getScaledInstance((int)(GameBoard.cellSide*borschtMulti), (int)(GameBoard.cellSide*borschtMulti), Image.SCALE_SMOOTH);
	public static Image grass = Toolkit.getDefaultToolkit().getImage("src/data/grass.png")
			.getScaledInstance((int)(GameBoard.cellSide*grassMulti), (int)(GameBoard.cellSide*grassMulti), Image.SCALE_SMOOTH);
	public static Image sheep = Toolkit.getDefaultToolkit().getImage("src/data/sheep.png")
			.getScaledInstance((int)(GameBoard.cellSide*sheepMulti), (int)(GameBoard.cellSide*sheepMulti), Image.SCALE_SMOOTH);
	public static Image player = Toolkit.getDefaultToolkit().getImage("src/data/player.png")
			.getScaledInstance((int)(GameBoard.cellSide*sheepMulti), (int)(GameBoard.cellSide*sheepMulti), Image.SCALE_SMOOTH);
	
	public static void reload()
	{
		milt = milt.getScaledInstance((int)(GameBoard.cellSide*miltMulti), (int)(GameBoard.cellSide*miltMulti), Image.SCALE_SMOOTH);
		guarana = guarana.getScaledInstance((int)(GameBoard.cellSide*guaranaMulti), (int)(GameBoard.cellSide*guaranaMulti), Image.SCALE_SMOOTH);
		wolfBerries = wolfBerries.getScaledInstance((int)(GameBoard.cellSide*wolfBMulti), (int)(GameBoard.cellSide*wolfBMulti), Image.SCALE_SMOOTH);
		borscht = borscht.getScaledInstance((int)(GameBoard.cellSide*borschtMulti), (int)(GameBoard.cellSide*borschtMulti), Image.SCALE_SMOOTH);
		grass = grass.getScaledInstance((int)(GameBoard.cellSide*grassMulti), (int)(GameBoard.cellSide*grassMulti), Image.SCALE_SMOOTH);
		sheep = sheep.getScaledInstance((int)(GameBoard.cellSide*sheepMulti), (int)(GameBoard.cellSide*sheepMulti), Image.SCALE_SMOOTH);
		player = player.getScaledInstance((int)(GameBoard.cellSide*sheepMulti), (int)(GameBoard.cellSide*sheepMulti), Image.SCALE_SMOOTH);
		
	}
}

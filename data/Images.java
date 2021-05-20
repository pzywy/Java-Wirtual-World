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
	private static float fireMulti = 1.05f;
	private static float playerMulti = 1.3f;
	private static float wolfMulti = 1.05f;
	private static float turtleMulti = 1.05f;
	private static float antelopeMulti = 1.05f;
	private static float cyberSheepMulti = 1.05f;
	
	private static Image _milt = Toolkit.getDefaultToolkit().getImage("src/data/milt.png");
	private static Image _guarana = Toolkit.getDefaultToolkit().getImage("src/data/guarana.png");
	private static Image _wolfBerries = Toolkit.getDefaultToolkit().getImage("src/data/wolfBerries.png");
	private static Image _borscht = Toolkit.getDefaultToolkit().getImage("src/data/borstch.png");
	private static Image _grass = Toolkit.getDefaultToolkit().getImage("src/data/grass.png");
	private static Image _sheep = Toolkit.getDefaultToolkit().getImage("src/data/sheep.png");
	private static Image _player = Toolkit.getDefaultToolkit().getImage("src/data/player.png");
	private static Image _fire = Toolkit.getDefaultToolkit().getImage("src/data/fire.png");
	private static Image _wolf = Toolkit.getDefaultToolkit().getImage("src/data/wolf.png");
	private static Image _turtle = Toolkit.getDefaultToolkit().getImage("src/data/turtle.png");
	private static Image _antelope = Toolkit.getDefaultToolkit().getImage("src/data/antelope.png");
	private static Image _cyberSheep = Toolkit.getDefaultToolkit().getImage("src/data/cybersheep.png");
	
	public static Image milt = _milt;
	public static Image guarana = _guarana;
	public static Image wolfBerries = _wolfBerries;
	public static Image borscht = _borscht;
	public static Image grass = _grass;
	public static Image sheep = _sheep;
	public static Image player = _player;
	public static Image fire = _fire;
	public static Image wolf = _wolf;
	public static Image turtle = _turtle;
	public static Image antelope = _antelope;
	public static Image cyberSheep = _cyberSheep;
	public static void reload()
	{
		milt = _milt.getScaledInstance((int)(GameBoard.cellSide*miltMulti), (int)(GameBoard.cellSide*miltMulti), Image.SCALE_SMOOTH);
		guarana = _guarana.getScaledInstance((int)(GameBoard.cellSide*guaranaMulti), (int)(GameBoard.cellSide*guaranaMulti), Image.SCALE_SMOOTH);
		wolfBerries = _wolfBerries.getScaledInstance((int)(GameBoard.cellSide*wolfBMulti), (int)(GameBoard.cellSide*wolfBMulti), Image.SCALE_SMOOTH);
		borscht = _borscht.getScaledInstance((int)(GameBoard.cellSide*borschtMulti), (int)(GameBoard.cellSide*borschtMulti), Image.SCALE_SMOOTH);
		grass = _grass.getScaledInstance((int)(GameBoard.cellSide*grassMulti), (int)(GameBoard.cellSide*grassMulti), Image.SCALE_SMOOTH);
		sheep = _sheep.getScaledInstance((int)(GameBoard.cellSide*sheepMulti), (int)(GameBoard.cellSide*sheepMulti), Image.SCALE_SMOOTH);
		player = _player.getScaledInstance((int)(GameBoard.cellSide*playerMulti), (int)(GameBoard.cellSide*playerMulti), Image.SCALE_SMOOTH);
		fire = _fire.getScaledInstance((int)(GameBoard.cellSide*fireMulti), (int)(GameBoard.cellSide*fireMulti), Image.SCALE_SMOOTH);
		turtle = _turtle.getScaledInstance((int)(GameBoard.cellSide*turtleMulti), (int)(GameBoard.cellSide*turtleMulti), Image.SCALE_SMOOTH);
		wolf = _wolf.getScaledInstance((int)(GameBoard.cellSide*wolfMulti), (int)(GameBoard.cellSide*wolfMulti), Image.SCALE_SMOOTH);
		antelope = _antelope.getScaledInstance((int)(GameBoard.cellSide*antelopeMulti), (int)(GameBoard.cellSide*antelopeMulti), Image.SCALE_SMOOTH);
		cyberSheep = _cyberSheep.getScaledInstance((int)(GameBoard.cellSide*cyberSheepMulti), (int)(GameBoard.cellSide*cyberSheepMulti), Image.SCALE_SMOOTH);
	}
}

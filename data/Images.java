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
	private static float turtleMulti = 1.25f;
	private static float antelopeMulti = 1.05f;
	private static float cyberSheepMulti = 1.15f;
	private static float foxSheepMulti = 0.95f;
	
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
	private static Image _fox = Toolkit.getDefaultToolkit().getImage("src/data/fox.png");
	
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
	public static Image fox = _fox;
	
	public static void reload(GameBoard board)
	{
		milt = _milt.getScaledInstance((int)(board.cellSide*miltMulti), (int)(board.cellSide*miltMulti), Image.SCALE_SMOOTH);
		guarana = _guarana.getScaledInstance((int)(board.cellSide*guaranaMulti), (int)(board.cellSide*guaranaMulti), Image.SCALE_SMOOTH);
		wolfBerries = _wolfBerries.getScaledInstance((int)(board.cellSide*wolfBMulti), (int)(board.cellSide*wolfBMulti), Image.SCALE_SMOOTH);
		borscht = _borscht.getScaledInstance((int)(board.cellSide*borschtMulti), (int)(board.cellSide*borschtMulti), Image.SCALE_SMOOTH);
		grass = _grass.getScaledInstance((int)(board.cellSide*grassMulti), (int)(board.cellSide*grassMulti), Image.SCALE_SMOOTH);
		sheep = _sheep.getScaledInstance((int)(board.cellSide*sheepMulti), (int)(board.cellSide*sheepMulti), Image.SCALE_SMOOTH);
		player = _player.getScaledInstance((int)(board.cellSide*playerMulti), (int)(board.cellSide*playerMulti), Image.SCALE_SMOOTH);
		fire = _fire.getScaledInstance((int)(board.cellSide*fireMulti), (int)(board.cellSide*fireMulti), Image.SCALE_SMOOTH);
		turtle = _turtle.getScaledInstance((int)(board.cellSide*turtleMulti), (int)(board.cellSide*turtleMulti), Image.SCALE_SMOOTH);
		wolf = _wolf.getScaledInstance((int)(board.cellSide*wolfMulti), (int)(board.cellSide*wolfMulti), Image.SCALE_SMOOTH);
		antelope = _antelope.getScaledInstance((int)(board.cellSide*antelopeMulti), (int)(board.cellSide*antelopeMulti), Image.SCALE_SMOOTH);
		cyberSheep = _cyberSheep.getScaledInstance((int)(board.cellSide*cyberSheepMulti), (int)(board.cellSide*cyberSheepMulti), Image.SCALE_SMOOTH);
		fox = _fox.getScaledInstance((int)(board.cellSide*foxSheepMulti), (int)(board.cellSide*foxSheepMulti), Image.SCALE_SMOOTH);
	}
}

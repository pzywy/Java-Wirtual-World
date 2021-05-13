package Plants;

import java.awt.Color;

import Board.GameBoard;
import Organisms.ORG;
import Organisms.Plant;

public class Milt extends Plant {

	public Milt(int _x, int _y, GameBoard _board) {
		super(ORG.MLECZ, 0, _x, _y, _board);
		
	setColor(Color.yellow);
	}
	
}

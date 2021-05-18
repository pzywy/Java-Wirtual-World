package Organisms;

import Board.GameBoard;
import util.Point;

public abstract class Plant extends Organism {

	
	
	public Plant(ORG _name, int _strengh, Point _pos,GameBoard _board) {
		super(_name, _strengh, 0, _pos,_board);
	}

	
	@Override
	public void turn() {
		attemtReproduct();
		super.turn();
	}
	
	//1 for pass, 0 for blocked, -1 for dead
	@Override
	public int didPassCollision(Organism org)
	{
		gotEaten(org);
		return 1;
	}
	


	
	
}

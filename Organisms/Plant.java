package Organisms;

import Board.GameBoard;

public abstract class Plant extends Organism {

	public Plant(ORG _name, int _strengh, int _x, int _y,GameBoard _board) {
		super(_name, _strengh, 0, _x, _y,_board);
	}

}

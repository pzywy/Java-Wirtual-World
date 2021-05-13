package Organisms;

import java.util.Random;

import Board.GameBoard;
import Board.Point;
import Plants.Milt;

public abstract class Plant extends Organism {

	public Plant(ORG _name, int _strengh, Point _pos,GameBoard _board) {
		super(_name, _strengh, 0, _pos,_board);
	}

	
	@Override
	public void turn() {
		attemtReproduct();
		super.turn();
	}
	
	void attemtReproduct()
	{
		Random rand = new Random();
		if(rand.nextInt(5)==0)
		{
			System.out.println("Attempting reproduce "+getName()+ "on Pos: "+getPos().getX()+" "+getPos().getY());
			Point pos = getEmptyNeighbourCell();
			
			if(pos.getX()!=-1)
			{
				reproduct(pos);
			}
		}
	}

	protected void reproduct(Point pos) {

	}
	
	
}

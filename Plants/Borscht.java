package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Board.Point;
import Organisms.ORG;
import Organisms.Plant;
import data.Images;

public class Borscht extends Plant {

	public Borscht(Point _pos, GameBoard _board) {
		super(ORG.BARSZCZ, 999, _pos, _board);
		
	setColor(Color.white);
	reproductionChance = 1;
	setMaxAge(1000);
	

	}
	
	public Image getImg() {
		
				
		return Images.borscht;

	}
	
	@Override
	public void turn() {
		terminateAllNeighbour();
		super.turn();
	}
	
	protected void reproduct(Point _pos)
	{
		new Borscht(_pos,board);
		super.reproduct(_pos);
	}
	
}

package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Organisms.ORG;
import Organisms.Organism;
import Organisms.Plant;
import data.Images;
import util.Point;

public class Fire extends Plant {

	public Fire(Point _pos, GameBoard _board) {
		super(ORG.TRAWA, 0, _pos, _board);
		
	setColor(Color.red);
	reproductionChance = 150;
	setMaxAge(5);
	}
	
	@Override
	protected int getReproduceAge()
	{
		return 0;
	}
	public Image getImg() {			
		return Images.fire;
	}
	
	protected void reproduct(Point _pos)
	{
		new Fire(_pos,board);
		super.reproduct(_pos);
	}
	
	@Override
	public int didPassCollision(Organism org)
	{
		if(org.getStrengh()<5)
		{			
			org.died("of fire");
			return -1;
		}
		else
		{
			gotEaten(org);
			return 1;
		}
		
	}
	
	
}

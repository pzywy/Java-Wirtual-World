package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Board.Point;
import Organisms.ORG;
import Organisms.Organism;
import Organisms.Plant;
import data.Images;

public class WolfBerries extends Plant {

	public WolfBerries(Point _pos, GameBoard _board) {
		super(ORG.WILCZEJAGODY, 99, _pos, _board);
		
	setColor(Color.red);
	reproductionChance = 15;
	setMaxAge(600);
	

	}
	@Override
	public Image getImg() {					
		return Images.wolfBerries;
	}
	@Override
	public int didPassCollision(Organism org)
	{
		gotEaten(org);
		return -1;
	}
	@Override
	public void gotEaten(Organism org)
	{
		org.died("of wolfberries");
		super.gotEaten(org);
	}
	
	@Override
	protected void reproduct(Point _pos)
	{
		new WolfBerries(_pos,board);
		super.reproduct(_pos);
	}
	
}

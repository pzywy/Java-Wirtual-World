package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Organisms.ORG;
import Organisms.Plant;
import data.Images;
import util.Point;

public class Grass extends Plant {

	public Grass(Point _pos, GameBoard _board) {
		super(ORG.TRAWA, 0, _pos, _board);
		
	setColor(Color.green);
	reproductionChance = 75;
	setMaxAge(50);
	

	}
	
	
	
	public Image getImg() {			
		return Images.grass;
	}
	
	protected void reproduct(Point _pos)
	{
		new Grass(_pos,board);
		super.reproduct(_pos);
	}
	
}

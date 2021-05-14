package Animals;

import java.awt.Image;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import data.Images;
import util.Point;

public class Sheep extends Animal {

	public Sheep(Point _pos, GameBoard _board) {
		super(ORG.OWCA, 4, 4, _pos, _board);
		
		setMaxAge(250);
		reproductionChance = 500;
	}

	@Override
	public void turn() {
		
		super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	@Override
	public Image getImg() {
		return Images.sheep;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new Sheep(_pos,board);
		super.reproduct(_pos);
	}
	
}

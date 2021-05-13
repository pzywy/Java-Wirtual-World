package Animals;

import java.awt.Image;

import Board.GameBoard;
import Board.Point;
import Organisms.Animal;
import Organisms.ORG;
import data.Images;

public class Sheep extends Animal {

	public Sheep(Point _pos, GameBoard _board) {
		super(ORG.OWCA, 4, 7, _pos, _board);
		
		setMaxAge(350);
		reproductionChance = 1000;
	}

	@Override
	public void turn() {
		
		super.turn();
		//System.out.println("Sheep ID: "+getID());
	}
	
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

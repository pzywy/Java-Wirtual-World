package Animals;

import java.awt.Image;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import data.Images;
import util.Point;

public class Wolf extends Animal {

	public Wolf(Point _pos, GameBoard _board) {
		super(ORG.WILK, 9, 5, _pos, _board);
		
		setMaxAge(250);
		reproductionChance = 150;
	}

	@Override
	public void turn() {
		
		super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	@Override
	public Image getImg() {
		return Images.wolf;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new Wolf(_pos,board);
		super.reproduct(_pos);
	}
	
}

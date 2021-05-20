package Animals;

import java.awt.Image;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import data.Images;
import util.Point;

public class Antelope extends Animal {

	public Antelope(Point _pos, GameBoard _board) {
		super(ORG.ANTYLOPA, 4, 4, _pos, _board);
		
		setMaxAge(550);
		reproductionChance = 600;//300
	}

	@Override
	public void turn() {
		move();
		super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	@Override
	public Image getImg() {
		return Images.antelope;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new Antelope(_pos,board);
		super.reproduct(_pos);
	}
	
}

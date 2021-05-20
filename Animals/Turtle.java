package Animals;

import java.awt.Image;
import java.util.Random;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import data.Images;
import util.Point;

public class Turtle extends Animal {

	public Turtle(Point _pos, GameBoard _board) {
		super(ORG.ZOLW, 2, 1, _pos, _board);
		
		setMaxAge(5000);
		reproductionChance = 500;
	}

	@Override
	public void turn() {
		Random rand = new Random();
		if(rand.nextInt(4)==0)
			super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	@Override
	public Image getImg() {
		return Images.turtle;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new Turtle(_pos,board);
		super.reproduct(_pos);
	}
	
	@Override
	public int didPassCollision(Organism org)
	{
		if(getName()==org.getName())
		{	
			attemtReproduct();
			return 0;
		}
		else if(org.getStrengh()<getStrengh())
		{
			org.died("killed by "+getName()+" with strengh: "+getStrengh());
			return -1;
		}
		//tutrle block
		else if (org.getStrengh()<5)
		{
			return 0;
		}			
		else
		{
			died("killed by" +org.getName() +" with strengh: "+org.getStrengh());
			return 1;
		}	
	}
	
}

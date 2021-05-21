package Animals;

import java.awt.Image;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import data.Images;
import util.Point;

public class Fox extends Animal {

	public Fox(Point _pos, GameBoard _board) {
		super(ORG.LIS, 3, 7, _pos, _board);
		
		setMaxAge(250);
		reproductionChance = 800;//300
	}

	@Override
	public void turn() {
		
		super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	@Override
	public Image getImg() {
		return Images.fox;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new Fox(_pos,board);
		super.reproduct(_pos);
	}
	
	
	@Override
	public void move()
	{
		Point dest = getRandomNeighbour();
		if(dest.getX()<0||dest.getY()<0)return;
		
		Organism org = board.getFromArray(dest);
		
		if(org!=null)
		{
			switch(org.didPassCollision(this))
			{
			case  0:  {break;}
			case -1:  {break;}//just cancel move if stronger
			case  1:  {board.addToArray(null, getPos()); setPos(dest); board.addToArray(this, dest); break;}
			}
		}
		else 
		{
			board.addToArray(null, getPos());
			setPos(dest);
			board.addToArray(this, dest);
		}
		
	}
}

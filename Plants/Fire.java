package Plants;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import Board.GameBoard;
import Organisms.ORG;
import Organisms.Organism;
import Organisms.Plant;
import data.Images;
import util.Point;

public class Fire extends Plant {

	public Fire(Point _pos, GameBoard _board) {
		super(ORG.FIRE, 4, _pos, _board);
		
	setColor(Color.red);
	reproductionChance = 250;
	setMaxAge(2);
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
	
	@Override
	protected void attemtReproduct()
	{
		if(getAge()<getReproduceAge() || reproductionChance<=0)return;
		Random rand = new Random();
		if(rand.nextInt((int)(1000/reproductionChance))==0)
		{
			//System.out.println("Attempting reproduce "+getName()+ " on Pos: "+getPos().getX()+" "+getPos().getY());
			Point pos = getRandomNeighbour();
			
			Organism org = board.getFromArray(pos);
			if(pos.getX()!=-1&&org!=null&&(org.getStrengh()<=getStrengh() || org instanceof Plant) && org.getName()!=getName())
			{
				org.died("burned");
				reproduct(pos);
				//more duration due to burning something
				board.getFromArray(pos).setMaxAge(board.getFromArray(pos).getMaxAge()*4);
			}
			else if(pos.getX()!=-1&&org==null)
			{
				reproduct(pos);
			}
		}
	}
	
	@Override
	public void gotEaten(Organism org)
	{
		//decrease max age after burn
		org.setMaxAge(org.getMaxAge()-getStrengh()*2);
		died("");
	}
	
	
}

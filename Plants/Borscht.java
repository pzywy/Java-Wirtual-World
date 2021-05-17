package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import Organisms.Plant;
import data.Images;
import util.Point;

public class Borscht extends Plant {

	public Borscht(Point _pos, GameBoard _board) {
		super(ORG.BARSZCZ, 999, _pos, _board);
		
	setColor(Color.white);
	reproductionChance = 1;
	setMaxAge(1000);
	

	}
	
	public Image getImg() {
			
		return Images.borscht;
	}
	
	@Override
	public void turn() {
		terminateAllAnimalNeighbour();
		super.turn();
	}
	
	protected void reproduct(Point _pos)
	{
		new Borscht(_pos,board);
		super.reproduct(_pos);
	}
	
	
	@Override
	public void gotEaten(Organism org)
	{
		//borsch gives eternal life :)
		org.setMaxAge(org.getMaxAge()+10000);
		super.gotEaten(org);
	}
	
	
	protected void terminateAllAnimalNeighbour()
	{
		Point cell = new Point(-1,-1);
		for(int i=1;i<=9;i++)
		{
			cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
			if(cell.getY()==2)	cell.setY(-1);
			
			if(cell.getX()==0&&cell.getY()==0)
				continue;
			
			cell.setX(cell.getX()+getPos().getX());
			cell.setY(cell.getY()+getPos().getY());
			
			if(cell.getX()<0 || cell.getY()<0 || cell.getY()>=GameBoard.rows || cell.getX()>=GameBoard.cols)
				continue;
			
			Organism org = board.getFromArray(cell);
			
			if(org!=null&&org.isAlive()&&org.getName()!=getName()&&org instanceof Animal)
			{
				//System.out.println("TERMINATE");
				org.died("of termination");
			}
			
		}
	}
	
}

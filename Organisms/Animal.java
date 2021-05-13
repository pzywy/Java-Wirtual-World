package Organisms;


import java.util.Random;

import Board.GameBoard;
import Board.Point;

public abstract class Animal extends Organism {

	
	
	public Animal(ORG _name, int _strengh,int _effort, Point _pos,GameBoard _board) {
		super(_name, _strengh, _effort, _pos,_board);
	}

	
	@Override
	public void turn() {
		move();
		super.turn();
	}
	
//1 for pass, 0 for blocked, -1 for dead
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
			org.died("killed by"+getName());
			return -1;
		}
		else
		{
			died("killed by"+org.getName());
			return 1;
		}	
	}
	
	

	public void move()
	{
		Point dest = getRandomNeighbour();
		Organism org = board.getFromArray(dest);
		if(org!=null)
		{
			switch(org.didPassCollision(this))
			{
			case 0:break;
			case -1: died("fighting "+org.getName());break;
			case 1: setPos(dest); board.addToArray(null, getPos()); break;
			}
		}
		else 
		{
			board.addToArray(null, getPos());
			setPos(dest);
		}
		
	}
	
	public Point getRandomNeighbour()
	{
		Point cell = new Point(0,0);
		while(cell.getX()+getPos().getX()<0 || cell.getY()+getPos().getY()<0 
				|| cell.getY()+getPos().getY()>=GameBoard.rows || cell.getX()+getPos().getX()>=GameBoard.cols
				||(cell.getX()==0&&cell.getY()==0))
		{

			Random rand = new Random();
			int i = rand.nextInt(10);		
			if(i==5)
				i = rand.nextInt(10);
			
			cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
			if(cell.getY()==2)cell.setY(-1);
			
						
		}
		cell.setX(cell.getX()+getPos().getX());
		cell.setY(cell.getY()+getPos().getY());
		return cell;
	}
}

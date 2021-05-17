package Organisms;


import java.util.Random;

import Board.GameBoard;
import util.Point;


public abstract class Animal extends Organism {

	
	
	public Animal(ORG _name, int _strengh,int _effort, Point _pos,GameBoard _board) {
		super(_name, _strengh, _effort, _pos,_board);
	}

	
	@Override
	public void turn() {
		move();
		//lookForPartners();
		//attemtReproduct();
		super.turn();
	}
	
	// guess sth is fcked up, not sure but maybe I shouldnt go through organism loop in organism
	@SuppressWarnings("unused")
	private void lookForPartners()
	{
		board.getListOfOrganisms().forEach((org) ->{
			
			if(org.getID()!=getID()&&org.isAlive()&&org.getName()==getName())
			{
				
				double distance; 
				int a = getPos().getX() > org.getPos().getX() ? getPos().getY() - org.getPos().getY() :org.getPos().getY() - getPos().getY();
				int b = getPos().getY() > org.getPos().getY() ? getPos().getY() - org.getPos().getY() :org.getPos().getY() - getPos().getY();
				distance = Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2));
				
				if(distance<3)
					attemtReproduct();
			}
		});
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
			org.died("killed by "+getName()+" with strengh: "+getStrengh());
			return -1;
		}
		else
		{
			died("killed by" +org.getName() +" with strengh: "+org.getStrengh());
			return 1;
		}	
	}
	
	@Override
	protected int getReproduceAge()
	{
		return 25;
	}

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
			case -1:  {died("fighting "+org.getName());break;}
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
	
	public Point getRandomNeighbour()
	{
		Point cell = new Point(0,0);
		int tries=0;
		while(cell.getX()+getPos().getX()<0 || cell.getY()+getPos().getY()<0 
				|| cell.getY()+getPos().getY()>=GameBoard.rows || cell.getX()+getPos().getX()>=GameBoard.cols
				||(cell.getX()==0&&cell.getY()==0))
		{
			tries++;
			if(tries>5)return new Point(-1,-1);
			Random rand = new Random();
			int i = rand.nextInt(10);		
			if(i%2==1)
				if(i<9)i++;
				else i--;
			
			cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
			if(cell.getY()==2)cell.setY(-1);
			
		}
		cell.setX(cell.getX()+getPos().getX());
		cell.setY(cell.getY()+getPos().getY());
		return cell;
	}
}

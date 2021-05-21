package Organisms;



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
			//System.out.println("collision with same specie on pos" + org.getPos().getX() +", "+org.getPos().getY());
			attemtReproduct();
			return 0;
			
		}
		
		
		else if(org.getStrengh()<getStrengh())
		{
			//org.died("killed by "+getName()+" with strengh: "+getStrengh());
			return -1;
		}
		else
		{
			died("killed by " +org.getName() +" with strengh: "+org.getStrengh());
			gotEaten(org);
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
			case -1:  {died("fighting "+org.getName()+" with strengh "+org.getStrengh());break;}
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

package Animals;

import java.awt.Image;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import Plants.Fire;
import data.Images;
import util.Point;

public class Player extends Animal {
	public Player(Point _pos, GameBoard _board) {
		super(ORG.GRACZ, 5, 4, _pos, _board);
		
		setMaxAge(1000);
		reproductionChance = 0;
	}
	
	
	@Override
	public void turn() {
		
		super.turn();
		if(specialActionActive)specialAction();
		else if(specialActionCooldown>0)specialActionCooldown--;
		//System.out.println("Player: "+getPos().getX()+", "+getPos().getY());
		//System.out.println("on this pos: "+board.getFromArray(getPos()));
	}
	
	@Override
	public void move()
	{
		
		Point dest = getDestination();
		if(dest.getX()<0||dest.getY()<0)return;
		Organism org = board.getFromArray(dest);
		if(org!=null)
		{
			switch(org.didPassCollision(this))
			{
			case 0:break;
			case -1: died("player fighting "+org.getName());break;
			case 1:  board.addToArray(null, getPos()); setPos(dest);board.addToArray(this, dest); break;
			}
		}
		else 
		{
			board.addToArray(null, getPos());
			setPos(dest);
			board.addToArray(this, dest);
		}
		
	}
	
	private Point getDestination()
	{
		GameBoard.lastInput='/';
		int attempts=1000;
		
		while(GameBoard.lastInput=='/'&&attempts>=0)
		{
			attempts--;
			//it throws exception on interrupt but returning point(-1,-1) fixes problem
			try {TimeUnit.MILLISECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();return new Point(-1,-1);}
		}
		
		
		if(GameBoard.lastInput==' ')
		{
			specialAction();
			return getDestination();
		}
		
		//System.out.print("key: "+GameBoard.lastInput);
		if(GameBoard.lastInput=='d'&&getPos().getX()+1<GameBoard.cols&&getPos().getX()+1>=0)
			return(new Point(getPos().getX()+1,getPos().getY()));
		else if(GameBoard.lastInput=='a'&&getPos().getX()-1<GameBoard.cols&&getPos().getX()-1>=0)
			return(new Point(getPos().getX()-1,getPos().getY()));
		
		else if(GameBoard.lastInput=='w'&&getPos().getY()-1<GameBoard.rows&&getPos().getY()-1>=0)
			return(new Point(getPos().getX(),getPos().getY()-1));
		
		else if(GameBoard.lastInput=='s'&&getPos().getY()+1<GameBoard.rows&&getPos().getY()+1>=0)
			return(new Point(getPos().getX(),getPos().getY()+1));
		
		return new Point(-1,-1);
			
	}
	
	
	private int specialActionCooldown=0;
	private boolean specialActionActive=false;
	
	private void specialAction()
	{
		//if not active - activate
		if(!specialActionActive&&specialActionCooldown==0)
		{
			specialActionActive=true;
			specialActionCooldown=5;
			System.out.println("SPECIAL ACTION ACTIVATED");
			terminateAllNeighbour();
			
		}
		//special action is active!
		else if(specialActionActive)
		{
			specialActionCooldown--;
			if(specialActionCooldown<=0)
			{
				specialActionCooldown=5;
				specialActionActive=false;
			}
			
			System.out.println("SPECIAL ACTION");
			terminateAllNeighbour();
		}
		//on cooldown!
		else
		{
			System.out.println("Special action on cooldown "+specialActionCooldown+" turns left");
		}
	}
	
	protected void terminateAllNeighbour()
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
			
			if(org!=null&&org.isAlive()&&org.getName()!=getName())
			{
				//System.out.println("TERMINATE");
				org.died("of termination");
			}
			//create fire in place
			new Fire(cell,board);
			
		}
		//to show fire right after use of power
		GameBoard.repaint();
	}
	
	@Override
	public Image getImg() {
		return Images.player;
	}
}

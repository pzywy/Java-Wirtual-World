package Animals;

import java.awt.Image;
import java.util.concurrent.TimeUnit;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import data.Images;
import util.Point;

public class Player extends Animal {
	public Player(Point _pos, GameBoard _board) {
		super(ORG.GRACZ, 5, 4, _pos, _board);
		
		setMaxAge(1050);
		reproductionChance = 0;
	}
	
	
	@Override
	public void turn() {
		
		super.turn();
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
			case -1: died("fighting "+org.getName());break;
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
	
	
	
	@Override
	public Image getImg() {
		return Images.player;
	}
}

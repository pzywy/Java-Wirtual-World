package Animals;

import java.awt.Image;
import java.util.Iterator;
import java.util.Random;

import Board.GameBoard;
import Organisms.Animal;
import Organisms.ORG;
import Organisms.Organism;
import Plants.Borscht;
import data.Images;
import util.Point;

public class CyberSheep extends Animal {

	public CyberSheep(Point _pos, GameBoard _board) {
		super(ORG.CYBEROWCA, 11, 4, _pos, _board);
		
		setMaxAge(1050);
		reproductionChance = 50;
	}
	
	public Point borschtPos = new Point(-1,-1);

	@Override
	public void turn() {
		if(borschtPos.getX()<0||board.getFromArray(borschtPos)==null
				||(board.getFromArray(borschtPos)!=null
				&&board.getFromArray(borschtPos).getName()!=ORG.BARSZCZ))findBorscht();
		super.turn();
		//System.out.println("Sheep ID: "+getID()+" "+getPos().getX()+", "+getPos().getY());
	}
	
	private void findBorscht()
	{
		//to improve performance
		if(!board.areBorschtesOnBoard)return;
		Iterator<Organism> orgIt = board.getListOfOrganisms().iterator();
		while (orgIt.hasNext()) {
			Organism org = orgIt.next(); 
		   if(org.isAlive()&&org.getName()==ORG.BARSZCZ&&!((Borscht)org).aimed)
		   {	  
			   borschtPos=org.getPos();		
			 //create aim to prevent taking by other sheep
			   ((Borscht)org).aimed=true;
			   return;
		   }
		}
		borschtPos=new Point (-1,-1);
	}
	
	@Override // to go to borscht
	public Point getRandomNeighbour()
	{
		Point cell = new Point(0,0);
		int tries=0;
		while(cell.getX()+getPos().getX()<0 || cell.getY()+getPos().getY()<0 
				|| cell.getY()+getPos().getY()>=board.rows || cell.getX()+getPos().getX()>=board.cols
				||(cell.getX()==0&&cell.getY()==0))
		{
			tries++;
			if(tries>10)return new Point(-1,-1);

			int i=0;
			if(borschtPos.getX()>=0)
			{
				if(getPos().getX()>borschtPos.getX())i=2;
				else if(getPos().getX()<borschtPos.getX())i=8;
				else if(getPos().getY()<borschtPos.getY())i=6;
				else if(getPos().getY()>borschtPos.getY())i=4;
			}
			else 
			{
				Random rand = new Random();
				i = rand.nextInt(10);		
				if(i%2==1)
					if(i<9)i++;
					else i--;
			}
			
			cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
			if(cell.getY()==2)cell.setY(-1);
			
		}
		cell.setX(cell.getX()+getPos().getX());
		cell.setY(cell.getY()+getPos().getY());
		return cell;
	}
	
	@Override
	public void died(String cause)
	{
		//clear aim
		if(board.getFromArray(borschtPos)!=null)((Borscht)board.getFromArray(borschtPos)).aimed=false;
		super.died(cause);
	}
	
	@Override
	public Image getImg() {
		return Images.cyberSheep;
	}
	
	@Override
	protected void reproduct(Point _pos)
	{		
		new CyberSheep(_pos,board);
		super.reproduct(_pos);
	}
	
}

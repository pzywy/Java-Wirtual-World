package Board;
import java.util.ArrayList;
import java.util.List;

import Organisms.Organism;


public class GameBoard {

	public static final int cols=10;
	public static final int rows=10;
	public static int cellSide=25;
	
	private List<Organism> organismsList = new ArrayList<Organism>();
	private Organism[] organismArray = new Organism[cols*rows];
	
	public GameBoard()
	{
		for(int i=0;i<cols*rows;i++)
		{
			organismArray[i]=null;
			
		}
	}
	
	public List<Organism> getListOfOrganisms()
	{
		return organismsList;
	}
	
	public void addToArray(Organism org, Point pos)
	{
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>cols || pos.getX()>rows)
			return;
		organismArray[pos.getX()*cols + pos.getY()]= org;
	}
	public Organism getFromArray(Point pos)
	{
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>cols-1 || pos.getX()>rows-1)
			return null;
		return organismArray[pos.getX()*cols+pos.getY()];
	}
	
	public void addOrganism(Organism org)
	{
		organismsList.add(org);
		addToArray(org, org.getPos());
	}
	
	public void delOrganism(Organism org)
	{
		addToArray(null, org.getPos());
		organismsList.remove(org);	
	}
	
	public void turn()
	{

		for(int i = 0; i < organismsList.size();i++)
		{
			Organism org = organismsList.get(i);
			if(org.isAlive())
				org.turn();

		}
	}
	
}

package Board;
import java.util.ArrayList;
import java.util.List;

import Organisms.Organism;
import util.OrganismSorter;


public class GameBoard {

	public static final int cols=25;
	public static final int rows=15;
	public static int cellSide=25;
	public static int turnCount=0;
	
	private List<Organism> organismsList = new ArrayList<Organism>();
	private Organism[] organismArray = new Organism[cols*rows];
	
	private static int addedOrganismsCount=0;
	private static int currentIterateIndex=0;
	private static int turnIndexToMove = 0;
	private static boolean anythingAdedd=false;
	
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
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>rows-1 || pos.getX()>cols-1)
			return;
		organismArray[pos.getX()*rows + pos.getY()]= org;
	}
	public Organism getFromArray(Point pos)
	{
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>rows-1 || pos.getX()>cols-1)
			return null;
		return organismArray[pos.getX()*rows+pos.getY()];
	}
	
	public void addOrganism(Organism org)
	{
		addedOrganismsCount++;
		addToArray(org, org.getPos());
		organismsList.add(0,org);
		anythingAdedd=true;
	}
	
	public void delOrganism(Organism org)
	{
		
		if(currentIterateIndex>organismsList.indexOf(org))turnIndexToMove++;
		addToArray(null, org.getPos());		
		organismsList.remove(org);	
	}
	
	public void sortOrganisms()
	{
		organismsList.sort(new OrganismSorter());
		addedOrganismsCount=0;
		turnIndexToMove=0;
	}
	
	public void turn()
	{
		turnCount++;
		addedOrganismsCount=0;turnIndexToMove=0;
		for(int i = 0; i <organismsList.size() ;i++)
		{
			//System.out.println("Loop index "+i);
			currentIterateIndex=i;
			//prevent double tours after some organism dead
			i=i+addedOrganismsCount-turnIndexToMove;
			addedOrganismsCount=0;turnIndexToMove=0;
			
			Organism org = organismsList.get(i);
			
			if(org.isAlive())
				org.turn();

		}
		if(anythingAdedd)
		{
			anythingAdedd=false;
			sortOrganisms();
		}
		
	}
	
}

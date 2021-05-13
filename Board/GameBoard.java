package Board;
import java.util.ArrayList;
import java.util.List;

import Organisms.Organism;


public class GameBoard {

	static final int cols=10;
	static final int rows=10;
	static final int cellSide=25;
	
	private List<Organism> organismsList = new ArrayList<Organism>();
	
	public GameBoard()
	{
		
	}
	
	public List<Organism> getListOfOrganisms()
	{
		return organismsList;
	}
	
	public void addOrganism(Organism org)
	{
		organismsList.add(org);
	}
	
	public void delOrganism(Organism org)
	{
		organismsList.remove(org);
	}
	
}

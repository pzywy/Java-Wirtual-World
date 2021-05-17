package Board;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Organisms.ORG;
import Organisms.Organism;
import util.OrganismSorter;
import util.Point;


public class GameBoard {

	public static final int cols=30;
	public static final int rows=30;
	public static int cellSide=25;
	public static int turnCount=0;
	
	private List<Organism> organismsList = new ArrayList<Organism>();
	private Organism[] organismArray = new Organism[cols*rows];
	
	public static char lastInput='/';
	private static int addedOrganismsCount=0;
	private static int currentIterateIndex=0;
	private static int turnIndexToMove = 0;
	private static boolean anythingAdedd=false;
	private JFrame frame;
	public Organism player=null;
	
	public GameBoard()
	{
		for(int i=0;i<cols*rows;i++)
		{
			organismArray[i]=null;
			
		}
	}
	
	public void restart()
	{
		organismsList = new ArrayList<Organism>();
		organismArray = new Organism[cols*rows];
		turnCount=0;
	}
	
	public List<Organism> getListOfOrganisms()
	{
		List<Organism> _organismsList = new ArrayList<Organism>();
		_organismsList = organismsList;
		return _organismsList;
	}
	
	public void addToArray(Organism org, Point pos)
	{
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>rows || pos.getX()>cols)
			return;
		organismArray[pos.getY()*cols + pos.getX()]= org;
	}
	public Organism getFromArray(Point pos)
	{
		if(pos.getX()<0 || pos.getY()<0 || pos.getY()>rows || pos.getX()>cols)
			return null;
		return organismArray[pos.getY()*cols+pos.getX()];
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
		if(getFromArray(org.getPos())==null&&getFromArray(org.getPos()).getID()==org.getID())
			addToArray(null, org.getPos());		
		organismsList.remove(org);	
	}
	
	public void sortOrganisms()
	{
		organismsList.sort(new OrganismSorter());
		addedOrganismsCount=0;
		turnIndexToMove=0;
	}
	
	public void turn(JPanel panel)
	{
		mainGamePanel = panel;
		turnCount++;
		addedOrganismsCount=0;turnIndexToMove=0;
		
		//printAllOrganisms();
		
		for(int i = 0; i <organismsList.size() ;i++)
		{		
			if(player==null&&organismsList.get(i).getName()==ORG.GRACZ)player=organismsList.get(i);
			currentIterateIndex=i;
			
			//prevent double tours after some organism dead
			i=i+addedOrganismsCount-turnIndexToMove;
			addedOrganismsCount=0;turnIndexToMove=0;
			
			//System.out.println("Loop index "+i);
			
			if(i<0)i=0;
			if(i>=organismsList.size())break;
			Organism org = organismsList.get(i);
			
			if(org.isAlive())
				org.turn();
			else
				org.del();
			
			
		}
		
		Iterator<Organism> orgIt = organismsList.iterator();
		while (orgIt.hasNext()) {
			Organism org = orgIt.next(); // must be called before you can call i.remove()
		   if(!org.isAlive())
		   {	   
			   orgIt.remove();
			   org.del();
		   }
		}
		
		if(anythingAdedd)
		{
			anythingAdedd=false;
			sortOrganisms();
		}
		
	}
	

	//instant repaint if needed (usualy it's done after every turn)
	private static JPanel mainGamePanel;
	public static void repaint()
	{
		mainGamePanel.repaint();
	}
	
	@SuppressWarnings("unused")
	private void printAllOrganisms()
	{
		for(int i = 0; i <organismsList.size() ;i++)
		{
			System.out.println(i+": "+organismsList.get(i).getName());
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}

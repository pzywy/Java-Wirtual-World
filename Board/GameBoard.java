package Board;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Organisms.ORG;
import Organisms.Organism;
import util.OrganismSorter;
import util.Point;


public class GameBoard {

	public final int cols=30;
	public final int rows=30;
	public int cellSide=25;
	public int turnCount=0;
	
	
	private List<Organism> organismsList = new ArrayList<Organism>();
	private Organism[] organismArray = new Organism[cols*rows];
	
	public static char lastInput='/';
	private int addedOrganismsCount=0;
	private int currentIterateIndex=0;
	private int turnIndexToMove = 0;
	private static boolean anythingAdedd=false;
	private JFrame frame;
	public Organism player=null;
	//to improve performance
	public boolean areBorschtesOnBoard = false;
	
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
	
	
	//cleanes board if for some reason there are dead organisms.
	private void cleanUpBoard()
	{
		for(int i=0;i<cols*rows;i++)
		{
			if(organismArray[i]!=null&&organismArray[i].isAlive()==false) 
			{
				System.out.println(organismArray[i].getName() +" Deleted");				
				organismArray[i]=null;

			}
		}
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
		
		if(org!=null)
			{
			org.setAlive(true);
			//to improve performance (sets that borsches are or not on board to avoid pointless loops in cybersheep)
			if(!areBorschtesOnBoard&&org.getName()==ORG.BARSZCZ)areBorschtesOnBoard=true;
			}
		
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
		if(getFromArray(org.getPos())!=null&&getFromArray(org.getPos()).getID()==org.getID())
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
			
			if(org.getName()==ORG.GRACZ)repaint();
			
			if(org.isAlive())
				org.turn();
			else
				org.del();
			
			//every 50 turns cleanup
			if(turnCount%50==1)cleanUpBoard();
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
	
	
	public void saveToFile()
	{
		
		String saveFileTxt="";
		
		Iterator<Organism> orgIt = organismsList.iterator();
		while (orgIt.hasNext()) {
			Organism org = orgIt.next(); // must be called before you can call i.remove()
		   if(org.isAlive())
		   {	   
			  saveFileTxt += org.getName().toString()+" "+org.getAge()+" "+org.getMaxAge()
			  +" "+org.getPos().getX()
			  +" "+org.getPos().getY()+" "+org.getStrengh()+" " + org.isWasEating() + "\n";
		   }
		}
	
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		
		File destination = new File (System.getProperty("user.home")+ System.getProperty("file.separator")
		+ "Documents"+ System.getProperty("file.separator")+"WirtualWorld");	
		if (! destination.exists()){
			destination.mkdir();    
	    }
		fileChooser.setCurrentDirectory(destination);

		fileChooser.setDialogTitle("Save game state");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    try {
		    	FileWriter fw = new FileWriter(fileToSave.getAbsolutePath());
		    	fw.write(saveFileTxt);
		    	fw.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
		
	}
	
	public String loadFromFile()
	{
		String result="";
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		
		File destination = new File (System.getProperty("user.home")+ System.getProperty("file.separator")
		+ "Documents"+ System.getProperty("file.separator")+"WirtualWorld");	
		if (! destination.exists()){
			destination.mkdir();    
	    }
		fileChooser.setCurrentDirectory(destination);
		
		fileChooser.setDialogTitle("Choose save file load");   
		 
		int userSelection = fileChooser.showOpenDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToLoad = fileChooser.getSelectedFile();
		    try {
		    	BufferedReader b = new BufferedReader(new FileReader(fileToLoad));
		    	String load;	
		    	while((load=b.readLine())!=null)
		    	{
		    		result+=load+"\n";
		    		//System.out.println(load);
		    	}
		    	if(b!=null)
		    	{
		    		b.close();
		    	}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		    System.out.println("Load  file: " + fileToLoad.getAbsolutePath());
		    
		}
		return result;
	}
	
}

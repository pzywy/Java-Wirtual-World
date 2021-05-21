import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Animals.Antelope;
import Animals.CyberSheep;
import Animals.Fox;
import Animals.Player;
import Animals.Sheep;
import Animals.Turtle;
import Animals.Wolf;
import Board.GameBoard;
import util.MainWindow;
import util.Point;
import Board.PrintBoard;
import Organisms.ORG;
import Organisms.Organism;
import Plants.Borscht;
import Plants.Fire;
import Plants.Grass;
import Plants.Guarana;
import Plants.Milt;
import Plants.WolfBerries;
import data.Images;

public class GameControll{


	public static void main(String[] args) {
		
		createBoard();
		
	}
	static private GameBoard board;
	private static GameControll _frame;
	private static JPanel panel;
	private JFrame frame;
	private static JLabel label;
	public static boolean autoSymulate=false;
	private static Worker worker;
	
	
	private static void putOrganismsOnBoard(GameBoard board)
	{
		
		int maxX=board.cols;
		int maxY=board.rows;
		Random rand = new Random();
		
		new Player(new Point((int)maxX/2,(int)maxY/2),board);
		
		for (int x = 0;x<maxX;x++)
		{
			for(int y = 0;y<maxY;y++)
			{
				
				if(board.getFromArray(new Point(x,y))==null)
				{	
					if(rand.nextInt(100)==0)new Grass(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Milt(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Fox(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Sheep(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Antelope(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Wolf(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Turtle(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Guarana(new Point(x,y),board);				
					else if(rand.nextInt(100)==0)new WolfBerries(new Point(x,y),board);
					else if(rand.nextInt(100)==0)new Borscht(new Point(x,y),board);
					else if(rand.nextInt(300)==0)new CyberSheep(new Point(x,y),board);
					
				}
				
				
			}
		}	
		board.sortOrganisms();
	}
	
	public static void createBoard()
	{
		board = new GameBoard();
		_frame = new GameControll(board);
		putOrganismsOnBoard(board);
	}
	
	public void restart()
	{
		_frame.frame.dispose();
		board.restart();
		if(worker!=null)worker.cancel(true);
		autoSymulate=false;
		createBoard();
	}
	
	
	
	public static void turn(GameBoard board)
	{
		 _frame.frame.requestFocus();
		board.turn(panel);
        panel.repaint();
        label.setText("Turn nr: "+board.turnCount+"\n Player lived/max : "
        +board.player.getAge()+"/"+board.player.getMaxAge()+" strengh: "+board.player.getStrengh());
	}
	
	
	public GameControll(GameBoard board)
	{
		
		frame = new MainWindow();
		
		JButton button = new JButton("Restart");
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		    	restart();
		    	 _frame.frame.requestFocus();
		    }
		});
		
		JButton buttonAuto = new JButton("START/PAUSE");
		buttonAuto.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("auto on/of");
		    	autoSymulate=!autoSymulate;	   
		    	if(autoSymulate)
		    	{
		    		worker = new Worker(board);
		    		worker.execute();
		    		
		    	}
		    	else
		    	{
		    		worker.cancel(true);
		    	}
		    }
		});
		
		
		
		JButton save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("save");
		    	board.saveToFile();
		    	 	
		}});
		
		JButton load = new JButton("LOAD");
		load.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {	    	
		    	System.out.println("load"); 
		    	loadFromfile();
		}});
		
		panel = new PrintBoard(board);
		panel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		
		frame.addComponentListener(new ComponentAdapter() 
		{  
	        public void componentResized(ComponentEvent evt) {   
//	            int width = 7;  
//	            int heigh = 8;  
//	            Rectangle b = evt.getComponent().getBounds();
//	            evt.getComponent().setBounds(b.x, b.y, b.width, b.width*width/heigh);
	            Images.reload(board);
	        }
		});
		
		JLabel container = new JLabel();
		JPanel box = new JPanel();
		label = new JLabel("Turn nr: "+board.turnCount+"\n Player lived/max : 0"
		        +"/1000 "+" strengh: 5");
		
		container.setLayout(new GridLayout());	
		box.setLayout(new GridLayout());	
		box.add(save);
		box.add(load);
		box.add(button);
		container.add(label);
		container.add(box);
		container.setPreferredSize(new Dimension(700,40));
		
		
		frame.setLayout(new BorderLayout());
		frame.add(container,BorderLayout.PAGE_START);
		frame.add(panel,BorderLayout.CENTER);
		frame.add(buttonAuto,BorderLayout.PAGE_END);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(700,800);
		frame.setMinimumSize(new Dimension(350,400));
		frame.setVisible(true);		
		Images.reload(board);
		
		//keyboard handling
		frame.addKeyListener(new KeyListener() {		
			@Override
			public void keyTyped(KeyEvent e) {
				GameBoard.lastInput= e.getKeyChar();
			}		
			@Override
			public void keyReleased(KeyEvent e) {
				GameBoard.lastInput='/';			
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)GameBoard.lastInput='d';
				else if(e.getKeyCode()==KeyEvent.VK_LEFT)GameBoard.lastInput='a';
				else if(e.getKeyCode()==KeyEvent.VK_UP)GameBoard.lastInput='w';
				else if(e.getKeyCode()==KeyEvent.VK_DOWN)GameBoard.lastInput='s';
				
			}
		});
	}
	
	private static void loadFromfile()
	{
		String input="";
		try {
			input = board.loadFromFile();
			TimeUnit.MILLISECONDS.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		} 
		if(input==null||input=="")return;
		_frame.frame.dispose();
		board.restart();
		if(worker!=null)worker.cancel(true);
		autoSymulate=false;
		board = new GameBoard();
		_frame = new GameControll(board);
			
		//System.out.print(input);
		
		String[] lines = input.split("\n");
		System.out.println(lines.length);
		board.turnCount=Integer.parseInt(lines[0]);
		for(int i=1;i<lines.length;i++)
		{
			System.out.println("index: "+i+" "+lines[i]);
			Organism org;
			Point pos = new Point(-1,-1);
			String[] vars = lines[i].split(" ");
				
			
				pos = new Point(Integer.parseInt(vars[3]),Integer.parseInt(vars[4]));
				if(vars[0].equals(ORG.ANTYLOPA.toString()))org = new Antelope(pos, board);
				else if(vars[0].equals(ORG.BARSZCZ.toString()))org = new Borscht(pos, board);
				else if(vars[0].equals(ORG.CYBEROWCA.toString()))org = new CyberSheep(pos, board);
				else if(vars[0].equals(ORG.FIRE.toString()))org = new Fire(pos, board);
				else if(vars[0].equals(ORG.GRACZ.toString()))org = new Player(pos, board);
				else if(vars[0].equals(ORG.GUARANA.toString()))org = new Guarana(pos, board);
				else if(vars[0].equals(ORG.LIS.toString()))org = new Fox(pos, board);
				else if(vars[0].equals(ORG.MLECZ.toString()))org = new Milt(pos, board);
				else if(vars[0].equals(ORG.OWCA.toString()))org = new Sheep(pos, board);
				else if(vars[0].equals(ORG.TRAWA.toString()))org = new Grass(pos, board);
				else if(vars[0].equals(ORG.WILCZEJAGODY.toString()))org = new WolfBerries(pos, board);
				else if(vars[0].equals(ORG.WILK.toString()))org = new Wolf(pos, board);
				else if(vars[0].equals(ORG.ZOLW.toString()))org = new Turtle(pos, board);
				else {
					System.out.println(vars[0]+ " didnt match any entity");
					org = null;
				}
				
				if(org!=null)
				{
					org.setAge(Integer.parseInt(vars[1]));
					org.setMaxAge(Integer.parseInt(vars[2]));
					org.setStrengh(Integer.parseInt(vars[5]));
					if(vars[6].equals("true"))
						org.setWasEating(true);
					
					System.out.println(org.getName().toString()+" "+org.getAge()+" "+org.getMaxAge()
					  +" "+org.getPos().getX()
					  +" "+org.getPos().getY()+" "+org.getStrengh()+" "+org.isWasEating() + "\n");
				}	
		}
		
		System.out.println("END of Load, succes");
		board.sortOrganisms();
	}
}

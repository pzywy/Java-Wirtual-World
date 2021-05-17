import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Animals.Player;
import Animals.Sheep;
import Animals.Turtle;
import Animals.Wolf;
import Board.GameBoard;
import util.Keyboard;
import util.Point;
import Board.PrintBoard;
import Plants.Borscht;
import Plants.Grass;
import Plants.Guarana;
import Plants.Milt;
import Plants.WolfBerries;
import data.Images;

public class GameControll{


	public static void main(String[] args) {
		
		createBoard();
		
		
		//TODO:
		//Create some limits of one species on board
	
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
		new Milt(new Point(1,0),board);
		
		new Guarana(new Point(6,4),board);
		
		new WolfBerries(new Point(18,12),board);
		
		new Borscht(new Point(16,12),board);
		
		new Grass(new Point(19,0),board);
		
		new Player(new Point(11,11),board);
		
		new Sheep(new Point(5,5),board);
		new Sheep(new Point(5,6),board);
		new Sheep(new Point(6,6),board);
		
		new Wolf(new Point(25,15),board);
		new Wolf(new Point(26,15),board);
		
		
		new Turtle(new Point(0,5),board);
		new Turtle(new Point(1,6),board);
		board.sortOrganisms();
	}
	
	public static void createBoard()
	{
		board = new GameBoard();
		_frame = new GameControll(board);
		putOrganismsOnBoard(board);
	}
	
	public static void restart()
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
        label.setText("Turn nr: "+GameBoard.turnCount+"\n Player lived/max : "
        +board.player.getAge()+"/"+board.player.getMaxAge()+" strengh: "+board.player.getStrengh());
	}
	
	
	public GameControll(GameBoard board)
	{
		
		frame = new Keyboard();
		
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
		
		panel = new PrintBoard(board);
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		
		frame.addComponentListener(new ComponentAdapter() 
		{  
	        public void componentResized(ComponentEvent evt) {
	            Images.reload();
	        }
		});
		
		label = new JLabel("Turn nr: "+GameBoard.turnCount);
		frame.add(label,BorderLayout.BEFORE_FIRST_LINE);
		frame.add(panel,BorderLayout.CENTER);
		frame.add(buttonAuto,BorderLayout.EAST);
		frame.add(button,BorderLayout.AFTER_LAST_LINE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(1000,800);
		frame.setVisible(true);		
		Images.reload();
		
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
}

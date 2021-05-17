import java.awt.BorderLayout;
import java.awt.GridLayout;
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
private static GameControll _frame;

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		
		
		//setup input
		GameBoard.lastInput='/';
		_frame = new GameControll(board);
		
		_frame.frame.addKeyListener(new KeyListener() {		
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
				// TODO Auto-generated method stub
				
			}
		});
		 _frame.frame.setAlwaysOnTop(true);
		 
		 
		 // add organisms
		new Milt(new Point(1,0),board);
	
		new Guarana(new Point(6,4),board);
		
		new WolfBerries(new Point(18,12),board);
		
		new Borscht(new Point(16,12),board);
		
		new Grass(new Point(19,0),board);
		
		new Player(new Point(11,11),board);
		
		new Sheep(new Point(5,5),board);
		new Sheep(new Point(5,6),board);
		new Sheep(new Point(6,6),board);
		
		board.sortOrganisms();
	}
	
	
	private static JPanel panel;
	private JFrame frame;
	private static JLabel label;
	public static boolean autoSymulate=false;
	private Worker worker;
	
	
	
	public static void turn(GameBoard board)
	{
		 _frame.frame.requestFocus();
		board.turn(panel);
        panel.repaint();
        label.setText("Turn nr: "+GameBoard.turnCount);
	}
	
	
	public GameControll(GameBoard board)
	{
		
		frame = new Keyboard();
		
		JButton button = new JButton("NEXT TURN");
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        turn(board);
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
		frame.add(buttonAuto,BorderLayout.AFTER_LAST_LINE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(1000,800);
		frame.setVisible(true);		
		Images.reload();
	}


}

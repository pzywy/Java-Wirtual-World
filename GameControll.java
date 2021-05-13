import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Board.GameBoard;
import Board.Point;
import Board.PrintBoard;
import Plants.Milt;
import data.Images;

public class GameControll{

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		new Milt(new Point(0,0),board);
		//board.delOrganism(milt);
		
		new GameControll(board);
		new Milt(new Point(6,4),board);

	}
	
	
	private JPanel panel;
	private JFrame frame;
	public void turn(GameBoard board)
	{
		board.turn();
        panel.repaint();
	}
	
	public GameControll(GameBoard board)
	{
		frame = new JFrame();
		
		JButton button = new JButton("NEXT TURN");
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        turn(board);
		    }
		});
		
		panel = new PrintBoard(board);
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		//panel.setLayout(new GridLayout(0, 1));
		
		frame.addComponentListener(new ComponentAdapter() 
		{  
	        public void componentResized(ComponentEvent evt) {
	            Images.reload();
	        }
	});
		
		frame.add(panel,BorderLayout.CENTER);
		frame.add(button,BorderLayout.AFTER_LAST_LINE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(600,600);
		frame.setVisible(true);		
		
	}


}

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Board.GameBoard;
import Board.PrintBoard;
import Plants.Milt;

public class GUI{

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		Milt milt = new Milt(1, 1,board);
		//board.delOrganism(milt);
		
		new GUI(board);
		Milt milt2 = new Milt(0, 0,board);

	}
	
	
	private JPanel panel;
	private JFrame frame;
	public GUI(GameBoard board)
	{
		frame = new JFrame();
		JButton button = new JButton("NEXT TURN");
		button.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    }
		});
		
		panel = new PrintBoard(board);
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
			
		
		frame.add(panel,BorderLayout.CENTER);
		frame.add(button,BorderLayout.AFTER_LAST_LINE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(600,600);
		frame.setVisible(true);	

	}


}

import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI{

	public static void main(String[] args) {
		new GUI();
	}
	
	private JPanel panel;
	private JFrame frame;
	public GUI()
	{
		frame = new JFrame();
		
		panel = new PrintBoard();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
			
		
		frame.add(panel,BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Wirtual World");
		frame.pack();
		frame.setSize(600,600);
		frame.setVisible(true);	

	}


}

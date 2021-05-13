package Board;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PrintBoard extends JPanel {
	
	private GameBoard board;
	
	public PrintBoard(GameBoard _board)
	{
		board = _board;
	}
	
	private static final long serialVersionUID = 1L;
	
	static final int originX=25;
	static final int originY=25;
	
	
	

	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		printGrid(g2);
		
		
		board.getListOfOrganisms().forEach((org) ->{
			drawCell(g2,org.getX(),org.getY(),org.getColor());	
		});
			
	}
	
	
	private void drawCell(Graphics2D g,int row, int col,Color color)
	{
		g.setColor(color);
		int x = originX;
		int y = originY;
		g.fillRoundRect(x*(row+1), y*(col+1), GameBoard.cellSide, originY, 30, 30);
	}
	
	private void printGrid(Graphics2D g)
	{
		for(int i=0;i<GameBoard.rows+1;i++)
		{
			g.drawLine(originX, originY+i*GameBoard.cellSide,originX+GameBoard.cols*GameBoard.cellSide, originY+i*GameBoard.cellSide);	
		}
		for(int i=0;i<GameBoard.cols+1;i++)
		{
			g.drawLine(originX + i * GameBoard.cellSide,originY,originX + i * GameBoard.cellSide,originY + GameBoard.rows * GameBoard.cellSide);	
		}
	}
}

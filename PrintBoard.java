import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PrintBoard extends JPanel {
	
//	private static int frameX;
//	private static int frameY;
//	public BoardPanel(int _frameX,int _frameY)
//	{
//		frameX = _frameX;
//		frameY = _frameY;
//	}
	
	private static final long serialVersionUID = 1L;
	
	static final int originX=25;
	static final int originY=25;
	
	static final int cellSide=25;
	

	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		printGrid(g2);
		drawCell(g2,0,2);
	}
	
	
	private void drawCell(Graphics2D g,int row, int col)
	{
		g.setColor(Color.BLUE);
		int x = originX;
		int y = originY;
		g.fillRoundRect(x*(row+1), y*(col+1), cellSide, originY, 30, 30);
	}
	
	private void printGrid(Graphics2D g)
	{
		for(int i=0;i<GameBoard.rows+1;i++)
		{
			g.drawLine(originX, originY+i*cellSide,originX+GameBoard.cols*cellSide, originY+i*cellSide);	
		}
		for(int i=0;i<GameBoard.cols+1;i++)
		{
			g.drawLine(originX + i * cellSide,originY,originX + i * cellSide,originY + GameBoard.rows * cellSide);	
		}
	}
}

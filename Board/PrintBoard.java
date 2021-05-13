package Board;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import Organisms.Organism;

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
		GameBoard.cellSide = ( getWidth() ) / 15;
				
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		printGrid(g2);
		
		
		board.getListOfOrganisms().forEach((org) ->{
			if(org.isAlive())
				//drawCell(g2,org.getPos().getX(),org.getPos().getY(),org.getColor());
				
				drawImg(g2,org.getPos().getX(),org.getPos().getY(),org.getImg());
		});
		
//		for(int i = 0; i < board.getListOfOrganisms().size();i++)
//		{
//			Organism org = board.getListOfOrganisms().get(i);
//			if(org.isAlive())
//				drawImg(g2,org.getPos().getX(),org.getPos().getY(),org.getImg());
//		}
		
	}
	
	
	private void drawCell(Graphics2D g,int row, int col,Color color)
	{
		
		g.setColor(color);
		int x = originX;
		int y = originY;
		g.fillRoundRect(
				(int)(x*1.05)+GameBoard.cellSide *(row),
				(int)(y*1.05)+GameBoard.cellSide *(col),
				(int)Math.round(GameBoard.cellSide*0.9),
				(int)Math.round(GameBoard.cellSide*0.9)
				, 30, 30);
	}
	
	private void drawImg(Graphics2D g,int row, int col, Image img)
	{
		int x = originX;
		int y = originY;
		
		g.drawImage(img,
				(int)(x*1.05)+GameBoard.cellSide *(row),
				(int)(y*1.05)+GameBoard.cellSide *(col)
				,this);  
	}
	
	private void printGrid(Graphics2D g)
	{
		for(int i=0;i<GameBoard.rows+1;i++)
		{
			g.drawLine(originX, originY+i*GameBoard.cellSide,
					originX+GameBoard.cols*GameBoard.cellSide, originY+i*GameBoard.cellSide);	
		}
		for(int i=0;i<GameBoard.cols+1;i++)
		{
			g.drawLine(originX + i * GameBoard.cellSide,
					originY,originX + i * GameBoard.cellSide,
					originY + GameBoard.rows * GameBoard.cellSide);	
		}
	}
}

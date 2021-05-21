package Board;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		super.paintComponent(g);
		board.cellSide = ( getHeight()*10/board.rows ) / 11;
				
		
		
		//g2.setColor(new Color(86, 125, 70));   NICE GREEN
		g.setColor(new Color(194, 178, 128));
		
		g.fillRect(originX, originY,(int)(board.cellSide*(board.cols+0.4)), (int)(board.cellSide*(board.rows+0.4)));
		
		g.setColor(Color.black);
		//printGrid(g);
		
		
		List<Organism> organismsList = new ArrayList<Organism>(); 
		organismsList = board.getListOfOrganisms();
		
		organismsList.forEach((org) ->{
			if(org!=null&&org.isAlive())
				drawImg(g,org.getPos().getX(),org.getPos().getY(),org.getImg());
		});
		
//		for(int x=0;x<board.cols;x++)
//		{
//			for(int y=0;y<board.rows;y++)
//			{
//				Organism org = board.getFromArray(new Point(x,y));
//						if(org!=null&&org.isAlive())
//							drawImg(g,org.getPos().getX(),org.getPos().getY(),org.getImg());
//			}
//		}
		
		
//		for (Iterator<Organism> _org = board.getListOfOrganisms().iterator(); _org.hasNext();) {
//			Organism org = _org.next();
//			if(org!=null&&org.isAlive())
//				drawImg(g,org.getPos().getX(),org.getPos().getY(),org.getImg());
//			//else if(org!=null&&org.isAlive()) org.del();
//		}
		
		
		
		
	}
	
	
	@SuppressWarnings("unused")
	private void drawCell(Graphics2D g,int row, int col,Color color)
	{
		
		g.setColor(color);
		int x = originX;
		int y = originY;
		g.fillRoundRect(
				(int)(x*1.05)+board.cellSide *(row),
				(int)(y*1.05)+board.cellSide *(col),
				(int)Math.round(board.cellSide*0.9),
				(int)Math.round(board.cellSide*0.9)
				, 30, 30);
	}
	
	private void drawImg(Graphics g,int row, int col, Image img)
	{
		int x = originX;
		int y = originY;
		
		g.drawImage(img,
				(int)(x*1.05)+board.cellSide *(row),
				(int)(y*1.05)+board.cellSide *(col)
				,this);  
	}
	
	@SuppressWarnings("unused")
	private void printGrid(Graphics g)
	{
		for(int i=0;i<board.rows+1;i++)
		{
			g.drawLine(originX, originY+i*board.cellSide,
					originX+board.cols*board.cellSide, originY+i*board.cellSide);	
		}
		for(int i=0;i<board.cols+1;i++)
		{
			g.drawLine(originX + i * board.cellSide,
					originY,originX + i * board.cellSide,
					originY + board.rows * board.cellSide);	
		}
	}
}

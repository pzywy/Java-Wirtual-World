package Plants;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;


import Board.GameBoard;
import Board.Point;
import Organisms.ORG;
import Organisms.Plant;
import data.Images;

public class Milt extends Plant {

	public Milt(Point _pos, GameBoard _board) {
		super(ORG.MLECZ, 0, _pos, _board);
		
	setColor(Color.yellow);
	
	
			
			
	//setImg(Toolkit.getDefaultToolkit().getImage("src/data/milt.jpg"));

	}
	
	public Image getImg() {
		
				
		return Images.milt;

	}
	
	protected void reproduct(Point _pos)
	{
		System.out.println("Reproduce "+getName()+ "on Pos: "+_pos.getX()+" "+_pos.getY());
		new Milt(_pos,board);
		System.out.println("Reproducted");
	}
	
}

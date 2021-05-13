package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Board.Point;
import Organisms.ORG;
import Organisms.Plant;
import data.Images;

public class Milt extends Plant {

	public Milt(Point _pos, GameBoard _board) {
		super(ORG.MLECZ, 0, _pos, _board);
		
	setColor(Color.yellow);
	reproductionChance = 20;
	setMaxAge(100);
	
			

	}
	
	@Override
	public void turn() {
		attemtReproduct();
		attemtReproduct();
		super.turn();
		//System.out.println("Milt Pos: "+getPos().getX()+", "+getPos().getY());
	}
	
	public Image getImg() {	
		return Images.milt;
	}
	
	protected void reproduct(Point _pos)
	{		
		new Milt(_pos,board);
		super.reproduct(_pos);
	}
	
}

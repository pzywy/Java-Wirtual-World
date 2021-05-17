package Plants;

import java.awt.Color;
import java.awt.Image;
import Board.GameBoard;
import Organisms.ORG;
import Organisms.Organism;
import Organisms.Plant;
import data.Images;
import util.Point;

public class Guarana extends Plant {

	public Guarana(Point _pos, GameBoard _board) {
		super(ORG.GUARANA, 0, _pos, _board);
		
	setColor(Color.red);
	reproductionChance = 15;
	setMaxAge(400);
	

	}
	
	public Image getImg() {			
		return Images.guarana;
	}
	
	public void gotEaten(Organism org)
	{
		org.setMaxAge(org.getMaxAge()+10);
		org.setStrengh(org.getStrengh()+3);
		System.out.println(org.getName()+" ate guarana (+3 strengh)");
		super.gotEaten(org);
		
	}
	
	protected void reproduct(Point _pos)
	{
		new Guarana(_pos,board);
		super.reproduct(_pos);
	}
	

	
}

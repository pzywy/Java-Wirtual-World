package Organisms;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import Board.GameBoard;
import Board.Point;

public abstract class Organism {
public Organism(ORG _name,int _strengh, int _effort, Point _pos,GameBoard _board)
{
	setName(_name);
	setStrengh(_strengh);
	setEffort(_effort);
	setPos(_pos);
	setBoard(_board);
	setAlive(true);
	setAge(0);
	board.addOrganism(this);
}


public void turn()
{
	setAge(age+1);
	//pos=new Point(pos.getX(),pos.getY()+1);
}

public Point getEmptyNeighbourCell()
{
	System.out.println("Getting empty cell");
	Point cell = new Point(-1,-1);
	for(int i=0;i<=9;i++)
	{
		Random rand = new Random();
		i = rand.nextInt(10);
		
		cell = new Point((int)((i/3.5)-1),((i % 3) + 1) % 3);
		if(cell.getY()==2)cell.setY(-1);
		
		cell.setX(cell.getX()+pos.getX());
		cell.setY(cell.getY()+pos.getY());
		
		System.out.println("checking Pos= "+cell.getX()+" "+cell.getY());
		
		if(cell.getX()<0 || cell.getY()<0 || cell.getY()>=GameBoard.cols || cell.getX()>=GameBoard.rows)
			continue;
		
		if(board.getFromArray(cell)==null||board.getFromArray(cell).isAlive())
			return cell;
	}
	return new Point(-1,-1);
}

protected GameBoard board;
private ORG name;
private int strengh;
private int effort;
private Point pos;
private int age;//in turns
private boolean alive;
private Color color = Color.blue;
private Image img;

public boolean isAlive() {
	return alive;
}
public void setAlive(boolean alive) {
	this.alive = alive;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

public int getEffort() {
	return effort;
}
public void setEffort(int effort) {
	this.effort = effort;
}
public int getStrengh() {
	return strengh;
}
public void setStrengh(int strengh) {
	this.strengh = strengh;
}
public ORG getName() {
	return name;
}
public void setName(ORG name) {
	this.name = name;
}

int getID() 
{
	return java.lang.System.identityHashCode(this);
}
public GameBoard getBoard() {
	return board;
}
public void setBoard(GameBoard board) {
	this.board = board;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}


public Image getImg() {
	return img;
}


public void setImg(Image img) {
	this.img = img;
}


public Point getPos() {
	return pos;
}


public void setPos(Point pos) {
	this.pos = pos;
}




}

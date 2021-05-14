package Organisms;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import Board.GameBoard;
import util.Point;

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

//1 for pass, 0 for blocked, -1 for dead
public int didPassCollision(Organism org)
{
	System.out.println(org.getName()+" attacked "+getName());
	return 0;
}


public void turn()
{
	//System.out.println("That was Turn of "+getName());
	if(!alive)return;
	setAge(age+1);
	//pos=new Point(pos.getX(),pos.getY()+1);
		//dies of old
	if(getAge()>=getMaxAge())died("of old in age " + getAge());
	
}

public void died(String cause)
{
	alive=false;
	System.out.println(getName()+" Died "+cause);
	//del();
}

public void del()
{
	board.delOrganism(this);
}

public Point getEmptyNeighbourCell()
{
	//System.out.println("Getting empty cell");
	Point cell = new Point(-1,-1);
	for(int i=0;i<=9;i++)
	{
		Random rand = new Random();
		i = rand.nextInt(10);
		
		cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
		if(cell.getY()==2)cell.setY(-1);
		
		cell.setX(cell.getX()+pos.getX());
		cell.setY(cell.getY()+pos.getY());
		
		//System.out.println("checking Pos= "+cell.getX()+" "+cell.getY());
		
		if(cell.getX()<0 || cell.getY()<0 || cell.getY()>=GameBoard.rows || cell.getX()>=GameBoard.cols)
			continue;
		
		if(board.getFromArray(cell)==null||!board.getFromArray(cell).isAlive())
			return cell;
	}
	return new Point(-1,-1);
}



protected void terminateAllNeighbour()
{
	Point cell = new Point(-1,-1);
	for(int i=1;i<=9;i++)
	{
		cell = new Point(-1 + (int)(i/3.5)	,(	(i % 3) + 1) % 3);
		if(cell.getY()==2)	cell.setY(-1);
		
		if(cell.getX()==0&&cell.getY()==0)
			continue;
		
		cell.setX(cell.getX()+pos.getX());
		cell.setY(cell.getY()+pos.getY());
		
		if(cell.getX()<0 || cell.getY()<0 || cell.getY()>=GameBoard.rows || cell.getX()>=GameBoard.cols)
			continue;
		
		Organism org = board.getFromArray(cell);
		
		if(org!=null&&org.isAlive()&&org.getName()!=getName())
		{
			//System.out.println("TERMINATE");
			org.died("of termination");
		}
		
	}
}


protected void attemtReproduct()
{
	if(getAge()<getReproduceAge() || reproductionChance<=0)return;
	Random rand = new Random();
	if(rand.nextInt((int)(1000/reproductionChance))==0)
	{
		//System.out.println("Attempting reproduce "+getName()+ " on Pos: "+getPos().getX()+" "+getPos().getY());
		Point pos = getEmptyNeighbourCell();
		
		if(pos.getX()!=-1)
		{
			reproduct(pos);
		}
	}
}
protected void reproduct(Point pos) {
	//System.out.println("Reproduce "+getName()+ " on Pos: "+pos.getX()+" "+pos.getY());
}


protected int reproductionChance=2;//in percent
protected GameBoard board;
private ORG name;
private int strengh;
private int effort;
private Point pos;
private int age;//in turns
private boolean alive;
private Color color = Color.blue;
private Image img;
private int maxAge=100;

protected int getReproduceAge()
{
	return 10;
}

public int getMaxAge() {
	return maxAge;
}

public void setMaxAge(int maxAge) {
	this.maxAge = maxAge;
}


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

public int getID() 
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

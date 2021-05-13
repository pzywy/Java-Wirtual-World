package Organisms;

import java.awt.Color;

import Board.GameBoard;

public abstract class Organism {
public Organism(ORG _name,int _strengh, int _effort, int _x, int _y,GameBoard _board)
{
	setName(_name);
	setStrengh(_strengh);
	setEffort(_effort);
	setX(_x);
	setY(_y);
	setBoard(_board);
	
	board.addOrganism(this);
}

private GameBoard board;
private ORG name;
private int strengh;
private int effort;
private int X;
private int Y;
private int age;//in turns
private boolean alive;
private Color color = Color.blue;

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
public int getY() {
	return Y;
}
public void setY(int y) {
	Y = y;
}
public int getX() {
	return X;
}
public void setX(int x) {
	X = x;
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

}

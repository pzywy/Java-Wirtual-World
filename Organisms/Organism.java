package Organisms;

public abstract class Organism {
public Organism(ORG _name,int _strengh, int _effort, int _x, int _y)
{
	name = _name;
	strengh=_strengh;
	effort = _effort;
	X = _x;
	Y = _y;
}

private ORG name;
private int strengh;
private int effort;
private int X;
private int Y;
private int age;//in turns
private boolean alive;

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
	
}

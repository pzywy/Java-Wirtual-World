package util;

public class Point {
private int x;
private int y;

public Point(int _x, int _y)
{
	setX(_x);
	setY(_y);
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

}

package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Keyboard extends JFrame implements KeyListener {
 
// 	private char lastInput='/';
 
 	public Keyboard() {
 		addKeyListener(this);
 		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
 	}
 
 	@Override
 	public void keyPressed(KeyEvent evt) {
// 		lastInput = evt.getKeyChar();
// 		GameBoard.lastInput=lastInput;
// 		System.out.println(lastInput);
// 		GameBoard.lastInput=lastInput;
 	}
 
 	@Override
 	public void keyReleased(KeyEvent evt) {
// 		lastInput = '/';
// 		GameBoard.lastInput=lastInput;
 	}
 
 	@Override
 	public void keyTyped(KeyEvent evt) {
 
 	}
 	
 

 }
package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainWindow extends JFrame implements KeyListener {
 
// 	private char lastInput='/';
 
 	public MainWindow() {
 		addKeyListener(this);
 		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
 	}
 
 	@Override
 	public void keyPressed(KeyEvent evt) {
 	}
 
 	@Override
 	public void keyReleased(KeyEvent evt) {
 	}
 
 	@Override
 	public void keyTyped(KeyEvent evt) {
 
 	}
 }
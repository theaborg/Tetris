package se.liu.thebo717.tetris;

import javax.swing.*;
import java.awt.*;


public class TetrisViewer
{
    private TetrisComponent tetrisComponent;
    private JFrame frame = new JFrame();
    private BarComponent barComponent;
    private Board board;

    public TetrisViewer(final Board board) {
	tetrisComponent = new TetrisComponent(board);
	barComponent = new BarComponent(board);
    }

    public JFrame getFrame() {
	return frame;
    }

    public TetrisComponent getTetrisComponent() {
	return tetrisComponent;
    }

    public BarComponent getBarComponent() {
	return barComponent;
    }

    public void show(){
	frame.setJMenuBar(barComponent.getMenuBar());
	frame.setLayout(new BorderLayout());
	frame.add(tetrisComponent);
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
    }

}

package se.liu.thebo717.tetris;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarComponent extends JComponent implements ActionListener
{
    final JMenuBar menuBar = new JMenuBar();

    private final JMenu file = new JMenu("Options");
    private JMenuItem controls = new JMenuItem("Controls", 'C');
    private JMenuItem quit = new JMenuItem("Quit", 'Q');

    private int score;
    private Board board;
    private JLabel scoreViewer;


    public BarComponent(final Board board) {
	this.board = board;

	file.add(controls);
	controls.addActionListener(this);
	file.add(quit);
	quit.addActionListener(this);
	menuBar.add(file);

	score = board.getPoints();
	scoreViewer = new JLabel("SCORE:	" + score);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(scoreViewer);
    }

    public JMenuBar getMenuBar() {
	return menuBar;
    }

    public JMenu getFile() {
	return file;
    }

    public void actionPerformed(final ActionEvent e) {
	if(e.getSource().equals(quit)){
	    System.out.println("quit");
	    boolean exit = JOptionPane.showConfirmDialog(null,
							 "Would you like to quit?", "",
							 JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	    if(exit){
		System.exit(0);
	    }
	}
	else if (e.getSource().equals(controls)){
	    JOptionPane.showConfirmDialog(null,
					  "KEY UP: rotate left	" + System.lineSeparator() +
					  "KEY DOWN: rotate right" + System.lineSeparator() +
					  "KEY LEFT: move left" + System.lineSeparator() +
					  "KEY RIGHT: move right",
					  "Controls", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					  null);
	}
    }

    public void updatePoints() {
	int nextScore = board.getPoints();
	if(score != nextScore){
	    score = nextScore;
	    menuBar.remove(scoreViewer);
	    scoreViewer = new JLabel("SCORE:	" + score);
	    menuBar.add(scoreViewer);
	}
    }
}

package se.liu.thebo717.tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisViewer
{
    private Board board;
    private JFrame frame = new JFrame();

    public TetrisViewer(final Board board) {
	this.board = board;

    }
    public void show(Board board){
	//hämtar storlek från boarden
	int width = board.getWidth();
	int height = board.getHeight();
	//gör en ny converter och JtextArea
	BoardToTextConverter converter = new BoardToTextConverter();
	JTextArea textArea = new JTextArea(height, width);
	//fyller textArean med strängen som returneras från BoardToTextConverter
	textArea.setText(converter.convertToText(board));
	//sätter layouthanterare till borderlayout
	frame.setLayout(new BorderLayout());
	//lägger till textarean i frame och placerar den i mitten
	frame.add(textArea, BorderLayout.CENTER);
	//sätter all text till samma stil och storlek
	textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	//gör fönstret synligt
	frame.pack();
	frame.setVisible(true);
    }
}

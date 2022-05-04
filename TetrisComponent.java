package se.liu.thebo717.tetris;
import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private final EnumMap<SquareType, Color> squareColors = createColorMap();
    private final int squareHeight;
    private final int squareWidth;
    private final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();


    public TetrisComponent(final Board board) {
	this.board = board;
	squareHeight = getPreferredSize().height / board.getHeight();
	squareWidth= getPreferredSize().width / board.getWidth();
    }

    public Board getBoard() {
	return board;
    }

    public Dimension getPreferredSize() {
	Dimension size = super.getPreferredSize();
	final int gameScreenRatio = 2;
	size.width = board.getWidth()*25;
	size.height = board.getHeight()*25;
	return size;
    }

    private static EnumMap<SquareType, Color> createColorMap(){
	EnumMap<SquareType, Color> squareColors = new EnumMap<>(SquareType.class);
	squareColors.put(SquareType.EMPTY, Color.GRAY);
	squareColors.put(SquareType.I, Color.GREEN);
	squareColors.put(SquareType.O, Color.BLUE);
	squareColors.put(SquareType.T, Color.MAGENTA);
	squareColors.put(SquareType.S, Color.CYAN);
	squareColors.put(SquareType.Z, Color.RED);
	squareColors.put(SquareType.J, Color.YELLOW);
	squareColors.put(SquareType.L, Color.WHITE);
	return squareColors;
    }

    @Override protected void paintComponent(Graphics g) {
	final  Graphics2D g2 = (Graphics2D) g;
	final int distanceBetweenSquares = 1;

	for (int height = 0; height < board.getHeight(); height++) {
	    for (int width = 0; width < board.getWidth(); width++) {
		SquareType squareAtCurrentPos = board.getVisibleSquareAt(height,width);
		g2.setColor(squareColors.get(squareAtCurrentPos));
		g2.fillRect(width * squareWidth, height * squareHeight,
			    squareWidth-distanceBetweenSquares,
			    squareHeight-distanceBetweenSquares);
	    }
	}
    }

    @Override public void boardChanged() {
	repaint();
    }
}

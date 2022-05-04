package se.liu.thebo717.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveAction
{
    private Board board;

    public MoveAction(final TetrisViewer viewer, Board board) {
        JFrame frame = viewer.getFrame();
        this.board = board;
        JComponent pane = frame.getRootPane();
        final InputMap in = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        in.put(KeyStroke.getKeyStroke("LEFT"), Direction.LEFT);
        in.put(KeyStroke.getKeyStroke("RIGHT"), Direction.RIGHT);
        in.put(KeyStroke.getKeyStroke("UP"), Direction.UP);
        in.put(KeyStroke.getKeyStroke("DOWN"), Direction.DOWN);


        final ActionMap act = pane.getActionMap();
        act.put(Direction.LEFT, new Move(Direction.LEFT));
        act.put(Direction.RIGHT, new Move(Direction.RIGHT));
        act.put(Direction.UP, new Rotate(Direction.UP));
        act.put(Direction.DOWN, new Rotate(Direction.DOWN));


    }

    private class Move extends AbstractAction{
        private Direction direction;
        private Move(Direction direction){
            this.direction = direction;
        }
        @Override public void actionPerformed(final ActionEvent e) {
            board.move(direction);
        }
    }
    private class Rotate extends AbstractAction{
        private Direction direction;
        private Rotate(Direction direction){
            this.direction = direction;
        }
        @Override public void actionPerformed(final ActionEvent e) {
            board.rotate(direction);
        }
    }
}

package se.liu.thebo717.tetris;
import javax.swing.*;
import java.awt.event.ActionEvent;



public class StepMaker extends AbstractAction
{
    private Board board;
    private TetrisViewer viewer;
    private int timerValue;
    private StartscreenViewer startscreenViewer;

    public StepMaker(final Board board, final TetrisViewer viewer, HighscoreList highscoreList){
	this.board = board;
	this.viewer = viewer;
	timerValue  = 0;
	startscreenViewer = new StartscreenViewer();
    }

    public void actionPerformed(ActionEvent e){
	HighScore highScore = board.getHighscore();
	timerValue ++;
	if (timerValue > 5 && timerValue != 7){
	    if(board.isGameOver()){
		GameShell.setClockTimerMode(false);
		highScore.setNameAndScore(board.getPoints());
	    }
	    else {
		TetrisComponent component = viewer.getTetrisComponent();
		board.addBoardListener(component);
		board.tick();
		MoveAction moveAction = new MoveAction(viewer, board);
		viewer.getBarComponent().updatePoints();
		viewer.show();
	    }
	}
	else if (timerValue == 7){
	    startscreenViewer.getFrame().dispose();
	}
	else{
	    startscreenViewer.show();
	}
    }
}

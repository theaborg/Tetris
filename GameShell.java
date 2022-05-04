package se.liu.thebo717.tetris;
import java.util.Random;
import javax.swing.*;


public class GameShell
{
    private final static Random RND = new Random();
    private final static int BOARD_WIDTH = 10;
    private final static int BOARD_HEIGHT = 20;
    private static HighscoreList highscoreList = new HighscoreList();
    /* här får jag showstop, men vet inte hur jag ska fixa det //:
    	kan inte sätta clockTimer till final, för då kan jag inte sätta värdet i konstruktorn,
    	kan inte heller ta bort static för då kan jag inte stoppa klockan ifrån andra klasser när
    	spelet är över och jag inte vill köra actionperformed mer.
    	Kanske ändra tillbaka så klockan fortsätter ticka i guess ): jobbigt
     */
    private static Timer clockTimer = null;
    private final static int DELAY = 20;

    public GameShell() {
	Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT, highscoreList);
	TetrisViewer tetrisViewer = new TetrisViewer(board);
	StepMaker stepMaker = new StepMaker(board, tetrisViewer, highscoreList);
	clockTimer = new Timer(DELAY, stepMaker);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    public static boolean getClockTimerMode() {
	return clockTimer.isRunning();
    }

    public static void setClockTimerMode(final boolean mode) {
	if(mode){
	    clockTimer.start();
	}
	else {
	    clockTimer.stop();
	}
    }

    public static void main(String[] args) {
	new GameShell();
    }
}

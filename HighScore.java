package se.liu.thebo717.tetris;

import javax.swing.*;
import java.util.List;

public class HighScore
{
    private String name = null;
    private HighscoreList scoreList = new HighscoreList();
    private ScoreComparator scoreComparator;
    private final static int SCORELIST_LENGTH = 10;

    public HighScore(final HighscoreList scoreList) {
	this.scoreList = scoreList;
	this.scoreComparator = new ScoreComparator(scoreList);
    }

    public void setNameAndScore(int score){
	List<Integer> sorted = scoreComparator.getSortedList();
	String name = JOptionPane.showInputDialog("Enter name:");
	if(sorted == null || sorted.size() < SCORELIST_LENGTH || sorted.get(sorted.size()-1) < score){
	    scoreList.addNameAndScore(score, name);
	}
	scoreList.viewHighscoreList();
    }
}

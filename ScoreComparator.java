package se.liu.thebo717.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreComparator
{
    private HighscoreList highscoreList;
    private List<Integer> sortedList = null;

    public ScoreComparator(final HighscoreList highscoreList) {
	this.highscoreList = highscoreList;
    }

    public List<Integer> getSortedList() {
	return sortedList;
    }

    public List<Integer> compareScore(){
	Map<Integer, String > scoreMap = highscoreList.getScoreMap();
	Map<Integer, String > sortedScoreMap = new HashMap<>();
	Integer[] scoreList = new Integer[scoreMap.size()];
	List<Integer> tempScoreList = new ArrayList<>(scoreMap.keySet());
	scoreList = tempScoreList.toArray(scoreList);
	sortedList = Arrays.stream(scoreList).sorted().toList();
	System.out.println(sortedList);
	return sortedList;
    }
}

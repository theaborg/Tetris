package se.liu.thebo717.tetris;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighscoreList extends JComponent
{
    private JFrame frame = new JFrame();
    private ScoreComparator comparator = new ScoreComparator(this);
    private Map<Integer,String> scoreMap = new HashMap<>();

    private final static int SCOREBOARD_WIDTH = 500;
    private final static int SCOREBOARD_HEIGHT = 600;
    private final static int SPACE_BETWEEN_LABELS = 50;
    private final static int TOP_MARGIN = 10;
    private final static int SIDE_MARGIN = 100;
    private final static int LABEL_WIDTH = 100;
    private final static int LABEL_HEIGHT= 20;
    private final static int LIST_LENGTH = 10;
    private final static int TITLE_WIDTH = 400;
    private final static int TITLE_HEIGHT = 40;


    public Map<Integer, String> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(final Map<Integer, String> scoreMap) {
        this.scoreMap = scoreMap;
    }

    public void addNameAndScore(int score, String name){
        scoreMap.put(6000, "Isak");
        scoreMap.put(200, "Jessica");
        scoreMap.put(900, "Jessica");
        scoreMap.put(400, "Thea");

        scoreMap.put(score, name);
    }

    public void viewHighscoreList(){
        Gson gson = new Gson();
        List<Integer> sortedScores = comparator.compareScore();
        int heightCoord = SPACE_BETWEEN_LABELS;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCOREBOARD_WIDTH, SCOREBOARD_HEIGHT);

        JLabel title = new JLabel("HIGHSCORES:");
        title.setBounds(SIDE_MARGIN, TOP_MARGIN, TITLE_WIDTH,TITLE_HEIGHT);
        frame.add(title);

        int size = sortedScores.size()-1;
        for (int index = 0; index < LIST_LENGTH ; index++) {

            Integer score = sortedScores.get(size-index);
            JLabel scoreLabel = new JLabel(score.toString() + " points");
            scoreLabel.setBounds(SIDE_MARGIN*2, TOP_MARGIN + heightCoord, LABEL_WIDTH,LABEL_HEIGHT);
            frame.add(scoreLabel);

            JLabel nameLabel = new JLabel(scoreMap.get(score) + ": ");
            nameLabel.setBounds(SIDE_MARGIN, TOP_MARGIN+ heightCoord , LABEL_WIDTH,LABEL_HEIGHT);
            frame.add(nameLabel);

            heightCoord += SPACE_BETWEEN_LABELS;
            if(index==size){
                index = LIST_LENGTH;
            }
        }

        //sista labeln hamnar tokigt så jag lägger till en tom sista :)
        JLabel bugFix = new JLabel();
        bugFix.setBounds(SIDE_MARGIN, heightCoord, LABEL_WIDTH, LABEL_HEIGHT);
        frame.add(bugFix);

        frame.setVisible(true);
    }

    @Override protected void paintComponent(Graphics g) {
    }

}

package se.liu.thebo717.tetris;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Board
{
    private int points = 0;
    private SquareType[][] squares;
    private int width;
    private int height;
    private boolean gameOver = false;
    private final static int MARGIN = 2;
    private final static int DOUBLE_MARGIN = MARGIN*2;
    private final Point startingPos;
    private final static Random RND = new Random();
    private Poly polyFalling;
    private Point fallingPos;
    private List<BoardListener> boardListeners = new ArrayList<>();
    private Map<Integer,Integer> pointMap = Map.of(1, 100, 2, 300, 3, 500, 4, 800);
    private HighScore highscore;


    public Board(final int width, final int height, HighscoreList highscoreList) {
	this.highscore = new HighScore(highscoreList);
	this.width = width;
	this.height = height;
	polyFalling = null;
	fallingPos = null;
	startingPos = new Point((width/2)-1, 0);
	squares = new SquareType[height+DOUBLE_MARGIN][width+DOUBLE_MARGIN];
	for (int heightCoord= 0; heightCoord < getHeight() + DOUBLE_MARGIN; heightCoord++) {
	    for (int widthCoord = 0; widthCoord < getWidth() + DOUBLE_MARGIN; widthCoord++) {
		if (heightCoord < MARGIN || widthCoord < MARGIN
		    || heightCoord >= getHeight()+MARGIN|| widthCoord >= getWidth()+MARGIN){
			squares[heightCoord][widthCoord] = SquareType.OUTSIDE;
		}
		else {
		    squares[heightCoord][widthCoord] = SquareType.EMPTY;
		}
	    }
	}
	notifyListeners();
    }

    public int getPoints() {
	return points;
    }

    public HighScore getHighscore() {
	return highscore;
    }

    public SquareType getSquareType(int heightCoord, int widthCoord) {
	return squares[heightCoord+MARGIN][widthCoord+MARGIN];
    }

    public Poly getPolyFalling() {return polyFalling;}

    public Point getFallingPos() {return fallingPos;}

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public boolean isGameOver() {
	return gameOver;
    }

    public void rotate(Direction dir){
	if(polyFalling!=null) {
	    SquareType[][] rotatedArray = polyFalling.getShape();
	    int polyIndex = polyFalling.getSquareTypeIndex();
	    int polySize = rotatedArray.length;
	    if (dir.equals(Direction.UP)) {
		rotatedArray = rotateLeft(polyFalling);
		if (!hasCollision(fallingPos, new Poly(rotatedArray, polyIndex))) {
		    polyFalling = new Poly(rotatedArray, polyIndex);
		}
	    }
	    else if (dir.equals(Direction.DOWN)) {
		rotatedArray = rotateRight(polyFalling);
		if (!hasCollision(fallingPos, new Poly(rotatedArray, polyIndex))) {
		    polyFalling = new Poly(rotatedArray, polyIndex);
		}
	    }
	}
    }

    private SquareType[][] rotateRight(Poly poly){
	SquareType[][] rotatedPoly = rotateLeft(poly);
	rotatedPoly = rotateLeft(new Poly(rotatedPoly, polyFalling.getSquareTypeIndex()));
	rotatedPoly = rotateLeft(new Poly(rotatedPoly, polyFalling.getSquareTypeIndex()));
	return rotatedPoly;
    }

    private SquareType[][] rotateLeft(Poly poly){
	int polyLength = poly.getShape().length;
	SquareType[][] rotatedPoly = new SquareType[polyLength][polyLength];
	int polySize = rotatedPoly.length;
	for (int heightCoord = 0; heightCoord < polySize; heightCoord++) {
	    for (int widthCoord = 0; widthCoord < polySize; widthCoord++) {
		rotatedPoly[widthCoord][heightCoord] =
			poly.getShape()[heightCoord][polySize-1-widthCoord];
	    }
	}
	return rotatedPoly;
    }

    public void addBoardListener(BoardListener bl){
	boardListeners.add(bl);
    }

    private void notifyListeners(){
	for (BoardListener boardListener: boardListeners) {
	    boardListener.boardChanged();
	}
    }

    public void tick(){
	if (!isGameOver()){
	    int fullRows = checkForFullRow();
	    addPoints(fullRows);
	    if(polyFalling != null){
		moveFalling();
	    }
	    else {
		setFalling();
	    }
	}
	notifyListeners();
    }

    public void setFalling(){
	TetrominoMaker tetrominoMaker = new TetrominoMaker();
	int typeIndex =  RND.nextInt(1,SquareType.values().length-1);
	fallingPos = startingPos;
	polyFalling = tetrominoMaker.getPoly(typeIndex);
    }

    public void moveFalling(){
	if (hasCollision(startingPos, polyFalling)){
	    gameOver = true;
	    System.out.println("game over");
	}
	else {
	    Point possibleFallingPos = new Point(fallingPos.x, fallingPos.y + 1);
	    if ((fallingPos.y < getHeight()-1) && !hasCollision(possibleFallingPos, polyFalling)) {
		fallingPos = possibleFallingPos;
	    }
	    else{
		setPoly();
	    }
	}
    }

    public void move(Direction direction){
	if(fallingPos != null){
	    if (direction.equals(Direction.LEFT)){
		Point possibleFallingPos = new Point(fallingPos.x -1, fallingPos.y);
		if (!hasCollision(possibleFallingPos, polyFalling)){
		    fallingPos = possibleFallingPos;
		}
	    }
	    else if (direction.equals(Direction.RIGHT)){
		Point possibleFallingPos = new Point(fallingPos.x + 1, fallingPos.y);
		if (!hasCollision(possibleFallingPos, polyFalling)){
		    fallingPos = possibleFallingPos;
		}
	    }
	    notifyListeners();
	}
    }

    public boolean hasCollision(Point coord, Poly poly){
	//om ingen poly faller så har vi ingen kollision
	if(poly == null){
	    return false;
	}
	int heightCoord = coord.y;
	int widthCoord = coord.x;
	int fallingSize = poly.getShape().length;
	for (int internHeightCoord = 0; internHeightCoord < fallingSize; internHeightCoord++) {
	    for (int internWidthCoord = 0; internWidthCoord < fallingSize ; internWidthCoord++) {
		if (!poly.getShape()[internHeightCoord][internWidthCoord].equals(SquareType.EMPTY) &&
		    !getSquareType(heightCoord + internHeightCoord,
				  widthCoord + internWidthCoord).equals(SquareType.EMPTY)){
		    return true;
		}
	    }
	}
	return false;
    }

    public void setPoly(){
	for (int heightCoord = 0; heightCoord < polyFalling.getShape().length; heightCoord++) {
	    for (int widthCoord = 0; widthCoord < polyFalling.getShape().length; widthCoord++) {
		SquareType fallingSquare = polyFalling.getShape()[heightCoord][widthCoord];
		if (getVisibleSquareAt(fallingPos.y + heightCoord,
				       fallingPos.x + widthCoord).equals(fallingSquare)){
		    squares[fallingPos.y + heightCoord + MARGIN][fallingPos.x + widthCoord + MARGIN]
			    = polyFalling.getShape()[heightCoord][widthCoord];
		}
	    }
	}
	polyFalling = null;
    }

    public SquareType getVisibleSquareAt(int heightCoord,int widthCoord){
	if(polyFalling == null){
	    return getSquareType(heightCoord,widthCoord);
	}
	//vi sätter fallingSize till polyns storlek
	int fallingSize = polyFalling.getShape().length -1;
	//om x och y ligger inom polyns area
	if((widthCoord>=fallingPos.x && widthCoord<=fallingPos.x+fallingSize) &&
	   (heightCoord>=fallingPos.y && heightCoord<=fallingPos.y+fallingSize)){

	    int internWidthCoord = widthCoord - fallingPos.x;
	    int internHeightCoord = heightCoord - fallingPos.y;
	    SquareType fallingSquare = polyFalling.getShape()[internHeightCoord][internWidthCoord];
	    //och om rutans Squaretype inte är EMPTY
	    if(!fallingSquare.equals(SquareType.EMPTY)){
		//då returneras rutans Squaretype
		return fallingSquare;
	    }
	}
	//annars returneras den Squaretype som finns på platsen enligt boardens array
	return getSquareType(heightCoord,widthCoord);
    }
    
    public int checkForFullRow(){
	int amountOfFilledSquares = 0;
	int amountOfFilledRows = 0;
	int widthCoord = 0;
	for (int i = 0; i < getHeight(); i++) {
	    for (int j = 0; j < getWidth(); j++) {
		if (!getSquareType(i, j).equals(SquareType.EMPTY)){
		    amountOfFilledSquares ++;
		}
	    }
	    if (amountOfFilledSquares == getWidth()){
		amountOfFilledRows++;
		removeRow(i);
	    }
	    amountOfFilledSquares = 0;
	}
	return amountOfFilledRows;
    }
    
    public void removeRow(int heightCoord){
	for (int j = 0; j < getWidth(); j++) {
	    squares[heightCoord+MARGIN][j+MARGIN] = SquareType.EMPTY;
	}
	adjustBoard(heightCoord);
    }

    public void adjustBoard(int rowRemoved){
	for (int heightCoord = rowRemoved-1; heightCoord >=0; heightCoord--) {
	    for (int widthCoord = 0; widthCoord < getWidth(); widthCoord++) {
		squares[MARGIN + heightCoord +1][MARGIN + widthCoord] = getSquareType(heightCoord, widthCoord);
	    }
	}
    }

    public void addPoints(int removedRows){
	if(pointMap.get(removedRows) != null){
	    points += pointMap.get(removedRows);
	    System.out.println(points);
	}
    }
}


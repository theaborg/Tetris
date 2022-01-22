package se.liu.thebo717.tetris;

import java.awt.*;
import java.util.Random;

public class Board
{
    private SquareType[][] squares;
    private int width;
    private int height;
    private final static Random RND = new Random();
    private Poly polyFalling;
    private Point fallingPos;

    public Board(final int width, final int height) {
	TetrominoMaker t = new TetrominoMaker();
	this.width = width;
	this.height = height;
	squares = new SquareType[width][height];
	//för varje rad
	for (int row = 0; row < height; row++) {
	    //för varje kolumn
	    for (int col = 0; col < width; col++) {
		squares[col][row] = SquareType.EMPTY;
	    }
	}
	//polyFalling = t.getPoly(2);
	//fallingPos = new Point(0, 0);
    }

    public SquareType getSquareType(int x, int y) {
	return squares[x][y];
    }

    public Poly getPolyFalling() {return polyFalling;}

    public Point getFallingPos() {return fallingPos;}

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public static SquareType[][] fillSquare(int x, int y, Board board){
	//vi sätter index till ett tal mellan 1 och antalet squaretypes som finns
	int typeIndex = RND.nextInt(SquareType.values().length-1);
	board.squares[x][y] = SquareType.values()[typeIndex];
	return board.squares;
    }

    public SquareType getVisibleSquareAt(int x,int y){
	if(polyFalling == null){
	    return getSquareType(x,y);
	}
	//vi sätter fallingSize till polyns storlek
	int fallingSize = polyFalling.getShape().length -1;
	//om x och y ligger inom polyns area
	if((x>=fallingPos.x && x<=fallingPos.x+fallingSize) &&
	   (y>=fallingPos.y && y<=fallingPos.y+fallingSize)){
	    int internSquareX = x - fallingPos.x;
	    int internSquareY = y-fallingPos.y;
	    SquareType fallingSquare = polyFalling.getShape()[internSquareX][internSquareY];
	    //och om rutans Squaretype inte är EMPTY
	    if(!fallingSquare.equals(SquareType.EMPTY)){
		//då returneras rutans Squaretype
		return fallingSquare;
	    }
	}
	//annars returneras den Squaretype som finns på platsen enligt boardens array
	return getSquareType(x,y);
    }
}


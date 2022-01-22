package se.liu.thebo717.tetris;

import java.util.Random;

public enum SquareType
{
    EMPTY, I, O, T, S, Z, J, L;

    public static void main(String[] args) {
	Random rnd = new Random();
	//skriver ut 25 slumpmässiga squaretypes enligt deluppgift
	for (int i = 0; i <= 25; i++) {
	    int squareType = rnd.nextInt(SquareType.values().length);
	    System.out.println(SquareType.values()[squareType]);
	}
    }
}

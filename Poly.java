package se.liu.thebo717.tetris;

public class Poly
{

    private SquareType[][] shape;
    private int squareTypeIndex;

    public Poly(final SquareType[][] shape, final int squareTypeIndex) {
	//polyns form, dvs dess array med ifyllda positioner
	this.shape = shape;
	this.squareTypeIndex = squareTypeIndex;
    }

    public int getSquareTypeIndex() {
	return squareTypeIndex;
    }

    public SquareType[][] getShape() {
	return shape;
    }

}

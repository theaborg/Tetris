package se.liu.thebo717.tetris;

public class Poly
{
    private SquareType[][] shape; //eller int[][]?

    public Poly(final SquareType[][] shape) {
        //polyns form, dvs dess array med ifyllda positioner
        this.shape = shape;
    }

    public SquareType[][] getShape() {
        return shape;
    }
}

package se.liu.thebo717.tetris;

public class TetrominoMaker
{
    public int getNumberOfTypes() {
	return SquareType.values().length-1;
    }
    public Poly getPoly(int n) {
	//beroende på vilken Squaretype gör vi olika polys
	switch (SquareType.values()[n]){
	    case J:
		return makeJBlock(n);
	    case L:
		return makeLBlock(n);
	    case S:
		return makeSBlock(n);
	    case T:
		return makeTBlock(n);
	    case Z:
		return makeZBlock(n);
	    case O:
		return make0Block(n);
	    case I:
		return makeIBlock(n);
	    default:
		throw new IllegalArgumentException("Invalid index: " + n);
	}
    }

    private Poly makeJBlock(int n){
	//gör en ny Squaretype[][], vars stolek beror på hur stort utrymme typen behöver
	SquareType j = SquareType.J;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {j, e, e},
					{j, j, j},
					{e, e, e}};
	//returnerar ett nytt polyobjekt
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly makeLBlock(int n){
	SquareType l = SquareType.L;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {e, e, l},
					{l, l, l},
					{e, e, e}};
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly makeSBlock(int n){
	SquareType s = SquareType.S;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {e, s, s},
					{s, s, e},
					{e, e, e}};
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly makeTBlock(int n){
	SquareType t = SquareType.T;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {e, t, e},
					{t, t, t},
					{e, e, e}};
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly makeZBlock(int n){
	SquareType z = SquareType.Z;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {z, z, e},
					{e, z, z},
					{e, e, e} };
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly make0Block(int n){
	SquareType o = SquareType.O;
	SquareType[][] squareArray = {  {o, o},
					{o, o}};
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }

    private Poly makeIBlock(int n){
	SquareType i = SquareType.I;
	SquareType e = SquareType.EMPTY;
	SquareType[][] squareArray = {  {e, e, e, e},
					{i, i, i, i},
					{e, e, e, e},
					{e, e, e, e}};
	Poly polyObject = new Poly(squareArray, n);
	return polyObject;
    }
}

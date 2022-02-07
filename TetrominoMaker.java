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
                return makeJBlock();
            case L:
                return makeLBlock();
            case S:
                return makeSBlock();
            case T:
                return makeTBlock();
            case Z:
                return makeZBlock();
            case O:
                return make0Block();
            case I:
                return makeIBlock();
            default:
                throw new IllegalArgumentException("Invalid index: " + n);
        }
    }

    private Poly makeJBlock(){
        //gör en ny Squaretype[][], vars stolek beror på hur stort utrymme typen behöver
        SquareType j = SquareType.J;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = { {j, j, empty},
                {empty, j, empty},
                {empty, j, empty}};
        //returnerar ett nytt polyobjekt
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeLBlock(){
        SquareType l = SquareType.L;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = {{empty, l, empty},
                {empty, l, empty},
                {l, l, empty}};
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeSBlock(){
        SquareType s = SquareType.S;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = { {empty, s, empty},
                                        {s, s, empty},
                                        {s, empty, empty}};
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeTBlock(){
        SquareType t = SquareType.T;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = {{empty, t, empty},
                {t, t, empty},
                {empty, t, empty}};
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeZBlock(){
        SquareType z = SquareType.Z;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = {{z, empty, empty},
                                     {z, z, empty},
                                     {empty, z, empty} };
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly make0Block(){
        SquareType o = SquareType.O;
        SquareType[][] squareArray = {{o, o},
                {o, o}};
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeIBlock(){
        SquareType i = SquareType.I;
        SquareType empty = SquareType.EMPTY;
        SquareType[][] squareArray = { {empty, i, empty, empty},
                {empty, i, empty, empty},
                {empty, i, empty, empty},
                {empty, i, empty, empty}};
    Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

}

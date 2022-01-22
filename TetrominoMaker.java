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
        SquareType[][] squareArray = new SquareType[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //Fult, komma på bättre sätt?
                if ((row==0 && col==0)||(row==1 && col==0)||
                    (row==1 && col==1)||(row==1 && col==2)){
                    //om rutan har rätt koordinater lägger vi till J
                    squareArray[col][row] = SquareType.J;
                }
                else{
                    //annars empty
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        //returnerar ett nytt polyobjekt
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeLBlock(){
        SquareType[][] squareArray = new SquareType[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ((row==1 && col==0)||(row==1 && col==1)||
                    (row==1 && col==2)||(row==0 && col==2)){
                    squareArray[col][row] = SquareType.L;
                }
                else{
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeSBlock(){
        SquareType[][] squareArray = new SquareType[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ((row==0 && col==2)||(row==0 && col==1)||
                    (row==1 && col==1)||(row==1 && col==0)){
                    squareArray[col][row] = SquareType.S;
                }
                else{
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeTBlock(){
        SquareType[][] squareArray = new SquareType[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ((row==1 && col==0)||(row==1 && col==1)||
                    (row==1 && col==2)||(row==0 && col==1)){
                    squareArray[col][row] = SquareType.T;
                }
                else{
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeZBlock(){
        SquareType[][] squareArray = new SquareType[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if ((row==0 && col==0)||(row==0 && col==1)||
                    (row==1 && col==1)||(row==1 && col==2)){
                    squareArray[col][row] = SquareType.Z;
                }
                else{
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly make0Block(){
        SquareType[][] squareArray = new SquareType[2][2];
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                squareArray[col][row] = SquareType.O;
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

    private Poly makeIBlock(){
        SquareType[][] squareArray = new SquareType[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if ((row==1 && col==0)||(row==1 && col==1)||
                    (row==1 && col==2)||(row==1 && col==3)){
                    squareArray[col][row] = SquareType.I;
                }
                else{
                    squareArray[col][row] = SquareType.EMPTY;
                }
            }
        }
        Poly polyObject = new Poly(squareArray);
        return polyObject;
    }

}

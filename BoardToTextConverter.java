package se.liu.thebo717.tetris;


public class BoardToTextConverter
{
    public String convertToText (Board board){
	StringBuilder boardString = new StringBuilder();
	for (int row = 0; row < board.getHeight(); row++) {
	    for (int col = 0; col < board.getWidth(); col++) {
		//beroende på vilken squaretype det finns på (col, row)...
		switch (board.getVisibleSquareAt(col, row)){
		    case EMPTY:
			boardString.append("- ");
			break;
		    case I:
			boardString.append("I ");
			break;
		    case J:
			boardString.append("J ");
			break;
		    case L:
			boardString.append("L ");
			break;
		    case O:
			boardString.append("O ");
			break;
		    case S:
			boardString.append("S ");
			break;
		    case T:
			boardString.append("T ");
			break;
		    case Z:
			boardString.append("Z ");
			break;
		}
	    }
	    //bryter rad
	    boardString.append("\n");
	}
	return boardString.toString();
    }
}

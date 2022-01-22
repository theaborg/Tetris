package se.liu.thebo717.tetris;
import java.util.Random;

public class TestBoard
{
    public void loopTests() {
        //en loop med en slumpmässigt vald squaretype på en slumpad koordinat
        Random RND = new Random();
        for (int i = 0; i < 11; i++) {
            int x = RND.nextInt(1, 10);
            int y = RND.nextInt(1, 10);
            Board board = new Board(x, y);
            int cellX = RND.nextInt(x);
            int cellY = RND.nextInt(y);
            board.fillSquare(cellX, cellY, board);
            BoardToTextConverter converter = new BoardToTextConverter();
            System.out.println(converter.convertToText(board));
        }
    }

    public void makeFallingPoly() {
        //Lägg till fallingPoly i board!
        Board board = new Board(5, 8);
        BoardToTextConverter converter = new BoardToTextConverter();
        System.out.println(converter.convertToText(board));
    }

    public static void main(String[] args) {
        Random RND = new Random();
        Board board = new Board(8, 10);
        board.fillSquare(7, 9, board);
        TetrisViewer viewer = new TetrisViewer(board);
        viewer.show(board);
    }
    }

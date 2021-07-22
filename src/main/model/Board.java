package model;



import java.util.List;

public class Board {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    private int[][] board;


    // EFFECTS: creates a new board and sets its width and height
    public Board() {
        this.board = new int[HEIGHT][WIDTH];

    }

    // MODIFIES: this
    // EFFECTS: fills in board with all 0s
    public void fillBoard() {
        for (int[] i : this.board) {
            for (int num : i) {
                num = 0;
            }
        }
    }

    public void setBoard(int x, int y) {
        this.board[x][y] = 1;
    }

    public int getValue(int x, int y) {
        return board[x][y];
    }


}

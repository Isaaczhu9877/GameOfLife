package model;



import java.util.List;

// Class that represents a game grid with a given height and width
public class Board {
    public static final int HEIGHT = 50;
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

    //0 0 0
    //0 1 0
    //0 0 0

    // REQUIRES: Cell to be on the the board
    // EFFECTS: Returns the number of neighbours to this cell
    public int checkSurrounding(int y, int x) {
        int neighbours = 0;
        if (checkLeftUpNeighbour(y - 1, x - 1)) {
            neighbours++;
        }
        if (checkUpNeighbour(y - 1, x)) {
            neighbours++;
        }
        if (checkRightUpNeighbour(y - 1, x + 1)) {
            neighbours++;
        }
        if (checkLeftNeighbour(y, x - 1)) {
            neighbours++;
        }
        if (checkRightNeighbour(y, x + 1)) {
            neighbours++;
        }
        if (checkLeftDownNeighbour(y + 1, x - 1)) {
            neighbours++;
        }
        neighbours += (checkDownNeighbour(y + 1, x));
        neighbours += (checkRightDownNeighbour(y + 1, x + 1));
        return neighbours;
    }

    private boolean checkLeftUpNeighbour(int up, int left) {
        return (up >= 0 && left >= 0 && board[up][left] == 1);
    }

    private boolean checkUpNeighbour(int up, int x) {
        return (up >= 0 && board[up][x] == 1);
    }

    private boolean checkRightUpNeighbour(int up, int right) {
        return (up >= 0 && right < WIDTH && board[up][right] == 1);
    }

    private boolean checkLeftNeighbour(int y, int left) {
        return (left >= 0 && board[y][left] == 1);
    }

    private boolean checkRightNeighbour(int y, int right) {
        return (right < WIDTH && board[y][right] == 1);
    }

    private boolean checkLeftDownNeighbour(int down, int left) {
        return (down < HEIGHT && left >= 0 && board[down][left] == 1);
    }

    private int checkDownNeighbour(int down, int x) {
        if (down < HEIGHT && board[down][x] == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    private int checkRightDownNeighbour(int down, int right) {
        if (down < HEIGHT && right < WIDTH && board[down][right] == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setBoard(int x, int y, int status) {
        this.board[y][x] = status;
    }

    public int getValue(int x, int y) {
        return board[y][x];
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

}

package model;


// Class that represents a game grid with a given width and height
// 0,0 is at upper left and x increases from right to left and y from top to bottom
public class Board {
    private int height; //30
    private int width;  // 100
    private int[][] board;


    // EFFECTS: creates a new board and sets its width and height
    public Board(int x, int y) {
        this.height = y;
        this.width = x;
        this.board = new int[y][x];

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

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkLeftUpNeighbour(int up, int left) {
        return (up >= 0 && left >= 0 && board[up][left] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkUpNeighbour(int up, int x) {
        return (up >= 0 && board[up][x] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkRightUpNeighbour(int up, int right) {
        return (up >= 0 && right < width && board[up][right] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkLeftNeighbour(int y, int left) {
        return (left >= 0 && board[y][left] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkRightNeighbour(int y, int right) {
        return (right < width && board[y][right] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private boolean checkLeftDownNeighbour(int down, int left) {
        return (down < height && left >= 0 && board[down][left] == 1);
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private int checkDownNeighbour(int down, int x) {
        if (down < height && board[down][x] == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // EFFECTS: returns true if the coordinates are a valid board space and there is a living cell there, else false
    private int checkRightDownNeighbour(int down, int right) {
        if (down < height && right < width && board[down][right] == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // EFFECTS: sets given coordinates on board to given status
    public void setBoard(int x, int y, int status) {
        this.board[y][x] = status;
    }

    // EFFECTS: gets the value at given coordinate on board
    public int getValue(int x, int y) {
        return board[y][x];
    }

    // EFFECTS: returns the width of the board
    public int getWidth() {
        return width;
    }

    // EFFECTS: returns the height of the board
    public int getHeight() {
        return height;
    }

}

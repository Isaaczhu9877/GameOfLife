package model;


//Class that represents a Cell with a given x and y position and a status on if its living of read
public class Cell {
    private int status;
    private int posX;
    private int posY;


    // EFFECTS: creates cell at position x,y
    public Cell(int posY, int posX) {
        status = 1;
        this.posX = posX;
        this.posY = posY;
    }



    // MODIFIES: this
    // EFFECTS: kills the cell
    public void kill() {
        this.setStatus(0);
    }

    public int getStatus() {
        return status;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean equals(Object obj) {
        boolean result;
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Cell newCell = (Cell) obj;
        result = (this.posY == newCell.posY && this.posX == newCell.posX);
        return result;
    }

}

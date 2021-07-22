package model;

public class Cell {
    private int status;
    private int posX;
    private int posY;


    // EFFECTS: creates cell at position x,y
    public Cell(int posX, int posY) {
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
}

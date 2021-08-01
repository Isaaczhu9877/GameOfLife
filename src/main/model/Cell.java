package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.WritableClass;

//Class that represents a Cell with a given x and y position and a status stating if its living of read
public class Cell implements WritableClass {
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

    // EFFECTS: override for the equals method in the cell class that compares if two cells have the same coordinates
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("posX", posX);
        json.put("posY", posY);
        return json;
    }
}

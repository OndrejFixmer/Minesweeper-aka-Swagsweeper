package cz.educanet.minesweeper.logic;

public class Cell {

    private int type;
    private boolean JeBomba;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean JeBomba() {
        return JeBomba;
    }

    public void BombicSet(boolean bomb) {
        JeBomba = bomb;
    }
}

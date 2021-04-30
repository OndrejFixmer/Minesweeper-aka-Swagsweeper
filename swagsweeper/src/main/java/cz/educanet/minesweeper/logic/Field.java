package cz.educanet.minesweeper.logic;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private int radky;
    private int Sloupce;
    private Cell[][] playground;

    public static Field generetField(int radky, int Sloupce) {
        Field myField = new Field();
        myField.radky = radky;
        myField.Sloupce = Sloupce;
        myField.playground = new Cell[Sloupce][radky];

        for (int i = 0; i < radky; i++) {
            for (int j = 0; j < Sloupce; j++) {
                Cell myCell = new Cell();
                myCell.setType(0);
                myCell.BombicSet(false);

                myField.playground[j][i] = myCell;
            }
        }
        return myField.generateBombs(myField);
    }

    public Cell getCellPosition(int x, int y) {
        return this.playground[x][y];
    }

    public int getBombCount() {
        return 10;
    }

    public Field generateBombs(Field myField) {

        Random rand = new Random();
        int counter = 0;

        while (counter != getBombCount()) {
            int x = rand.nextInt(Sloupce);
            int y = rand.nextInt(radky);

            while (playground[x][y].JeBomba()) {
                x = rand.nextInt(Sloupce);
                y = rand.nextInt(radky);
            }
            playground[x][y].BombicSet(true);
            counter++;
        }
        return myField;
    }

    public boolean isClicked(int x, int y) {
        return playground[x][y].JeBomba();
    }

    public void setCellType(int x, int y, int type) {
        Cell a = playground[x][y];
        a.setType(type);
        playground[x][y] = a;
    }

    public int getCellType(int x, int y) {
        Cell a = playground[x][y];
        return a.getType();
    }
}

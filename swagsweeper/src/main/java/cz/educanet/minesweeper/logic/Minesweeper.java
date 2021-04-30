package cz.educanet.minesweeper.logic;

public class Minesweeper {

    private Field playground;
    private int PocetRadku;
    private int PocetSloupce;
    private boolean isClicked2;
    private int clearCells;

    public Minesweeper(int radky, int Sloupce) {
        this.PocetRadku = radky;
        this.PocetSloupce = Sloupce;

        this.playground = Field.generetField(radky, Sloupce);
        clearCells = (radky * Sloupce) - playground.getBombCount();
    }

    /**
     * 0 - Hidden
     * 1 - Visible
     * 2 - Flag
     * 3 - Question mark
     *
     * @param x X
     * @param y Y
     * @return field type
     */
    public int getField(int x, int y) {
        return playground.getCellType(x, y);
    }

    public void toggleFieldState(int x, int y) {
        if (getField(x, y) == 0) {
            playground.setCellType(x, y, 2);
        } else if (getField(x, y) == 2) {
            playground.setCellType(x, y, 3);
        } else if (getField(x, y) == 3) {
            playground.setCellType(x, y, 0);
        }
    }

    public Field odhal2
            (int x, int y, Field input) {
        Field playground = input;
        System.out.println("KOLIK ZBYVA DO VYHRY: " + clearCells);
        playground.getCellPosition(x, y).setType(1);

        if (getAdjacentBombCount(x, y) == 0) {
            boolean Tl =
                    (x != 0) && (y != 0);
                boolean Tr =
                        (x != PocetSloupce - 1) && (y != 0);
                    boolean Bl =
                            (x != 0) && (y != PocetRadku - 1);
                        boolean Br =
                                (x != PocetSloupce - 1) && (y != PocetRadku - 1);

            if (Br && !JeBombaOnPosition(x + 1, y + 1)
                    && getField(x + 1, y + 1) == 0) {
                playground.getCellPosition(x + 1, y + 1).setType(1);
                odhal2(x + 1, y + 1, input);
                clearCells--;
            }
            if ((Tr || Br) && !JeBombaOnPosition(x + 1, y)
                    && getField(x + 1, y) == 0) {
                playground.getCellPosition(x + 1, y).setType(1);
                odhal2(x + 1, y, input);
                clearCells--;
            }
            if (Tr && !JeBombaOnPosition(x + 1, y - 1)
                    && getField(x + 1, y - 1) == 0) {
                playground.getCellPosition(x + 1, y - 1).setType(1);
                odhal2(x + 1, y - 1, input);
                clearCells--;

            }
            if ((Tr || Tl) && !JeBombaOnPosition(x, y - 1)
                    && getField(x, y - 1) == 0) {
                playground.getCellPosition(x, y - 1).setType(1);
                odhal2(x, y - 1, input);
                clearCells--;

            }
            if (Tl && !JeBombaOnPosition(x - 1, y - 1)
                    && getField(x - 1, y - 1) == 0) {
                playground.getCellPosition(x - 1, y - 1).setType(1);
                odhal2(x - 1, y - 1, input);
                clearCells--;

            }
            if ((Bl || Tl) && !JeBombaOnPosition(x - 1, y)
                    && getField(x - 1, y) == 0) {
                playground.getCellPosition(x - 1, y).setType(1);
                odhal2(x - 1, y, input);
                clearCells--;

            }
            if (Bl && !JeBombaOnPosition(x - 1, y + 1)
                    && getField(x - 1, y + 1) == 0) {
                playground.getCellPosition(x - 1, y + 1).setType(1);
                odhal2(x - 1, y + 1, input);
                clearCells--;

            }
            if ((Br || Bl) && !JeBombaOnPosition(x, y + 1)
                    && getField(x, y + 1) == 0) {
                playground.getCellPosition(x, y + 1).setType(1);
                odhal2(x, y + 1, input);
                clearCells--;

            }
        }
        return playground;
    }

    public void odhal(int x, int y) {
        if (playground.isClicked(x, y)) {
            isClicked2 = true;
        } else {
            playground = odhal2(x, y, playground);
            clearCells--;
        }
    }

    public int getAdjacentBombCount(int x, int y) {
        int bombs = 0;

        boolean Tl =
                (x != 0) && (y != 0);
            boolean Tr =
                    (x != PocetSloupce - 1) && (y != 0);
                boolean Bl =
                        (x != 0) && (y != PocetRadku - 1);
                        boolean Br =
                                (x != PocetSloupce - 1) && (y != PocetRadku - 1);

        if (Tr && JeBombaOnPosition
                (x + 1, y - 1)) {
            bombs++;
        }
            if ((Tr || Br) && JeBombaOnPosition
                    (x + 1, y)) {
            bombs++;
        }
                if (Br && JeBombaOnPosition
                        (x + 1, y + 1)) {
            bombs++;
        }
                    if ((Tr || Tl) && JeBombaOnPosition
                            (x, y - 1)) {
            bombs++;
        }
                        if ((Br || Bl) && JeBombaOnPosition
                                (x, y + 1)) {
            bombs++;
        }
                            if (Tl && JeBombaOnPosition
                                    (x - 1, y - 1)) {
            bombs++;
        }
                                if ((Bl || Tl) && JeBombaOnPosition
                                        (x - 1, y)) {
            bombs++;
        }
                                    if (Bl && JeBombaOnPosition
                                            (x - 1, y + 1)) {
            bombs++;
        }
        return bombs;
    }


    public boolean JeBombaOnPosition(int x, int y) {
        return playground.getCellPosition(x, y).JeBomba();
    }

    public boolean Vyhral() {
        return clearCells == 0;
    }

    public boolean Prohral() {
        return isClicked2;
    }

    public int getradky() {
        return PocetRadku;
    }

    public int getSloupce() {
        return PocetSloupce;
    }

}

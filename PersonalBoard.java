package game;

import gameoflife.GameOfLifeBoard;

public class PersonalBoard extends GameOfLifeBoard {
    
    public PersonalBoard(int w, int h) {
        super(w, h);
    }
    
    public boolean isInBoard(int x, int y) {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void turnToLiving(int x, int y) {
        if (isInBoard(x, y)) {
            boolean[][] board = getBoard();
            board[x][y] = true;
        }
    }
    
    @Override
    public void turnToDead(int x, int y) {
        if (isInBoard(x, y)) {
            boolean[][] board = getBoard();
            board[x][y] = false;
        }
    }
    
    @Override
    public boolean isAlive(int x, int y) {
        if (isInBoard(x, y)) {
            boolean[][] board = getBoard();
            return board[x][y];
        }
        else {
            return false;
        }
    }
    
    @Override
    public void initiateRandomCells(double probabilityForEachCell) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                double rand = Math.random();
                if (rand < probabilityForEachCell) {
                    getBoard() [x][y] = true;
                }
                else {
                    getBoard() [x][y] = false;
                }
            }
        }
    }
    
    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int total = 0;
        boolean[][] board = getBoard();
        
        for (int h = y - 1; h < y + 2; h++) {
            for (int w = x - 1; w < x + 2; w++) {
                if (isInBoard(w, h)) {
                    if (h != y || w != x) {
                        if (board[w][h] == true) {
                            total++;
                        }
                    }
                }
            }
        }
        
        return total;
    }
    
    @Override
    public void manageCell(int x, int y, int livingNeighbours) {
        boolean cell = isAlive(x, y);
        
        if (cell == true) {
            if (livingNeighbours < 2 || livingNeighbours > 3) {
                turnToDead(x, y);
            }
        }
        else if (cell == false) {
            if (livingNeighbours == 3) {
                turnToLiving(x, y);
            }
        }
    }
    
}

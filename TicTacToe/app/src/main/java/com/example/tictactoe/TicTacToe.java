package com.example.tictactoe;

public class TicTacToe {
    public static final int SIZE = 10;
    public int [][] game;
    public int turn;
    public TicTacToe(){
        game = new int[SIZE][SIZE];
    }
    public void resetGame(){
        for(int row=0; row<SIZE; row++){
            for(int col=0; col<SIZE; col++){
                game[row][col] = 0;
            }
        }
        turn = 1;
    }
    public int play(int row, int col){
        int currentTurn = turn;
        if(row>=0 && col>=0 && row<SIZE && col<SIZE && game[row][col]==0){
            game[row][col] = turn;
            if(turn == 1){
                turn = 2;
            }
            else {
                turn = 1;
            } return currentTurn;
        }
        else
            return 0;
    }
    public boolean canNotPlay (){
        boolean result = true;
        for (int row=0; row<SIZE; row++)
            for (int col=0; col<SIZE; col++)
                if (game[row][col]==0)
                    result = false;
        return result;
    }
    public int whoWon(){
        int rows = checkRows();
        int columns = checkColumns();
        int diagonals = checkDiagonals();
        if (rows > 0)
            return rows;
        if (columns > 0)
            return columns;
        if (diagonals > 0)
            return diagonals;
        return 0;
    }
    public boolean isGameOver(){
        return canNotPlay() || (whoWon() > 0);
    }
    private int checkRows(){
        for (int row=0; row<SIZE; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                if (game[row][col] != 0 && game[row][col] == game[row][col + 1]
                        && game[row][col + 1] == game[row][col + 2]
                        && game[row][col + 2] == game[row][col + 3]
                        && game[row][col + 3] == game[row][col + 4]) {
                    return game[row][col];
                }
            }
        }
        return 0;
    }
    private int checkColumns(){
        for (int col=0; col<SIZE; col++) {
            for (int row = 0; row <= SIZE-5; row++) {
                if (game[row][col] != 0 && game[row][col] == game[row + 1][col]
                        && game[row + 1][col] == game[row + 2][col]
                        && game[row + 2][col] == game[row + 3][col]
                        && game[row + 3][col] == game[row + 4][col]) {
                    return game[row][col];
                }
            }
        }
        return 0;
    }

    private int checkDiagonals(){
        for (int row = 0; row <= SIZE - 5; row++) {
            for (int col = 0; col <= SIZE - 5; col++) {
                int currentCell = game[row][col];
                if (currentCell != 0 &&
                        currentCell == game[row + 1][col + 1] &&
                        currentCell == game[row + 2][col + 2] &&
                        currentCell == game[row + 3][col + 3] &&
                        currentCell == game[row + 4][col + 4]) {
                    if ((row == 0 || game[row - 1][col - 1] != currentCell) &&
                            (row == SIZE - 5 || game[row + 5][col + 5] != currentCell)) {
                        return currentCell;
                    }
                }

                int oppositeCell = game[row][col + 4];
                if (oppositeCell != 0 &&
                        oppositeCell == game[row + 1][col + 3] &&
                        oppositeCell == game[row + 2][col + 2] &&
                        oppositeCell == game[row + 3][col + 1] &&
                        oppositeCell == game[row + 4][col]) {
                    if ((row == 0 || col == SIZE - 5 || game[row - 1][col + 5] != oppositeCell) &&
                            (row == SIZE - 5 || col == 0 || game[row + 5][col - 1] != oppositeCell)) {
                        return oppositeCell;
                    }
                }
            }
        }
        return 0;
    }

    public String result(){
        if (whoWon()>0)
            return "Player " + whoWon() + " won!";
        else if (canNotPlay())
            return "DRAW!!!";
        else
            return "PLAY!!!";

    }
}

package com.example.jim.tictactoe;

/**
 * Created by Jim on 2014/11/3.
 */
public class Board {
    public static final int PLAYER_X = -2;
    public static final int PLAYER_O = -1;
    public static final int EMPTY = 0;
    public static final int PLAYER_O_WIN = 1;
    public static final int PLAYER_X_WIN = 2;
    public static final int DRAW = 3;

    public int currentPlayer = PLAYER_O;
    public int dimension = 3;
    public int[] intBoard = new int[dimension * dimension];

    public Board (int dimension) {
        this.dimension = dimension;
        for (int index = 0;index < dimension * dimension; index++) {
                   intBoard[index] = EMPTY;
        }
    }

    public void setStartPlayer(int startPlayer){
        this.currentPlayer = startPlayer;
    }

    // TODO make the intBoard into a changable way; change into OOP constroctor
    //   public static final int DIMENSION = 3;
    //   public static void setIntBoard (int dimension) {
    //       for (int index = 0; index < dimension * dimension; index++) {
    //           intBoard[index] = EMPTY;
    //       }
    //   }
    //   public static int[] intBoard;




    public int getCurrentPlayer() { // TODO maybe delete this useless function
        return currentPlayer;
    }

    public void switchPlayer (int formerPlayer) {
        if (formerPlayer == PLAYER_O) {
            currentPlayer = PLAYER_X;
        }
        if (formerPlayer == PLAYER_X) {
            currentPlayer = PLAYER_O;
        }
    }

    public boolean checkGameFinish(int[] intBoard) {
        // check row for winner
        if (this.checkRowForWinner()){
            return true;
        }
        // check col for winner
        if (this.checkColForWinner()){
            return true;
        }
        // check diagonal for winner
        if (this.checkDiagonalForWinner()){
            return true;
        }
        // check full for draw
        if (this.checkForDraw()) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonalForWinner() {
        // go through two diagonals line by start from different index and add different numbers
        for (int startIndex = 0; startIndex < this.dimension; startIndex += this.dimension - 1) {
            int difference;
            if (startIndex == 0) {
                difference = this.dimension + 1;
            }
            else {
                difference = this.dimension - 1;
            }
            int count = 0;
            // went through every position on the diagonal, EXCEPT the last point
            for (int index = startIndex; index < (this.dimension - 1) * this.dimension - 1; index += difference) {
                if (this.intBoard[index] == EMPTY ||
                        this.intBoard[index] != this.intBoard[index + difference]) {
                    break;
                }
                count ++;
            }
            if (count == this.dimension - 1) {
                // TODO CHANGE THE BOARD
                for (int index = startIndex; index < (this.dimension - 1) * this.dimension - 1;index += difference) {
                    intBoard[index] = - intBoard[index]; // change the status from x or o to x_win or o_win
                }
                // TODO CHANGE THE BOARD
                return true;
            }
        }
        return false;
    }

    public boolean checkColForWinner() {
        for (int remainder = 0; remainder < this.dimension; remainder ++) { // goes through each col
            int count = 0;
            for (int quotient = 0; quotient < this.dimension - 1; quotient ++ ) { // goes through each row in a specific col, except the last row
                int index = remainder + quotient * this.dimension;
                if (this.intBoard[index] == EMPTY ||
                        this.intBoard[index] != this.intBoard[index + this.dimension] ) {
                    break; // this column cannot be taken by the same player, look for whether finished in another column
                }
                count ++;
            }
            if (count == this.dimension - 1){
                return true;
            }
        }
        return false;
    }

    public boolean checkRowForWinner (){
        for (int quotient = 0; quotient < this.dimension; quotient ++) { // goes through each col
            int count = 0;
            for (int remainder = 0; remainder < this.dimension - 1; remainder ++ ) { // goes through each row in a specific col, except the last row
                int index = remainder + quotient * this.dimension;
                if (this.intBoard[index] == EMPTY ||
                        this.intBoard[index] != this.intBoard[index + 1] ) {
                    break; // this column cannot be taken by the same player, look for whether finished in another column
                }
                count ++;
            }
            if (count == this.dimension - 1){
                return true;
            }
        }
        return false;
    }

    public boolean checkForDraw (){
        for (int index = 0; index < this.dimension * this.dimension; index++) {
            if (this.intBoard[index] == EMPTY) {
                return false;
            }
        }
        return true;
    }
    public void updateIntBoard(int position){
        // System.out.println(intBoard[position] + "update int board");
        this.intBoard[position] = getCurrentPlayer();
    }

    public int  updateImageBoardBeforeGameFinished () {
        if (this.getCurrentPlayer() == PLAYER_O) {
            return R.drawable.player_o;
        }
        else {
            return R.drawable.player_x;
        }
    }

    public int updateImageBoardAfterGameFinished (int index) {
        if (this.intBoard[index] == PLAYER_O) {
            return R.drawable.player_o;
        }
        else if (this.intBoard[index] == PLAYER_X) {
            return R.drawable.player_x;
        }
        else if (intBoard[index] == PLAYER_X_WIN) {
            return R.drawable.player_x_win;
        }
        else if (intBoard[index] == PLAYER_O_WIN) {
            return R.drawable.player_o_win;
        }
        else {
            return R.drawable.empty;
        }
    }

    public int updateBoard(int position) {
        int newImage;
        if (!this.checkGameFinish(intBoard) && this.intBoard[position] == EMPTY) {
            this.updateIntBoard(position);
            newImage = this.updateImageBoardBeforeGameFinished();
            this.switchPlayer(this.getCurrentPlayer());
        }
        else {
            newImage = this.updateImageBoardAfterGameFinished(position);
        }
        return newImage;
    }

}

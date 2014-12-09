package com.example.jim.tictactoe;

/**
 * Created by Jim on 2014/11/1.
 */
public class Logic {

    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 0;
    public static final int EMPTY = -1;
    public static final int PLAYER_X_WIN = 2;
    public static final int PLAYER_O_WIN = 3;
    public static final int DRAW = 4;

    private static final String[] GAME_STATUS = {"Player O", "Player X", "Player O win!", "Player X win!", "Draw"};

    private static int currentPlayer = PLAYER_O;
    private static final int DIMENSION = 3;
    private static int[] intBoard = {EMPTY,EMPTY,EMPTY,
                                      EMPTY,EMPTY,EMPTY,
                                      EMPTY,EMPTY,EMPTY,};

 // TODO make the intBoard into a changable way; change into OOP constroctor
 //   public static final int DIMENSION = 3;
 //   public static void setIntBoard (int dimension) {
 //       for (int index = 0; index < dimension * dimension; index++) {
 //           intBoard[index] = EMPTY;
 //       }
 //   }
 //   public static int[] intBoard;

    /**
     *  reset all the elements in the intBoard to EMPTY
     *  reset the currentPlayer to the startPlayer
     */
    public static void resetIntBoard () {
        for (int index = 0; index < DIMENSION * DIMENSION; index ++) {
            intBoard[index] = EMPTY;
        }
        currentPlayer = PLAYER_O;
    }

    /**
     * return the currentPlayer
     * @return an integer represent the current player
     */
    protected static int getCurrentPlayer() { // TODO maybe delete this useless function
        return currentPlayer;
    }

    protected static int getDimension() {
        return DIMENSION;
    }

    protected static int[] getIntBoard() {
        int[] intBoardClone = new int[Logic.intBoard.length];
        for (int index = 0; index < Logic.intBoard.length; index ++) {
            intBoardClone[index] = Logic.intBoard[index];
        }
        return intBoardClone;
    }

    /**
     * return the current game status
     * if the game is finished, return the winner or draw
     * if the game is not finished, return the current player
     * @return  a string representation of the game status
     */
    public static String getGameStatus() {
        if (checkForDraw(intBoard)) { // check if the game finished in draw
            return GAME_STATUS[DRAW];
        }
        if (checkGameFinish(intBoard)) { // check if the game finished with a winner
            if (currentPlayer == PLAYER_O) {
                return GAME_STATUS[PLAYER_O_WIN];
            }
            if (currentPlayer == PLAYER_X) {
                return GAME_STATUS[PLAYER_X_WIN];
            }
        }
        // check if the game is still in progress
        return GAME_STATUS[currentPlayer];
    }

    /**
     * switch the current player
     * @param formerPlayer
     */
    private static void switchPlayer (int formerPlayer) {
        if (formerPlayer == PLAYER_O) {
            currentPlayer = PLAYER_X;
        }
        if (formerPlayer == PLAYER_X) {
            currentPlayer = PLAYER_O;
        }
    }

    /**
     * check whether the game finishes
     * @param intBoard a array of integers that indicate the status at each position
     * @return boolean: if game is finished, return true; else return false
     */
    protected static boolean checkGameFinish(int[] intBoard) {
        // check row for winner
        if (checkRowForWinner(intBoard)){
            return true;
        }
        // check col for winner
        if (checkColForWinner(intBoard)){
            return true;
        }
        // check diagonal for winner
        if (checkDiagonalForWinner(intBoard)){
            return true;
        }
        // check full for draw
        if (checkForDraw(intBoard)) {
            return true;
        }
        return false;
    }

    /**
     * check whether one of the diagonals are filled with same players
     * @param intBoard a array of integers that indicate the status at each position
     * @return boolean: if game is finished, return true; else return false
     */
    public static boolean checkDiagonalForWinner(int [] intBoard) {
        // go through two diagonals line by start from different index and add different numbers
        for (int startIndex = 0; startIndex < DIMENSION; startIndex += DIMENSION - 1) {
            int difference;
            if (startIndex == 0) {
                difference = DIMENSION + 1;
            }
            else {
                difference = DIMENSION - 1;
            }
            int count = 0;
            // went through every position on the diagonal, EXCEPT the last point
            for (int index = startIndex; index < (DIMENSION - 1) * DIMENSION - 1; index += difference) {
                 if (intBoard[index] == EMPTY ||
                     intBoard[index] != intBoard[index + difference]) {
                    break;
                }
                count ++;
            }
            if (count == DIMENSION - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * check whether one of the columns are filled with same players
     * @param intBoard a array of integers that indicate the status at each position
     * @return boolean: if game is finished, return true; else return false
     */
    public static boolean checkColForWinner(int[] intBoard) {
        for (int remainder = 0; remainder < DIMENSION; remainder ++) { // goes through each col
            int count = 0;
            for (int quotient = 0; quotient < DIMENSION - 1; quotient ++ ) { // goes through each row in a specific col, except the last row
                int index = remainder + quotient * DIMENSION;
                if (intBoard[index] == EMPTY ||
                    intBoard[index] != intBoard[index + DIMENSION] ) {
                    break; // this column cannot be taken by the same player, look for whether finished in another column
                }
                count ++;
            }
            if (count == DIMENSION - 1){
                return true;
            }
        }
        return false;
    }

    /**
     * check whether one of the rows are filled with same players
     * @param intBoard a array of integers that indicate the status at each position
     * @return boolean: if game is finished, return true; else return false
     */
    public static boolean checkRowForWinner (int[] intBoard){
        for (int quotient = 0; quotient < DIMENSION; quotient ++) { // goes through each col
            int count = 0;
            for (int remainder = 0; remainder < DIMENSION - 1; remainder ++ ) { // goes through each row in a specific col, except the last row
                int index = remainder + quotient * DIMENSION;
                if (intBoard[index] == EMPTY ||
                    intBoard[index] != intBoard[index + 1] ) {
                    break; // this column cannot be taken by the same player, look for whether finished in another column
                }
                count ++;
            }
            if (count == DIMENSION - 1){
                return true;
            }
        }
        return false;
    }

    /**
     * check whether all the positions in the grid are full
     * @param intBoard a array of integers that indicate the status at each position
     * @return boolean: if game is finished, return true; else return false
     */
    public static boolean checkForDraw (int[] intBoard){
        // check if the grid is full
        for (int index = 0; index < DIMENSION * DIMENSION; index++) {
            if (intBoard[index] == EMPTY) {
                return false;
            }
        }
        // check if there is a winner
        if (checkColForWinner(intBoard) ||
            checkRowForWinner(intBoard) ||
            checkDiagonalForWinner(intBoard)) {
            return false;
        }
        return true;
    }

    /**
     * update the intBoard by changing the value at given position
     * @param position the index of the item being changed
     */
    private static void updateIntBoard(int position){
       // System.out.println(intBoard[position] + "update int board");
        intBoard[position] = getCurrentPlayer();
    }

    /**
     * return a new image for the given position before the game finished
     * @return an int represents the image
     */
    private static int  updateImageBoardBeforeGameFinished () {
        if (getCurrentPlayer() == PLAYER_O) {
            return R.drawable.player_o;
        }
        else {
            return R.drawable.player_x;
        }
    }

    /**
     * return the same image for the given position after the game finished
     * @param index : the index of the picture in the array that is clicked
     * @return an int represents the image
     */
    private static int updateImageBoardAfterGameFinished (int index) {
        if (intBoard[index] == PLAYER_O) {
            return R.drawable.player_o;
        }
        else if (intBoard[index] == PLAYER_X) {
            return R.drawable.player_x;
        }
        else {
            return R.drawable.empty;
        }
    }


    /**
     * check whether the position is already filled
     * if not, update both the image and the int board at the given position
     * switch the player
     * if the position is filled, do nothing
     * @param position the position (index) of the picture at which the click happened
     * @return and integer of the image that will be draw at the position
     */
    public static int updateBoard(int position) {
        int newImage;
        if (!checkGameFinish(intBoard) && intBoard[position] == EMPTY) {
            // human
            updateIntBoard(position);
            newImage = updateImageBoardBeforeGameFinished();
            switchPlayer(getCurrentPlayer());
            // machine
            if (!checkGameFinish(intBoard)) {
                // int machinePlayerPosition = MachinePlayer.machinePlayerMove(getIntBoard(), getCurrentPlayer()); // Monte Carlo
                int machinePlayerPosition = MiniMaxMachinePlayer.miniMaxMove(getIntBoard(), getCurrentPlayer())[1]; // Mini Max
                updateIntBoard(machinePlayerPosition);
                switchPlayer(getCurrentPlayer());
            }
        }
        else {
            newImage = updateImageBoardAfterGameFinished(position);
        }
        return newImage;
    }


}

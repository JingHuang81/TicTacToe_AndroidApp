package com.example.jim.tictactoe;
import java.util.*;

/**
 * Monte Carlo Tic-Tac-Toe Player
 * (not grantee to make the best move when the player do not make the best move)
 * Created on 2014/11/8.
 */
public class MachinePlayer {
    private static final int NUM_TRAIL = 50;
    private static final int SCORE_MACHINE_PLAYER = 1;
    private static final int SCORE_OTHER_PLAYER = 1;
    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 0;
    private static final int EMPTY = -1;


    /**
     * switch the current player
     * @param formerPlayer
     */
    private static int switchPlayer (int formerPlayer) {
        if (formerPlayer == PLAYER_O) {
            return PLAYER_X;
        }
        else {
            return PLAYER_O;
        }
    }

    private static int getAnRandomEmptyPosition(int[] board) {
        List<Integer> emptyPositions = new ArrayList<Integer>();
        for (int index = 0; index < board.length; index++ ) {
            if (board[index] == EMPTY) {
                emptyPositions.add(index);
            }
        }
        /* generate a RANDOM NUMBER
         * A wrong way to do it:
         * int randomIndex = (int) (Math.random() * (emptyPositions.size() - 1));
         */
        int randomIndex =( new Random().nextInt(emptyPositions.size()));
        return emptyPositions.get(randomIndex);
    }

    /**
     * This function takes a current board and the next player to move.
     * The function should play a game starting with the given player by making random moves, alternating between players.
     * The function should return when the game is over.
     * @param board
     * @param currentPlayer
     */
    public static int machineTrail(int[] board, int currentPlayer) {
        while (!Logic.checkGameFinish(board)) {
            //for (int i = 0; i < 9; i++) {
            //    System.out.println("intBoard " + board[i]);
            //}
            //System.out.println(currentPlayer + "current player");
            int position = getAnRandomEmptyPosition(board);
            System.out.println("random choice of position " + position);
            board[position] = currentPlayer;
            currentPlayer = switchPlayer(currentPlayer);
        }
        return switchPlayer(currentPlayer);
    }


    public static void machineUpdateScores(int[] scoreBoard, int[] intBoardClone, int winner) {
        for (int index = 0; index < scoreBoard.length; index ++) {
            if (Logic.checkForDraw(intBoardClone)) {
                break;
            }
            else {
                // if (Logic.checkGameFinish(intBoardClone)){
                    if ( winner == intBoardClone[index]) {
                        scoreBoard[index] = scoreBoard[index] + SCORE_MACHINE_PLAYER;
                    }
                    else if (EMPTY == intBoardClone[index]) {
                        scoreBoard[index] = scoreBoard[index];
                    }
                    else {
                        scoreBoard[index] = scoreBoard[index] - SCORE_OTHER_PLAYER;
                    }
                //}
            }
        }
        // TODO PRINT OUT STATEMENT FOR DEBUG. DELETE WHEN FINISHED
        System.out.print("intBoard: ");
        for (int i = 0; i < intBoardClone.length;i++){
            System.out.print(intBoardClone[i] + " ");
        }
        System.out.println(" ");
        System.out.print("scoreboard: ");
        for (int i = 0; i < scoreBoard.length;i++){
            System.out.print(scoreBoard[i] + " ");
        }
        System.out.println(" ");
   }

    public static int getBestMove (int[] intBoardClone, int[] scoreBoard ) {
        int maximum = - NUM_TRAIL; // any number that is negative and small enough
        List<Integer> result = new ArrayList<Integer>();
        for (int index = 0; index < scoreBoard.length; index ++) {
            if (intBoardClone[index] == EMPTY && scoreBoard[index] > maximum) {
                result.clear();
                result.add(index);
                maximum = scoreBoard[index];
            }
            else if (intBoardClone[index] == EMPTY && scoreBoard[index] == maximum) {
                result.add(index);
                maximum = scoreBoard[index];
            }
        }
        // TODO: DEBUG TEST
          System.out.print("start: ");
          for (int i = 0; i < scoreBoard.length; i++) {
              System.out.print(scoreBoard[i] + ", ");
          }
          System.out.println(result + " ");
//        // TODO: probably need to handle the case when result is an empty set
        if (result.size() == 0) {
            return -1; // TODO magic number: represent the board is full and nothing to choose
        }
        else {
            int randomIndex = (new Random().nextInt(result.size()));
            return result.get(randomIndex);
        }
    }

    public static int getMachinePlayerMove (int[] intBoard, int player, int trails) {
        int[] scoreBoard = new int[intBoard.length];
        for (int index = 0; index < intBoard.length; index++ ){
            scoreBoard[index] = 0;
        }
        for (int trail = 0; trail < trails; trail++) {
            // make a clone of intBoard
            int[] intBoardClone = new int[intBoard.length];
            for (int index = 0; index < intBoard.length; index++) {
                intBoardClone[index] = intBoard[index];
            }
            int winner = machineTrail(intBoardClone, player);
            machineUpdateScores(scoreBoard,intBoardClone,winner);
        }
        return getBestMove(intBoard,scoreBoard);
    }

    public static int machinePlayerMove (int[] intBoardClone,int player){
        int move =  MiniMaxMachinePlayer.miniMaxMove(intBoardClone,player)[1];
        return move;
        // return getMachinePlayerMove(intBoardClone,player,NUM_TRAIL);
    }
}

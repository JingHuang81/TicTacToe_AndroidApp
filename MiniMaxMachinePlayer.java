package com.example.jim.tictactoe;
import java.util.*;


/**
 * Created on 2014/12/5.
 */
public class MiniMaxMachinePlayer {

    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 0;
    private static final int EMPTY = -1;
    private static final Map<Integer, Integer> SCORES = new HashMap<Integer, Integer>();
    static {
        SCORES.put(PLAYER_X, 1);
        SCORES.put(PLAYER_O, -1);
        SCORES.put(Logic.DRAW, 0);
    }

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

    private static List<Integer> getEmptyPositions(int[] board) {
        List<Integer> emptyPositions = new ArrayList<Integer>();
        for (int index = 0; index < board.length; index++) {
            if (board[index] == EMPTY) {
                emptyPositions.add(index);
            }
        }
        return emptyPositions;
    }

    public static int[] miniMaxMove(int[] intBoard, int player){
        int[] result = new int[2];
        if (Logic.checkGameFinish(intBoard)){
            result[0] = SCORES.get(player); // TODO FIND THE CURRENT PLAYER?
            result[1] = -1;
            // System.out.println("base case");
            return result;
        }
        else {
            List<Integer> emptyPositions = getEmptyPositions(intBoard);
            int shiftPlayer;
            shiftPlayer = switchPlayer(player);
            int compareScore = SCORES.get(shiftPlayer);
            result[0] = compareScore;
            result[1] = -1;
            for (Integer position : emptyPositions) {
                // make a clone of the intBoard
                int[] intBoardClone = new int[intBoard.length];
                for (int index = 0; index < intBoard.length; index++) {
                    intBoardClone[index] = intBoard[index];
                }

                intBoardClone[position] = player; // Equivalent to move
                if (Logic.checkGameFinish(intBoardClone)){
                    if (Logic.checkForDraw(intBoardClone)){
                        result[0] = SCORES.get(Logic.DRAW);
                    }
                    else {
                        result[0] = SCORES.get(player);
                        result[1] = position;
                    }
                    // System.out.println("case1");
                    return result;
                }
                else{
                    int[] scoreAndMove = new int[2];
                    scoreAndMove = miniMaxMove(intBoardClone, shiftPlayer);
                    int score = scoreAndMove[0];
                    if (player == PLAYER_O && score < compareScore){
                        compareScore = score;
                        result[0] = compareScore;
                        result[1] = position;
                        if (score == SCORES.get(player)){
                            // System.out.println("case2");
                            return result;
                        }
                    }
                    if (player == PLAYER_X && score > compareScore){
                        compareScore = score;
                        result[0] = compareScore;
                        result[1] = position;
                        if (score == SCORES.get(player)){
                            // System.out.println("case3");
                            return result;
                        }
                    }
                }
            }
            // System.out.println("outcome generates from here");
            return result;
        }
    }
}

package com.osmig.game_board;

import com.osmig.key_listener.ArrowKeyListener;

public class GameBoard {

    public static String sp = "                                                                    ";
    public static String[] regular_board = {
            sp +"@","    ","@","    ","@","    ","@","    ","@","    ","@","    ","@","    ","@","    ","@","    ","$", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"@","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n",
            sp +"#","    ","@","    ","@","    ","@" ,"    ","@","    ","@","    ","@","    ","@","    ","@","    ","@", "\n"
    };

    // # for starter
    // % for challenger

    public static int startingPosition = 180;
    public static int targetPosition = 18;

    public static void main(String[] args) {
        System.out.println(regular_board.length);
//        regular_board[180] = sp +"#";
//        regular_board[160] = sp + "#";
//        regular_board[140] = sp+ "#";
//        regular_board[141] = "--> ";
//        regular_board[142] = "#";
//        regular_board[122] = "#";
//        regular_board[121] = " <--";
//        regular_board[120] = sp+ "#";
        //regular_board[18] = "#";

        printBoard();

        ArrowKeyListener.checkPlayerMove(regular_board);
    }

    public static void printBoard(){
        for (String s : regular_board){
            // listen to user movement, a = left, d = right, w = up, s = down
            System.out.print(s);
        }
    }

}

package com.osmig.key_listener;

import com.osmig.game_board.GameBoard;

import java.util.Scanner;

public class ArrowKeyListener {

    public static String getMove(String  q){
        Scanner scanner = new Scanner(System.in);
        System.out.print(q);
        return scanner.nextLine();
    }

    public static void checkPlayerMove(String[] board){
        int currentPosition = 180;
        System.out.println("\n" + GameBoard.sp +"Write 'a', 'w', 's', or 'd' after >> to move # & 'q' to break\n");

        boolean run = true;
        do {


            String move = getMove(GameBoard.sp + ">> ");
            switch (move) {
                case "a", "A" -> {
                    System.out.println(GameBoard.sp + "Move left");
                    currentPosition -= 2;
                    System.out.println(GameBoard.sp + "current position is now: " +currentPosition);
                    if (currentPosition < 180 || currentPosition < 160 || currentPosition < 140 || currentPosition < 120
                       || currentPosition < 100 || currentPosition < 80 || currentPosition < 60 || currentPosition < 40
                       || currentPosition < 20 || currentPosition < 0){
                        System.out.println(GameBoard.sp + "cant move beyond left edge");
                    }
                }

                case "w", "W" -> {
                    System.out.println(GameBoard.sp + "Move up");
                    currentPosition -= 20;
                    System.out.println(GameBoard.sp + "current position is now: " +currentPosition);
                    if (currentPosition < 0){
                        System.out.println(GameBoard.sp + "Cant move beyond upper edge");
                    }

                }

                case "s", "S" -> {
                    System.out.println(GameBoard.sp + "Move down");
                    currentPosition += 20;
                    System.out.println(GameBoard.sp + "current position is now: " +currentPosition);
                    if (currentPosition > 180 || currentPosition > 182 || currentPosition > 184 || currentPosition > 186
                        || currentPosition > 188|| currentPosition > 190|| currentPosition > 192 || currentPosition > 194
                        || currentPosition > 196 || currentPosition > 198){
                        System.out.println(GameBoard.sp + "cant move beyond bottom edge");
                    }

                }

                case "d", "D" -> {
                    System.out.println(GameBoard.sp + "Move right");
                    currentPosition += 2;
                    System.out.println(GameBoard.sp + "current position is now: " +currentPosition);
                    if (currentPosition > 198 || currentPosition > 178 || currentPosition > 158 || currentPosition > 138
                       || currentPosition > 118 || currentPosition > 98 || currentPosition > 78 || currentPosition > 58
                       || currentPosition > 38 || currentPosition > 18){
                        System.out.println(GameBoard.sp + "Cant move beyond upper edge");
                    }
                }

                case "q", "Q" -> run = false;

                default -> System.out.println(GameBoard.sp + "* not available move *");


            }

        } while (run);

    }


}

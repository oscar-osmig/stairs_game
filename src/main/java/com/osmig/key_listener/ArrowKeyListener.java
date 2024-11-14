package com.osmig.key_listener;

import com.osmig.clear_screen.ClearConsole;
import com.osmig.game_board.GameBoard;
import com.osmig.save_play.WritePlayToFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ArrowKeyListener {

    public static String getMove(String  q){
        Scanner scanner = new Scanner(System.in);
        System.out.print(q);
        return scanner.nextLine();
    }

    public static boolean askToPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GameBoard.sp + "Do you want to play again? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equalsIgnoreCase("y");
    }

    private static String player1Name;
    private static String player2Name;
    public static void getPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n" + GameBoard.sp + "Enter Player 1's name: ");
        player1Name = scanner.nextLine();
        System.out.print("\n" + GameBoard.sp +  "Enter Player 2's name: ");
        player2Name = scanner.nextLine();
    }

    public static boolean askToReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(GameBoard.sp + "Do you want to replay the game? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equalsIgnoreCase("y");
    }

    public static void checkPlayerMove(String[][] board) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;

        while (true) {
            ClearConsole.clear();
            WritePlayToFile.clearReplayFile();
            getPlayerNames();
            System.out.print("\n" + GameBoard.sp + "Enter W (up), A (left), S (down), D (right) to move:\n");
            GameBoard.resetGameBoard();
            GameBoard.printBoard();
            player1Turn = true; // Reset to Player 1's turn at the start of a new game

            while (true) {
                System.out.println(player1Turn ? "\n" + GameBoard.sp + player1Name + "'s turn" : "\n" + GameBoard.sp + player2Name + "'s turn");
                System.out.print("\n" + GameBoard.sp + ">> ");
                String input = scanner.nextLine().toUpperCase();

                // Variable to track if the move was successful
                boolean moveSuccessful = false;

                // Process the input and make moves
                switch (input) {
                    case "W": // Up
                        moveSuccessful = GameBoard.movePlayer(-1, 0);
                        break;
                    case "A": // Left
                        moveSuccessful = GameBoard.movePlayer(0, -2);
                        break;
                    case "S": // Down
                        moveSuccessful = GameBoard.movePlayer(1, 0);
                        break;
                    case "D": // Right
                        moveSuccessful = GameBoard.movePlayer(0, 2);
                        break;
                    default:
                        System.out.println(GameBoard.sp + "Invalid input. Use W, A, S, or D.");
                        continue; // Skip the rest of the loop if input is invalid
                }

                // Only write to file and switch turns if the move was successful
                if (moveSuccessful) {
                    WritePlayToFile.WriteToFile();
                    player1Turn = !player1Turn; // Alternate turn only on a successful move
                }

                ClearConsole.clear();
                GameBoard.printBoard();

                // Check if player has reached the target
                if (GameBoard.win) {
                    System.out.println(GameBoard.sp + "Congratulations " + (player1Turn ? player1Name : player2Name) + "! You reached the winning spot!");

                    // Ask to play again, and break out if they choose not to
                    if (!askToPlayAgain()) {
                        if (askToReplay()) {
                            GameBoard.replayGame();
                            WritePlayToFile.clearReplayFile();
                            ClearConsole.clear();
                            System.out.println("\n\n" +GameBoard.sp + "Thank you for playing! Goodbye.");
                            GameBoard.resetGameBoard();
                            System.exit(0);
                            return;
                        } else {
                            System.exit(0);
                        }
                    } else {
                        GameBoard.resetGameBoard();
                        GameBoard.win = false;
                        WritePlayToFile.clearReplayFile();
                        break;
                    }
                }

                // Check if no more moves are available
                if (!GameBoard.hasAvailableMoves()) {
                    System.out.println(GameBoard.sp + "No more available moves! Game over.");
                    GameBoard.win = false;
                    if (!askToReplay()) {
                        System.out.println("Thank you for playing! Goodbye.");
                        WritePlayToFile.clearReplayFile();
                        System.exit(0);
                    } else {
                        GameBoard.replayGame();
                        ClearConsole.clear();
                        System.out.println(GameBoard.sp + "Thank you for playing! Goodbye.");
                        Thread.sleep(300);
                        WritePlayToFile.clearReplayFile();
                        GameBoard.resetGameBoard();
                        System.exit(0);
                    }
                    break;
                }
            }
        }
    }
}




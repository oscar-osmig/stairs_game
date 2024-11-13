package com.osmig.key_listener;

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
        System.out.print("Do you want to play again? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equalsIgnoreCase("y");
    }

    private static String player1Name;
    private static String player2Name;
    public static void getPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Player 1's name: ");
        player1Name = scanner.nextLine();
        System.out.print("\nEnter Player 2's name: ");
        player2Name = scanner.nextLine();
    }

    public static boolean askToReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to replay the game? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equalsIgnoreCase("y");
    }

    public static void checkPlayerMove(String[][] board) throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;



        while (true) {

            getPlayerNames();
            System.out.print("\n" + "Enter W (up), A (left), S (down), D (right) to move:\n");
            GameBoard.resetGameBoard();
            GameBoard.printBoard();
            // Reset the board and players' positions if they choose to play again
            player1Turn = true; // Reset to Player 1's turn at the start of a new game

            while (true) {
                System.out.println(player1Turn ? "\n" + player1Name + "'s turn" : "\n" + player2Name + "'s turn");
                System.out.print("\n>> ");
                String input = scanner.nextLine().toUpperCase();

                // Process the input and make moves
                switch (input) {
                    case "W": // Up
                        GameBoard.movePlayer(-1, 0);
                        WritePlayToFile.WriteToFile();
                        break;
                    case "A": // Left
                        GameBoard.movePlayer(0, -2);
                        WritePlayToFile.WriteToFile();
                        break;
                    case "S": // Down
                        GameBoard.movePlayer(1, 0);
                        WritePlayToFile.WriteToFile();
                        break;
                    case "D": // Right
                        GameBoard.movePlayer(0, 2);
                        WritePlayToFile.WriteToFile();
                        break;
                    default:
                        System.out.println("Invalid input. Use W, A, S, or D.");
                        continue; // Skip the rest of the loop if input is invalid
                }

                GameBoard.printBoard();
                // Check if player has reached the target
                if (GameBoard.win) {
                    System.out.println("Congratulations " + (player1Turn ? player1Name : player2Name) + "! You reached the winning spot!");

                    // Ask to play again, and break out if they choose not to
                    if (!askToPlayAgain()) {
                        if (askToReplay()){
                            GameBoard.replayGame();
                            WritePlayToFile.clearReplayFile();
                            System.out.println("Thank you for playing! Goodbye.");
                            GameBoard.resetGameBoard();
                            System.exit(0);
                            return; // Exit the method, ending the game completely
                        }else {
                            System.exit(0);
                        }
                        //GameBoard.replayGame();
                    } else {
                        GameBoard.resetGameBoard();
                        GameBoard.win = false;
                        WritePlayToFile.clearReplayFile();
                        //GameBoard.printBoard();
                        break; // Break inner loop to restart the game in the outer loop
                    }
                }

                // Check if no more moves are available
                if (!GameBoard.hasAvailableMoves()) {
                    System.out.println("No more available moves! Game over.");
                    GameBoard.win = false;
                    if (!askToReplay()){
                        System.out.println("Thank you for playing! Goodbye.");
                    }else {
                        GameBoard.replayGame();
                        System.out.println("Thank you for playing! Goodbye.");
                        WritePlayToFile.clearReplayFile();
                        GameBoard.resetGameBoard();
                        System.exit(0);
                    }
                    break; // Exit the inner loop to start a new game
                }

                // Alternate turn
                player1Turn = !player1Turn;
            }

            // If the player wants to replay the game, read and display it

        }
    }

}




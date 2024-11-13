package com.osmig.key_listener;

import com.osmig.game_board.GameBoard;

import java.util.Scanner;

public class ArrowKeyListener {

    public static String getMove(String  q){
        Scanner scanner = new Scanner(System.in);
        System.out.print(q);
        return scanner.nextLine();
    }

    public static boolean askToPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
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

    public static void checkPlayerMove(String[][] board){
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;

        getPlayerNames();
        System.out.print("\n"+"Enter W (up), A (left), S (down), D (right) to move:");

        while (true) {
            // Reset the board and players' positions if they choose to play again
            GameBoard.resetGameBoard();

            while (true) {
                System.out.println(player1Turn ? "\n" + player1Name + "'s turn" : "\n" + player2Name + "'s turn");
                System.out.print("\n>> ");
                String input = scanner.nextLine().toUpperCase();

                switch (input) {
                    case "W", "w": // Up
                        GameBoard.movePlayer(-1, 0);
                        break;
                    case "A", "a": // Left
                        GameBoard.movePlayer(0, -2);
                        break;
                    case "S", "s": // Down
                        GameBoard.movePlayer(1, 0);
                        break;
                    case "D", "d": // Right
                        GameBoard.movePlayer(0, 2);
                        break;
                    default:
                        System.out.println("Invalid input. Use W, A, S, or D.");
                }
                // Check if player has reached the winning spot
                // After the move, check if the player has reached the target (denoted by "$")
                if (GameBoard.win == true) {
                    System.out.println("Congratulations " + (player1Turn ? player1Name : player2Name) + "! You reached" +
                            " the winning spot!");
                    askToPlayAgain();
                    break; // Exit the loop if the player reaches the target
                }
                if (!GameBoard.hasAvailableMoves()) {
                    System.out.println("No more available moves! Game over.");
                    break; // Exit the loop if no moves are available
                }
                player1Turn = !player1Turn;
            }
            // Ask if they want to play again after the game ends
        }
    }
}




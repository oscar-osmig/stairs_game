package com.osmig.game_board;

import com.osmig.key_listener.ArrowKeyListener;
import com.osmig.save_play.WritePlayToFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBoard {

    public static String sp = "                                                                    ";

    public static String[][] board = {
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "$"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
            {"#", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"}
    };

    // # for starter
    // % for challenger

    public static int startingCol = 0;
    public static int startingRow = 9;

    public static int posX = startingRow;
    public static int posY = startingCol;

    public static int targetCol = 18;
    public static int targetRow = 0;


    public static void replayGame() {
        System.out.println("Replaying Game...");

        try (BufferedReader reader = new BufferedReader(new FileReader(WritePlayToFile.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // Add a delay for better readability
                Thread.sleep(500); // Half a second delay
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static boolean win = false;
    public static void movePlayer(int dx, int dy) {

        int newX = posX + dx;
        int newY = posY + dy;

        // Boundary check
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
            System.out.println("Move is out of bounds.");
            return;
        }

        // Prevent moving into previously occupied positions
        String targetCell = board[newX][newY];
        if (targetCell.equals("-->") || targetCell.equals("<--") || targetCell.equals("|") || targetCell.equals("#")) {
            System.out.println("Cannot move to a previously occupied position.");
            return;
        }


        // Set the directional indicator in the correct position
        if (dy == 2) { // Moving right
            board[posX][posY + 1] = "--> "; // Place "-->" in the space before the new position
        } else if (dy == -2) { // Moving left
            board[posX][posY - 1] = " <--"; // Place "<--" in the space after the previous position
        } else if (dx == -1 || dx == 1) { // Moving up or down
            board[posX][posY] = "|";
        }
        // Check if target is reached
        // Check if target is reached
        if (newX == targetRow && newY == targetCol) {
            System.out.println("Target reached!");
            board[newX][newY] = "#";// Replace "@" with "#"
            win = true;
        } else {
            // Update the new position
            board[newX][newY] = "#";
        }

        posX = newX;
        posY = newY;

        //printBoard();

//        if (!hasAvailableMoves()){
//            System.out.println("No more available moves!");
//        }
    }

    public static boolean hasAvailableMoves() {
        // Directions array: {row_offset, col_offset}
        int[][] directions = { {1, 0}, {-1, 0}, {0, 2}, {0, -2} };

        for (int[] dir : directions) {
            int checkX = posX + dir[0];
            int checkY = posY + dir[1];

            // Boundary check
            if (checkX >= 0 && checkX < board.length && checkY >= 0 && checkY < board[0].length) {
                String cell = board[checkX][checkY];
                if (cell.equals("@")) { // Check if cell contains "@"
                    return true; // Found an available move
                }
            }
        }
        return false; // No available moves
    }


    public static void main(String[] args) throws IOException {
        //printBoard();
        ArrowKeyListener.checkPlayerMove(board);
    }

    public static void printBoard(){
        for (String[] row: board){
            for (String element : row){
                System.out.print(element);
            }
            System.out.println();
            // listen to user movement, a = left, d = right, w = up, s = down
        }
    }

    public static void resetGameBoard() {

        board = new String[][]{
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "$"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"},
                {"#", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@", "    ", "@"}
        };
        System.out.println();
//        printBoard();
        // Reset positions
        posX = startingRow;
        posY = startingCol;

    }


}

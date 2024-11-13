package com.osmig.save_play;

import com.osmig.game_board.GameBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WritePlayToFile {

    public static final String filePath = "game_log.txt";

    public static void WriteToFile() throws IOException {

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String[] row : GameBoard.board) {
                for (String cell : row) {
                    writer.write(cell);
                }
                writer.write("\n");
            }
            writer.write("\n");  // Add a blank line to separate moves
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void clearReplayFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("");
    }


}


import java.io.*;
import java.util.*;

// Nasreen
// SaveLoadManager: Manages saving and loading game state to and from a text file.
public class SaveLoadManager {

    private static final String SAVE_FILE = "chessgame_save.txt";

    // Method to save the current game state to a text file
    public static void saveGame(ChessBoard chessboard, String currentPlayer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            // Save current player's turn
            writer.println(currentPlayer);

            // Save the state of each piece on the board
            ChessPiece[][] board = chessboard.getBoard();
            for (int row = 0; row < chessboard.getHeight(); row++) {
                for (int col = 0; col < chessboard.getWidth(); col++) {
                    ChessPiece piece = board[row][col];
                    if (piece != null) {
                        writer.println(piece.getName() + "," + piece.getColor() + "," + row + "," + col);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load a game state from a text file
    public static void loadGame(ChessBoard chessboard, String[] currentPlayer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            // Load current player's turn
            currentPlayer[0] = reader.readLine();

            // Clear the board before loading
            ChessPiece[][] board = chessboard.getBoard();
            for (int row = 0; row < chessboard.getHeight(); row++) {
                for (int col = 0; col < chessboard.getWidth(); col++) {
                    board[row][col] = null;
                }
            }

            // Load the state of each piece on the board
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String color = parts[1];
                int row = Integer.parseInt(parts[2]);
                int col = Integer.parseInt(parts[3]);

                ChessPiece piece = ChessPieceCreator.createChessPiece(name, color, row, col);
                chessboard.setPiece(row, col, piece);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to reset the save file to its initial state
    public static void resetSaveFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            // Initial state of the save file
            writer.println("Blue"); // Default starting player
            // Save the initial state of each piece on the board
            writer.println("Tor,Red,0,0");
            writer.println("Biz,Red,0,1");
            writer.println("Sau,Red,0,2");
            writer.println("Biz,Red,0,3");
            writer.println("Xor,Red,0,4");
            writer.println("Ram,Red,1,0");
            writer.println("Ram,Red,1,1");
            writer.println("Ram,Red,1,2");
            writer.println("Ram,Red,1,3");
            writer.println("Ram,Red,1,4");

            writer.println("Tor,Blue,7,0");
            writer.println("Biz,Blue,7,1");
            writer.println("Sau,Blue,7,2");
            writer.println("Biz,Blue,7,3");
            writer.println("Xor,Blue,7,4");
            writer.println("Ram,Blue,6,0");
            writer.println("Ram,Blue,6,1");
            writer.println("Ram,Blue,6,2");
            writer.println("Ram,Blue,6,3");
            writer.println("Ram,Blue,6,4");
        } catch (IOException e) {
            e.printStackTrace();
        
}
    }
}
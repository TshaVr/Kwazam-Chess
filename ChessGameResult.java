//HANNA
//ChessGameResult class evaluates the game state to determine if the game is over and identifies the winner based on the game rules.
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
public class ChessGameResult {
    
    // Check if the "Sau" piece of a specific color is captured
    private boolean isSauCaptured(String color, ChessPiece[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null && piece.getName().equals("Sau") && piece.getColor().equals(color)) {
                    return false; 
                }
            }
        }
        return true; 
    }

    // Check if the game is over by determining if any Sau piece is captured
    public boolean isGameOver(ChessPiece[][] board) {
        // Check if either the Red or Blue Sau is captured
        return isSauCaptured("Red", board) || isSauCaptured("Blue", board);
    }

    // Determine the winner 
    public String getWinner(String currentPlayer) {
        return currentPlayer + " wins by capturing the Sau!";
    }

    //Display the winner in a GUI popup window
    public void showWinner(String currentPlayer, ChessGameController controller) {
        String message = getWinner(currentPlayer); // Get the winner message
        SwingUtilities.invokeLater(() -> {
            int option = JOptionPane.showOptionDialog(
                null,
                message + "\nDo you want to start a new game or exit?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"New Game", "Exit"},
                "New Game"
            );

            if (option == JOptionPane.YES_OPTION) {
                // Start a new game
                controller.startNewGame();
            } else if (option == JOptionPane.NO_OPTION) {
                // Exit the program
                System.exit(0);
            }
        });
    }
    // Method to check the game status and show the winner
    public void checkGameStatus(String currentPlayer, ChessPiece[][] board, ChessGameController controller) {
        if (isGameOver(board)) {
            showWinner(currentPlayer, controller);
        }
    }
}


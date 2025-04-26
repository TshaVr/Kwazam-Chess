//HANNA + ARIEF
//CONTROLLER OF THE CHESSBOARD. 
//CENTRAL CONTROLLER, MANAGES INTERACTION BETWEEN CHESSBOARD AND BOARDVIEW.

import java.util.ArrayList;
import java.util.List;

public class ChessGameController {
    private ChessBoard chessboard; // Model
    private BoardView boardView; // View
    private String currentPlayer;
    private ChessGameRule gameRules;
    private ChessGameResult gameResult;
    private int startX = -1;
    private int startY = -1;
    private int turnCount = 0;
    private ChessPiece selectedPiece = null;

    public ChessGameController(ChessBoard chessboard, BoardView boardView) {
        this.chessboard = chessboard;
        this.boardView = boardView;
        currentPlayer = "Blue";
        gameRules = new ChessGameRule();
        gameResult = new ChessGameResult();
        initializeBoard();
        chessboard.printBoard();
        startNewGame();
    }

    //ARIEF
    private void initializeBoard() {
        // RED
        ChessPiece Tor = new Tor("Red", 0, 0, "/images/red_tor.png");
        placePiece(Tor);
        ChessPiece biz1 = new Biz("Red", 0, 1, "/images/red_biz.png");
        placePiece(biz1);
        ChessPiece Sau = new Sau("Red", 0, 2, "/images/red_sau.png");
        placePiece(Sau);
        ChessPiece biz2 = new Biz("Red", 0, 3, "/images/red_biz.png");
        placePiece(biz2);
        ChessPiece Xor = new Xor("Red", 0, 4, "/images/red_xor.png");
        placePiece(Xor);
        ChessPiece Ram1 = new Ram("Red", 1, 0, "/images/red_ram.png");
        placePiece(Ram1);
        ChessPiece Ram2 = new Ram("Red", 1, 1, "/images/red_ram.png");
        placePiece(Ram2);
        ChessPiece Ram3 = new Ram("Red", 1, 2, "/images/red_ram.png");
        placePiece(Ram3);
        ChessPiece Ram4 = new Ram("Red", 1, 3, "/images/red_ram.png");
        placePiece(Ram4);
        ChessPiece Ram5 = new Ram("Red", 1, 4, "/images/red_ram.png");
        placePiece(Ram5);

        // BLUE
        ChessPiece RedTor = new Tor("Blue", 7, 0, "/images/blue_tor.png");
        placePiece(RedTor);
        ChessPiece Redbiz1 = new Biz("Blue", 7, 1, "/images/blue_biz.png");
        placePiece(Redbiz1);
        ChessPiece RedSau = new Sau("Blue", 7, 2, "/images/blue_sau.png");
        placePiece(RedSau);
        ChessPiece Redbiz2 = new Biz("Blue", 7, 3, "/images/blue_biz.png");
        placePiece(Redbiz2);
        ChessPiece RedXor = new Xor("Blue", 7, 4, "/images/blue_xor.png");
        placePiece(RedXor);
        ChessPiece RedRam1 = new Ram("Blue", 6, 0, "/images/blue_ram.png");
        placePiece(RedRam1);
        ChessPiece RedRam2 = new Ram("Blue", 6, 1, "/images/blue_ram.png");
        placePiece(RedRam2);
        ChessPiece RedRam3 = new Ram("Blue", 6, 2, "/images/blue_ram.png");
        placePiece(RedRam3);
        ChessPiece RedRam4 = new Ram("Blue", 6, 3, "/images/blue_ram.png");
        placePiece(RedRam4);
        ChessPiece RedRam5 = new Ram("Blue", 6, 4, "/images/blue_ram.png");
        placePiece(RedRam5);
    }

    //ARIEF
    public void placePiece(ChessPiece piece) {
        chessboard.setPiece(piece.getRow(), piece.getCol(), piece);
    }

    //HANNA
    public void startNewGame() {
        // Clear the board
        ChessPiece[][] board = chessboard.getBoard();
        for (int row = 0; row < chessboard.getHeight(); row++) {
            for (int col = 0; col < chessboard.getWidth(); col++) {
                board[row][col] = null;
            }
        }
        // Initialie new game board
        initializeBoard();

        // Reset the save file
        SaveLoadManager.resetSaveFile();

        // Reset the current player
        currentPlayer = "Blue";
        boardView.setFlipped(false); // Ensure board is not flipped at the start
        updateBoardView(); // Update the board view with the new game state
    }

    //HANNA
    public boolean makeMove(int startX, int startY, int endX, int endY) {
        ChessPiece piece = chessboard.getPiece(startX, startY);
        if (piece == null) {
            return false;
        }
        // Validate the move
        boolean isMoveValid = piece.verifyMove(startX, startY, endX, endY, chessboard.getBoard());
        if (isMoveValid == false) {
            System.out.println("\nInvalid move!\n");
            return false;
        }
        
        boolean isOpponent = gameRules.isOpponentPiece(piece, endX, endY, chessboard.getBoard());
        if (!isOpponent && chessboard.getPiece(endX, endY) != null) {
            System.out.println("Cannot move to a square occupied by a friendly piece!");
            return false;
        }

        chessboard.movePiece(startX, startY, endX, endY, piece);
        turnCount++;
        if (turnCount % 2 == 0) {
            gameRules.transformPiece(chessboard);
        }
        // Check if Sau is captured
        gameResult.checkGameStatus(currentPlayer, chessboard.getBoard(), this);
        if (gameResult.isGameOver(chessboard.getBoard())) {
            return true; // Game over
        }
        // Switch player and flip the board
        switchPlayer();
        boardView.setFlipped(currentPlayer.equals("Red"));
        return true;
    }

    //HANNA
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        boardView.setFlipped(currentPlayer.equals("Red")); // Flip the board if the current player is Red
    }

    // Method to clear and update the board view
    public void updateBoardView() {
        boardView.clearBoard(); // Clear the visual board
        ChessPiece[][] board = chessboard.getBoard();
        for (int row = 0; row < chessboard.getHeight(); row++) {
            for (int col = 0; col < chessboard.getWidth(); col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    boardView.update(piece, row, col); // Update the visual board with the loaded pieces
                }
            }
        }
    }

    public ChessGameController getGameCtrl() {
        return this;
    }

    //ARIEF
    public void handleUserClick(int row, int col) {
        ChessPiece clickedPiece = chessboard.getPiece(row, col);

        if (selectedPiece == null) {
            // Select a piece
            if (clickedPiece != null && clickedPiece.getColor().equals(currentPlayer)) {
                selectedPiece = clickedPiece;
                startX = row;
                startY = col;

                // Highlight valid moves
                List<int[]> validMoves = getValidMoves(selectedPiece);
                boardView.highlightValidMoves(validMoves);
            }
        } else {
            // Make a move or deselect
            boolean isMoveSuccessful = makeMove(startX, startY, row, col);

            // Clear the box for the original location after a move
            if (isMoveSuccessful) {
                boardView.clearBox(startX, startY); // Clear old square
                boardView.update(selectedPiece, row, col); // Use update() method
            }

            selectedPiece = null;
            startX = -1;
            startY = -1;

            // Clear highlights
            chessboard.printBoard();
            boardView.clearHighlights();

            if (!isMoveSuccessful) {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    // Get all valid moves for a piece
private List<int[]> getValidMoves(ChessPiece piece) {
    List<int[]> validMoves = new ArrayList<>();
    ChessPiece[][] board = chessboard.getBoard();

    for (int row = 0; row < chessboard.getHeight(); row++) {
        for (int col = 0; col < chessboard.getWidth(); col++) {
            if (row != piece.getRow() || col != piece.getCol()) { // Skip same position
                if (piece.verifyMove(piece.getRow(), piece.getCol(), row, col, board)) {
                    validMoves.add(new int[] { row, col });
                }
            }
        }
    }
    return validMoves;
}

    //HANNA
     private void switchPlayer() {
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
        System.out.println("\nIt's now " + currentPlayer + "'s turn.\n");
    }
}

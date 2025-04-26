//Hanna, Thirissha
public class ChessGameRule {

    public void transformPiece(ChessBoard chessboard) {
        ChessPiece[][] board = chessboard.getBoard();
        System.out.println("\nXor and Tor has now transformed!\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null) {
                    if (piece.getName().equals("Tor") && piece.getColor().equals("Blue")) {
                        board[i][j] = new Xor(piece.getColor(), piece.getRow(), piece.getCol(), "/images/blue_xor.png");
                    } else if (piece.getName().equals("Tor") && piece.getColor().equals("Red")) {
                        board[i][j] = new Xor(piece.getColor(), piece.getRow(), piece.getCol(), "/images/red_xor.png");
                    } else if (piece.getName().equals("Xor") && piece.getColor().equals("Blue")) {
                        board[i][j] = new Tor(piece.getColor(), piece.getRow(), piece.getCol(), "/images/blue_tor.png");
                    } else if (piece.getName().equals("Xor") && piece.getColor().equals("Red")) {
                        board[i][j] = new Tor(piece.getColor(), piece.getRow(), piece.getCol(), "/images/red_tor.png");
                    }
                }
            }
        }
        chessboard.printBoard();
    }


    //ARIEF
    public boolean isOpponentPiece(ChessPiece piece, int row, int col, ChessPiece[][] board) {
        ChessPiece targetPiece = board[row][col];

        if (targetPiece == null) {
            System.out.println("\nTarget square is empty.\n");
            return false; // Empty square is not an opponent
        }

        boolean isOpponent = !piece.getColor().equals(targetPiece.getColor());
        return isOpponent;
    }
}

// Thirissha
import javax.swing.ImageIcon;

public class Ram extends ChessPiece {
    private boolean changedDirection = false;

    public Ram(String color, int row, int col, String imagePath) {
        super("Ram", color, row, col, imagePath);
    }

    public boolean verifyMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {

        // Ram Piece: One step forward move only (X direction just like Pawns in Chess)
        if (!isReachedEnd(startX, endX, board) && !changedDirection) {
            boolean isValidMove = moveOneRowForward(getColor(), startX, endX) && (startY == endY);
            return isValidMove;
        } else {
            boolean isChangedDirection = moveOneRowBackward(getColor(), startX, endX) && (startY == endY);
            return isChangedDirection;
        }
    }

    private boolean moveOneRowBackward(String color, int startX, int endX) {
        if (getColor().equals("Blue")) {
            return ((endX - startX) == 1);
        } else if (getColor().equals("Red")) {
            return ((endX - startX) == -1);
        } else {
            return false;
        }
    }

    private boolean isReachedEnd(int startX, int endX, ChessPiece[][] board) {
        if (getColor().equals("Blue") && (startX == 0)) {
            changedDirection = true;
            return true;
        } else if (getColor().equals("Red") && (startX == (board.length) - 1)) {
            changedDirection = true;
            return true;
        }
        return false;
    }


    // Determine which Ram, so it can only move forward
    public boolean moveOneRowForward(String color, int startX, int endX) {
        if (getColor().equals("Blue")) {
            return ((endX - startX) == -1);
        } else if (getColor().equals("Red")) {
            return ((endX - startX) == 1);
        } else {
            return false;
        }
    }


    public ImageIcon getImage() {
        return this.image;
    }
}

import javax.swing.ImageIcon;

//ARIEF
public class Xor extends ChessPiece {
    public Xor(String color, int row, int col, String imagePath){
        super("Xor", color, row, col, imagePath);
    }

    public boolean verifyMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // Check if the move is diagonal
        if (!isDiagonalMove(startX, startY, endX, endY)) {
            return false;
        }

        // Check if the move is within bounds
        if (!isWithinBounds(endX, endY, board)) {
            return false;
        }

        if (!isDiagonalMove(startX, startY, endX, endY)){
            return false;
        }
        return isPathClear(startX, startY, endX, endY, board);
    }

    private boolean isDiagonalMove(int startX, int startY, int endX, int endY) {
        return Math.abs(endX - startX) == Math.abs(endY - startY);
    }

    private boolean isPathClear(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int dirX = (endX - startX) / Math.abs(endX - startX); // Direction of movement in X
        int dirY = (endY - startY) / Math.abs(endY - startY); // Direction of movement in Y
        if (dirX == 0 && dirY == 0) {
            System.out.println("Cannot move to your own place.");
            return false;
        }

        int currentX = startX + dirX;
        int currentY = startY + dirY;

        while (currentX != endX && currentY != endY) {
            if (board[currentX][currentY] != null) { // Check if the square is occupied
                return false; // Path is blocked
            }
            currentX += dirX;
            currentY += dirY;
        }

        return true; // Path is clear
    }

    public ImageIcon getImage() {
        return this.image;
    }
}

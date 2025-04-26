//ARIEF
import javax.swing.ImageIcon;

//ARIEF
public class Tor extends ChessPiece { 

    public Tor(String color, int row, int col, String imagePath){
        super("Tor", color, row, col, imagePath);
    }

    public boolean verifyMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        if (!isWithinBounds(endX, endY, board)) {
            return false;
        }

        if (!isOrthogonalMove(startX, startY, endX, endY)) {
            return false;
        }

        return isPathClear(startX, startY, endX, endY, board);
    }

    //Check either move is horizontal or vertical bcs of Orthogonal
    private boolean isOrthogonalMove(int startX, int startY, int endX, int endY) {
        return (startX == endX || startY == endY);
    }

    //CHECK IF PIECE EXISTS IN PATH
    private boolean isPathClear(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        if (startX == endX) { // Vertical move
            int minY = Math.min(startY, endY);
            int maxY = Math.max(startY, endY);
            for (int y = minY + 1; y < maxY; y++) {
                if (isSquareOccupied(startX, y, board)) { 
                    return false;
                }
            }
        } else if (startY == endY) { // Horizontal move
            int minX = Math.min(startX, endX);
            int maxX = Math.max(startX, endX);
            for (int x = minX + 1; x < maxX; x++) {
                if (isSquareOccupied(x, startY, board)) { 
                    return false;
                }
            }
        }
        return true; // Path is clear
    }

    public ImageIcon getImage() {
        return this.image;
    }
    
}

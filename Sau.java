import javax.swing.ImageIcon;

public class Sau extends ChessPiece {
    public Sau(String color, int row, int col, String imagePath) {
        super("Sau", color, row, col, imagePath);
    }

    public boolean verifyMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // Sau Movement: Moves one step in any direction
        return Math.abs(endX - startX) <= 1 && Math.abs(endY - startY) <= 1;
    }
    
    public ImageIcon getImage(){
            return this.image;
    }
}
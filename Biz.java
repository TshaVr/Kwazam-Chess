import javax.swing.ImageIcon;

public class Biz extends ChessPiece  {

    public Biz(String color, int row, int col, String imagePath) {
        super("Biz", color, row, col, imagePath);
    }
    
    public boolean verifyMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {
        //Biz Move: Moves in a L shape "3 by 2" / "2 by 3" and within the bounds (Like Knights in Real Chess)

       if (!isWithinBounds(endRow, endCol, board)) {
        return false;
       }

       return isLShapeMove(startRow, startCol, endRow, endCol);
    }

    private boolean isLShapeMove(int startRow,int  startCol,int endRow,int endCol) {
        return ((Math.abs(endRow - startRow) == 2 && Math.abs(endCol - startCol) == 1) ||
               (Math.abs(endRow - startRow) == 1 && Math.abs(endCol - startCol) == 2));        
    }

    public ImageIcon getImage(){
            return this.image;
    }

}
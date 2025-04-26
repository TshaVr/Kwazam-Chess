import java.util.ArrayList;
import java.util.List;

//ARIEF
//MODEL OF THE CHESSBOARD. CHESSPIECE 2D ARRAY STORES WHERE THE PIECES ARE ON THE BOARD.
public class ChessBoard implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private ChessPiece[][] board;
    private int width;
    private int height;

    public ChessBoard(int width, int height){
        this.width = width;
        this.height = height;
        this.board = new ChessPiece[height][width];
    }

    public void notifyObservers(ChessPiece piece, int newRow, int newCol) {
        for (Observer observer : observers) {
            observer.update(piece, newRow, newCol);
        }
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    public ChessPiece[][] getBoard(){
        return board;
    }

    public void setPiece(int row, int col, ChessPiece piece) {
        piece.setPosition(row, col);
        board[row][col] = piece;
        notifyObservers(piece, row, col);
    }
    
    public ChessPiece getPiece(int row, int col){
        return board[row][col];
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    //ARIEF
    public void movePiece(int startX, int startY, int endRow, int endCol, ChessPiece piece){
        board[endRow][endCol] = piece;
        board[startX][startY] = null; //remove piece from initial position
        piece.setPosition(endRow, endCol);
        notifyObservers(piece, endRow, endCol);
    }

    protected void clearBoard(){
         ChessPiece[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[col].length; col++){
                setPiece(row,col,null);
            }
        }
    }


    //DEBUGGING PURPOSE
    public void printBoard() {
        ChessPiece[][] board = getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

    }

}

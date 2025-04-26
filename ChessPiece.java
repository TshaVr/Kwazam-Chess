
//Thirissha
//Arief
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Chess Pieces Superclass
public abstract class ChessPiece implements Subject {
    private String name;
    private String color;
    private int row;
    private int col;
    protected ImageIcon image;
    private final List<Observer> observers = new ArrayList<>();

    public ChessPiece(String name, String color, int row, int col, String imagePath) {
        this.name = name;
        this.color = color;
        this.row = row;
        this.col = col;
        setImage(imagePath);
    }
    // Observer pattern methods

    // ARIEF
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ChessPiece piece, int row, int col) {
        for (Observer observer : observers) {
            observer.update(getPiece(), getRow(), getCol());
        }
    }

    // ---------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ChessPiece getPiece() {
        return ChessPiece.this;
    }

    // ARIEF
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        notifyObservers(getPiece(), row, col);
    }

    // ARIEF: Resizing Image so that it fits the board
    private ImageIcon ResizeImage(String imagePath) {
        ImageIcon unresizedImg = new ImageIcon(getClass().getResource(imagePath));
        Image resizedImg = unresizedImg.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    // SETTING IMAGE USING RESIZEDIMG
    public void setImage(String imagePath) {
        this.image = ResizeImage(imagePath);
    }

    public abstract boolean verifyMove(int startX, int startY, int endX, int endY, ChessPiece[][] board);

    // Thirissha
    // Utility methods for movement logic
    protected boolean isWithinBounds(int x, int y, ChessPiece[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public boolean isSquareOccupied(int x, int y, ChessPiece[][] board) {
        return board[x][y] != null;
    }

    public ImageIcon getImage() {
        return image;
        // default implementation
    }

    @Override
    public String toString() {
        return getName() + " (" + getColor() + ")";
    }
}
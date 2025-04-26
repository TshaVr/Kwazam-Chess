import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

//ARIEF + HANNA
//THIS CLASS'S PURPOSE IS TO RENDER THE VIEW OF THE CHESSBOARD.
public class BoardView extends JPanel implements Observer {
    private List<Observer> observers = new ArrayList<>();
    private List<GridBoxes> gridBoxes = new ArrayList<>();
    private ChessBoard chessboard;
    private int width;
    private int height;
    private boolean isFlipped;
    private List<GridBoxes> highlightedBoxes = new ArrayList<>();


    public BoardView(int width, int height, ChessBoard chessboard) {
        this.chessboard = chessboard;
        this.width = width;
        this.height = height;
        chessboard.addObserver(this);//dont remove
        this.isFlipped = false;
        initializeView();
        
    }

    public void highlightValidMoves(List<int[]> validMoves) {
        clearHighlights(); // Ensure no old highlights remain

        for (int[] move : validMoves) {
            int row = move[0];
            int col = move[1];

            for (GridBoxes gridBox : gridBoxes) {
                if (gridBox.getRow() == row && gridBox.getCol() == col) {
                    gridBox.setBackground(new Color(173, 216, 230)); // Light blue background
                    highlightedBoxes.add(gridBox);
                }
            }
        }
    }

    public void clearHighlights() {
        for (GridBoxes gridBox : highlightedBoxes) {
            gridBox.setBackground(Color.WHITE); // Reset to default
        }
        highlightedBoxes.clear();
    }

    //HANNA
    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
        repaint(); // Redraw the board when flipped
    }

    //ARIEF
    public void update(ChessPiece piece, int row, int col) {
        // Update the entire board
        for (GridBoxes gridBox : gridBoxes) {
            int gridRow = gridBox.getRow();
            int gridCol = gridBox.getCol();
            ChessPiece currentPiece = chessboard.getPiece(gridRow, gridCol);
            gridBox.removeAll();
            if (currentPiece != null) {
                JLabel newPieceImg = new JLabel(currentPiece.getImage());
                gridBox.add(newPieceImg, BorderLayout.CENTER);
            }
        }
        refreshView();
    }

    // Method to refresh the entire view without specific arguments
    public void refreshView() {
        revalidate();
        repaint();
    }

    public void clearBoard() {
        for (GridBoxes gridBox : gridBoxes) {
            gridBox.setPiece(null); // Clear all pieces from the visual board
            gridBox.setBackground(Color.WHITE); // Reset the background color
        }
    }

    //ARIEF
    private void creatingGrids(ChessPiece piece, int row, int col) {
        GridBoxes chessBox = new GridBoxes(piece, row, col);
        gridBoxes.add(chessBox);
        if (piece != null) {
            JLabel label = new JLabel(piece.getImage());
            chessBox.add(label);
        }
        add(chessBox);
    }

    //ARIEF
    public void clearBox(int row, int col){
            for (GridBoxes gridBox : gridBoxes){
                if (gridBox.getRow() == row && gridBox.getCol() == col){
                    gridBox.removeAll();
                }
            }
    }

    //ARIEF
    private void initializeView(){
        setLayout(new GridLayout(height, width));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                ChessPiece piece = chessboard.getPiece(row, col); // Get the piece at the current position
                creatingGrids(piece, row, col);
            }
        }
    }

    //HANNA
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GridBoxes gridBox : gridBoxes) {
            int displayRow = isFlipped ? height - 1 - gridBox.getRow() : gridBox.getRow();
            int displayCol = isFlipped ? width - 1 - gridBox.getCol() : gridBox.getCol();
            gridBox.setLocation(displayCol * gridBox.getWidth(), displayRow * gridBox.getHeight());
        }
    }

}

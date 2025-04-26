import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;

//ARIEF
//CLASS THAT CREATES INDIVIDUAL BOXES IN THE CHESS GAME. STORES ROWS AND COLUMNS SO PIECES CAN MOVE.
public class GridBoxes extends JPanel {
    private ChessPiece piece;
    private int row;
    private int col;

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        removeAll(); // Remove existing components
        if (piece != null) {
            JLabel newPieceImage = new JLabel(piece.getImage());
            add(newPieceImage, BorderLayout.CENTER); // Add the piece's image to the grid box
        }
        revalidate();
        repaint();
    }

    public GridBoxes(ChessPiece piece, int row, int col) {
        this.row = row;
        this.col = col;
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                KwazamChessMain.getInstance().getGameCtrl().handleUserClick(row, col);
            }
        });
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
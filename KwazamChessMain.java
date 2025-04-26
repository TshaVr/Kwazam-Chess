import javax.swing.*;
import java.awt.*;

public class KwazamChessMain extends JFrame {
    private static KwazamChessMain instance;
    private ChessBoard chessboard; // Model
    private BoardView boardView; // View
    private ChessGameController gameController; // Controller

    // Private because singleton
    private KwazamChessMain() {
        super("Kwazam Chess");
        setLayout(new FlowLayout());
        int width = 5;
        int height = 8;

        // Initialise the chess board (Model)
        chessboard = new ChessBoard(width, height);
        // Initialise the board view (View)
        boardView = new BoardView(width, height, chessboard); // View linked to the model
        // Initialise the game controller (Controller)
        gameController = new ChessGameController(chessboard, boardView); // Controller integrates model and view
        add(boardView);

        // Set frame properties
        setSize(500, 800);
        setMinimumSize(new Dimension(400, 800));

        // Add menu for Save and Load functionality
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveItem = new JMenuItem("Save Game");
        JMenuItem loadItem = new JMenuItem("Load Game");

        // Attach Save/Load actions
        newGameItem.addActionListener(e -> gameController.startNewGame());
        saveItem.addActionListener(e -> SaveLoadManager.saveGame(chessboard, gameController.getCurrentPlayer()));
        loadItem.addActionListener(e -> {
            String[] currentPlayer = new String[1];
            SaveLoadManager.loadGame(chessboard, currentPlayer);
            gameController.setCurrentPlayer(currentPlayer[0]);
            gameController.updateBoardView(); // Update the board view after loading
        });

        fileMenu.add(newGameItem);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Display the game window
        setVisible(true);

        // Start the game logic
        gameController.startNewGame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Using singleton to ensure only one chess game is running.
    public static KwazamChessMain getInstance() {
        if (instance == null) {
            // Thread Safe Singleton
            synchronized (KwazamChessMain.class) {
                instance = new KwazamChessMain();
            }
        }
        return instance;
    }

    public ChessGameController getGameCtrl() {
        return gameController;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KwazamChessMain chessMain = KwazamChessMain.getInstance();
        });
    }
}

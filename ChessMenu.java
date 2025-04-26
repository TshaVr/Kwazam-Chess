import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Subject interface for the "Observer" pattern
interface MenuSubject {
    void addObserver(MenuObserver observer);

    void removeObserver(MenuObserver observer);

    void notifyObservers(String actionCommand);
}

// Observer interface for the "Observer" pattern
interface MenuObserver {
    void update(String actionCommand);
}

// Implementation of ChessMenu as the Subject
public class ChessMenu extends JFrame implements MenuSubject {
    private final List<MenuObserver> observers = new ArrayList<>();
    private ChessBoard chessboard;
    private ChessGameController gameController;

    public ChessMenu(ChessBoard chessboard, ChessGameController gameController) {
        this.chessboard = chessboard;
        this.gameController = gameController;

        // To set the frame
        setTitle("Kwazam Chess Game Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Menu's File
        JMenu fileMenu = new JMenu("K.C.G File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem saveGameItem = new JMenuItem("Save Game");
        JMenuItem loadGameItem = new JMenuItem("Load Game");

        // Adding action listeners to menu items
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Save Game")) {
                    SaveLoadManager.saveGame(chessboard, gameController.getCurrentPlayer());
                } else if (command.equals("Load Game")) {
                    String[] currentPlayer = new String[1];
                    SaveLoadManager.loadGame(chessboard, currentPlayer);
                    gameController.setCurrentPlayer(currentPlayer[0]);
                }
                // Notifies the observers when an action is performed
                notifyObservers(command);
            }
        };

        newGameItem.addActionListener(menuListener);
        saveGameItem.addActionListener(menuListener);
        loadGameItem.addActionListener(menuListener);

        // Adding the items into menu
        fileMenu.add(newGameItem);
        fileMenu.add(saveGameItem);
        fileMenu.add(loadGameItem);

        // Adding the menu on the bar
        menuBar.add(fileMenu);

        // Setting up the menu bar
        setJMenuBar(menuBar);
    }

    // Subject methods to manage the observers
    @Override
    public void addObserver(MenuObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MenuObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String actionCommand) {
        for (MenuObserver observer : observers) {
            observer.update(actionCommand);
        }
    }
}

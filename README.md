🧠 Custom Chess Game – Java Swing Application

This is a Java-based chess game with **custom pieces and unique rules**, 
built using the **Model-View-Controller (MVC)** architecture. 
The project simulates a simplified, strategy-based chess game with an engaging 
twist—each piece has its own original movement logic, and the game ends when 
the **Sau** piece is captured.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🧩 Features

- 🎲 Custom chess pieces: **Tor**, **Xor**, **Sau**, **Biz**, **Ram**
- 🧠 Unique movement logic for each piece
- 🔄 Automatic board flipping based on player turn
- 🧍 Observer pattern for efficient UI updates
- 🎯 Game ends when the **Sau** is captured (no checkmate/stalemate)
- 📦 Built with **Java Swing** for GUI
- ✅ Real-time move validation and turn-based play

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🏗️ Project Structure

- **Model:** Logic and data structure (e.g., `ChessBoard`, `ChessPiece`, and piece subclasses)
- **View:** GUI elements (`BoardView`, rendering pieces and board)
- **Controller:** Game flow and user interaction handler (`ChessGameController`)
- **Rules and Game Over Handling:** `ChessGameRule` and `ChessGameResult`

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🧠 Custom Pieces

| Piece | Movement Description        |
|-------|-----------------------------|
| Tor   | Orthogonal (like Rook)      |
| Xor   | Diagonal (like Bishop)      |
| Sau   | 1 step in any direction     |
| Biz   | 2 steps diagonally          |
| Ram   | 1 step forward only         |

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🚀 How to Run ##

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/custom-chess-game.git
   cd custom-chess-game

2. Open in an IDE like IntelliJ or NetBeans.

3. Make sure image assets are placed in the /images/ folder.

4. Run the Main.java or equivalent main class.

5. Start playing!

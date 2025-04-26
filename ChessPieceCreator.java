// nasreen
// ChessPieceCreator: Factory class to create chess pieces from string data.
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ChessPieceCreator {

    private static final Map<String, Function<String, ChessPiece>> pieceCreators = new HashMap<>();

    static {
        pieceCreators.put("Sau", attrs -> new Sau(attrs.split(",")[0], Integer.parseInt(attrs.split(",")[1]), Integer.parseInt(attrs.split(",")[2]), attrs.split(",")[3]));
        pieceCreators.put("Biz", attrs -> new Biz(attrs.split(",")[0], Integer.parseInt(attrs.split(",")[1]), Integer.parseInt(attrs.split(",")[2]), attrs.split(",")[3]));
        pieceCreators.put("Xor", attrs -> new Xor(attrs.split(",")[0], Integer.parseInt(attrs.split(",")[1]), Integer.parseInt(attrs.split(",")[2]), attrs.split(",")[3]));
        pieceCreators.put("Tor", attrs -> new Tor(attrs.split(",")[0], Integer.parseInt(attrs.split(",")[1]), Integer.parseInt(attrs.split(",")[2]), attrs.split(",")[3]));
        pieceCreators.put("Ram", attrs -> new Ram(attrs.split(",")[0], Integer.parseInt(attrs.split(",")[1]), Integer.parseInt(attrs.split(",")[2]), attrs.split(",")[3]));
    }

    // Method to create a chess piece based on its name and attributes.
    public static ChessPiece createChessPiece(String pieceName, String pieceColor, int pieceRow, int pieceCol) {
        String imagePath = "/images/" + pieceColor.toLowerCase() + "_" + pieceName.toLowerCase() + ".png";
        String attrs = pieceColor + "," + pieceRow + "," + pieceCol + "," + imagePath;

        Function<String, ChessPiece> creator = pieceCreators.get(pieceName);
        if (creator != null) {
            return creator.apply(attrs);
        } else {
            throw new IllegalArgumentException("Unknown piece type: " + pieceName);
        }
    }
}

package toys;

import range.AgeGroup;
import range.IdentityToy;

public class ToyPuzzle extends Toy {
    int piecesCount;

    public ToyPuzzle(int piecesCount, IdentityToy identityToy, String code, double price, int weight, double size, AgeGroup ageGroup) {
        super(identityToy, code, price, weight, size, ageGroup);
        this.piecesCount = piecesCount;
    }

    public int getPiecesCount() {
        return piecesCount;
    }

    public void setPiecesCount(int piecesCount) {
        this.piecesCount = piecesCount;
    }

    public void show() {
        super.show();
        System.out.println("Puzzle Details:");
        System.out.println("Number of Pieces: " + piecesCount);
    }
}

package toys;

import range.AgeGroup;
import range.IdentityToy;

public class ToyBlocks extends ToyPuzzle {
    public ToyBlocks(int piecesCount, IdentityToy identityToy, String code, double price, int weight, double size, AgeGroup ageGroup) {
        super(piecesCount, identityToy, code, price, weight, size, ageGroup);
    }
    public void show() {
        super.show();
        System.out.println("ToyBlocks " + super.getPiecesCount() + " pieces");
    }

}
